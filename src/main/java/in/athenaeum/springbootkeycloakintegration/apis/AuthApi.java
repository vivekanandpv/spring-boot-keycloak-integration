package in.athenaeum.springbootkeycloakintegration.apis;

import in.athenaeum.springbootkeycloakintegration.auth.AuthService;
import in.athenaeum.springbootkeycloakintegration.viewmodels.LoginViewModel;
import in.athenaeum.springbootkeycloakintegration.viewmodels.RefreshTokenViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthApi {
    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginViewModel viewModel) {
        return ResponseEntity.ok(authService.login(viewModel));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map> refreshToken(@RequestBody RefreshTokenViewModel viewModel) {
        return ResponseEntity.ok(authService.refresh(viewModel));
    }
}
