package com.amazon.mws.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GetLowestOfferListingsForASINResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetLowestOfferListingsForAsinResponse {
	
	@XmlElementRef(required=true)
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
