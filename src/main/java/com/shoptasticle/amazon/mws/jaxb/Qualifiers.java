package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Qualifiers")
public class Qualifiers {

    @JacksonXmlProperty(localName = "FulfillmentChannel")
	private String fulfillmentChannel;

    @JacksonXmlProperty(localName = "ShipsDomestically")
	private String shipsDomestically;

    @JacksonXmlProperty(localName = "ShippingTime")
	private ShippingTime shippingTime;

    @JacksonXmlProperty(localName = "SellerPositiveFeedbackRating")
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
