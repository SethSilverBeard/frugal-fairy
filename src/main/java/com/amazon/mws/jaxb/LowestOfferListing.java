package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="LowestOfferListing")
@XmlAccessorType(XmlAccessType.FIELD)
public class LowestOfferListing {
	
	@XmlElementRef
	private Price price;
	
	@XmlElementRef
	private Qualifiers qualifiers;
	
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Qualifiers getQualifiers() {
		return qualifiers;
	}

	public void setQualifiers(Qualifiers qualifiers) {
		this.qualifiers = qualifiers;
	}
}
