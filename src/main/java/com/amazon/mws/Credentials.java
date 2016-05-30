package com.amazon.mws;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * @author wingert
 */
public class Credentials {

    public static final Credentials NA_Seth = new Credentials("awsAccessKey",
            "awsSecretKey", "https://mws.amazonservices.com");

    private String accessKey;
    private String secretKey;
    private String endpoint;

    public Credentials(String accessKey, String secretKey, String endpoint) throws IOException {
        super();
        Properties properties = new Properties();
        //properties.load(Files.);
        List<String> configEntries = Files.readAllLines(Paths.get("shoptasticles.config"));
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.endpoint = endpoint;
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
}
