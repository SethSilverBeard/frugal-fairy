package com.amazon.mws.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	
	@XmlElement(name="AllOfferListingsConsidered")
	private String allOfferListingsConsidered;
	
	@XmlElementWrapper(name="LowestOfferListings")
	@XmlElement(name="LowestOfferListing")
	private List<LowestOfferListing> lowestOfferListings;

	public Product() {
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
