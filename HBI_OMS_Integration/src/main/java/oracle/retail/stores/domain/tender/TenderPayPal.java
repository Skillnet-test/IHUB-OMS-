
/*
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.                                             DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    1.    07/05/2015    Jigar N       CR #17 PayPal Redfund			Initial Version- A Tender PayPal Class.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */

package oracle.retail.stores.domain.tender;

import java.util.Locale;

import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;


public class TenderPayPal extends AbstractTenderLineItem implements TenderPayPalIfc
{

    private static final long serialVersionUID = 1L;
    protected String  referenceID = null;

    /**
     * Constructs TenderPayPal object.
     **/
    public TenderPayPal() {

        typeCode = TenderLineItemConstantsIfc.TENDER_TYPE_PAYPAL;
        setHasDenominations(true);
    }

    /**
     * Constructs TenderSupplierCoupon object with tender amount.
     * 
     * @param tender
     *            amount tendered
     **/
    public TenderPayPal(CurrencyIfc tender) {

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

        TenderPayPal tc = new TenderPayPal();

        this.setCloneAttributes(tc);
        return tc;
    }



    protected void setCloneAttributes(TenderPayPal newClass) 
    {
    	super.setCloneAttributes(newClass);
        newClass.setReferenceID(getReferenceID());
    }

	@Override
	public String toJournalString(Locale locale) {
		return "";
	}


    
}
