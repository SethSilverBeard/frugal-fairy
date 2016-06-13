package com.amazon.mws.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ShippingTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShippingTime {
	
	@XmlElement(name="Max")
	private String max;

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}
}
