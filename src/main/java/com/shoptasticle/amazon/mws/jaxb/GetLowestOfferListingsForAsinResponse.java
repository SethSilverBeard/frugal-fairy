package com.shoptasticle.amazon.mws.jaxb;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "GetLowestOfferListingsForASINResponse", namespace = "http://mws.amazonservices.com/schema/Products/2011-10-01")
public class GetLowestOfferListingsForAsinResponse {

	@JacksonXmlProperty(localName = "GetLowestOfferListingsForASINResult")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<GetLowestOfferListingsForAsinResult> getLowestOfferListingsForAsinResult;
	
	public GetLowestOfferListingsForAsinResponse() {
	}

	public List<GetLowestOfferListingsForAsinResult> getGetLowestOfferListingsForAsinResult() {
		return getLowestOfferListingsForAsinResult;
	}

	public void setGetLowestOfferListingsForAsinResult(
			List<GetLowestOfferListingsForAsinResult> getLowestOfferListingsForAsinResult) {
		this.getLowestOfferListingsForAsinResult = getLowestOfferListingsForAsinResult;
	}
}
