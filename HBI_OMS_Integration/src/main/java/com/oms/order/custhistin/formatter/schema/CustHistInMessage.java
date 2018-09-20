//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.10 at 04:06:46 PM IST 
//


package com.oms.order.custhistin.formatter.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerHistoryRequest">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="customer_number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="alternate_sold_to_id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="number_of_orders" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="direct_order_number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="direct_order_ship_to_nbr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="alternate_order_number" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="send_detail" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="exclude_order_channel" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="last_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="postal_code" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="source" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *             &lt;enumeration value="CWCUSTHISTIN"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="resp_qmgr" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resp_q" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerHistoryRequest"
})
@XmlRootElement(name = "Message")
public class CustHistInMessage {

    @XmlElement(name = "CustomerHistoryRequest", required = true)
    protected CustHistInMessage.CustomerHistoryRequest customerHistoryRequest;
    @XmlAttribute(name = "source")
    protected String source;
    @XmlAttribute(name = "target")
    protected String target;
    @XmlAttribute(name = "type", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String type;
    @XmlAttribute(name = "resp_qmgr")
    protected String respQmgr;
    @XmlAttribute(name = "resp_q")
    protected String respQ;

    /**
     * Gets the value of the customerHistoryRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CustHistInMessage.CustomerHistoryRequest }
     *     
     */
    public CustHistInMessage.CustomerHistoryRequest getCustomerHistoryRequest() {
        return customerHistoryRequest;
    }

    /**
     * Sets the value of the customerHistoryRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustHistInMessage.CustomerHistoryRequest }
     *     
     */
    public void setCustomerHistoryRequest(CustHistInMessage.CustomerHistoryRequest value) {
        this.customerHistoryRequest = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the respQmgr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespQmgr() {
        return respQmgr;
    }

    /**
     * Sets the value of the respQmgr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespQmgr(String value) {
        this.respQmgr = value;
    }

    /**
     * Gets the value of the respQ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespQ() {
        return respQ;
    }

    /**
     * Sets the value of the respQ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespQ(String value) {
        this.respQ = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="company" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="customer_number" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="alternate_sold_to_id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="number_of_orders" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="direct_order_number" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="direct_order_ship_to_nbr" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="alternate_order_number" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="send_detail" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="exclude_order_channel" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="last_name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="postal_code" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CustomerHistoryRequest {

        @XmlAttribute(name = "company")
        protected String company;
        @XmlAttribute(name = "customer_number")
        protected String customerNumber;
        @XmlAttribute(name = "alternate_sold_to_id")
        protected String alternateSoldToId;
        @XmlAttribute(name = "number_of_orders")
        protected String numberOfOrders;
        @XmlAttribute(name = "direct_order_number")
        protected String directOrderNumber;
        @XmlAttribute(name = "direct_order_ship_to_nbr")
        protected String directOrderShipToNbr;
        @XmlAttribute(name = "alternate_order_number")
        protected String alternateOrderNumber;
        @XmlAttribute(name = "send_detail")
        protected String sendDetail;
        @XmlAttribute(name = "exclude_order_channel")
        protected String excludeOrderChannel;
        @XmlAttribute(name = "last_name")
        protected String lastName;
        @XmlAttribute(name = "postal_code")
        protected String postalCode;

        /**
         * Gets the value of the company property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCompany() {
            return company;
        }

        /**
         * Sets the value of the company property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCompany(String value) {
            this.company = value;
        }

        /**
         * Gets the value of the customerNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCustomerNumber() {
            return customerNumber;
        }

        /**
         * Sets the value of the customerNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCustomerNumber(String value) {
            this.customerNumber = value;
        }

        /**
         * Gets the value of the alternateSoldToId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlternateSoldToId() {
            return alternateSoldToId;
        }

        /**
         * Sets the value of the alternateSoldToId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlternateSoldToId(String value) {
            this.alternateSoldToId = value;
        }

        /**
         * Gets the value of the numberOfOrders property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumberOfOrders() {
            return numberOfOrders;
        }

        /**
         * Sets the value of the numberOfOrders property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumberOfOrders(String value) {
            this.numberOfOrders = value;
        }

        /**
         * Gets the value of the directOrderNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDirectOrderNumber() {
            return directOrderNumber;
        }

        /**
         * Sets the value of the directOrderNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDirectOrderNumber(String value) {
            this.directOrderNumber = value;
        }

        /**
         * Gets the value of the directOrderShipToNbr property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDirectOrderShipToNbr() {
            return directOrderShipToNbr;
        }

        /**
         * Sets the value of the directOrderShipToNbr property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDirectOrderShipToNbr(String value) {
            this.directOrderShipToNbr = value;
        }

        /**
         * Gets the value of the alternateOrderNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlternateOrderNumber() {
            return alternateOrderNumber;
        }

        /**
         * Sets the value of the alternateOrderNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlternateOrderNumber(String value) {
            this.alternateOrderNumber = value;
        }

        /**
         * Gets the value of the sendDetail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSendDetail() {
            return sendDetail;
        }

        /**
         * Sets the value of the sendDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSendDetail(String value) {
            this.sendDetail = value;
        }

        /**
         * Gets the value of the excludeOrderChannel property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExcludeOrderChannel() {
            return excludeOrderChannel;
        }

        /**
         * Sets the value of the excludeOrderChannel property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExcludeOrderChannel(String value) {
            this.excludeOrderChannel = value;
        }

        /**
         * Gets the value of the lastName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets the value of the lastName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastName(String value) {
            this.lastName = value;
        }

        /**
         * Gets the value of the postalCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         * Sets the value of the postalCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPostalCode(String value) {
            this.postalCode = value;
        }

    }

}
