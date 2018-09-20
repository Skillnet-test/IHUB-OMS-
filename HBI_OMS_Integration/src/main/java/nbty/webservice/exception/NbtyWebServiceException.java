/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for exceptions
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.exception;

public class NbtyWebServiceException extends RuntimeException
{
    /**
     * 
     */
    private static final long serialVersionUID = 130031429975192310L;

    private int code;

    public NbtyWebServiceException(String message)
    {
        super(message);
    }

    public NbtyWebServiceException(int code, String message)
    {
        super(message);
        this.setCode(code);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }
}
