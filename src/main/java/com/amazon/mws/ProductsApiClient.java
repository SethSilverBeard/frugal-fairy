package com.amazon.mws;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.pricechecker.HttpRequestUtils;
import com.pricechecker.Price;
import com.pricechecker.PriceChecker;

public class ProductsApiClient implements PriceChecker {
	
	private static Logger logger = Logger.getLogger(ProductsApiClient.class.getName());
	
    private Credentials credentials;
    private SignatureCalculator signatureCalculator;
	
	private ProductsApiClient(Credentials credentials) {
		this.credentials = Objects.requireNonNull(credentials);
		this.signatureCalculator = SignatureCalculator.createSignatureCalculator(credentials, "Products");

	}
	
	/**
	 * 
	 * @param asin
	 * @return
	 * @throws SignatureException 
	 */
	public String createGetLowestOffersUrlParameters(String asin) throws SignatureException {
		Map<String, String> urlParameters = new TreeMap<String,String>();
		urlParameters.put("Action", "GetLowestPricedOffersForASIN");
		urlParameters.put("AWSAccessKeyId", credentials.getAccessKey());
		urlParameters.put("MarketplaceId", credentials.getMarketplaceId()); //US Marketplace
		urlParameters.put("SellerId", credentials.getSellerId());
		urlParameters.put("ItemCondition", "New");
		urlParameters.put("SignatureMethod",  "HmacSHA256");
		urlParameters.put("SignatureVersion", "2");
		urlParameters.put("Version", "2011-10-01");
		urlParameters.put("Timestamp", getCurrentTimestamp());
		signatureCalculator.sign(urlParameters);
		return HttpRequestUtils.mapToQueryString(urlParameters);
	}
	
	public static ProductsApiClient createProductsApiClient(Credentials credentials) {
		return new ProductsApiClient(credentials);
	}
	
	public String findOffers(String asin) throws Exception {
		HttpURLConnection connection = null;  
		  try {
		    //Create connection
		    URL url = new URL(credentials.getEndpoint() + "/Products/2011-10-01");
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; rv:2.2) Gecko/20110201");
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    String findOffersUrlParameters = createGetLowestOffersUrlParameters(asin);
		    connection.setRequestProperty("Content-Length", Integer.toString(findOffersUrlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    logger.info(findOffersUrlParameters);
		    wr.writeBytes(findOffersUrlParameters);
		    wr.close();
		    
		    //Get Response  
		    InputStream is;
		    if (connection.getResponseCode() == 200) {
		    	is = connection.getInputStream();
		    } else {
		    	is = connection.getErrorStream();
		    }
		    
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    while((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    if (connection.getResponseCode() != 200) {
		    	throw new AmazonPriceCheckerException(response.toString());
		    }
		    return response.toString();
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
	}
    
    /**
     * @return a new ISO 8601 date
     */
    private String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(new Date());
    }

	@Override
	public List<Price> getLowestPrices() {
		// TODO Auto-generated method stub
		return null;
	}
}
