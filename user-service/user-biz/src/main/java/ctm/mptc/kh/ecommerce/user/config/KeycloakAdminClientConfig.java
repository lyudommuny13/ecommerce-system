package ctm.mptc.kh.ecommerce.user.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakAdminClientConfig {

    public static final String REALM_NAME = "ecommerce";

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:8888")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("ecommerce-admin-client")
                .clientSecret("osqHS8Trmuk82sU3iOWn95zpkjFHxQytofseZZFlGw0qAKXankOzzn3BMIEA9lZoHI8NNz7ny6IZPce9Pndwpv")
                .realm(REALM_NAME)
                .build();
    }
}
