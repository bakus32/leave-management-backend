package ci.yobak.leavemanagement.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.RequestPath;
import org.springframework.web.accept.ApiVersionResolver;
import org.springframework.web.accept.PathApiVersionResolver;

@Configuration
public class ApiVersionConfig {

    private static final int API_VERSION_SEGMENT_INDEX = 1;

    @Bean
    public ApiVersionResolver apiVersionResolver() {

        return new PathApiVersionResolver(
                API_VERSION_SEGMENT_INDEX,
                this::isVersionedPath
        );
    }

    private boolean isVersionedPath(RequestPath requestPath) {

        String path = requestPath
                .pathWithinApplication()
                .value();

        return path.startsWith("/api/");
    }
}
