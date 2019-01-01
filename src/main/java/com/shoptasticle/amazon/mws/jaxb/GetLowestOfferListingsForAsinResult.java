package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "GetLowestOfferListingsForASINResult")
public class GetLowestOfferListingsForAsinResult {

    @JacksonXmlProperty(localName = "ASIN", isAttribute = true)
	private String asin;

    @JacksonXmlProperty(localName = "status", isAttribute = true)
	private String status;

    @JacksonXmlProperty(localName = "Product")
	private Product product;

    @JacksonXmlProperty(localName = "Error")
	private Error error;

	public GetLowestOfferListingsForAsinResult() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
