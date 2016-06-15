package com.amazon.mws.client;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Credentials {

    private String accessKey;
    private String secretKey;
    private URI endpoint;
    private String sellerId;
    private String marketplaceId;

    private Credentials() throws IOException {
        super();
    }
    
    public static Credentials createUsingConfigFile() throws AmazonPriceCheckerException {
    	Credentials creds;
		try {
			creds = new Credentials();
			Properties properties = new Properties();
			properties.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + File.separator + "shoptasticles.config")));
			creds.accessKey = properties.getProperty("amazon.mws.access.key");
			creds.secretKey = properties.getProperty("amazon.mws.secret.key");
			creds.sellerId = properties.getProperty("amazon.mws.seller.id");
			creds.marketplaceId = properties.getProperty("amazon.mws.marketplace.id");
			creds.endpoint = new URI("https://mws.amazonservices.com");
		} catch (IOException | URISyntaxException e) {
			throw new AmazonPriceCheckerException("Unable to create AmazonPriceChecker due to config file issues", e);
		}
        return creds;
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

	@Override
	public String toString() {
		return "Credentials [accessKey=" + accessKey + ", secretKey=" + secretKey + ", endpoint=" + endpoint
				+ ", sellerId=" + sellerId + ", marketplaceId=" + marketplaceId + "]";
	}
}
