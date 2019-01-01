package com.shoptasticle.amazon.mws.jaxb;

// @XmlRootElement(name="ShippingTime")
// @XmlAccessorType(XmlAccessType.FIELD)
public class ShippingTime {

    // @XmlElement(name="Max")
    private String max;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
