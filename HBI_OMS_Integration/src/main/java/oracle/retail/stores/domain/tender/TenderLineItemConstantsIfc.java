package oracle.retail.stores.domain.tender;

/**
 * Interface for tender line items. This interface is implemented by all tender line items and AbstractTenderLineItem.
 * 
 * @author Amitkumar Patel
 */
public interface TenderLineItemConstantsIfc { // begin interface

    // TenderLineItemIfc
    /**
     * revision number supplied by source-code control system
     */
    public static final String revisionNumber = "$Revision: /main/10 $";
    /**
     * unknown tender type
     */
    public static final int TENDER_TYPE_UNKNOWN = -1;
    /**
     * cash tender type
     */
    public static final int TENDER_TYPE_CASH = 0;
    /**
     * charge tender type
     */
    public static final int TENDER_TYPE_CHARGE = 1;
    /**
     * check tender type
     */
    public static final int TENDER_TYPE_CHECK = 2;
    /**
     * traveler's check tender type
     */
    public static final int TENDER_TYPE_TRAVELERS_CHECK = 3;
    /**
     * gift certificate
     */
    public static final int TENDER_TYPE_GIFT_CERTIFICATE = 4;
    /**
     * mail bank check
     */
    public static final int TENDER_TYPE_MAIL_BANK_CHECK = 5;
    /**
     * debit
     */
    public static final int TENDER_TYPE_DEBIT = 6;
    /**
     * coupon
     */
    public static final int TENDER_TYPE_COUPON = 7;
    /**
     * gift card
     **/
    public static final int TENDER_TYPE_GIFT_CARD = 8;
    /**
     * store credit
     **/
    public static final int TENDER_TYPE_STORE_CREDIT = 9;
    /**
     * mall certificate
     */
    public static final int TENDER_TYPE_MALL_GIFT_CERTIFICATE = 10;
    /**
     * purchase order
     **/
    public static final int TENDER_TYPE_PURCHASE_ORDER = 11;
    /**
     * purchase order
     **/
    public static final int TENDER_TYPE_MONEY_ORDER = 12;
    /**
     * e-check
     **/
    public static final int TENDER_TYPE_E_CHECK = 13;
    /**
     * Luncheon Voucher
     **/
    public static final int TENDER_TYPE_LUNCHEON_VOUCHER = 14;

    /**
     * Supplier Coupon
     **/
    public static final int TENDER_TYPE_SUPPLIER_COUPON = 15;

    /**
     * WebPayment Coupon
     **/
    public static final int TENDER_TYPE_WEBPAYMENT = 16;
    
    /**
     * PayPal Tender
     **/
    public static final int TENDER_TYPE_PAYPAL = 17;
    
    /**
     * EPayment Tender
     **/
    public static final int TENDER_TYPE_ETENDER = 18;
    
    
    /**
     * Switch Tender
     **/
    public static final int TENDER_TYPE_SWITCH = 19;
    
    /**
     * SOLO Tender
     **/
    public static final int TENDER_TYPE_SOLO = 19;
    
    /**
     * POS Tender
     **/
    public static final int TENDER_TYPE_POS = 20;
    
    /**
     * Replacement Order Tender
     **/
    public static final int TENDER_TYPE_REPLACEMENT_ORDER = 21;
    
    
    /**
     * Highest int used to define the tender types. This is provided so the extending interfaces know where to start the
     * count.
     **/
    public static final int TENDER_TYPE_LAST_USED = 21;

    /**
     * tender type descriptors for line display device
     */
    public static final String TENDER_LINEDISPLAY_DESC[] = { "Cash", "Credit", "Check", "TravelCk", "GiftCert",
            "MailCheck", "Debit", "Coupon", "GiftCard", "StoreCr", "MallCert", "P.O.", "MoneyOrder", "E-Check",
            "LuncheonVoucher", "SupplierCoupon", "WebPayment","PayPal","ETender","Switch","Solo","POS","ReplacementOrder" };

    /**
     * Tender Entry method for auto. Equals "Auto" to start with "A" for use on receipts.
     */
    public static final String ENTRY_METHOD_AUTO = "Auto";

    /**
     * Tender Entry method for manual. Equals "Manual" to start with "M" for use on receipts.
     */
    public static final String ENTRY_METHOD_MANUAL = "Manual";

    /**
     * Tender Entry method for magnetic swipe - used vice-versa with Auto. Equals "Swipe" to start with "S" for use on
     * receipts.
     */
    public static final String ENTRY_METHOD_MAGSWIPE = "Swipe";

    /**
     * Indicates the width of the journal line for proper formatting of journal strings.
     **/
    public static final int JOURNAL_LINE_LENGTH = 40;
    /**
     * Used in journal entries to indicate whether tender is received, not refunded/reversed.
     **/
    public static final String JOURNAL_TENDERED = new String("Tendered");
    /**
     * Used in journal entries to indicate whether tender is refunded/reversed, not received.
     **/
    public static final String JOURNAL_REVERSED = new String("Reversed");
    /**
     * Used in journal entries to indicate that tender is received.
     **/
    public static final String JOURNAL_RECEIVED = new String("Received");
    /**
     * Used to format the amounts in the journal entries.
     **/
    public static final String JOURNAL_AMOUNT_FORMAT = new String("(#0.00);#0.00");
    /**
     * Used to right-justify the amount in journal entries.
     **/
    public static final int JOURNAL_AMOUNT_LOCATION = 30;
    /**
     * store certificate
     */
    public static final String CERTIFICATE_TYPE_STORE = "Store";
    /**
     * corporate certificate
     */
    public static final String CERTIFICATE_TYPE_CORPORATE = "Corporate";

    /**
     * foreign certificate
     */
    public static final String CERTIFICATE_TYPE_FOREIGN = "Foreign";

	public static final String CASH_ROUNDING_NAME = "ROUNDING ADJUSTMENT";

} // end interface TenderLineItemIfc
