//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.13 at 10:58:34 AM IST 
//


package com.oms.order.orderout.formatter.schema;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.order package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OrderOutMessage }
     * 
     */
    public OrderOutMessage createMessage() {
        return new OrderOutMessage();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header }
     * 
     */
    public OrderOutMessage.Header createMessageHeader() {
        return new OrderOutMessage.Header();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos }
     * 
     */
    public OrderOutMessage.Header.ShipTos createMessageHeaderShipTos() {
        return new OrderOutMessage.Header.ShipTos();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo createMessageHeaderShipTosShipTo() {
        return new OrderOutMessage.Header.ShipTos.ShipTo();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Promotions }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Promotions createMessageHeaderShipTosShipToPromotions() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Promotions();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs createMessageHeaderShipTosShipToOrdMsgs() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Errors }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Errors createMessageHeaderShipTosShipToErrors() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Errors();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Details }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Details createMessageHeaderShipTosShipToDetails() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Details();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail createMessageHeaderShipTosShipToDetailsDetail() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments createMessageHeaderShipTosShipToDetailsDetailShipments() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.Payments }
     * 
     */
    public OrderOutMessage.Header.Payments createMessageHeaderPayments() {
        return new OrderOutMessage.Header.Payments();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Promotions.Promotion }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Promotions.Promotion createMessageHeaderShipTosShipToPromotionsPromotion() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Promotions.Promotion();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs.OrdMsg }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs.OrdMsg createMessageHeaderShipTosShipToOrdMsgsOrdMsg() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.OrdMsgs.OrdMsg();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Errors.Error }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Errors.Error createMessageHeaderShipTosShipToErrorsError() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Errors.Error();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments.Shipment }
     * 
     */
    public OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments.Shipment createMessageHeaderShipTosShipToDetailsDetailShipmentsShipment() {
        return new OrderOutMessage.Header.ShipTos.ShipTo.Details.Detail.Shipments.Shipment();
    }

    /**
     * Create an instance of {@link OrderOutMessage.Header.Payments.Payment }
     * 
     */
    public OrderOutMessage.Header.Payments.Payment createMessageHeaderPaymentsPayment() {
        return new OrderOutMessage.Header.Payments.Payment();
    }

}
