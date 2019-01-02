package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "MwsPrice")
public class MwsPrice {

	@JacksonXmlProperty(localName = "LandedPrice")
	private PriceType landedPrice;

	@JacksonXmlProperty(localName = "ListingPrice")
	private PriceType listingPrice;

	@JacksonXmlProperty(localName = "Shipping")
	private PriceType shippingPrice;
	
	public MwsPrice() {
	}

	public PriceType getLandedPrice() {
		return landedPrice;
	}

	public void setLandedPrice(PriceType landedPrice) {
		this.landedPrice = landedPrice;
	}

	public PriceType getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(PriceType listingPrice) {
		this.listingPrice = listingPrice;
	}

	public PriceType getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(PriceType shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
}
