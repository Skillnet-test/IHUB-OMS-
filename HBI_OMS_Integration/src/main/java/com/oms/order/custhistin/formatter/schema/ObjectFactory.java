//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.10 at 04:06:46 PM IST 
//


package com.oms.order.custhistin.formatter.schema;

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
     * Create an instance of {@link CustHistInMessage }
     * 
     */
    public CustHistInMessage createMessage() {
        return new CustHistInMessage();
    }

    /**
     * Create an instance of {@link CustHistInMessage.CustomerHistoryRequest }
     * 
     */
    public CustHistInMessage.CustomerHistoryRequest createMessageCustomerHistoryRequest() {
        return new CustHistInMessage.CustomerHistoryRequest();
    }

}
