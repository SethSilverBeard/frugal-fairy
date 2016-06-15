package com.amazon.mws.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.mws.jaxb.GetLowestOfferListingsForAsinResponse;
import com.amazon.mws.jaxb.GetLowestOfferListingsForAsinResult;
import com.amazon.mws.jaxb.LowestOfferListing;
import com.amazon.mws.jaxb.Product;
import com.listingchecker.HttpRequestUtils;
import com.listingchecker.Listing;
import com.listingchecker.ListingFinder;
import com.listingchecker.SearchCriteria;

public class AmazonPriceChecker implements ListingFinder {
	
	private static final Logger logger = LogManager.getLogger(AmazonPriceChecker.class);
	
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
		Map<String, String> urlParameters = new TreeMap<String,String>();
		urlParameters.put("Action", "GetLowestOfferListingsForASIN");
		urlParameters.put("ASINList.ASIN.1", asin);
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
		return null;
	}

	public List<Listing> parseAmazonOffersXml(String xml) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(GetLowestOfferListingsForAsinResponse.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			//begin lines to ignore namespace
			XMLInputFactory xif = XMLInputFactory.newFactory();
			xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false); // this is the magic line
			StreamSource source = new StreamSource(new StringReader(xml));
			XMLStreamReader xsr = xif.createXMLStreamReader(source);
			//end lines to ignore namespace
			GetLowestOfferListingsForAsinResponse getLowestOffersReponse = (GetLowestOfferListingsForAsinResponse) jaxbUnmarshaller.unmarshal(xsr);
			List<GetLowestOfferListingsForAsinResult> getLowestOfferListingsForAsinResults = getLowestOffersReponse.getGetLowestOfferListingsForAsinResult();
			if (getLowestOfferListingsForAsinResults.isEmpty()) {
				throw new AmazonPriceCheckerException("Zero GetLowestOfferListingsForAsinResult elements found when calling GetLowestOfferListingsForASIN");
			}
			GetLowestOfferListingsForAsinResult getLowestOfferListingsForAsinResult = getLowestOfferListingsForAsinResults.get(0);
			Product product = getLowestOfferListingsForAsinResult.getProduct();
			if (product == null) {
				throw new AmazonPriceCheckerException("Zero Products elements found when parsing GetLowestOfferListingsForASIN response");
			}
			List<LowestOfferListing> lowestOfferListings = product.getLowestOfferListings();
			if (lowestOfferListings == null || lowestOfferListings.isEmpty()) {
				throw new AmazonPriceCheckerException("Zero LowestOfferListing elements found when parsing GetLowestOfferListingsForASIN response");
			}
			//loop through offers and populate prices
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
		} catch (JAXBException | XMLStreamException e) {
			throw new AmazonPriceCheckerException("Unable to parse XML response from Amazon Products API", e);
		}
	}

}
