/**
 * 
 */
package com.oms.order.common;

/**
 * @author Jigar
 *
 */
public enum PaymentMethodEnum 
{
	CASH_PAYMENT(1,"CASH","Cash"),
	AMEX_CARD_PAYMENT(3,"CRDT","AmEx"),
	VISA_CARD_PAYMENT(4,"CRDT","Visa"),
	CREDIT_CARD_PAYMENT(5,"CRDT","MasterCard"),
	DISCOVER_CARD_PAYMENT(6,"CRDT","Discover"),
	MAESTRO_CARD_PAYMENT(7,"CRDT","Maestro"),
	SWITCH_PAYMENT(8,"CRDT","Switch"),
	DEBIT_CARD_PAYMENT(9,"CRDT","Visa"),
	SOLO_PAYMENT(10,"CRDT","Solo"),
	JCB_CARD_PAYMENT(11,"CRDT","JCB"),
	PAYPAL_CARD_PAYMENT(12,"PYPL","Paypal"),
	POS_PAYMENT(13,"CASH","POS"),
	REPLACEMENT_ORDER(14,"CASH","ReplacementOrder");
	
	
	
	private final int value;
	
	private final String type;
	
	private String subType;

	PaymentMethodEnum(int v,String t,String st) {
        value = v;
        type= t;
        subType=st;
    }

    public int getPayMethodValue() 
    {
        return value;
    }
    
    public String getPayMethodType() 
    {
        return type;
    }

    public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public static PaymentMethodEnum fromValue(int v) {
        for (PaymentMethodEnum c: PaymentMethodEnum.values()) {
            if (c.value == v) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }
    
    public static PaymentMethodEnum fromType(String t) {
        for (PaymentMethodEnum c: PaymentMethodEnum.values()) {
            if (c.type.equals(t)) {
                return c;
            }
        }
        throw new IllegalArgumentException();
    }

}
