package jp.haru.myrica.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haru
 * @date 2024-05-15 22:19
 */
@ConfigurationProperties(prefix = "myrica.grpc")
public class GrpcServerProperties {

    private int port = 10013;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
