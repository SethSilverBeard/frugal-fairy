package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Price")
@XmlAccessorType(XmlAccessType.FIELD)
public class Price {
	
	@XmlElementRef
	private LandedPrice landedPrice;

	@XmlElementRef
	private ListingPrice listingPrice;
	
	@XmlElementRef
	private Shipping shippingPrice;
	
	public Price() {
	}

	public LandedPrice getLandedPrice() {
		return landedPrice;
	}

	public void setLandedPrice(LandedPrice landedPrice) {
		this.landedPrice = landedPrice;
	}

	public ListingPrice getListingPrice() {
		return listingPrice;
	}

	public void setListingPrice(ListingPrice listingPrice) {
		this.listingPrice = listingPrice;
	}

	public Shipping getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Shipping shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

}
