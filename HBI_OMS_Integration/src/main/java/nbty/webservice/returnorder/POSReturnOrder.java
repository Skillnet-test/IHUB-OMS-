/* 
------------------------------------------------------------------------------------------- FILE HISTORY --------------------------------------------------------------------------
FILE VER.   DATE        DEVELOPER     DEEFECT ID/FSD MOD.     DESCRIPTION
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   1.       19/02/2018   Vaibhav       HBI Everest            New Class for REST receving web order id data 
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
package nbty.webservice.returnorder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Vaibhav
 */
public class POSReturnOrder
{
    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String orderID;

    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String companyCode;
    
    @JsonProperty("ship_to_nbr")
    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String shipToNbr;
    
    
    @JsonProperty("odt_seq_nbr")
    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    private String odtSeqNbr;
    
    @NotEmpty
    @NotNull
    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String item;

    public POSReturnOrder()
    {

    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String OrderID)
    {
        this.orderID = OrderID;
    }

    public String getcompanyCode()
    {
        return companyCode;
    }

    public void setcompanyCode(String companyCode)
    {
        this.companyCode = companyCode;
    }

    public String getShipToNbr()
    {
        return shipToNbr;
    }

    public void setShipToNbr(String shiptonbr)
    {
        this.shipToNbr = shiptonbr;
    }

    public String getOdtSeqNbr()
    {
        return odtSeqNbr;
    }

    public void setOdtSeqNbr(String odtSeqNbr)
    {
        this.odtSeqNbr = odtSeqNbr;
    }

    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "{" + "orderID:" + orderID + ","
                + "companyCode:" + companyCode + ","
                + "ship_to_nbr:" + shipToNbr + ","
                + "odt_seq_nbr:" + odtSeqNbr + ","
                + "item:" + item + '}';
    }


}
