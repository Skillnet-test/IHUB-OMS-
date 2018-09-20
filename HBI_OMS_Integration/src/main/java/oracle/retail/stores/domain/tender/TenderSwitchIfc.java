
package oracle.retail.stores.domain.tender;

import oracle.retail.stores.domain.tender.TenderLineItemIfc;



public interface TenderSwitchIfc extends TenderLineItemIfc 
{
	public String getReferenceID();

	public void setReferenceID(String referenceID);

}
