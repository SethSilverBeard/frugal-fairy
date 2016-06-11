package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GetLowestOfferListingsForASINResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLowestOfferListingsForAsinResult {
	
	@XmlAttribute(name="ASIN",required=true)
	private String asin;
	
	@XmlAttribute(name="status",required=true)
	private String status;
	
	@XmlElementRef
	private Product product;
	
	@XmlElementRef
	private Error error;

	public GetLowestOfferListingsForAsinResult() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
