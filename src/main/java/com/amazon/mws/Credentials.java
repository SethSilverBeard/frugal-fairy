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

    public Credentials() throws IOException {
        super();
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + File.separator + "shoptasticles.config")));
        this.accessKey = properties.getProperty("amazon.mws.access.key");
        this.secretKey = properties.getProperty("amazon.mws.secret.key");
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

    @Override
    public String toString() {
        return "Credentials{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}
