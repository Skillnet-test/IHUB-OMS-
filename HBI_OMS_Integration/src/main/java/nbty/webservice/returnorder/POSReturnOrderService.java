/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for webservice 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.returnorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oms.exception.OMSException;
import com.payment.dao.CompanyCodeDao;

import nbty.webservice.dao.POSReturnOrderDAO;

/**
 * @author Vaibhav
 */

@Service
public class POSReturnOrderService
{
    @Autowired
    CompanyCodeDao companyCodes;

    @Autowired
    POSReturnOrderDAO posReturnOrderDAO;

    public void persistToDB(POSReturnOrder posReturnOrder) throws OMSException
    {
        // TODO Auto-generated method stub
        String storeid;
        storeid = companyCodes.getWareHouseNumber(posReturnOrder.getcompanyCode());
        posReturnOrderDAO.insertReturnOrderNumber(posReturnOrder, storeid);
    }
}
