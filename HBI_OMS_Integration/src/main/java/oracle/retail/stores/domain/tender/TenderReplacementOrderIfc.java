
package oracle.retail.stores.domain.tender;

import oracle.retail.stores.domain.tender.TenderLineItemIfc;



public interface TenderReplacementOrderIfc extends TenderLineItemIfc 
{
	public String getReferenceID();

	public void setReferenceID(String referenceID);

}
