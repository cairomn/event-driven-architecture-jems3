package usp.icmc.ssc.lasdpc.management.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://127.0.0.1:4200",
                    "http://localhost:4200",
                    "http://andromeda.lasdpc.icmc.usp.br:4200",
                    "http://andromeda.lasdpc.icmc.usp.br:4071",
                    "http://andromeda.lasdpc.icmc.usp.br:4072",
                    "http://andromeda.lasdpc.icmc.usp.br:4074"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
