package com.shoptasticle.amazon.mws.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoptasticle.amazon.mws.jaxb.GetLowestOfferListingsForAsinResponse;
import com.shoptasticle.amazon.mws.jaxb.GetLowestOfferListingsForAsinResult;
import com.shoptasticle.amazon.mws.jaxb.LowestOfferListing;
import com.shoptasticle.amazon.mws.jaxb.Product;
import com.shoptasticle.pricechecker.HttpRequestUtils;
import com.shoptasticle.pricechecker.Listing;
import com.shoptasticle.pricechecker.ListingFinder;
import com.shoptasticle.pricechecker.SearchCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AmazonPriceChecker implements ListingFinder {

	private ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.xml().featuresToDisable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES).build();

	private static final Logger logger = LogManager.getLogger(AmazonPriceChecker.class);

	@Autowired
	private Credentials credentials;

	private SignatureCalculator signatureCalculator;

	private AmazonPriceChecker(Credentials credentials) {
		this.credentials = Objects.requireNonNull(credentials);
		this.signatureCalculator = SignatureCalculator.createSignatureCalculator(credentials, "Products/2011-10-01");

	}

	/**
	 * 
	 * @param asin
	 * @return
	 * @throws SignatureException
	 */
	private String createGetLowestOffersUrlParameters(String asin) throws SignatureException {
		Objects.requireNonNull(asin);
		Map<String, String> urlParameters = new TreeMap<String, String>();
		urlParameters.put("Action", "GetLowestOfferListingsForASIN");
		urlParameters.put("ASINList.ASIN.1", asin);
		urlParameters.put("AWSAccessKeyId", credentials.getAccessKey());
		urlParameters.put("MarketplaceId", credentials.getMarketplaceId()); // US Marketplace
		urlParameters.put("SellerId", credentials.getSellerId());
		urlParameters.put("ItemCondition", "New");
		urlParameters.put("SignatureMethod", "HmacSHA256");
		urlParameters.put("SignatureVersion", "2");
		urlParameters.put("Version", "2011-10-01");
		urlParameters.put("Timestamp", getCurrentTimestamp());
		signatureCalculator.sign(urlParameters);
		return HttpRequestUtils.mapToQueryString(urlParameters);
	}

	public static AmazonPriceChecker createProductsApiClient(Credentials credentials) {
		return new AmazonPriceChecker(credentials);
	}

	public String findOffersAsString(String asin) throws Exception {
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
		    }
		    rd.close();
		    if (connection.getResponseCode() != 200) {
		    	throw new AmazonPriceCheckerException(response.toString());
		    }
		    logger.debug(response);
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
	public List<Listing> findListings(SearchCriteria searchCriteria) {
		Objects.requireNonNull(searchCriteria);
		try {
			String offers = findOffersAsString(searchCriteria.getSearchString());
			return parseAmazonOffersXml(offers);
		} catch (Exception e) {
			throw new AmazonPriceCheckerException(e);
		}
	}

	public List<Listing> parseAmazonOffersXml(String xml) {
		try {
			GetLowestOfferListingsForAsinResponse getLowestOffersReponse = objectMapper.readValue(xml, GetLowestOfferListingsForAsinResponse.class);
			List<GetLowestOfferListingsForAsinResult> getLowestOfferListingsForAsinResults = getLowestOffersReponse
					.getGetLowestOfferListingsForAsinResult();
			if (getLowestOfferListingsForAsinResults.isEmpty()) {
				throw new AmazonPriceCheckerException(
						"Zero GetLowestOfferListingsForAsinResult elements found when calling GetLowestOfferListingsForASIN");
			}
			GetLowestOfferListingsForAsinResult getLowestOfferListingsForAsinResult = getLowestOfferListingsForAsinResults
					.get(0);
			Product product = getLowestOfferListingsForAsinResult.getProduct();
			if (product == null) {
				throw new AmazonPriceCheckerException(
						"Zero Products elements found when parsing GetLowestOfferListingsForASIN response");
			}
			List<LowestOfferListing> lowestOfferListings = product.getLowestOfferListings();
			if (lowestOfferListings == null || lowestOfferListings.isEmpty()) {
				throw new AmazonPriceCheckerException(
						"Zero [Product > LowestOfferListing] elements found when parsing GetLowestOfferListingsForASIN response");
			}
			// loop through offers and populate prices
			List<Listing> prices = new ArrayList<Listing>();
			for (LowestOfferListing lowestOffer : lowestOfferListings) {
				Listing price = new Listing();
				price.setDateRetrieved(new Date());
				price.setItemPrice(lowestOffer.getPrice().getListingPrice().getAmount());
				price.setShippingPrice(lowestOffer.getPrice().getShippingPrice().getAmount());
				price.setTotal(lowestOffer.getPrice().getLandedPrice().getAmount());
				price.setHttpLink("https://www.amazon.com/dp/" + getLowestOfferListingsForAsinResult.getAsin());
				prices.add(price);
				logger.info(price);
			}
			return prices;
		} catch (IOException e) {
			throw new AmazonPriceCheckerException("Unable to parse XML response from Amazon Products API", e);
		}
	}

}
