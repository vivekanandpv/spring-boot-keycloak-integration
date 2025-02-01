package in.athenaeum.springbootkeycloakintegration.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class PublicApi {
    @GetMapping
    public String publicApi() {
        return "Welcome general public to Spring Boot Keycloak Integration";
    }
}
