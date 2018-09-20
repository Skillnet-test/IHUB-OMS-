/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package oracle.retail.stores.domain.lineitem;

import java.math.BigDecimal;
import java.util.Locale;
import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;
import oracle.retail.stores.commerceservices.externalorder.ExternalOrderItemIfc;
import oracle.retail.stores.common.utility.LocalizedCodeIfc;
import oracle.retail.stores.common.utility.LocalizedTextIfc;
import oracle.retail.stores.domain.discount.ItemDiscountStrategyIfc;
import oracle.retail.stores.domain.discount.PromotionLineItemIfc;
import oracle.retail.stores.domain.employee.EmployeeIfc;
import oracle.retail.stores.domain.registry.RegistryIDIfc;
import oracle.retail.stores.domain.stock.PLUItemIfc;
import oracle.retail.stores.domain.stock.ReturnMessageDTO;
import oracle.retail.stores.domain.utility.EYSDate;
import oracle.retail.stores.domain.utility.EntryMethod;
import oracle.retail.stores.foundation.utility.xml.XMLConversionException;
import oracle.retail.stores.foundation.utility.xml.XMLConverterIfc;

public abstract interface SaleReturnLineItemIfc extends AbstractTransactionLineItemIfc, DiscountableTaxableLineItemIfc {
	public static final String revisionNumber = "$Revision: /rgbustores_13.4x_generic_branch/2 $";
	public static final int ITEM_PRICE_LENGTH = 12;
	public static final int ITEM_NUMBER_LENGTH = 20;
	public static final int DISCOUNT_LITERAL_LENGTH = 19;
	public static final int DISCOUNT_BOTH = 3;

	public abstract void initialize(PLUItemIfc paramPLUItemIfc, BigDecimal paramBigDecimal, ItemTaxIfc paramItemTaxIfc,
			EmployeeIfc paramEmployeeIfc, RegistryIDIfc paramRegistryIDIfc, ReturnItemIfc paramReturnItemIfc);

	public abstract void initialize(PLUItemIfc paramPLUItemIfc, ItemTaxIfc paramItemTaxIfc,
			EmployeeIfc paramEmployeeIfc, RegistryIDIfc paramRegistryIDIfc, ReturnItemIfc paramReturnItemIfc,
			ExternalOrderItemIfc paramExternalOrderItemIfc);

	public abstract void calculateLineItemPrice();

	public abstract void modifyItemTaxRate(double paramDouble, int paramInt);

	public abstract void modifyItemTaxRate(double paramDouble, LocalizedCodeIfc paramLocalizedCodeIfc);

	public abstract void modifyItemPrice(CurrencyIfc paramCurrencyIfc, int paramInt);

	public abstract void modifyItemPrice(CurrencyIfc paramCurrencyIfc, LocalizedCodeIfc paramLocalizedCodeIfc);

	public abstract void modifyItemRegistry(RegistryIDIfc paramRegistryIDIfc, boolean paramBoolean);

	public abstract RegistryIDIfc getRegistry();

	public abstract ItemPriceIfc getItemPrice();

	public abstract void setItemPrice(ItemPriceIfc paramItemPriceIfc);

	public abstract BigDecimal getQuantityReturnable();

	public abstract BigDecimal getQuantityReturnedDecimal();

	public abstract void setQuantityReturned(BigDecimal paramBigDecimal);

	public abstract boolean isSaleLineItem();

	public abstract boolean isReturnLineItem();

	public abstract boolean isOrderItem();

	public abstract boolean isUnitOfMeasureItem();

	public abstract boolean isSerializedItem();

	public abstract boolean isServiceItem();

	public abstract PLUItemIfc getPLUItem();

	public abstract String getPLUItemID();

	public abstract String getItemSerial();

	public abstract void setPLUItem(PLUItemIfc paramPLUItemIfc);

	public abstract void setPLUItemID(String paramString);

	public abstract void setItemSerial(String paramString);

	public abstract ReturnItemIfc getReturnItem();

	public abstract void setReturnItem(ReturnItemIfc paramReturnItemIfc);

	public abstract void setReturnMessage(ReturnMessageDTO paramReturnMessageDTO);

	public abstract ReturnMessageDTO getReturnMessage();

	public abstract boolean getRegistryModifiedFlag();

	public abstract void setRegistryModifiedFlag(boolean paramBoolean);

	public abstract void translateFromElement(XMLConverterIfc paramXMLConverterIfc) throws XMLConversionException;

	public abstract Object clone();

	public abstract String toJournalString(int paramInt);

	public abstract String toJournalString(EYSDate paramEYSDate);

	public abstract String toJournalString(EYSDate paramEYSDate, String paramString);

	public abstract String toJournalString(EYSDate paramEYSDate, String paramString, Locale paramLocale);

	public abstract String toJournalDeleteString();

	public abstract String toJournalDeleteString(int paramInt);

	public abstract String toJournalDeleteString(int paramInt, Locale paramLocale);

	public abstract String toJournalRemoveString();

	public abstract String toJournalRemoveString(Locale paramLocale);

	public abstract String toJournalManualDiscount(ItemDiscountStrategyIfc paramItemDiscountStrategyIfc,
			boolean paramBoolean, Locale paramLocale);

	public abstract String toJournalManualDiscount(ItemDiscountStrategyIfc paramItemDiscountStrategyIfc,
			boolean paramBoolean);

	public abstract void clearItemDiscountsByPercentage(int paramInt, boolean paramBoolean);

	public abstract void clearItemDiscountsByPercentage(int paramInt1, int paramInt2, boolean paramBoolean);

	public abstract void clearItemDiscountsByAmount(int paramInt, boolean paramBoolean);

	public abstract void clearItemDiscountsByAmount(int paramInt1, int paramInt2, boolean paramBoolean);

	public abstract void clearItemMarkdownsByPercentage();

	public abstract void clearItemMarkdownsByPercentage(int paramInt);

	public abstract void clearItemMarkdownsByAmount();

	public abstract void clearItemMarkdownsByAmount(int paramInt);

	public abstract void clearItemDiscounts(String paramString);

	public abstract int getItemTaxMethod();

	public abstract void setItemTaxMethod(int paramInt);

	public abstract boolean taxExceedsSellingPrice();

	public abstract boolean discountExceedsSellingPrice();

	public abstract boolean multipleDiscountsApplied();

	public abstract ItemDiscountStrategyIfc getAdvancedPricingDiscount();

	public abstract void removeAdvancedPricingDiscount();

	public abstract void removeManualDiscount();

	public abstract CurrencyIfc getSellingPrice();

	public abstract CurrencyIfc getPrintedSellingPrice();

	public abstract CurrencyIfc getExtendedSellingPrice();

	public abstract CurrencyIfc getExtendedDiscountedSellingPrice();

	public abstract CurrencyIfc getItemDiscountTotal();

	public abstract CurrencyIfc getDiscountAmount(int paramInt1, int paramInt2);

	public abstract ItemDiscountStrategyIfc[] getItemDiscountsByPercentage();

	public abstract ItemDiscountStrategyIfc[] getItemDiscountsByAmount();

	public abstract ItemDiscountStrategyIfc[] getReturnItemDiscounts();

	public abstract ItemDiscountStrategyIfc[] getTransactionDiscounts();

	public abstract boolean isClassifiedAs(String paramString);

	public abstract String getComparator(int paramInt);

	public abstract boolean isKitHeader();

	public abstract boolean isKitComponent();

	public abstract int getKitHeaderReference();

	public abstract void setKitHeaderReference(int paramInt);

	public abstract String getItemDescription(Locale paramLocale);

	public abstract LocalizedTextIfc getLocalizedItemDescriptions();

	public abstract String getItemDescription();

	public abstract String getItemID();

	public abstract BigDecimal getItemQuantityDecimal();

	public abstract Number getItemQuantity();

	public abstract int getTaxMode();

	public abstract boolean getTaxable();

	public abstract String getTaxStatusDescriptor();

	public abstract boolean isEligibleForSend();

	public abstract boolean getItemSendFlag();

	public abstract boolean isSpecialOrderEligible();

	public abstract void setItemSendFlag(boolean paramBoolean);

	public abstract void setSendLabelCount(int paramInt);

	public abstract int getSendLabelCount();

	public abstract void setGiftReceiptItem(boolean paramBoolean);

	public abstract boolean isGiftReceiptItem();

	public abstract void setAlterationItemFlag(boolean paramBoolean);

	public abstract boolean getAlterationItemFlag();

	public abstract boolean isAlterationItem();

	public abstract OrderItemStatusIfc getOrderItemStatus();

	public abstract void setOrderItemStatus(OrderItemStatusIfc paramOrderItemStatusIfc);

	public abstract String getLineReference();

	public abstract void setLineReference(String paramString);

	public abstract int getOrderLineReference();

	public abstract void setOrderLineReference(int paramInt);

	public abstract void setEntryMethod(EntryMethod paramEntryMethod);

	public abstract EntryMethod getEntryMethod();

	public abstract String getPosItemID();

	public abstract boolean isGiftCardIssue();

	public abstract boolean isGiftCardReload();

	public abstract boolean isGiftItem();

	public abstract boolean hasDamageDiscount();

	public abstract boolean hasEmployeeDiscount();

	public abstract int getItemType();

	public abstract String getItemSizeCode();

	public abstract void setItemSizeCode(String paramString);

	public abstract boolean isReturnable();

	public abstract boolean isPriceAdjustmentLineItem();

	public abstract boolean isPartOfPriceAdjustment();

	public abstract void setIsPartOfPriceAdjustment(boolean paramBoolean);

	public abstract void setIsPriceAdjustmentLineItem(boolean paramBoolean);

	public abstract int getPriceAdjustmentReference();

	public abstract void setPriceAdjustmentReference(int paramInt);

	public abstract void setItemQuantity(BigDecimal paramBigDecimal);

	public abstract int getOriginalLineNumber();

	public abstract void setOriginalLineNumber(int paramInt);

	public abstract long getOriginalTransactionSequenceNumber();

	public abstract void setOriginalTransactionSequenceNumber(long paramLong);

	public abstract void setFromTransaction(boolean paramBoolean);

	public abstract boolean isFromTransaction();

	public abstract boolean isRelatedItemReturnable();

	public abstract void setRelatedItemReturnable(boolean paramBoolean);

	public abstract void setRelatedItemSequenceNumber(int paramInt);

	public abstract int getRelatedItemSequenceNumber();

	public abstract void setRelatedItemDeleteable(boolean paramBoolean);

	public abstract boolean isRelatedItemDeleteable();

	public abstract void addRelatedItemLineItem(SaleReturnLineItemIfc paramSaleReturnLineItemIfc);

	public abstract SaleReturnLineItemIfc[] getRelatedItemLineItems();

	public abstract void setRelatedItemLineItems(SaleReturnLineItemIfc[] paramArrayOfSaleReturnLineItemIfc);

	public abstract PromotionLineItemIfc[] getPromotionLineItems();

	public abstract void addPromotionLineItem(PromotionLineItemIfc paramPromotionLineItemIfc);

	public abstract void setHasPriceModification(boolean paramBoolean);

	public abstract boolean hasPriceModification();

	public abstract String getItemReceiptMessage();

	public abstract String getItemFooterMessage();

	public abstract String getItemRebateMessage();

	public abstract String getItemMessageID(String paramString1, String paramString2);

	public abstract boolean hasReturnItem();

	public abstract void setHasReturnItem(boolean paramBoolean);

	public abstract boolean hasSendItem();

	public abstract void setHasSendItem(boolean paramBoolean);

	public abstract boolean isSelectedForItemModification();

	public abstract void setSelectedForItemModification(boolean paramBoolean);

	public abstract String getItemIMEINumber();

	public abstract void setItemIMEINumber(String paramString);

	public abstract boolean isFromExternalOrder();

	public abstract String getExternalOrderItemID();

	public abstract void setExternalOrderItemID(String paramString);

	public abstract String getExternalOrderParentItemID();

	public abstract void setExternalOrderParentItemID(String paramString);

	public abstract boolean hasExternalPricing();

	public abstract void setExternalPricingFlag(boolean paramBoolean);

	public abstract String getReceiptDescription();

	public abstract void setReceiptDescription(String paramString);

	public abstract Locale getReceiptDescriptionLocale();

	public abstract void setReceiptDescriptionLocale(Locale paramLocale);

	public abstract void setReceiptDescriptionFromPLUItem(Locale paramLocale);

	public abstract boolean isDamageDiscountEligible();

	public abstract boolean isExternalOrderItemUpdateSourceFlag();

	public abstract void setExternalOrderItemUpdateSourceFlag(boolean paramBoolean);
	
	public int getWebOrderItemSeqNo();
	
	public void setWebOrderItemSeqNo(int webOrderItemSeqNo);

	public String getWebOrderNumber();

	public void setWebOrderNumber(String webOrderNumber);
	
	public EYSDate getWebOrderBusinessDate();
	
	public void setWebOrderBusinessDate(EYSDate webOrderBusinessDate);
	
	public boolean isWebOrderReturnItem();

	public void setWebOrderReturnItem(boolean webOrderReturnItem);
	
	public String getWebOrderShipToLineNo();

    public void setWebOrderShipToLineNo(String webOrderShipToLineNo);
}