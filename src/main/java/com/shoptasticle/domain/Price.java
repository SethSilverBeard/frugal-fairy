package com.shoptasticle.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Price implements Comparable<Price>{

	private String url;
	private String sellerName;
	private String shipFromState;
	private BigDecimal itemPrice;
	private BigDecimal shippingPrice;
	private BigDecimal total;
	private LocalDateTime dateRetrieved;

	public Price() {
	}

	public Price(BigDecimal total) {
		this.total = total;
	}

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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LocalDateTime getDateRetrieved() {
		return dateRetrieved;
	}
	public void setDateRetrieved(LocalDateTime dateRetrieved) {
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
		return "MwsPrice [sellerName=" + sellerName + ", shipFromState=" + shipFromState + ", url=" + url
				+ ", itemPrice=" + itemPrice + ", shippingPrice=" + shippingPrice + ", total=" + total
				+ ", dateRetrieved=" + dateRetrieved + "]";
	}




	@Override
	public int compareTo(Price o) {
		if (o == null) return -1;
		if (o.getTotal() == null) return -1;
		if (this.getTotal() == null) return 1;
		if (o.getTotal() == null && this.getTotal() == null) return 0;
		if (this.getTotal().compareTo(o.getTotal()) == 0) {
			//if dont do this, 2 prices of "59.99" from different sellers will result in only 1 of them in TreeSet
			return Integer.valueOf(this.hashCode()).compareTo(o.hashCode());
		}
		return this.getTotal().compareTo(o.getTotal());
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Price price = (Price) o;
		return Objects.equals(url, price.url) &&
				Objects.equals(sellerName, price.sellerName) &&
				Objects.equals(shipFromState, price.shipFromState) &&
				Objects.equals(itemPrice, price.itemPrice) &&
				Objects.equals(shippingPrice, price.shippingPrice) &&
				Objects.equals(total, price.total) &&
				Objects.equals(dateRetrieved, price.dateRetrieved);
	}

	@Override
	public int hashCode() {
		return Objects.hash(url, sellerName, shipFromState, itemPrice, shippingPrice, total, dateRetrieved);
	}
}
