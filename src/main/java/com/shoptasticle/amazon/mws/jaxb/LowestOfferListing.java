package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "LowestOfferListing")
public class LowestOfferListing {

	@JacksonXmlProperty(localName = "Price")
	private MwsPrice mwsPrice;

	@JacksonXmlProperty(localName = "Qualifiers")
	private Qualifiers qualifiers;
	
	public MwsPrice getMwsPrice() {
		return mwsPrice;
	}

	public void setMwsPrice(MwsPrice mwsPrice) {
		this.mwsPrice = mwsPrice;
	}

	public Qualifiers getQualifiers() {
		return qualifiers;
	}

	public void setQualifiers(Qualifiers qualifiers) {
		this.qualifiers = qualifiers;
	}
}
