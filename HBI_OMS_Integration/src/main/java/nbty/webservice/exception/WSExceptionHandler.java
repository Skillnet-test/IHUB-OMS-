/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for exception handler
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Vaibhav
 */
@ControllerAdvice
@RestController
public class WSExceptionHandler extends ResponseEntityExceptionHandler
{
    /**
     * The logger to which log messages will be sent.
     */
    private static final Logger wslogger = Logger.getLogger(WSExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request)
    {

        WSExceptionResponse exceptionResponse = new WSExceptionResponse(HttpStatus.BAD_REQUEST.toString(),
                WSResponseErrorCodesEnum.BAD_REQUEST_INVALIDDATA.getMessage());
        wslogger.error("BAD Request", ex);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        WSExceptionResponse exceptionResponse = new WSExceptionResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(),
                WSResponseErrorCodesEnum.UNSUPPORTED_MEDIA_TYPE.getMessage());
        wslogger.error("UNSUPPORTED MEDIA TYPE", ex);
        return new ResponseEntity(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    @ExceptionHandler({ NbtyWebServiceException.class})
    public final ResponseEntity<WSExceptionResponse> handleNbtyWebServiceException(NbtyWebServiceException ex,
            WebRequest request)
    {
        WSExceptionResponse exceptionResponse = new WSExceptionResponse(HttpStatus.BAD_REQUEST.toString(),
                WSResponseErrorCodesEnum.BAD_REQUEST_COMPANY_CODE.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (ex.getCode() == WSResponseErrorCodesEnum.BAD_REQUEST_COMPANY_CODE.getCode())
        {
            wslogger.error("Company Code not found", ex);
            return new ResponseEntity(exceptionResponse, status);
        }
        if (ex.getCode() == WSResponseErrorCodesEnum.UNAUTHORIZED.getCode())
        {
            status = HttpStatus.UNAUTHORIZED;
            exceptionResponse = new WSExceptionResponse(HttpStatus.UNAUTHORIZED.toString(),
                    WSResponseErrorCodesEnum.UNAUTHORIZED.getMessage());
            wslogger.error("Unauthorized", ex);
            return new ResponseEntity(exceptionResponse, status);
        }
        return new ResponseEntity(exceptionResponse, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request)
    {
        // This is a 500 error but since the data sent is incorrect so sending
        // response as 400
        WSExceptionResponse exceptionResponse = new WSExceptionResponse(HttpStatus.BAD_REQUEST.toString(),
                WSResponseErrorCodesEnum.BAD_REQUEST_INVALIDDATA.getMessage());
        wslogger.error("Bad Data", ex);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        WSExceptionResponse exceptionResponse = new WSExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                WSResponseErrorCodesEnum.INTERNAL_SERVER_ERROR.getMessage());
        wslogger.error("Error while loading request" + ex);
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
