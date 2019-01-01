package com.shoptasticle.amazon.mws.client;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConfigurationProperties(prefix = "amazon.mws")
@Validated
public class Credentials {

    @NotBlank
    private String accessKey;
    @NotBlank
    private String secretKey;
    @NotBlank
    private String sellerId;
    @NotBlank
    private String marketplaceId;

    private URI endpoint;

    public Credentials() {
        try {
            this.endpoint = new URI("https://mws.amazonservices.com");
        } catch (URISyntaxException e) {
            throw new AmazonPriceCheckerException("Unable to create AmazonPriceChecker due to config file issues", e);
        }
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "Credentials [accessKey=" + accessKey + ", secretKey=" + secretKey + ", endpoint=" + endpoint
                + ", sellerId=" + sellerId + ", marketplaceId=" + marketplaceId + "]";
    }
}
