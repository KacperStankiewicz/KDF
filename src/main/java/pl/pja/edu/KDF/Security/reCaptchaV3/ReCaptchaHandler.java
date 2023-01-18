package pl.pja.edu.KDF.Security.reCaptchaV3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.pja.edu.KDF.Configuration.ApplicationProperties;

@Service
public class ReCaptchaHandler {

    private final Logger log = LoggerFactory.getLogger(ReCaptchaHandler.class);

    private final ApplicationProperties applicationProperties;

    public ReCaptchaHandler(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public float verify(String reCaptchaTokenResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("secret", applicationProperties.getReCaptchaSecretKey());
        map.add("response", reCaptchaTokenResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ReCaptchaV3Response response = restTemplate.postForObject(
            applicationProperties.getReCaptchaServerURL(),
            request,
            ReCaptchaV3Response.class
        );

        if (response != null && response.isSuccess()) {
            log.debug("reCaptcha v3 score: {}", response.getScore());
            return response.getScore();
        }

        return 0.0f;
    }
}
