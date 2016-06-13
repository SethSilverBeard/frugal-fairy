package com.pricechecker;

import java.math.BigDecimal;
import java.util.Date;

public class Price {
	
	private String sellerName;
	private String shipFromState;
	private String httpLink;
	private BigDecimal itemPrice;
	private BigDecimal shippingPrice;
	private BigDecimal total;
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
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	public BigDecimal getShippingPrice() {
		return shippingPrice;
	}
	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	@Override
	public String toString() {
		return "Price [sellerName=" + sellerName + ", shipFromState=" + shipFromState + ", httpLink=" + httpLink
				+ ", itemPrice=" + itemPrice + ", shippingPrice=" + shippingPrice + ", total=" + total
				+ ", dateRetrieved=" + dateRetrieved + "]";
	}
}
