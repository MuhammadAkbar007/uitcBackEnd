package web.uitc.uitcbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import web.uitc.uitcbackend.entity.User;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<User> auditorAware() {
        return new AuditingAwareImpl();
    }
}
