package cn.az.code.grpc.support.server;

import cn.az.code.grpc.protos.echo.HelloServiceGrpc;
import cn.az.code.grpc.protos.echo.Request;
import cn.az.code.grpc.protos.echo.Response;
import cn.az.code.grpc.interceptor.CustomServerTracingInterceptor;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Hello
 *
 * @author haru
 * @since 2021-09-15 17:11
 */
public class HelloGrpcServer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloGrpcServer.class);

    private Server server;
    private final Tracer tracer;

    public HelloGrpcServer(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void run() {
        CustomServerTracingInterceptor tracingInterceptor = new CustomServerTracingInterceptor(this.tracer);
        int port = 50051;
        try {
            server = ServerBuilder.forPort(port)
                .addService(tracingInterceptor.intercept(new HelloImpl()))
                .build()
                .start();
        } catch (IOException e) {
            LOGGER.error("fail to start grpc server", e);
            return;
        }

        LOGGER.info("server started, listening on {}", port);
        Runtime.getRuntime().addShutdownHook(new ThreadFactoryBuilder().build().newThread(
            () -> {
                LOGGER.warn("shutting down gRPC server since JVM is shutting down.");
                try {
                    HelloGrpcServer.this.stop();
                } catch (InterruptedException e) {
                    LOGGER.error("interrupted", e);
                    Thread.currentThread().interrupt();
                }
                LOGGER.warn("*** server shut down");
            }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon
     * threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class HelloImpl extends HelloServiceGrpc.HelloServiceImplBase {
        @Override
        public void sayHello(Request request, StreamObserver<Response> responseObserver) {
            Response response = Response.newBuilder()
                .setMsg("Hello " + request.getMsg() + ", today is " + request.getDate()).setCode(200).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
