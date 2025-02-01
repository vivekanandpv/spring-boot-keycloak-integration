package in.athenaeum.springbootkeycloakintegration.auth;

import in.athenaeum.springbootkeycloakintegration.viewmodels.LoginViewModel;
import in.athenaeum.springbootkeycloakintegration.viewmodels.RefreshTokenViewModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthServiceImplementation implements AuthService {
    private final String tokenUri;
    private final String clientId;
    private final String clientSecret;
    private final RestTemplate restTemplate;

    public AuthServiceImplementation(
            @Value("${AUTH_TOKEN_URI}") String tokenUri,
            @Value("${AUTH_CLIENT_ID}") String clientId,
            @Value("${AUTH_CLIENT_SECRET}") String clientSecret,
            RestTemplateBuilder builder
    ) {
        this.tokenUri = tokenUri;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.restTemplate = builder.build();
    }
    
    @Override
    public Map login(LoginViewModel viewModel) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "password");
        body.add("username", viewModel.getUsername());
        body.add("password", viewModel.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(tokenUri, HttpMethod.POST, requestEntity, Map.class).getBody();
    }

    @Override
    public Map refresh(RefreshTokenViewModel viewModel) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "refresh_token");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("refresh_token", viewModel.getRefreshToken());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(tokenUri, HttpMethod.POST, requestEntity, Map.class).getBody();
    }
}
