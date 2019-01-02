package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "GetLowestOfferListingsForASINResult")
public class GetLowestOfferListingsForAsinResult {

    @JacksonXmlProperty(localName = "ASIN", isAttribute = true)
	private String asin;

    @JacksonXmlProperty(localName = "status", isAttribute = true)
	private String status;

    @JacksonXmlProperty(localName = "MwsProduct")
	private MwsProduct mwsProduct;

    @JacksonXmlProperty(localName = "Error")
	private Error error;

	public GetLowestOfferListingsForAsinResult() {
	}

	public MwsProduct getMwsProduct() {
		return mwsProduct;
	}

	public void setMwsProduct(MwsProduct mwsProduct) {
		this.mwsProduct = mwsProduct;
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
