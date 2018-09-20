/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class to send modified response 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vaibhav
 */
@JsonIgnoreProperties({ "message", "details" })
public class WSExceptionResponse
{

    @JsonProperty("statusCode")
    private String statusCode;

    @JsonProperty("statusMessage")
    private String statusMessage;

    public WSExceptionResponse(String statusCode, String statusMessage)
    {
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public String getMessage()
    {
        return statusMessage;
    }

    public String getDetails()
    {
        return statusCode;
    }

    @Override
    public String toString()
    {
        return "WSExceptionResponse{" + "statusMessage='" + statusMessage + '\'' + ", statusCode='" + statusCode + '\''
                + '}';
    }
}
