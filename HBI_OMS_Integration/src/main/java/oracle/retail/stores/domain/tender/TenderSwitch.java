

package oracle.retail.stores.domain.tender;

import java.util.Locale;

import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;


public class TenderSwitch extends AbstractTenderLineItem implements TenderSwitchIfc
{

    private static final long serialVersionUID = 1L;
    protected String  referenceID = null;

    /**
     * Constructs TenderPayPal object.
     **/
    public TenderSwitch() {

        typeCode = TenderLineItemConstantsIfc.TENDER_TYPE_SWITCH;
        setHasDenominations(true);
    }

    /**
     * Constructs TenderSupplierCoupon object with tender amount.
     * 
     * @param tender
     *            amount tendered
     **/
    public TenderSwitch(CurrencyIfc tender) {

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

        TenderSwitch tc = new TenderSwitch();

        this.setCloneAttributes(tc);
        return tc;
    }



    protected void setCloneAttributes(TenderSwitch newClass) 
    {
    	super.setCloneAttributes(newClass);
        newClass.setReferenceID(getReferenceID());
    }



	@Override
	public String toJournalString(Locale locale) {
		return "";
	}

    
}
