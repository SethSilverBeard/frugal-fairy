package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Price")
@XmlAccessorType(XmlAccessType.FIELD)
public class Price {
	
	@XmlElement(name="LandedPrice")
	private PriceType landedPrice;

	@XmlElement(name="ListingPrice")
	private PriceType listingPrice;
	
	@XmlElement(name="Shipping")
	private PriceType shippingPrice;
	
	public Price() {
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
