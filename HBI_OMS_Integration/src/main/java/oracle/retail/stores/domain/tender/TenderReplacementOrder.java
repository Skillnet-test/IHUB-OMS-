

package oracle.retail.stores.domain.tender;

import java.util.Locale;

import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;


public class TenderReplacementOrder extends AbstractTenderLineItem implements TenderReplacementOrderIfc
{

    private static final long serialVersionUID = 1L;
    protected String  referenceID = null;

    /**
     * Constructs TenderPayPal object.
     **/
    public TenderReplacementOrder() {

        typeCode = TenderLineItemConstantsIfc.TENDER_TYPE_REPLACEMENT_ORDER;
        setHasDenominations(true);
    }

    /**
     * Constructs TenderSupplierCoupon object with tender amount.
     * 
     * @param tender
     *            amount tendered
     **/
    public TenderReplacementOrder(CurrencyIfc tender) {

        this();
        setAmountTender(tender);
    }

    /**
	 * @return the referenceID
	 */
	public String getReferenceID() 
	{
		return referenceID;
	}

	/**
	 * @param referenceID the referenceID to set
	 */
	public void setReferenceID(String referenceID) 
	{
		this.referenceID = referenceID;
	}

	/**
     * Clone TenderSupplierCoupon object.
     * <P>
     * 
     * @return instance of TenderSupplierCoupon object
     **/
    @Override
    public Object clone() {

        TenderReplacementOrder tc = new TenderReplacementOrder();

        this.setCloneAttributes(tc);
        return tc;
    }



    protected void setCloneAttributes(TenderReplacementOrder newClass) 
    {
    	super.setCloneAttributes(newClass);
        newClass.setReferenceID(getReferenceID());
    }



	@Override
	public String toJournalString(Locale locale) {
		return "";
	}

    
}
