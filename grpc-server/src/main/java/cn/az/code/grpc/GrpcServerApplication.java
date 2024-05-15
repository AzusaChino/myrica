package cn.az.code.grpc;

import cn.az.code.grpc.support.server.HelloGrpcServer;
import io.jaegertracing.Configuration;
import io.opentracing.Tracer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GRPC server
 *
 * @author haru
 * @since 2021-09-15 17:08
 */
@SpringBootApplication
public class GrpcServerApplication {

    public static void main(String[] args) throws Exception {
        // global tracer
        Configuration configuration = new Configuration("myrica-grpc-server");

        Tracer tracer = configuration.getTracer();
        HelloGrpcServer server = new HelloGrpcServer(tracer);
        server.run();
        server.blockUntilShutdown();
    }
}
