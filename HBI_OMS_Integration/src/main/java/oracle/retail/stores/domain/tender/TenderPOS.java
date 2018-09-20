

package oracle.retail.stores.domain.tender;

import java.util.Locale;

import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;


public class TenderPOS extends AbstractTenderLineItem implements TenderPOSIfc
{

    private static final long serialVersionUID = 1L;
    protected String  referenceID = null;

    /**
     * Constructs TenderPayPal object.
     **/
    public TenderPOS() {

        typeCode = TenderLineItemConstantsIfc.TENDER_TYPE_POS;
        setHasDenominations(true);
    }

    /**
     * Constructs TenderSupplierCoupon object with tender amount.
     * 
     * @param tender
     *            amount tendered
     **/
    public TenderPOS(CurrencyIfc tender) {

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

        TenderPOS tc = new TenderPOS();

        this.setCloneAttributes(tc);
        return tc;
    }



    protected void setCloneAttributes(TenderPOS newClass) 
    {
    	super.setCloneAttributes(newClass);
        newClass.setReferenceID(getReferenceID());
    }



	@Override
	public String toJournalString(Locale locale) {
		return "";
	}

    
}
