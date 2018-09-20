/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package oracle.retail.stores.domain.factory;

import java.util.Locale;

import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;
import oracle.retail.stores.commerceservices.common.currency.CurrencyTypeIfc;
import oracle.retail.stores.commerceservices.common.currency.CurrencyTypeListIfc;
import oracle.retail.stores.commerceservices.security.EmployeeComplianceIfc;
import oracle.retail.stores.commerceservices.security.PasswordPolicyEvaluatorIfc;
import oracle.retail.stores.common.utility.LocalizedCodeIfc;
import oracle.retail.stores.common.utility.LocalizedTextIfc;
import oracle.retail.stores.domain.alert.AlertEntryIfc;
import oracle.retail.stores.domain.alert.AlertListIfc;
import oracle.retail.stores.domain.audit.AuditEntryIfc;
import oracle.retail.stores.domain.customer.CaptureCustomerIfc;
import oracle.retail.stores.domain.customer.CustomerGroupIfc;
import oracle.retail.stores.domain.customer.CustomerIfc;
import oracle.retail.stores.domain.customer.CustomerInfoIfc;
import oracle.retail.stores.domain.customer.IRSCustomerIfc;
import oracle.retail.stores.domain.customer.event.CustomerEventBabyIfc;
import oracle.retail.stores.domain.customer.event.CustomerEventSpecialIfc;
import oracle.retail.stores.domain.customer.event.CustomerEventWeddingIfc;
import oracle.retail.stores.domain.customer.event.MerchandisePreferenceIfc;
import oracle.retail.stores.domain.discount.AdvancedPricingRuleIfc;
import oracle.retail.stores.domain.discount.AdvancedPricingRuleSearchCriteriaIfc;
import oracle.retail.stores.domain.discount.BestDealGroupIfc;
import oracle.retail.stores.domain.discount.CustomerDiscountByPercentageIfc;
import oracle.retail.stores.domain.discount.DiscountCalculationIfc;
import oracle.retail.stores.domain.discount.DiscountListEntryIfc;
import oracle.retail.stores.domain.discount.DiscountListIfc;
import oracle.retail.stores.domain.discount.DiscountRuleIfc;
import oracle.retail.stores.domain.discount.ItemDiscountByAmountIfc;
import oracle.retail.stores.domain.discount.ItemDiscountByPercentageIfc;
import oracle.retail.stores.domain.discount.ItemTransactionDiscountAuditIfc;
import oracle.retail.stores.domain.discount.PromotionLineItemIfc;
import oracle.retail.stores.domain.discount.ReturnItemTransactionDiscountAuditIfc;
import oracle.retail.stores.domain.discount.SuperGroupIfc;
import oracle.retail.stores.domain.discount.TransactionDiscountByAmountIfc;
import oracle.retail.stores.domain.discount.TransactionDiscountByPercentageIfc;
import oracle.retail.stores.domain.emessage.EMessageIfc;
import oracle.retail.stores.domain.employee.EmployeeClockEntryIfc;
import oracle.retail.stores.domain.employee.EmployeeIfc;
import oracle.retail.stores.domain.employee.RoleFunctionGroupIfc;
import oracle.retail.stores.domain.employee.RoleFunctionIfc;
import oracle.retail.stores.domain.employee.RoleIfc;
import oracle.retail.stores.domain.event.ItemMaintenanceEventIfc;
import oracle.retail.stores.domain.event.ItemPriceMaintenanceEventIfc;
import oracle.retail.stores.domain.event.MaintenanceEventIfc;
import oracle.retail.stores.domain.event.PriceChangeIfc;
import oracle.retail.stores.domain.event.PriceDerivationRuleMaintenanceEventIfc;
import oracle.retail.stores.domain.externalorder.ExternalOrderSaleItemIfc;
import oracle.retail.stores.domain.externalorder.ExternalOrderSendPackageItemIfc;
import oracle.retail.stores.domain.externalorder.LegalDocumentIfc;
import oracle.retail.stores.domain.financial.AssociateProductivityIfc;
import oracle.retail.stores.domain.financial.BillPayIfc;
import oracle.retail.stores.domain.financial.DepartmentActivityIfc;
import oracle.retail.stores.domain.financial.DrawerIfc;
import oracle.retail.stores.domain.financial.EmployeeActivityIfc;
import oracle.retail.stores.domain.financial.FinancialCountIfc;
import oracle.retail.stores.domain.financial.FinancialCountTenderItemIfc;
import oracle.retail.stores.domain.financial.FinancialTotalsIfc;
import oracle.retail.stores.domain.financial.HardTotalsBuilderIfc;
import oracle.retail.stores.domain.financial.HardTotalsIfc;
import oracle.retail.stores.domain.financial.LayawayIfc;
import oracle.retail.stores.domain.financial.LayawaySummaryEntryIfc;
import oracle.retail.stores.domain.financial.PaymentHistoryInfoIfc;
import oracle.retail.stores.domain.financial.PaymentIfc;
import oracle.retail.stores.domain.financial.ReconcilableCountIfc;
import oracle.retail.stores.domain.financial.RegisterIfc;
import oracle.retail.stores.domain.financial.ReportingPeriodIfc;
import oracle.retail.stores.domain.financial.ShippingMethodIfc;
import oracle.retail.stores.domain.financial.StoreSafeIfc;
import oracle.retail.stores.domain.financial.StoreStatusAndTotalsIfc;
import oracle.retail.stores.domain.financial.StoreStatusIfc;
import oracle.retail.stores.domain.financial.TaxTotalsContainerIfc;
import oracle.retail.stores.domain.financial.TaxTotalsIfc;
import oracle.retail.stores.domain.financial.TillIfc;
import oracle.retail.stores.domain.financial.TimeIntervalActivityIfc;
import oracle.retail.stores.domain.giftregistry.GiftRegistryIfc;
import oracle.retail.stores.domain.ixretail.log.POSLogBatchGeneratorIfc;
import oracle.retail.stores.domain.ixretail.log.POSLogTransactionEntryIfc;
import oracle.retail.stores.domain.job.ActiveJobIfc;
import oracle.retail.stores.domain.job.NotificationRecipientsIfc;
import oracle.retail.stores.domain.job.ScheduledJobIfc;
import oracle.retail.stores.domain.job.message.JobControlEventMessageIfc;
import oracle.retail.stores.domain.job.schedule.CustomScheduleDocumentIfc;
import oracle.retail.stores.domain.job.schedule.DailyScheduleDocumentIfc;
import oracle.retail.stores.domain.job.schedule.MonthlyByDayScheduleDocumentIfc;
import oracle.retail.stores.domain.job.schedule.WeeklyScheduleDocumentIfc;
import oracle.retail.stores.domain.job.task.TaskInfoIfc;
import oracle.retail.stores.domain.lineitem.ItemContainerProxyIfc;
import oracle.retail.stores.domain.lineitem.ItemPriceIfc;
import oracle.retail.stores.domain.lineitem.ItemTaxIfc;
import oracle.retail.stores.domain.lineitem.KitComponentLineItemIfc;
import oracle.retail.stores.domain.lineitem.KitHeaderLineItemIfc;
import oracle.retail.stores.domain.lineitem.OrderItemStatusIfc;
import oracle.retail.stores.domain.lineitem.OrderLineItemIfc;
import oracle.retail.stores.domain.lineitem.PriceAdjustmentLineItemIfc;
import oracle.retail.stores.domain.lineitem.ReturnItemIfc;
import oracle.retail.stores.domain.lineitem.ReturnResponseLineItemIfc;
import oracle.retail.stores.domain.lineitem.SaleReturnLineItemIfc;
import oracle.retail.stores.domain.lineitem.SendPackageLineItemIfc;
import oracle.retail.stores.domain.manager.datareplication.DataReplicationBatchGeneratorIfc;
import oracle.retail.stores.domain.manager.datareplication.DataReplicationCustomerEntryIfc;
import oracle.retail.stores.domain.manager.payment.AuthorizeCallReferralRequestIfc;
import oracle.retail.stores.domain.manager.payment.AuthorizeInstantCreditRequestIfc;
import oracle.retail.stores.domain.manager.payment.AuthorizeInstantCreditResponseIfc;
import oracle.retail.stores.domain.manager.payment.AuthorizeTransferRequestIfc;
import oracle.retail.stores.domain.manager.payment.AuthorizeTransferResponseIfc;
import oracle.retail.stores.domain.manager.payment.CardTokenRequestIfc;
import oracle.retail.stores.domain.manager.payment.CardTokenResponseIfc;
import oracle.retail.stores.domain.manager.payment.CustomerInteractionRequestIfc;
import oracle.retail.stores.domain.manager.payment.CustomerInteractionResponseIfc;
import oracle.retail.stores.domain.manager.payment.PaymentServiceResponseIfc;
import oracle.retail.stores.domain.manager.payment.ReversalRequestIfc;
import oracle.retail.stores.domain.manager.payment.SignatureCaptureRequestIfc;
import oracle.retail.stores.domain.manager.payment.SignatureCaptureResponseIfc;
import oracle.retail.stores.domain.manager.payment.StatusResponseIfc;
import oracle.retail.stores.domain.manager.payment.pincomm.KeyManagementResponseIfc;
import oracle.retail.stores.domain.manager.payment.pincomm.PinCommEncryptionUtilityIfc;
import oracle.retail.stores.domain.manager.rtlog.RTLogExportBatchGeneratorIfc;
import oracle.retail.stores.domain.order.OrderDeliveryDetailIfc;
import oracle.retail.stores.domain.order.OrderIfc;
import oracle.retail.stores.domain.order.OrderRecipientIfc;
import oracle.retail.stores.domain.order.OrderStatusIfc;
import oracle.retail.stores.domain.order.OrderSummaryEntryIfc;
import oracle.retail.stores.domain.purchasing.PurchaseOrderIfc;
import oracle.retail.stores.domain.purchasing.PurchaseOrderLineItemIfc;
import oracle.retail.stores.domain.purchasing.SupplierIfc;
import oracle.retail.stores.domain.registry.RegistryIDIfc;
import oracle.retail.stores.domain.returns.ReturnTenderDataContainer;
import oracle.retail.stores.domain.returns.ReturnTenderDataElement;
import oracle.retail.stores.domain.shipping.ShippingMethodSearchCriteriaIfc;
import oracle.retail.stores.domain.stock.AlterationPLUItemIfc;
import oracle.retail.stores.domain.stock.GiftCardPLUItemIfc;
import oracle.retail.stores.domain.stock.GiftCertificateItemIfc;
import oracle.retail.stores.domain.stock.ItemClassificationIfc;
import oracle.retail.stores.domain.stock.ItemColorIfc;
import oracle.retail.stores.domain.stock.ItemIfc;
import oracle.retail.stores.domain.stock.ItemImageIfc;
import oracle.retail.stores.domain.stock.ItemInfoIfc;
import oracle.retail.stores.domain.stock.ItemInquirySearchCriteriaIfc;
import oracle.retail.stores.domain.stock.ItemKitIfc;
import oracle.retail.stores.domain.stock.ItemSizeIfc;
import oracle.retail.stores.domain.stock.ItemStyleIfc;
import oracle.retail.stores.domain.stock.ItemTypeIfc;
import oracle.retail.stores.domain.stock.KitComponentIfc;
import oracle.retail.stores.domain.stock.ManufacturerIfc;
import oracle.retail.stores.domain.stock.MerchandiseClassificationIfc;
import oracle.retail.stores.domain.stock.PLUItemIfc;
import oracle.retail.stores.domain.stock.ProductGroupIfc;
import oracle.retail.stores.domain.stock.ProductIfc;
import oracle.retail.stores.domain.stock.RelatedItemContainerIfc;
import oracle.retail.stores.domain.stock.RelatedItemGroupIfc;
import oracle.retail.stores.domain.stock.RelatedItemIfc;
import oracle.retail.stores.domain.stock.RelatedItemSummaryIfc;
import oracle.retail.stores.domain.stock.StockItemIfc;
import oracle.retail.stores.domain.stock.UnitOfMeasureIfc;
import oracle.retail.stores.domain.stock.UnknownItemIfc;
import oracle.retail.stores.domain.stock.classification.MerchandiseHierarchyGroupIfc;
import oracle.retail.stores.domain.stock.classification.MerchandiseHierarchyLevelIfc;
import oracle.retail.stores.domain.stock.classification.MerchandiseHierarchyLevelKeyIfc;
import oracle.retail.stores.domain.stock.classification.MerchandiseHierarchyTreeIfc;
import oracle.retail.stores.domain.store.DepartmentIfc;
import oracle.retail.stores.domain.store.DistrictIfc;
import oracle.retail.stores.domain.store.RegionIfc;
import oracle.retail.stores.domain.store.StoreIfc;
import oracle.retail.stores.domain.store.WorkstationIfc;
import oracle.retail.stores.domain.supply.SupplyCategoryIfc;
import oracle.retail.stores.domain.supply.SupplyItemIfc;
import oracle.retail.stores.domain.supply.SupplyItemSearchCriteriaIfc;
import oracle.retail.stores.domain.supply.SupplyOrderIfc;
import oracle.retail.stores.domain.supply.SupplyOrderLineItemIfc;
import oracle.retail.stores.domain.tax.ExciseTaxRuleIfc;
import oracle.retail.stores.domain.tax.FixedAmountTaxCalculatorIfc;
import oracle.retail.stores.domain.tax.NewTaxRuleIfc;
import oracle.retail.stores.domain.tax.OverrideItemTaxByAmountRuleIfc;
import oracle.retail.stores.domain.tax.OverrideItemTaxByRateRuleIfc;
import oracle.retail.stores.domain.tax.OverrideItemTaxRuleIfc;
import oracle.retail.stores.domain.tax.OverrideTransactionTaxByAmountRuleIfc;
import oracle.retail.stores.domain.tax.OverrideTransactionTaxByRateRuleIfc;
import oracle.retail.stores.domain.tax.ReturnTaxCalculatorIfc;
import oracle.retail.stores.domain.tax.ReverseItemTaxRuleIfc;
import oracle.retail.stores.domain.tax.ReverseTaxCalculatorIfc;
import oracle.retail.stores.domain.tax.TableTaxRuleIfc;
import oracle.retail.stores.domain.tax.TaxEngineIfc;
import oracle.retail.stores.domain.tax.TaxExemptTaxRuleIfc;
import oracle.retail.stores.domain.tax.TaxHistorySelectionCriteriaIfc;
import oracle.retail.stores.domain.tax.TaxInformationContainerIfc;
import oracle.retail.stores.domain.tax.TaxInformationIfc;
import oracle.retail.stores.domain.tax.TaxRateCalculatorIfc;
import oracle.retail.stores.domain.tax.TaxRuleItemContainerIfc;
import oracle.retail.stores.domain.tax.TaxTableLineItemIfc;
import oracle.retail.stores.domain.tax.ValueAddedTaxRuleIfc;
import oracle.retail.stores.domain.tender.TenderCashIfc;
import oracle.retail.stores.domain.tender.TenderChargeIfc;
import oracle.retail.stores.domain.tender.TenderCheckIfc;
import oracle.retail.stores.domain.tender.TenderCouponIfc;
import oracle.retail.stores.domain.tender.TenderDebitIfc;
import oracle.retail.stores.domain.tender.TenderDescriptorIfc;
import oracle.retail.stores.domain.tender.TenderGiftCardIfc;
import oracle.retail.stores.domain.tender.TenderGiftCertificateIfc;
import oracle.retail.stores.domain.tender.TenderLimitsIfc;
import oracle.retail.stores.domain.tender.TenderMailBankCheckIfc;
import oracle.retail.stores.domain.tender.TenderMoneyOrderIfc;
import oracle.retail.stores.domain.tender.TenderPOSIfc;
import oracle.retail.stores.domain.tender.TenderPayPalIfc;
import oracle.retail.stores.domain.tender.TenderPurchaseOrderIfc;
import oracle.retail.stores.domain.tender.TenderReplacementOrderIfc;
import oracle.retail.stores.domain.tender.TenderSoloIfc;
import oracle.retail.stores.domain.tender.TenderStoreCreditIfc;
import oracle.retail.stores.domain.tender.TenderSwitchIfc;
import oracle.retail.stores.domain.tender.TenderTravelersCheckIfc;
import oracle.retail.stores.domain.transaction.BankDepositTransactionIfc;
import oracle.retail.stores.domain.transaction.BillPayTransactionIfc;
import oracle.retail.stores.domain.transaction.InstantCreditTransactionIfc;
import oracle.retail.stores.domain.transaction.ItemSummaryIfc;
import oracle.retail.stores.domain.transaction.LayawayPaymentTransactionIfc;
import oracle.retail.stores.domain.transaction.LayawayTransactionIfc;
import oracle.retail.stores.domain.transaction.NoSaleTransactionIfc;
import oracle.retail.stores.domain.transaction.OrderTransactionIfc;
import oracle.retail.stores.domain.transaction.PaymentTransactionIfc;
import oracle.retail.stores.domain.transaction.PurgeCriteriaIfc;
import oracle.retail.stores.domain.transaction.PurgeResultIfc;
import oracle.retail.stores.domain.transaction.PurgeTransactionEntryIfc;
import oracle.retail.stores.domain.transaction.RedeemTransactionIfc;
import oracle.retail.stores.domain.transaction.RegisterOpenCloseTransactionIfc;
import oracle.retail.stores.domain.transaction.SaleReturnTransactionIfc;
import oracle.retail.stores.domain.transaction.SearchCriteriaIfc;
import oracle.retail.stores.domain.transaction.StoreOpenCloseTransactionIfc;
import oracle.retail.stores.domain.transaction.TillAdjustmentTransactionIfc;
import oracle.retail.stores.domain.transaction.TillOpenCloseTransactionIfc;
import oracle.retail.stores.domain.transaction.TransactionIDIfc;
import oracle.retail.stores.domain.transaction.TransactionIfc;
import oracle.retail.stores.domain.transaction.TransactionKeyIfc;
import oracle.retail.stores.domain.transaction.TransactionSummaryIfc;
import oracle.retail.stores.domain.transaction.TransactionTaxIfc;
import oracle.retail.stores.domain.transaction.TransactionTotalsIfc;
import oracle.retail.stores.domain.transaction.VoidTransactionIfc;
import oracle.retail.stores.domain.utility.AddressIfc;
import oracle.retail.stores.domain.utility.AlterationIfc;
import oracle.retail.stores.domain.utility.CardIfc;
import oracle.retail.stores.domain.utility.CardTypeIfc;
import oracle.retail.stores.domain.utility.CodeEntryIfc;
import oracle.retail.stores.domain.utility.CodeListIfc;
import oracle.retail.stores.domain.utility.CodeListMapIfc;
import oracle.retail.stores.domain.utility.CodeListSearchCriteriaIfc;
import oracle.retail.stores.domain.utility.CodeSearchCriteriaIfc;
import oracle.retail.stores.domain.utility.CountryIfc;
import oracle.retail.stores.domain.utility.CurrencyRoundingCalculatorIfc;
import oracle.retail.stores.domain.utility.CurrencyRoundingRuleSearchCriteriaIfc;
import oracle.retail.stores.domain.utility.DiscountTypeCodeEntryIfc;
import oracle.retail.stores.domain.utility.EYSDate;
import oracle.retail.stores.domain.utility.EYSStatusIfc;
import oracle.retail.stores.domain.utility.EYSTime;
import oracle.retail.stores.domain.utility.EmailAddressIfc;
import oracle.retail.stores.domain.utility.GiftCardIfc;
import oracle.retail.stores.domain.utility.HouseCardIfc;
import oracle.retail.stores.domain.utility.InstantCreditIfc;
import oracle.retail.stores.domain.utility.PersonIfc;
import oracle.retail.stores.domain.utility.PersonNameIfc;
import oracle.retail.stores.domain.utility.PhoneIfc;
import oracle.retail.stores.domain.utility.RelatedItemTransactionInfoIfc;
import oracle.retail.stores.domain.utility.RuleIfc;
import oracle.retail.stores.domain.utility.SecurityOverrideIfc;
import oracle.retail.stores.domain.utility.StateIfc;
import oracle.retail.stores.domain.utility.StoreCreditIfc;
import oracle.retail.stores.domain.utility.StoreSearchCriteriaIfc;
import oracle.retail.stores.domain.utility.calendar.BusinessCalendarIfc;
import oracle.retail.stores.domain.utility.calendar.CalendarLevelFactoryIfc;
import oracle.retail.stores.domain.utility.calendar.CalendarLevelIfc;
import oracle.retail.stores.domain.utility.calendar.CalendarLevelKeyIfc;
import oracle.retail.stores.domain.utility.calendar.CalendarPeriodIfc;
import oracle.retail.stores.domain.utility.calendar.CalendarPeriodKeyIfc;

public abstract interface DomainObjectFactoryIfc extends DomainObjectsFactoryIfc {
	public static final String revisionNumber = "$Revision: /rgbustores_13.4x_generic_branch/19 $";

	public abstract ActiveJobIfc getActiveJobInstance();

	public abstract ActiveJobIfc getActiveJobInstance(Locale paramLocale);

	public abstract AddressIfc getAddressInstance();

	public abstract AddressIfc getAddressInstance(Locale paramLocale);

	public abstract AdvancedPricingRuleIfc getAdvancedPricingRuleInstance();

	public abstract AdvancedPricingRuleIfc getAdvancedPricingRuleInstance(Locale paramLocale);

	public abstract AdvancedPricingRuleSearchCriteriaIfc getAdvancedPricingRuleSearchCriteriaInstance();

	public abstract AdvancedPricingRuleSearchCriteriaIfc getAdvancedPricingRuleSearchCriteriaInstance(
			Locale paramLocale);

	public abstract CalendarLevelIfc getAggregateCalendarLevelInstance();

	public abstract CalendarLevelIfc getAggregateCalendarLevelInstance(Locale paramLocale);

	public abstract AlertEntryIfc getAlertEntryInstance();

	public abstract AlertEntryIfc getAlertEntryInstance(Locale paramLocale);

	public abstract AlertListIfc getAlertListInstance();

	public abstract AlertListIfc getAlertListInstance(Locale paramLocale);

	public abstract AlterationIfc getAlterationInstance();

	public abstract AlterationIfc getAlterationInstance(Locale paramLocale);

	public abstract AlterationPLUItemIfc getAlterationPLUItemInstance();

	public abstract AlterationPLUItemIfc getAlterationPLUItemInstance(Locale paramLocale);

	public abstract AssociateProductivityIfc getAssociateProductivityInstance();

	public abstract AssociateProductivityIfc getAssociateProductivityInstance(Locale paramLocale);

	public abstract AuditEntryIfc getAuditEntryInstance();

	public abstract AuditEntryIfc getAuditEntryInstance(Locale paramLocale);

	public abstract AuthorizeCallReferralRequestIfc getAuthorizeCallReferralRequestInstance();

	public abstract AuthorizeInstantCreditRequestIfc getAuthorizeInstantCreditRequestInstance();

	public abstract AuthorizeInstantCreditResponseIfc getAuthorizeInstantCreditResponseInstance();

	public abstract AuthorizeTransferRequestIfc getAuthorizeTransferRequestInstance();

	public abstract AuthorizeTransferResponseIfc getAuthorizeTransferResponseInstance();

	public abstract BankDepositTransactionIfc getBankDepositTransactionInstance();

	public abstract BankDepositTransactionIfc getBankDepositTransactionInstance(Locale paramLocale);

	public abstract BestDealGroupIfc getBestDealGroupInstance();

	public abstract BestDealGroupIfc getBestDealGroupInstance(Locale paramLocale);

	public abstract BillPayIfc getBillPayInstance();

	public abstract BillPayTransactionIfc getBillPayTransactionInstance();

	public abstract BusinessCalendarIfc getBusinessCalendarInstance();

	public abstract BusinessCalendarIfc getBusinessCalendarInstance(Locale paramLocale);

	public abstract CalendarLevelFactoryIfc getCalendarLevelFactoryInstance();

	public abstract CalendarLevelFactoryIfc getCalendarLevelFactoryInstance(Locale paramLocale);

	public abstract CalendarLevelKeyIfc getCalendarLevelKeyInstance();

	public abstract CalendarLevelKeyIfc getCalendarLevelKeyInstance(Locale paramLocale);

	public abstract CalendarPeriodIfc getCalendarPeriodInstance();

	public abstract CalendarPeriodIfc getCalendarPeriodInstance(Locale paramLocale);

	public abstract CalendarPeriodKeyIfc getCalendarPeriodKeyInstance();

	public abstract CalendarPeriodKeyIfc getCalendarPeriodKeyInstance(Locale paramLocale);

	public abstract CaptureCustomerIfc getCaptureCustomerInstance();

	public abstract CaptureCustomerIfc getCaptureCustomerInstance(Locale paramLocale);

	public abstract CardIfc getCardInstance();

	public abstract CardIfc getCardInstance(Locale paramLocale);

	public abstract CardTokenRequestIfc getCardTokenRequestInstance();

	public abstract CardTokenResponseIfc getCardTokenResponseInstance();

	public abstract CardTypeIfc getCardTypeInstance();

	public abstract CardTypeIfc getCardTypeInstance(Locale paramLocale);

	public abstract CodeEntryIfc getCodeEntryInstance();

	public abstract CodeEntryIfc getCodeEntryInstance(Locale paramLocale);

	public abstract CodeListIfc getCodeListInstance();

	public abstract CodeListIfc getCodeListInstance(Locale paramLocale);

	public abstract CodeListMapIfc getCodeListMapInstance();

	public abstract CodeListMapIfc getCodeListMapInstance(Locale paramLocale);

	public abstract CodeListSearchCriteriaIfc getCodeListSearchCriteriaInstance();

	public abstract CodeSearchCriteriaIfc getCodeSearchCriteriaInstance();

	public abstract CountryIfc getCountryInstance();

	public abstract CountryIfc getCountryInstance(Locale paramLocale);

	public abstract CurrencyIfc getCurrencyInstance(CurrencyTypeIfc paramCurrencyTypeIfc);

	public abstract CurrencyTypeIfc getCurrencyTypeInstance();

	public abstract CurrencyTypeIfc getCurrencyTypeInstance(Locale paramLocale);

	public abstract CurrencyTypeListIfc getCurrencyTypeListInstance();

	public abstract CurrencyTypeListIfc getCurrencyTypeListInstance(Locale paramLocale);

	public abstract CustomerDiscountByPercentageIfc getCustomerDiscountByPercentageInstance();

	public abstract CustomerDiscountByPercentageIfc getCustomerDiscountByPercentageInstance(Locale paramLocale);

	public abstract CustomerEventBabyIfc getCustomerEventBabyInstance();

	public abstract CustomerEventBabyIfc getCustomerEventBabyInstance(Locale paramLocale);

	public abstract CustomerEventSpecialIfc getCustomerEventSpecialInstance();

	public abstract CustomerEventSpecialIfc getCustomerEventSpecialInstance(Locale paramLocale);

	public abstract CustomerEventWeddingIfc getCustomerEventWeddingInstance();

	public abstract CustomerEventWeddingIfc getCustomerEventWeddingInstance(Locale paramLocale);

	public abstract CustomerGroupIfc getCustomerGroupInstance();

	public abstract CustomerGroupIfc getCustomerGroupInstance(Locale paramLocale);

	public abstract CustomerInfoIfc getCustomerInfoInstance();

	public abstract CustomerInfoIfc getCustomerInfoInstance(Locale paramLocale);

	public abstract CustomerIfc getCustomerInstance();

	public abstract CustomerIfc getCustomerInstance(Locale paramLocale);

	public abstract CustomerInteractionRequestIfc getCustomerInteractionRequestInstance();

	public abstract CustomerInteractionRequestIfc getCustomerInteractionRequestInstance(
			CustomerInteractionRequestIfc.RequestSubType paramRequestSubType);

	public abstract CustomerInteractionResponseIfc getCustomerInteractionResponseInstance();

	public abstract CustomerInteractionResponseIfc getCustomerInteractionResponseInstance(
			PaymentServiceResponseIfc.ResponseCode paramResponseCode);

	public abstract CustomScheduleDocumentIfc getCustomScheduleDocumentInstance();

	public abstract CustomScheduleDocumentIfc getCustomScheduleDocumentInstance(Locale paramLocale);

	public abstract DailyScheduleDocumentIfc getDailyScheduleDocumentInstance();

	public abstract DailyScheduleDocumentIfc getDailyScheduleDocumentInstance(Locale paramLocale);

	public abstract DataReplicationBatchGeneratorIfc getDataReplicationBatchGeneratorInstance();

	public abstract DataReplicationBatchGeneratorIfc getDataReplicationBatchGeneratorInstance(Locale paramLocale);

	public abstract DataReplicationCustomerEntryIfc getDataReplicationCustomerEntry();

	public abstract CalendarLevelIfc getDayCalendarLevelInstance();

	public abstract CalendarLevelIfc getDayCalendarLevelInstance(Locale paramLocale);

	public abstract DepartmentActivityIfc getDepartmentActivityInstance();

	public abstract DepartmentActivityIfc getDepartmentActivityInstance(Locale paramLocale);

	public abstract DepartmentIfc getDepartmentInstance();

	public abstract DepartmentIfc getDepartmentInstance(Locale paramLocale);

	public abstract DiscountCalculationIfc getDiscountCalculationInstance();

	public abstract DiscountCalculationIfc getDiscountCalculationInstance(Locale paramLocale);

	public abstract DiscountListEntryIfc getDiscountListEntryInstance();

	public abstract DiscountListEntryIfc getDiscountListEntryInstance(Locale paramLocale);

	public abstract DiscountListIfc getDiscountListInstance();

	public abstract DiscountListIfc getDiscountListInstance(Locale paramLocale);

	public abstract DiscountRuleIfc getDiscountRuleInstance();

	public abstract DiscountRuleIfc getDiscountRuleInstance(Locale paramLocale);

	public abstract DiscountTypeCodeEntryIfc getDiscountTypeCodeEntryInstance();

	public abstract DiscountTypeCodeEntryIfc getDiscountTypeCodeEntryInstance(Locale paramLocale);

	public abstract DistrictIfc getDistrictInstance();

	public abstract DistrictIfc getDistrictInstance(Locale paramLocale);

	public abstract DrawerIfc getDrawerInstance();

	public abstract DrawerIfc getDrawerInstance(Locale paramLocale);

	public abstract EmailAddressIfc getEmailAddressInstance();

	public abstract EmailAddressIfc getEmailAddressInstance(Locale paramLocale);

	public abstract EMessageIfc getEMessageInstance();

	public abstract EMessageIfc getEMessageInstance(Locale paramLocale);

	public abstract EmployeeActivityIfc getEmployeeActivityInstance();

	public abstract EmployeeActivityIfc getEmployeeActivityInstance(Locale paramLocale);

	public abstract EmployeeClockEntryIfc getEmployeeClockEntryInstance();

	public abstract EmployeeClockEntryIfc getEmployeeClockEntryInstance(Locale paramLocale);

	public abstract EmployeeComplianceIfc getEmployeeCompliance();

	public abstract EmployeeComplianceIfc getEmployeeCompliance(Locale paramLocale);

	public abstract EmployeeIfc getEmployeeInstance();

	public abstract EmployeeIfc getEmployeeInstance(Locale paramLocale);

	public abstract ExciseTaxRuleIfc getExciseTaxRuleInstance();

	public abstract ExciseTaxRuleIfc getExciseTaxRuleInstance(Locale paramLocale);

	public abstract TableTaxRuleIfc getTableTaxRuleInstance();

	public abstract TableTaxRuleIfc getTableTaxRuleInstance(Locale paramLocale);

	public abstract ExternalOrderSaleItemIfc getExternalOrderSaleItemInstance();

	public abstract ExternalOrderSendPackageItemIfc getExternalOrderSendPackageItemInstance();

	public abstract EYSDate getEYSDateInstance();

	public abstract EYSDate getEYSDateInstance(Locale paramLocale);

	public abstract EYSStatusIfc getEYSStatusInstance();

	public abstract EYSStatusIfc getEYSStatusInstance(Locale paramLocale);

	public abstract EYSTime getEYSTimeInstance();

	public abstract EYSTime getEYSTimeInstance(Locale paramLocale);

	public abstract String getFactoryID();

	public abstract FinancialCountIfc getFinancialCountInstance();

	public abstract FinancialCountIfc getFinancialCountInstance(Locale paramLocale);

	public abstract FinancialCountTenderItemIfc getFinancialCountTenderItemInstance();

	public abstract FinancialCountTenderItemIfc getFinancialCountTenderItemInstance(Locale paramLocale);

	public abstract FinancialTotalsIfc getFinancialTotalsInstance();

	public abstract FinancialTotalsIfc getFinancialTotalsInstance(Locale paramLocale);

	public abstract FixedAmountTaxCalculatorIfc getFixedAmountTaxCalculatorInstance();

	public abstract FixedAmountTaxCalculatorIfc getFixedAmountTaxCalculatorInstance(Locale paramLocale);

	public abstract GiftCardIfc getGiftCardInstance();

	public abstract GiftCardIfc getGiftCardInstance(Locale paramLocale);

	public abstract GiftCardPLUItemIfc getGiftCardPLUItemInstance();

	public abstract GiftCardPLUItemIfc getGiftCardPLUItemInstance(Locale paramLocale);

	public abstract GiftCertificateItemIfc getGiftCertificateItemInstance();

	public abstract GiftCertificateItemIfc getGiftCertificateItemInstance(Locale paramLocale);

	public abstract GiftRegistryIfc getGiftRegistryInstance();

	public abstract GiftRegistryIfc getGiftRegistryInstance(Locale paramLocale);

	public abstract HardTotalsBuilderIfc getHardTotalsBuilderInstance();

	public abstract HardTotalsBuilderIfc getHardTotalsBuilderInstance(Locale paramLocale);

	public abstract HardTotalsIfc getHardTotalsInstance();

	public abstract HardTotalsIfc getHardTotalsInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getHourCalendarLevelInstance();

	public abstract CalendarLevelIfc getHourCalendarLevelInstance(Locale paramLocale);

	public abstract HouseCardIfc getHouseCardInstance();

	public abstract HouseCardIfc getHouseCardInstance(Locale paramLocale);

	public abstract InstantCreditIfc getInstantCreditInstance();

	public abstract InstantCreditIfc getInstantCreditInstance(Locale paramLocale);

	public abstract InstantCreditTransactionIfc getInstantCreditTransactionInstance();

	public abstract InstantCreditTransactionIfc getInstantCreditTransactionInstance(Locale paramLocale);

	public abstract IRSCustomerIfc getIRSCustomerInstance();

	public abstract ItemClassificationIfc getItemClassificationInstance();

	public abstract ItemClassificationIfc getItemClassificationInstance(Locale paramLocale);

	public abstract ItemColorIfc getItemColorInstance();

	public abstract ItemColorIfc getItemColorInstance(Locale paramLocale);

	public abstract ItemContainerProxyIfc getItemContainerProxyInstance();

	public abstract ItemContainerProxyIfc getItemContainerProxyInstance(Locale paramLocale);

	public abstract ItemDiscountByAmountIfc getItemDiscountByAmountInstance();

	public abstract ItemDiscountByAmountIfc getItemDiscountByAmountInstance(Locale paramLocale);

	public abstract ItemDiscountByAmountIfc getItemDiscountByFixedPriceStrategyInstance();

	public abstract ItemDiscountByAmountIfc getItemDiscountByFixedPriceStrategyInstance(Locale paramLocale);

	public abstract ItemDiscountByPercentageIfc getItemDiscountByPercentageInstance();

	public abstract ItemDiscountByPercentageIfc getItemDiscountByPercentageInstance(Locale paramLocale);

	public abstract ItemImageIfc getItemImageInstance();

	public abstract ItemInfoIfc getItemInfoInstance();

	public abstract ItemInfoIfc getItemInfoInstance(Locale paramLocale);

	public abstract ItemInquirySearchCriteriaIfc getItemInquirySearchCriteriaInstance();

	public abstract ItemInquirySearchCriteriaIfc getItemInquirySearchCriteriaInstance(Locale paramLocale);

	public abstract ItemIfc getItemInstance();

	public abstract ItemIfc getItemInstance(Locale paramLocale);

	public abstract ItemKitIfc getItemKitInstance();

	public abstract ItemKitIfc getItemKitInstance(Locale paramLocale);

	public abstract ItemMaintenanceEventIfc getItemMaintenanceEventInstance();

	public abstract ItemMaintenanceEventIfc getItemMaintenanceEventInstance(Locale paramLocale);

	public abstract ItemPriceIfc getItemPriceInstance();

	public abstract ItemPriceIfc getItemPriceInstance(Locale paramLocale);

	public abstract ItemPriceMaintenanceEventIfc getItemPriceMaintenanceEventInstance();

	public abstract ItemPriceMaintenanceEventIfc getItemPriceMaintenanceEventInstance(Locale paramLocale);

	public abstract ItemSizeIfc getItemSizeInstance();

	public abstract ItemSizeIfc getItemSizeInstance(Locale paramLocale);

	public abstract ItemStyleIfc getItemStyleInstance();

	public abstract ItemStyleIfc getItemStyleInstance(Locale paramLocale);

	public abstract ItemSummaryIfc getItemSummaryInstance();

	public abstract ItemSummaryIfc getItemSummaryInstance(Locale paramLocale);

	public abstract ItemTaxIfc getItemTaxInstance();

	public abstract ItemTaxIfc getItemTaxInstance(Locale paramLocale);

	public abstract ItemTransactionDiscountAuditIfc getItemTransactionDiscountAuditInstance();

	public abstract CurrencyRoundingCalculatorIfc getCurrencyRoundingCalculatorInstance();

	public abstract CurrencyRoundingRuleSearchCriteriaIfc getCurrencyRoundingRuleSearchCriteriaInstance();

	public abstract ItemTransactionDiscountAuditIfc getItemTransactionDiscountAuditInstance(Locale paramLocale);

	public abstract ItemTypeIfc getItemTypeInstance();

	public abstract JobControlEventMessageIfc getJobControlEventMessageInstance();

	public abstract JobControlEventMessageIfc getJobControlEventMessageInstance(Locale paramLocale);

	public abstract KeyManagementResponseIfc getKeyManagementResponseInstance();

	public abstract KitComponentIfc getKitComponentInstance();

	public abstract KitComponentIfc getKitComponentInstance(Locale paramLocale);

	public abstract KitComponentLineItemIfc getKitComponentLineItemInstance();

	public abstract KitComponentLineItemIfc getKitComponentLineItemInstance(Locale paramLocale);

	public abstract KitHeaderLineItemIfc getKitHeaderLineItemInstance();

	public abstract KitHeaderLineItemIfc getKitHeaderLineItemInstance(Locale paramLocale);

	public abstract LayawayIfc getLayawayInstance();

	public abstract LayawayIfc getLayawayInstance(Locale paramLocale);

	public abstract LayawayPaymentTransactionIfc getLayawayPaymentTransactionInstance();

	public abstract LayawayPaymentTransactionIfc getLayawayPaymentTransactionInstance(Locale paramLocale);

	public abstract LayawaySummaryEntryIfc getLayawaySummaryEntryInstance();

	public abstract LayawaySummaryEntryIfc getLayawaySummaryEntryInstance(Locale paramLocale);

	public abstract LayawayTransactionIfc getLayawayTransactionInstance();

	public abstract LayawayTransactionIfc getLayawayTransactionInstance(Locale paramLocale);

	public abstract LegalDocumentIfc getLegalDocumentInstance();

	public abstract LocalizedCodeIfc getLocalizedCode();

	public abstract LocalizedCodeIfc getLocalizedCode(String paramString);

	public abstract LocalizedTextIfc getLocalizedText();

	public abstract LocalizedTextIfc getLocalizedText(Locale paramLocale);

	public abstract MaintenanceEventIfc getMaintenanceEventInstance();

	public abstract MaintenanceEventIfc getMaintenanceEventInstance(Locale paramLocale);

	public abstract ManufacturerIfc getManufacturerInstance();

	public abstract ManufacturerIfc getManufacturerInstance(Locale paramLocale);

	public abstract MerchandiseClassificationIfc getMerchandiseClassificationInstance();

	public abstract MerchandiseClassificationIfc getMerchandiseClassificationInstance(Locale paramLocale);

	public abstract MerchandiseHierarchyGroupIfc getMerchandiseHierarchyGroupInstance();

	public abstract MerchandiseHierarchyGroupIfc getMerchandiseHierarchyGroupInstance(Locale paramLocale);

	public abstract MerchandiseHierarchyLevelIfc getMerchandiseHierarchyLevelInstance();

	public abstract MerchandiseHierarchyLevelIfc getMerchandiseHierarchyLevelInstance(Locale paramLocale);

	public abstract MerchandiseHierarchyLevelKeyIfc getMerchandiseHierarchyLevelKeyInstance();

	public abstract MerchandiseHierarchyLevelKeyIfc getMerchandiseHierarchyLevelKeyInstance(Locale paramLocale);

	public abstract MerchandiseHierarchyTreeIfc getMerchandiseHierarchyTreeInstance();

	public abstract MerchandiseHierarchyTreeIfc getMerchandiseHierarchyTreeInstance(Locale paramLocale);

	public abstract MerchandisePreferenceIfc getMerchandisePreferenceInstance();

	public abstract MerchandisePreferenceIfc getMerchandisePreferenceInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getMinuteCalendarLevelInstance();

	public abstract CalendarLevelIfc getMinuteCalendarLevelInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getMonthDayCalendarLevelInstance();

	public abstract CalendarLevelIfc getMonthDayCalendarLevelInstance(Locale paramLocale);

	public abstract MonthlyByDayScheduleDocumentIfc getMonthlyByDayScheduleDocumentInstance();

	public abstract MonthlyByDayScheduleDocumentIfc getMonthlyByDayScheduleDocumentInstance(Locale paramLocale);

	public abstract NoSaleTransactionIfc getNoSaleTransactionInstance();

	public abstract NoSaleTransactionIfc getNoSaleTransactionInstance(Locale paramLocale);

	public abstract NotificationRecipientsIfc getNotificationRecipientsInstance();

	public abstract NotificationRecipientsIfc getNotificationRecipientsInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getNthWeekDayCalendarLevelInstance();

	public abstract CalendarLevelIfc getNthWeekDayCalendarLevelInstance(Locale paramLocale);

	public abstract OrderDeliveryDetailIfc getOrderDeliveryDetailInstance();

	public abstract OrderIfc getOrderInstance();

	public abstract OrderIfc getOrderInstance(Locale paramLocale);

	public abstract OrderItemStatusIfc getOrderItemStatusInstance();

	public abstract OrderItemStatusIfc getOrderItemStatusInstance(Locale paramLocale);

	public abstract OrderLineItemIfc getOrderLineItemInstance();

	public abstract OrderLineItemIfc getOrderLineItemInstance(Locale paramLocale);

	public abstract OrderRecipientIfc getOrderRecipientInstance();

	public abstract OrderStatusIfc getOrderStatusInstance();

	public abstract OrderStatusIfc getOrderStatusInstance(Locale paramLocale);

	public abstract OrderSummaryEntryIfc getOrderSummaryEntryInstance();

	public abstract OrderSummaryEntryIfc getOrderSummaryEntryInstance(Locale paramLocale);

	public abstract OrderTransactionIfc getOrderTransactionInstance();

	public abstract OrderTransactionIfc getOrderTransactionInstance(Locale paramLocale);

	public abstract OverrideItemTaxByAmountRuleIfc getOverrideItemTaxByAmountRuleInstance();

	public abstract OverrideItemTaxByAmountRuleIfc getOverrideItemTaxByAmountRuleInstance(Locale paramLocale);

	public abstract OverrideItemTaxByRateRuleIfc getOverrideItemTaxByRateRuleInstance();

	public abstract OverrideItemTaxByRateRuleIfc getOverrideItemTaxByRateRuleInstance(Locale paramLocale);

	public abstract OverrideItemTaxRuleIfc getOverrideItemTaxToggleOffRuleInstance();

	public abstract OverrideItemTaxRuleIfc getOverrideItemTaxToggleOffRuleInstance(Locale paramLocale);

	public abstract OverrideTransactionTaxByAmountRuleIfc getOverrideTransactionTaxByAmountRuleInstance();

	public abstract OverrideTransactionTaxByAmountRuleIfc getOverrideTransactionTaxByAmountRuleInstance(
			Locale paramLocale);

	public abstract OverrideTransactionTaxByRateRuleIfc getOverrideTransactionTaxByRateRuleInstance();

	public abstract OverrideTransactionTaxByRateRuleIfc getOverrideTransactionTaxByRateRuleInstance(Locale paramLocale);

	public abstract PasswordPolicyEvaluatorIfc getPasswordPolicyEvaluatorInstance();

	public abstract PasswordPolicyEvaluatorIfc getPasswordPolicyEvaluatorInstance(Locale paramLocale);

	public abstract PaymentHistoryInfoIfc getPaymentHistoryInfoInstance();

	public abstract PaymentIfc getPaymentInstance();

	public abstract PaymentIfc getPaymentInstance(Locale paramLocale);

	public abstract StatusResponseIfc getPaymentServiceStatusResponseInstance();

	public abstract PaymentTransactionIfc getPaymentTransactionInstance();

	public abstract PaymentTransactionIfc getPaymentTransactionInstance(Locale paramLocale);

	public abstract PersonIfc getPersonInstance();

	public abstract PersonIfc getPersonInstance(Locale paramLocale);

	public abstract PersonNameIfc getPersonNameInstance();

	public abstract PersonNameIfc getPersonNameInstance(Locale paramLocale);

	public abstract PhoneIfc getPhoneInstance();

	public abstract PhoneIfc getPhoneInstance(Locale paramLocale);

	public abstract PinCommEncryptionUtilityIfc getPinCommEncryptionUtilityInstance();

	public abstract PLUItemIfc getPLUItemInstance();

	public abstract PLUItemIfc getPLUItemInstance(Locale paramLocale);

	public abstract POSLogBatchGeneratorIfc getPOSLogBatchGeneratorInstance();

	public abstract POSLogBatchGeneratorIfc getPOSLogBatchGeneratorInstance(Locale paramLocale);

	public abstract POSLogTransactionEntryIfc getPOSLogTransactionEntryInstance();

	public abstract POSLogTransactionEntryIfc getPOSLogTransactionEntryInstance(Locale paramLocale);

	public abstract PriceAdjustmentLineItemIfc getPriceAdjustmentLineItemInstance();

	public abstract PriceAdjustmentLineItemIfc getPriceAdjustmentLineItemInstance(Locale paramLocale);

	public abstract PriceChangeIfc getPriceChangeInstance();

	public abstract PriceChangeIfc getPriceChangeInstance(Locale paramLocale);

	public abstract PriceDerivationRuleMaintenanceEventIfc getPriceDerivationRuleMaintenanceEventInstance();

	public abstract PriceDerivationRuleMaintenanceEventIfc getPriceDerivationRuleMaintenanceEventInstance(
			Locale paramLocale);

	public abstract ProductGroupIfc getProductGroupInstance();

	public abstract ProductGroupIfc getProductGroupInstance(Locale paramLocale);

	public abstract ProductIfc getProductInstance();

	public abstract ProductIfc getProductInstance(Locale paramLocale);

	public abstract PromotionLineItemIfc getPromotionLineItemInstance();

	public abstract PurchaseOrderIfc getPurchaseOrderInstance();

	public abstract PurchaseOrderIfc getPurchaseOrderInstance(Locale paramLocale);

	public abstract PurchaseOrderLineItemIfc getPurchaseOrderLineItemInstance();

	public abstract PurchaseOrderLineItemIfc getPurchaseOrderLineItemInstance(Locale paramLocale);

	public abstract PurgeCriteriaIfc getPurgeCriteriaInstance();

	public abstract PurgeCriteriaIfc getPurgeCriteriaInstance(Locale paramLocale);

	public abstract PurgeResultIfc getPurgeResultInstance();

	public abstract PurgeResultIfc getPurgeResultInstance(Locale paramLocale);

	public abstract PurgeTransactionEntryIfc getPurgeTransactionEntryInstance();

	public abstract PurgeTransactionEntryIfc getPurgeTransactionEntryInstance(Locale paramLocale);

	public abstract ReconcilableCountIfc getReconcilableCountInstance();

	public abstract ReconcilableCountIfc getReconcilableCountInstance(Locale paramLocale);

	public abstract RedeemTransactionIfc getRedeemTransactionInstance();

	public abstract RedeemTransactionIfc getRedeemTransactionInstance(Locale paramLocale);

	public abstract RegionIfc getRegionInstance();

	public abstract RegionIfc getRegionInstance(Locale paramLocale);

	public abstract RegisterIfc getRegisterInstance();

	public abstract RegisterIfc getRegisterInstance(Locale paramLocale);

	public abstract RegisterOpenCloseTransactionIfc getRegisterOpenCloseTransactionInstance();

	public abstract RegisterOpenCloseTransactionIfc getRegisterOpenCloseTransactionInstance(Locale paramLocale);

	public abstract RegistryIDIfc getRegistryIDInstance();

	public abstract RegistryIDIfc getRegistryIDInstance(Locale paramLocale);

	public abstract RelatedItemContainerIfc getRelatedItemContainerInstance();

	public abstract RelatedItemGroupIfc getRelatedItemGroupInstance();

	public abstract RelatedItemIfc getRelatedItemInstance();

	public abstract RelatedItemSummaryIfc getRelatedItemSummaryInstance();

	public abstract RelatedItemTransactionInfoIfc getRelatedItemTransactionInfoInstance();

	public abstract ReportingPeriodIfc getReportingPeriodInstance();

	public abstract ReportingPeriodIfc getReportingPeriodInstance(Locale paramLocale);

	public abstract ReturnItemIfc getReturnItemInstance();

	public abstract ReturnItemIfc getReturnItemInstance(Locale paramLocale);

	public abstract ReverseItemTaxRuleIfc getReturnItemTaxRuleInstance();

	public abstract ReverseItemTaxRuleIfc getReturnItemTaxRuleInstance(Locale paramLocale);

	public abstract ReturnItemTransactionDiscountAuditIfc getReturnItemTransactionDiscountAuditInstance();

	public abstract ReturnItemTransactionDiscountAuditIfc getReturnItemTransactionDiscountAuditInstance(
			Locale paramLocale);

	public abstract ReturnResponseLineItemIfc getReturnResponseLineItemInstance();

	public abstract ReturnResponseLineItemIfc getReturnResponseLineItemInstance(Locale paramLocale);

	public abstract ReturnTaxCalculatorIfc getReturnTaxCalculatorInstance();

	public abstract ReturnTaxCalculatorIfc getReturnTaxCalculatorInstance(Locale paramLocale);

	public abstract ReturnTenderDataContainer getReturnTenderDataContainerInstance();

	public abstract ReturnTenderDataContainer getReturnTenderDataContainerInstance(Locale paramLocale);

	public abstract ReturnTenderDataElement getReturnTenderDataElementInstance();

	public abstract ReturnTenderDataElement getReturnTenderDataElementInstance(Locale paramLocale);

	public abstract ReversalRequestIfc getReversalRequestInstance();

	public abstract ReverseTaxCalculatorIfc getReverseTaxCalculatorInstance();

	public abstract ReverseTaxCalculatorIfc getReverseTaxCalculatorInstance(Locale paramLocale);

	public abstract RoleFunctionGroupIfc getRoleFunctionGroupInstance();

	public abstract RoleFunctionGroupIfc getRoleFunctionGroupInstance(Locale paramLocale);

	public abstract RoleFunctionIfc getRoleFunctionInstance();

	public abstract RoleFunctionIfc getRoleFunctionInstance(Locale paramLocale);

	public abstract RoleIfc getRoleInstance();

	public abstract RoleIfc getRoleInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getRootCalendarLevelInstance();

	public abstract CalendarLevelIfc getRootCalendarLevelInstance(Locale paramLocale);

	public abstract RTLogExportBatchGeneratorIfc getRTLogBatchGeneratorInstance();

	public abstract RuleIfc getRuleBinRangeInstance();

	public abstract RuleIfc getRuleBinRangeInstance(Locale paramLocale);

	public abstract RuleIfc getRuleLengthInstance();

	public abstract RuleIfc getRuleLengthInstance(Locale paramLocale);

	public abstract RuleIfc getRuleMaskInstance();

	public abstract RuleIfc getRuleMaskInstance(Locale paramLocale);

	public abstract SaleReturnLineItemIfc getSaleReturnLineItemInstance();

	public abstract SaleReturnLineItemIfc getSaleReturnLineItemInstance(Locale paramLocale);

	public abstract SaleReturnTransactionIfc getSaleReturnTransactionInstance();

	public abstract SaleReturnTransactionIfc getSaleReturnTransactionInstance(Locale paramLocale);

	public abstract ScheduledJobIfc getScheduledJobInstance();

	public abstract ScheduledJobIfc getScheduledJobInstance(Locale paramLocale);

	public abstract SearchCriteriaIfc getSearchCriteriaInstance();

	public abstract SearchCriteriaIfc getSearchCriteriaInstance(Locale paramLocale);

	public abstract SecurityOverrideIfc getSecurityOverrideInstance();

	public abstract SecurityOverrideIfc getSecurityOverrideInstance(Locale paramLocale);

	public abstract SendPackageLineItemIfc getSendPackageLineItemInstance();

	public abstract SendPackageLineItemIfc getSendPackageLineItemInstance(Locale paramLocale);

	public abstract ShippingMethodIfc getShippingMethodInstance();

	public abstract ShippingMethodIfc getShippingMethodInstance(Locale paramLocale);

	public abstract ShippingMethodSearchCriteriaIfc getShippingMethodSearchCriteria();

	public abstract SignatureCaptureRequestIfc getSignatureCaptureRequestInstance();

	public abstract SignatureCaptureResponseIfc getSignatureCaptureResponseInstance();

	public abstract DiscountListIfc getSourceCriteriaInstance();

	public abstract DiscountListIfc getSourceCriteriaInstance(Locale paramLocale);

	public abstract StateIfc getStateInstance();

	public abstract StateIfc getStateInstance(Locale paramLocale);

	public abstract StockItemIfc getStockItemInstance();

	public abstract StockItemIfc getStockItemInstance(Locale paramLocale);

	public abstract StoreCreditIfc getStoreCreditInstance();

	public abstract StoreCreditIfc getStoreCreditInstance(Locale paramLocale);

	public abstract StoreIfc getStoreInstance();

	public abstract StoreIfc getStoreInstance(Locale paramLocale);

	public abstract StoreOpenCloseTransactionIfc getStoreOpenCloseTransactionInstance();

	public abstract StoreOpenCloseTransactionIfc getStoreOpenCloseTransactionInstance(Locale paramLocale);

	public abstract StoreSafeIfc getStoreSafeInstance();

	public abstract StoreSafeIfc getStoreSafeInstance(Locale paramLocale);

	public abstract StoreSearchCriteriaIfc getStoreSearchCriteriaInstance();

	public abstract StoreStatusAndTotalsIfc getStoreStatusAndTotalsInstance();

	public abstract StoreStatusAndTotalsIfc getStoreStatusAndTotalsInstance(Locale paramLocale);

	public abstract StoreStatusIfc getStoreStatusInstance();

	public abstract StoreStatusIfc getStoreStatusInstance(Locale paramLocale);

	public abstract SuperGroupIfc getSuperGroupInstance();

	public abstract SuperGroupIfc getSuperGroupInstance(Locale paramLocale);

	public abstract SupplierIfc getSupplierInstance();

	public abstract SupplierIfc getSupplierInstance(Locale paramLocale);

	public abstract SupplyCategoryIfc getSupplyCategoryInstance();

	public abstract SupplyCategoryIfc getSupplyCategoryInstance(Locale paramLocale);

	public abstract SupplyItemIfc getSupplyItemInstance();

	public abstract SupplyItemIfc getSupplyItemInstance(Locale paramLocale);

	public abstract SupplyItemSearchCriteriaIfc getSupplyItemSearchCriteriaInstance();

	public abstract SupplyItemSearchCriteriaIfc getSupplyItemSearchCriteriaInstance(Locale paramLocale);

	public abstract SupplyOrderIfc getSupplyOrderInstance();

	public abstract SupplyOrderIfc getSupplyOrderInstance(Locale paramLocale);

	public abstract SupplyOrderLineItemIfc getSupplyOrderLineItemInstance();

	public abstract SupplyOrderLineItemIfc getSupplyOrderLineItemInstance(Locale paramLocale);

	public abstract DiscountListIfc getTargetCriteriaInstance();

	public abstract DiscountListIfc getTargetCriteriaInstance(Locale paramLocale);

	public abstract TaskInfoIfc getTaskInfoInstance();

	public abstract TaskInfoIfc getTaskInfoInstance(Locale paramLocale);

	public abstract NewTaxRuleIfc getTaxByLineRuleInstance();

	public abstract NewTaxRuleIfc getTaxByLineRuleInstance(Locale paramLocale);

	public abstract TaxEngineIfc getTaxEngineInstance();

	public abstract TaxEngineIfc getTaxEngineInstance(Locale paramLocale);

	public abstract TaxExemptTaxRuleIfc getTaxExemptTaxRuleInstance();

	public abstract TaxExemptTaxRuleIfc getTaxExemptTaxRuleInstance(Locale paramLocale);

	public abstract TaxHistorySelectionCriteriaIfc getTaxHistorySelectionCriteriaInstance();

	public abstract TaxInformationContainerIfc getTaxInformationContainerInstance();

	public abstract TaxInformationContainerIfc getTaxInformationContainerInstance(Locale paramLocale);

	public abstract TaxInformationIfc getTaxInformationInstance();

	public abstract TaxInformationIfc getTaxInformationInstance(Locale paramLocale);

	public abstract NewTaxRuleIfc getTaxProrateRuleInstance();

	public abstract NewTaxRuleIfc getTaxProrateRuleInstance(Locale paramLocale);

	public abstract TaxRateCalculatorIfc getTaxRateCalculatorInstance();

	public abstract TaxRateCalculatorIfc getTaxRateCalculatorInstance(boolean paramBoolean);

	public abstract TaxRateCalculatorIfc getTaxRateCalculatorInstance(Locale paramLocale);

	public abstract TaxRateCalculatorIfc getTaxRateCalculatorInstance(Locale paramLocale, boolean paramBoolean);

	public abstract TaxRuleItemContainerIfc getTaxRuleItemContainerInstance();

	public abstract TaxRuleItemContainerIfc getTaxRuleItemContainerInstance(Locale paramLocale);

	public abstract TaxTableLineItemIfc getTaxTableLineItemInstance();

	public abstract TaxTableLineItemIfc getTaxTableLineItemInstance(Locale paramLocale);

	public abstract TaxTotalsContainerIfc getTaxTotalsContainerInstance();

	public abstract TaxTotalsContainerIfc getTaxTotalsContainerInstance(Locale paramLocale);

	public abstract TaxTotalsIfc getTaxTotalsInstance();

	public abstract TaxTotalsIfc getTaxTotalsInstance(Locale paramLocale);

	public abstract TenderCashIfc getTenderCashInstance();

	public abstract TenderCashIfc getTenderCashInstance(Locale paramLocale);

	public abstract TenderChargeIfc getTenderChargeInstance();

	public abstract TenderChargeIfc getTenderChargeInstance(Locale paramLocale);

	public abstract TenderCheckIfc getTenderCheckInstance();

	public abstract TenderCheckIfc getTenderCheckInstance(Locale paramLocale);

	public abstract TenderCouponIfc getTenderCouponInstance();

	public abstract TenderCouponIfc getTenderCouponInstance(Locale paramLocale);

	public abstract TenderDebitIfc getTenderDebitInstance();

	public abstract TenderDebitIfc getTenderDebitInstance(Locale paramLocale);

	public abstract TenderDescriptorIfc getTenderDescriptorInstance();

	public abstract TenderDescriptorIfc getTenderDescriptorInstance(Locale paramLocale);

	public abstract TenderGiftCardIfc getTenderGiftCardInstance();

	public abstract TenderGiftCardIfc getTenderGiftCardInstance(Locale paramLocale);

	public abstract TenderGiftCertificateIfc getTenderGiftCertificateInstance();

	public abstract TenderGiftCertificateIfc getTenderGiftCertificateInstance(Locale paramLocale);

	public abstract TenderLimitsIfc getTenderLimitsInstance();

	public abstract TenderLimitsIfc getTenderLimitsInstance(Locale paramLocale);

	public abstract TenderMailBankCheckIfc getTenderMailBankCheckInstance();

	public abstract TenderMailBankCheckIfc getTenderMailBankCheckInstance(Locale paramLocale);

	public abstract TenderMoneyOrderIfc getTenderMoneyOrderInstance();

	public abstract TenderMoneyOrderIfc getTenderMoneyOrderInstance(Locale paramLocale);

	public abstract TenderPurchaseOrderIfc getTenderPurchaseOrderInstance();

	public abstract TenderPurchaseOrderIfc getTenderPurchaseOrderInstance(Locale paramLocale);

	public abstract TenderStoreCreditIfc getTenderStoreCreditInstance();

	public abstract TenderStoreCreditIfc getTenderStoreCreditInstance(Locale paramLocale);

	public abstract TenderTravelersCheckIfc getTenderTravelersCheckInstance();

	public abstract TenderTravelersCheckIfc getTenderTravelersCheckInstance(Locale paramLocale);

	public abstract TillAdjustmentTransactionIfc getTillAdjustmentTransactionInstance();

	public abstract TillAdjustmentTransactionIfc getTillAdjustmentTransactionInstance(Locale paramLocale);

	public abstract TillIfc getTillInstance();

	public abstract TillIfc getTillInstance(Locale paramLocale);

	public abstract TillOpenCloseTransactionIfc getTillOpenCloseTransactionInstance();

	public abstract TillOpenCloseTransactionIfc getTillOpenCloseTransactionInstance(Locale paramLocale);

	public abstract TimeIntervalActivityIfc getTimeIntervalActivityInstance();

	public abstract TimeIntervalActivityIfc getTimeIntervalActivityInstance(Locale paramLocale);

	public abstract TransactionDiscountByAmountIfc getTransactionDiscountByAmountInstance();

	public abstract TransactionDiscountByAmountIfc getTransactionDiscountByAmountInstance(Locale paramLocale);

	public abstract TransactionDiscountByPercentageIfc getTransactionDiscountByPercentageInstance();

	public abstract TransactionDiscountByPercentageIfc getTransactionDiscountByPercentageInstance(Locale paramLocale);

	public abstract TransactionIDIfc getTransactionIDInstance();

	public abstract TransactionIDIfc getTransactionIDInstance(Locale paramLocale);

	public abstract TransactionIfc getTransactionInstance();

	public abstract TransactionIfc getTransactionInstance(Locale paramLocale);

	public abstract TransactionKeyIfc getTransactionKeyInstance();

	public abstract TransactionKeyIfc getTransactionKeyInstance(Locale paramLocale);

	public abstract TransactionSummaryIfc getTransactionSummaryInstance();

	public abstract TransactionSummaryIfc getTransactionSummaryInstance(Locale paramLocale);

	public abstract TransactionTaxIfc getTransactionTaxInstance();

	public abstract TransactionTaxIfc getTransactionTaxInstance(Locale paramLocale);

	public abstract TransactionTotalsIfc getTransactionTotalsInstance();

	public abstract TransactionTotalsIfc getTransactionTotalsInstance(Locale paramLocale);

	public abstract UnitOfMeasureIfc getUnitOfMeasureInstance();

	public abstract UnitOfMeasureIfc getUnitOfMeasureInstance(Locale paramLocale);

	public abstract UnknownItemIfc getUnknownItemInstance();

	public abstract UnknownItemIfc getUnknownItemInstance(Locale paramLocale);

	public abstract ValueAddedTaxRuleIfc getValueAddedTaxByLineRuleInstance();

	public abstract ValueAddedTaxRuleIfc getValueAddedTaxByLineRuleInstance(Locale paramLocale);

	public abstract ValueAddedTaxRuleIfc getValueAddedTaxProrateRuleInstance();

	public abstract ValueAddedTaxRuleIfc getValueAddedTaxProrateRuleInstance(Locale paramLocale);

	public abstract VoidTransactionIfc getVoidTransactionInstance();

	public abstract VoidTransactionIfc getVoidTransactionInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getWeekDayCalendarLevelInstance();

	public abstract CalendarLevelIfc getWeekDayCalendarLevelInstance(Locale paramLocale);

	public abstract WeeklyScheduleDocumentIfc getWeeklyScheduleDocumentInstance();

	public abstract WeeklyScheduleDocumentIfc getWeeklyScheduleDocumentInstance(Locale paramLocale);

	public abstract WorkstationIfc getWorkstationInstance();

	public abstract WorkstationIfc getWorkstationInstance(Locale paramLocale);

	public abstract CalendarLevelIfc getYearDayCalendarLevelInstance();

	public abstract CalendarLevelIfc getYearDayCalendarLevelInstance(Locale paramLocale);

	public abstract void setFactoryID(String paramString);
	
	public abstract TenderPayPalIfc getTenderPayPalInstance();

	public abstract TenderPayPalIfc getTenderPayPalInstance(Locale paramLocale);
	
	public abstract TenderSwitchIfc getTenderSwitchInstance();

	public abstract TenderSwitchIfc getTenderSwitchInstance(Locale paramLocale);
	
	public abstract TenderSoloIfc getTenderSoloInstance();

	public abstract TenderSoloIfc getTenderSoloInstance(Locale paramLocale);
	
	public abstract TenderPOSIfc getTenderPOSInstance();

	public abstract TenderPOSIfc getTenderPOSInstance(Locale paramLocale);
	
	public abstract TenderReplacementOrderIfc getTenderReplacementOrderInstance();

	public abstract TenderReplacementOrderIfc getTenderReplacementOrderInstance(Locale paramLocale);
}