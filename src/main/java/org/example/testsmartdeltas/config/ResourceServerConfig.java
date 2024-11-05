package org.example.testsmartdeltas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация ресурcного сервера для обработки запросов с JWT-аутентификацией.
 */
@Configuration
public class ResourceServerConfig {

    /**
     * Настраивает цепочку безопасности для ресурcного сервера.
     * <p>
     * Все запросы к `/student/**` требуют аутентификации, остальные разрешены без нее.
     * Используется JWT-аутентификация через OAuth2.
     *
     * @param http объект {@link HttpSecurity} для настройки фильтров безопасности.
     * @return настроенный {@link SecurityFilterChain} для ресурcного сервера.
     * @throws Exception в случае ошибки конфигурации.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/student/**").authenticated()
                                .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }

    /**
     * Создает декодер JWT для ресурcного сервера.
     * <p>
     * Настроен для получения ключей JWK с URI `http://localhost:8181/oauth2/jwks`.
     *
     * @return экземпляр {@link JwtDecoder} для декодирования JWT.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8181/oauth2/jwks").build();
    }
}



