package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Qualifiers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Qualifiers {

	@XmlElement(name = "FulfillmentChannel")
	private String fulfillmentChannel;
	
	@XmlElement(name = "ShipsDomestically")
	private String shipsDomestically;
	
	@XmlElement(name = "ShippingTime")
	private ShippingTime shippingTime;
	
	@XmlElement(name = "SellerPositiveFeedbackRating")
	private String sellerPositiveFeedbackRating;

	public String getFulfillmentChannel() {
		return fulfillmentChannel;
	}

	public void setFulfillmentChannel(String fulfillmentChannel) {
		this.fulfillmentChannel = fulfillmentChannel;
	}

	public String getShipsDomestically() {
		return shipsDomestically;
	}

	public void setShipsDomestically(String shipsDomestically) {
		this.shipsDomestically = shipsDomestically;
	}

	public ShippingTime getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(ShippingTime shippingTime) {
		this.shippingTime = shippingTime;
	}

	public String getSellerPositiveFeedbackRating() {
		return sellerPositiveFeedbackRating;
	}

	public void setSellerPositiveFeedbackRating(String sellerPositiveFeedbackRating) {
		this.sellerPositiveFeedbackRating = sellerPositiveFeedbackRating;
	}
}
