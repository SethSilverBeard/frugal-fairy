package com.amazon.mws;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

import com.pricechecker.Price;

public class ProductsApiClient {
	
	private static Logger logger = Logger.getLogger(ProductsApiClient.class.getName());
	
    private Credentials credentials;
    private SignatureCalculator signatureCalculator;
	
	private ProductsApiClient(Credentials credentials) {
		this.credentials = credentials;
		this.signatureCalculator = SignatureCalculator.createSignatureCalculator(credentials);
	}
	
	public static ProductsApiClient createProductsApiClient(Credentials credentials) {
		return new ProductsApiClient(credentials);
	}
	
	public List<Price> findOffers(String asin) {
		HttpURLConnection connection = null;  
		  try {
		    //Create connection
		    URL url = new URL(credentials.getEndpoint());
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    while((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
			  logger.severe(e.getMessage());
			  e.printStackTrace();
			  return null;
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
	}
	
	private void sendPost(String urlParams) throws Exception {
		SignatureCalculator.createSignatureCalculator(creds);
	}
}
