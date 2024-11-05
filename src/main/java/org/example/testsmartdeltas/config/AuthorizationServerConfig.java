package org.example.testsmartdeltas.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * Конфигурация сервера авторизации OAuth2 для управления клиентами, безопасностью и JWK.
 */
@Configuration
public class AuthorizationServerConfig {

    /**
     * Создает и регистрирует клиента для авторизации с использованием grant type CLIENT_CREDENTIALS.
     *
     * @return {@link RegisteredClientRepository}, содержащий зарегистрированного клиента.
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret(passwordEncoder().encode("secret"))
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("read")
                .tokenSettings(TokenSettings.builder().build())
                .clientSettings(ClientSettings.builder().build())
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    /**
     * Настраивает фильтр безопасности для сервера авторизации OAuth2.
     *
     * @param http {@link HttpSecurity} для настройки фильтра.
     * @return настроенный {@link SecurityFilterChain} для сервера авторизации.
     * @throws Exception в случае ошибки конфигурации.
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.build();
    }

    /**
     * Создает и настраивает {@link PasswordEncoder} для кодирования клиентских секретов.
     *
     * @return экземпляр {@link BCryptPasswordEncoder}.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создает и настраивает источник JWK для публикации JSON Web Key на /oauth2/jwks.
     *
     * @return {@link JWKSource} для генерации и публикации ключей.
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    /**
     * Генерирует пару RSA-ключей для использования в JWK.
     *
     * @return экземпляр {@link RSAKey}, содержащий публичный и приватный ключи.
     */
    private RSAKey generateRsa() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

            return new RSAKey.Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}







