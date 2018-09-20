
package oracle.retail.stores.domain.tender;

import oracle.retail.stores.domain.tender.TenderLineItemIfc;



public interface TenderPOSIfc extends TenderLineItemIfc 
{
	public String getReferenceID();

	public void setReferenceID(String referenceID);

}
