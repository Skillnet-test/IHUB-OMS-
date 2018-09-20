
/*
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.                                             DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    1.    07/05/2015    Jigar N       CR #17 PayPal Redfund			Initial Version- A Tender PayPal Class.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 */

package oracle.retail.stores.domain.tender;

import oracle.retail.stores.domain.tender.TenderLineItemIfc;



public interface TenderPayPalIfc extends TenderLineItemIfc 
{
	public String getReferenceID();

	public void setReferenceID(String referenceID);

}
