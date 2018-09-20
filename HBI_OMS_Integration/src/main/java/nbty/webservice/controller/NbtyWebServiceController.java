/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for webservice 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.error.dao.ErrorEventDao;
import com.oms.order.error.data.ErrorEventData;

import nbty.webservice.exception.NbtyWebServiceException;
import nbty.webservice.exception.WSExceptionResponse;
import nbty.webservice.exception.WSResponseErrorCodesEnum;
import nbty.webservice.returnorder.POSReturnOrder;
import nbty.webservice.returnorder.POSReturnOrderService;

/**
 * @author Vaibhav
 */
@RestController
public class NbtyWebServiceController
{

    private static final Logger logger = Logger.getLogger(NbtyWebServiceController.class);

    @Autowired
    POSReturnOrderService posService;

    @Autowired
    ErrorEventDao eventDao;

    @PostMapping(value = "/POSReturnOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public WSExceptionResponse orderID(@Valid @RequestBody POSReturnOrder posReturnOrder)
    {
        logger.info("Order ID Request: " + posReturnOrder.toString());

        try
        {
            posService.persistToDB(posReturnOrder);
        }
        catch (OMSException e)
        {
            logger.error(e);
            ErrorEventData eventData = new ErrorEventData();
            eventData.setContentType("application/json");
            eventData.setRequestType("OrderIDInsert");
            eventData.setPayload(posReturnOrder.toString());
            if (e.getCode() == OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getCode())
            {
                eventData.setErrorMessage(OMSErrorCodes.COMPANY_CODE_NOT_FOUND.getDescription());
                eventDao.insertErrorEventData(eventData);
                logger.error(
                        "NbtyWebServiceController: Company Code not found " + " Request: " + posReturnOrder.toString());
                throw new NbtyWebServiceException(WSResponseErrorCodesEnum.BAD_REQUEST_COMPANY_CODE.getCode(),
                        WSResponseErrorCodesEnum.BAD_REQUEST_COMPANY_CODE.getMessage());
            }
            else
            {
                eventData.setErrorMessage(OMSErrorCodes.DATABASE.getDescription());
                eventDao.insertErrorEventData(eventData);
                logger.error("NbtyWebServiceController: Error while inserting in database " + " Request: "
                        + posReturnOrder.toString());
                throw new NbtyWebServiceException(WSResponseErrorCodesEnum.INTERNAL_SERVER_ERROR.getCode(),
                        WSResponseErrorCodesEnum.INTERNAL_SERVER_ERROR.getMessage());
            }
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new NbtyWebServiceException(WSResponseErrorCodesEnum.INTERNAL_SERVER_ERROR.getCode(),
                    WSResponseErrorCodesEnum.INTERNAL_SERVER_ERROR.getMessage());

        }
        logger.info("Response: " + "200:OK");
        return new WSExceptionResponse(String.valueOf(WSResponseErrorCodesEnum.OK.getCode()),
                WSResponseErrorCodesEnum.OK.getMessage());
    }
}
