package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "Product")
public class MwsProduct {

    @JacksonXmlProperty(localName = "AllOfferListingsConsidered")
	private String allOfferListingsConsidered;

    @JacksonXmlElementWrapper(localName = "LowestOfferListings")
	private List<LowestOfferListing> lowestOfferListings;

	public MwsProduct() {
	}

	public List<LowestOfferListing> getLowestOfferListings() {
		return lowestOfferListings;
	}

	public void setLowestOfferListings(List<LowestOfferListing> lowestOfferListings) {
		this.lowestOfferListings = lowestOfferListings;
	}

	public String getAllOfferListingsConsidered() {
		return allOfferListingsConsidered;
	}

	public void setAllOfferListingsConsidered(String allOfferListingsConsidered) {
		this.allOfferListingsConsidered = allOfferListingsConsidered;
	}

}
