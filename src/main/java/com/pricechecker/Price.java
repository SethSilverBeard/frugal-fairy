package com.pricechecker;

import java.util.Date;

public class Price {
	private String sellerName;
	private String shipFromState;
	private String httpLink;
	private Date dateRetrieved;
	
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getShipFromState() {
		return shipFromState;
	}
	public void setShipFromState(String shipFromState) {
		this.shipFromState = shipFromState;
	}
	public String getHttpLink() {
		return httpLink;
	}
	public void setHttpLink(String httpLink) {
		this.httpLink = httpLink;
	}
	public Date getDateRetrieved() {
		return dateRetrieved;
	}
	public void setDateRetrieved(Date dateRetrieved) {
		this.dateRetrieved = dateRetrieved;
	}
}
