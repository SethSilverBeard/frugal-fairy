package com.amazon.mws;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Credentials {

    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String sellerId;
    private String marketplaceId;

    public Credentials() throws IOException {
        super();
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + File.separator + "shoptasticles.config")));
        this.accessKey = properties.getProperty("amazon.mws.access.key");
        this.secretKey = properties.getProperty("amazon.mws.secret.key");
        this.sellerId = properties.getProperty("amazon.mws.seller.id");
        this.marketplaceId = properties.getProperty("amazon.mws.marketplace.id");
        this.endpoint = "https://mws.amazonservices.com";
    }

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getEndpoint() {
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
