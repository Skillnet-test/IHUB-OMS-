/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for webservice response and error codes
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.exception;

/**
 * @author Vaibhav
 *
 */
public enum WSResponseErrorCodesEnum
{
    OK(200,"Ok"),
    BAD_REQUEST_COMPANY_CODE(400,"Bad Request: Invalid Company Code"),
    BAD_REQUEST_INVALIDDATA(400,"Bad Request: Check request data"),
    UNAUTHORIZED(401,"Unauthorised"),
    CONFLICT(409,"Conflict"),
    UNSUPPORTED_MEDIA_TYPE(415,"Unsupported Media Type"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error")
    ;
    
    
    private final int code;
    
    private final String message;
    

    WSResponseErrorCodesEnum(int c,String m) {
        code = c;
        message= m;
    }

    public String getMessage()
    {
        return message;
    }


    public int getCode()
    {
        return code;
    }
}
