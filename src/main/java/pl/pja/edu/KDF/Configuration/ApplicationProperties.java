package pl.pja.edu.KDF.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String jwtSecret;

    private String reCaptchaServerURL;

    private String reCaptchaSecretKey;

    public String getReCaptchaServerURL() {
        return reCaptchaServerURL;
    }

    public void setReCaptchaServerURL(String reCaptchaServerURL) {
        this.reCaptchaServerURL = reCaptchaServerURL;
    }

    public String getReCaptchaSecretKey() {
        return reCaptchaSecretKey;
    }

    public void setReCaptchaSecretKey(String reCaptchaSecretKey) {
        this.reCaptchaSecretKey = reCaptchaSecretKey;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }
}
