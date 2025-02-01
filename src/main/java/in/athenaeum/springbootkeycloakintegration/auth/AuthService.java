package in.athenaeum.springbootkeycloakintegration.auth;

import in.athenaeum.springbootkeycloakintegration.viewmodels.LoginViewModel;
import in.athenaeum.springbootkeycloakintegration.viewmodels.RefreshTokenViewModel;

import java.util.Map;

public interface AuthService {
    Map login(LoginViewModel viewModel);
    Map refresh(RefreshTokenViewModel viewModel);
}
