package in.athenaeum.springbootkeycloakintegration.apis;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sample")
public class SampleApi {
    
    @PreAuthorize("hasRole('user')")
    @GetMapping("/user")
    public String greetUser() {
        return "Welcome user to Spring Boot Keycloak Integration!";
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin")
    public String greetAdmin() {
        return "Welcome admin to Spring Boot Keycloak Integration!";
    }
}
