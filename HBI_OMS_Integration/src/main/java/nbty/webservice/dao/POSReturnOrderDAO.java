/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for webservice 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;

import nbty.webservice.returnorder.POSReturnOrder;

/**
 * @author Vaibhav
 */
@Repository
public class POSReturnOrderDAO
{
    private static final Logger logger = Logger.getLogger(POSReturnOrderDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String SQL_INSERT_ORDERDETAILS = "INSERT INTO POS_RTLOG_GENERATED VALUES (?,?,?,?,?,SYSTIMESTAMP)";

    public void insertReturnOrderNumber(POSReturnOrder posReturnOrder, String storeid) throws OMSException
    {
        try
        {
            jdbcTemplate.update(SQL_INSERT_ORDERDETAILS, new Object[] { storeid, posReturnOrder.getOrderID(),
                    posReturnOrder.getItem(), posReturnOrder.getShipToNbr(), posReturnOrder.getOdtSeqNbr() });
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new OMSException(OMSErrorCodes.DATABASE.getCode(), OMSErrorCodes.DATABASE.getDescription());
        }
    }
}
