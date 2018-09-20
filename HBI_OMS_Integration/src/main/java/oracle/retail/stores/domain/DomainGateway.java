package oracle.retail.stores.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import oracle.retail.stores.commerceservices.CommerceservicesGateway;
import oracle.retail.stores.commerceservices.common.currency.CurrencyIfc;
import oracle.retail.stores.commerceservices.common.currency.CurrencyServiceIfc;
import oracle.retail.stores.commerceservices.common.currency.CurrencyServiceLocator;
import oracle.retail.stores.commerceservices.common.currency.CurrencyType;
import oracle.retail.stores.commerceservices.common.currency.CurrencyTypeIfc;
import oracle.retail.stores.commerceservices.common.currency.CurrencyTypeList;
import oracle.retail.stores.commerceservices.common.currency.CurrencyTypeListIfc;
import oracle.retail.stores.domain.arts.CurrencyDataTransaction;
import oracle.retail.stores.domain.arts.DataTransactionFactory;
import oracle.retail.stores.domain.factory.DomainObjectFactory;
import oracle.retail.stores.domain.factory.DomainObjectFactoryIfc;
import oracle.retail.stores.domain.financial.CurrencyRoundingRule;
import oracle.retail.stores.domain.utility.CurrencyRoundingRuleSearchCriteriaIfc;
import oracle.retail.stores.foundation.manager.data.DataException;
import oracle.retail.stores.foundation.tour.gate.Gateway;
import oracle.retail.stores.foundation.utility.LocaleMap;
import oracle.retail.stores.foundation.utility.Util;

public class DomainGateway
implements Serializable
{
  static final long serialVersionUID = 3109092132515252758L;
  public static final String revisionNumber = "$Revision: /rgbustores_13.4.6_generic_branch/1 $";
  private static final Logger logger = Logger.getLogger(DomainGateway.class);
  public static final String DOMAIN_PROPERTY_GROUP_NAME = "domain";
  public static final String DOMAIN_OBJECT_FACTORY_PROPERTY_NAME = "DomainObjectFactory";
  public static final String DOMAIN_OBJECT_FACTORY_DEFAULT_CLASS_NAME = "oracle.retail.stores.domain.factory.DomainObjectFactory";
  protected static CurrencyTypeIfc baseCurrencyType = null;

  protected static Locale baseLocale = null;

  protected static Map<String, DecimalFormat> decimalMap = new HashMap();

  protected static Map<String, NumberFormat> numberMap = null;

  protected static Map<String, DecimalFormat> percentMap = null;

  protected static Map<String, DecimalFormat> msgMap = null;

  protected static Map<String, SimpleDateFormat> dateMap = null;

  protected static List<CurrencyTypeIfc> alternateCurrencyTypes = new ArrayList();

  protected static CurrencyServiceIfc currencyService = null;

  protected static DomainObjectFactoryIfc domainObjectFactory = null;

  protected static List<CurrencyRoundingRule> currencyRoundingRules = null;
    protected DomainGateway()
    {
    }

  public static CurrencyTypeIfc getBaseCurrencyType()
  {
    if (baseCurrencyType == null)
    {
      getCurrencyTypeList();
    }
    return baseCurrencyType;
  }

  public static void setBaseCurrencyType(CurrencyTypeIfc value)
  {
    baseCurrencyType = value;
    CommerceservicesGateway.setBaseCurrencyCurrencyCode(value.getCurrencyCode());
    CommerceservicesGateway.setBaseCurrencyCountryCode(value.getCountryCode());
  }

  public static void getCurrencyTypeList()
  {
    try
    {
      CurrencyTypeListIfc currencyTypeList = new CurrencyTypeList();
      currencyTypeList.setBaseCurrencyType(CurrencyType.GBP);
      currencyTypeList.addAlternateCurrencyType(CurrencyType.EUR);
      if (logger.isInfoEnabled())
      {
        logger.info(new StringBuilder().append("Retrieved CurrencyTypeList:").append(currencyTypeList.toString()).toString());
      }

      setBaseCurrencyType(currencyTypeList.getBaseCurrencyType());
      setAlternateCurrencyTypes(currencyTypeList.getAlternateCurrencyTypes());
    }
    catch (Exception e)
    {
      logger.error(new StringBuilder().append("Primary currency type list could not be retrieved from database: ").append(Util.throwableToString(e)).toString());

      logger.warn("Defaulting currency type to GBP.");

      String countryCode = CurrencyType.GBP.getCountryCode();
      String currencyCode = CurrencyType.GBP.getCurrencyCode();
      String currencyNumber = CurrencyType.GBP.getCurrencyNumber();

      CurrencyType currencyType = new CurrencyType(countryCode, currencyCode, currencyNumber);
      setBaseCurrencyType(currencyType);
    }
  }

  public static CurrencyDataTransaction getDataTransaction()
  {
    return (CurrencyDataTransaction)DataTransactionFactory.create("persistence_CurrencyDataTransaction");
  }
  
  /*Spec Name/#:16630559: CANADIAN GOVERNMENT TO PHASE OUT PENNIES
	Developer: Nikita
	Reviewed By: Nilesh Pandey
	Issue # (if any):
	Comments:
  */
  public static List<CurrencyRoundingRule> getCurrencyRoundingRuleList(String currencyRoundingType, BigDecimal currencyRoundingDenomination)
  {
    if (currencyRoundingRules == null)
    {
      String IsoCurrencyCode = baseCurrencyType.getCurrencyCode();
      CurrencyRoundingRuleSearchCriteriaIfc currencyRoundingRuleSearchCriteria = domainObjectFactory.getCurrencyRoundingRuleSearchCriteriaInstance();
      currencyRoundingRuleSearchCriteria.setCurrencyRoundingType(currencyRoundingType);
      currencyRoundingRuleSearchCriteria.setCurrencyRoundingDenomination(currencyRoundingDenomination);
      currencyRoundingRuleSearchCriteria.setIsoCurrencyCode(IsoCurrencyCode);
      CurrencyDataTransaction currencyDataTransaction = (CurrencyDataTransaction)DataTransactionFactory.create("persistence_CurrencyRoundingRuleDataTransaction");
      try
      {
        currencyRoundingRules = currencyDataTransaction.readCurrencyRoundingRuleList(currencyRoundingRuleSearchCriteria);
      }
      catch (DataException e)
      {
        logger.error(new StringBuilder().append("Currency Rounding Rules could not be retrieved from database: ").append(e).toString());
      }
    }
    
    return currencyRoundingRules;
    /*Spec Name/#:16630559: CANADIAN GOVERNMENT TO PHASE OUT PENNIES
	Developer: Nikita
	Reviewed By: Nilesh Pandey
	Issue # (if any):
	Comments:
    */
  }

  public static DecimalFormat getDecimalFormat(Locale locale)
  {
    return getFormat(locale, "DecimalFormat", "#0.00;(#0.00)");
  }

  public static DecimalFormat getWholeNumberFormat(Locale locale)
  {
    return getFormat(locale, "WholeNumberFormat", "#0;(#0)");
  }

  public static DecimalFormat getFormat(Locale locale, String propertyName, String pattern)
  {
    return getFormat(locale, getProperty(propertyName, pattern));
  }

  public static DecimalFormat getFormat(Locale locale, String pattern)
  {
    DecimalFormat formatter = null;

    StringBuilder localeKey = new StringBuilder(pattern).append(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant());

    String key = localeKey.toString();

    if (decimalMap == null)
    {
      decimalMap = new HashMap();
    }

    if (!decimalMap.containsKey(key))
    {
      formatter = (DecimalFormat)DecimalFormat.getInstance(locale);
      formatter.applyPattern(pattern);
      decimalMap.put(key, formatter);
    }
    else
    {
      formatter = (DecimalFormat)decimalMap.get(key);
    }

    return formatter;
  }

  public static NumberFormat getNumberFormat(Locale locale)
  {
    NumberFormat formatter = null;

    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant());

    String key = localeKey.toString();

    if (numberMap == null)
    {
      numberMap = new HashMap();
    }

    if (!numberMap.containsKey(key))
    {
      formatter = NumberFormat.getInstance(locale);
      numberMap.put(key, formatter);
    }
    else
    {
      formatter = (NumberFormat)numberMap.get(key);
    }

    return formatter;
  }

  public static DecimalFormat getPercentFormat(Locale locale)
  {
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant());

    String key = localeKey.toString();

    if (percentMap == null)
    {
      percentMap = new HashMap();
    }

    DecimalFormat formatter = null;
    if (!percentMap.containsKey(key))
    {
      formatter = (DecimalFormat)NumberFormat.getPercentInstance(locale);

      formatter.applyPattern(getProperty("PercentageFormat", "#0.###%"));

      percentMap.put(key, formatter);
    }
    else
    {
      formatter = (DecimalFormat)percentMap.get(key);
    }

    return formatter;
  }

  public static SimpleDateFormat getSimpleDateFormat(int datestyle, Locale locale)
  {
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant()).append(datestyle);

    String key = localeKey.toString();

    if (dateMap == null)
    {
      dateMap = new HashMap();
    }

    SimpleDateFormat formatter = null;
    if (!dateMap.containsKey(key))
    {
      formatter = (SimpleDateFormat)DateFormat.getDateInstance(datestyle, locale);

      formatter.applyPattern(getY4DateFormat(formatter.toPattern()));

      dateMap.put(key, formatter);
    }
    else
    {
      formatter = (SimpleDateFormat)dateMap.get(key);
    }

    return formatter;
  }

  public static SimpleDateFormat getDateTimeFormat(int datestyle, int timestyle, Locale locale)
  {
    SimpleDateFormat formatter = null;
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant()).append(datestyle).append(timestyle);

    String key = localeKey.toString();

    if (dateMap == null)
    {
      dateMap = new HashMap();
    }

    if (!dateMap.containsKey(key))
    {
      formatter = (SimpleDateFormat)DateFormat.getDateTimeInstance(datestyle, timestyle, locale);

      formatter.applyPattern(getY4DateFormat(formatter.toPattern()));

      dateMap.put(key, formatter);
    }
    else
    {
      formatter = (SimpleDateFormat)dateMap.get(key);
    }

    return formatter;
  }

  public static SimpleDateFormat getTimeFormat(int timestyle, Locale locale)
  {
    SimpleDateFormat formatter = null;
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant()).append("-").append(timestyle);

    String key = localeKey.toString();

    if (dateMap == null)
    {
      dateMap = new HashMap();
    }

    if (!dateMap.containsKey(key))
    {
      formatter = (SimpleDateFormat)DateFormat.getTimeInstance(timestyle, locale);
      dateMap.put(key, formatter);
    }
    else
    {
      formatter = (SimpleDateFormat)dateMap.get(key);
    }

    return formatter;
  }

  public static String getY4DateFormat(String format)
  {
    StringBuilder newFormat = new StringBuilder(format);

    int start = format.indexOf("y");

    int end = format.lastIndexOf("y") + 1;

    newFormat.replace(start, end, getProperty("Year4Format", "yyyy"));

    return newFormat.toString();
  }

  public static DecimalFormat getDecimalFormat()
  {
    if (baseLocale == null)
    {
      baseLocale = LocaleMap.getLocale(LocaleMap.DEFAULT);
    }
    return getDecimalFormat(baseLocale);
  }

  public static NumberFormat getNumberFormat()
  {
    if (baseLocale == null)
    {
      baseLocale = LocaleMap.getLocale(LocaleMap.DEFAULT);
    }
    return getNumberFormat(baseLocale);
  }

  public static DecimalFormat getPercentFormat()
  {
    if (baseLocale == null)
    {
      baseLocale = LocaleMap.getLocale(LocaleMap.DEFAULT);
    }
    return getPercentFormat(baseLocale);
  }

  public static SimpleDateFormat getSimpleDateFormat()
  {
    if (baseLocale == null)
    {
      baseLocale = LocaleMap.getLocale(LocaleMap.DEFAULT);
    }
    return getSimpleDateFormat(baseLocale);
  }

  public static SimpleDateFormat getSimpleDateFormat(Locale locale)
  {
    SimpleDateFormat formatter = null;
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant()).append(3);

    if (dateMap == null)
    {
      dateMap = new HashMap();
    }

    if (!dateMap.containsKey(localeKey.toString()))
    {
      formatter = (SimpleDateFormat)DateFormat.getDateInstance(3, locale);

      formatter.applyPattern(getY4DateFormat(formatter.toPattern()));

      dateMap.put(localeKey.toString(), formatter);
    }
    else
    {
      formatter = (SimpleDateFormat)dateMap.get(localeKey.toString());
    }

    return formatter;
  }

  public static SimpleDateFormat getSimpleDateFormat(Locale locale, String pattern)
  {
    SimpleDateFormat formatter = null;
    StringBuilder localeKey = new StringBuilder(locale.getLanguage()).append(locale.getCountry()).append(locale.getVariant()).append(pattern);

    String key = localeKey.toString();

    if (dateMap == null)
    {
      dateMap = new HashMap();
    }

    if (!dateMap.containsKey(key))
    {
      formatter = new SimpleDateFormat(pattern, locale);

      dateMap.put(key, formatter);
    }
    else
    {
      formatter = (SimpleDateFormat)dateMap.get(key);
    }

    return formatter;
  }

  public static SimpleDateFormat getTimeFormat()
  {
    if (baseLocale == null)
    {
      baseLocale = LocaleMap.getLocale(LocaleMap.DEFAULT);
    }
    return getTimeFormat(3, baseLocale);
  }

  public static CurrencyIfc getBaseCurrencyInstance()
  {
    if (getBaseCurrencyType() == null)
    {
      getCurrencyTypeList();
    }

    return getFactory().getCurrencyInstance(getBaseCurrencyType());
  }

  protected static CurrencyServiceIfc getCurrencyService()
  {
    if (currencyService == null)
    {
      currencyService = CurrencyServiceLocator.getCurrencyService();
    }
    return currencyService;
  }

  public static CurrencyIfc getBaseCurrencyInstance(String value)
  {
    CurrencyIfc c = getBaseCurrencyInstance();
    c.setStringValue(value);
    return c;
  }

  public static CurrencyIfc getBaseCurrencyInstance(BigDecimal value)
  {
    CurrencyIfc c = getBaseCurrencyInstance();
    c.setDecimalValue(value);
    return c;
  }

  public static CurrencyTypeIfc[] getAlternateCurrencyTypes()
  {
    return (CurrencyTypeIfc[])alternateCurrencyTypes.toArray(new CurrencyType[alternateCurrencyTypes.size()]);
  }

  public static void setAlternateCurrencyTypes(CurrencyTypeIfc[] value)
  {
    int length = value.length;
    alternateCurrencyTypes = new ArrayList(length);
    CommerceservicesGateway.resetAlternateCurrencyCodes();
    for (int i = 0; i < length; i++)
    {
      alternateCurrencyTypes.add(value[i]);
      CommerceservicesGateway.addAlternateCurrencyCodes(value[i].getCountryCode(), value[i].getCurrencyCode());
    }
  }

  public static void addAlternateCurrencyType(CurrencyTypeIfc value)
  {
    alternateCurrencyTypes.add(value);
  }

  public static CurrencyTypeIfc findCurrencyType(String code)
    throws IllegalArgumentException
  {
    return findCurrencyType(code, true);
  }

  public static CurrencyTypeIfc findCurrencyType(String code, boolean useCache)
	throws IllegalArgumentException {
    if ((getBaseCurrencyType() == null) || (!useCache))
    {
      getCurrencyTypeList();
    }

    CurrencyTypeIfc c = null;
 	try
        {
            for (int i = alternateCurrencyTypes.size() - 1; i >= 0; i--)
            {
                c = (CurrencyTypeIfc)alternateCurrencyTypes.get(i);
                if (c != null)
                {
                    if ((Util.isObjectEqual(c.getCountryCode(), code))
                            || (Util.isObjectEqual(c.getCurrencyCode(), code)))
                    {
                        return c;
                    }
                }
            }
        }
        catch (Exception e)
        {
            logger.error("Unable to get Alternate Currency : " + e);
        }
    c = getBaseCurrencyType();
    if ((Util.isObjectEqual(c.getCountryCode(), code)) || (Util.isObjectEqual(c.getCurrencyCode(), code)))
    {
      return c;
    }
    if (useCache == true)
    {
      findCurrencyType(code, false);
    }

    throw new IllegalArgumentException(new StringBuilder().append("\"").append(code).append("\" not found among available currency types.").toString());
  }

  public static CurrencyIfc getAlternateCurrencyInstance(String key)
    throws IllegalArgumentException
  {
    CurrencyTypeIfc t = findCurrencyType(key);

    CurrencyIfc c = null;
    try
    {
      c = getFactory().getCurrencyInstance(t);
    }
    catch (Exception e)
    {
      c = getBaseCurrencyInstance();
    }

    return c;
  }

  public static CurrencyIfc getAlternateCurrencyInstance(String key, String value)
    throws IllegalArgumentException
  {
    CurrencyIfc c = getAlternateCurrencyInstance(key);
    c.setStringValue(value);
    return c;
  }

  public static CurrencyIfc getCurrencyInstance(String key)
    throws IllegalArgumentException
  {
    if (Util.isObjectEqual(getBaseCurrencyType().getCountryCode(), key))
    {
      return getBaseCurrencyInstance();
    }

    return getAlternateCurrencyInstance(key);
  }

  public static CurrencyIfc getCurrencyInstance(String key, String value)
    throws IllegalArgumentException
  {
    CurrencyIfc c = getCurrencyInstance(key);
    c.setStringValue(value);
    return c;
  }

  public static CurrencyIfc convertToBase(CurrencyIfc alternateCurrency)
  {
    CurrencyIfc baseCurrency = getBaseCurrencyInstance();

    baseCurrency.setStringValue(alternateCurrency.getStringValue());

    baseCurrency = baseCurrency.multiply(alternateCurrency.getBaseConversionRate());

    if (!Util.isEmpty(alternateCurrency.getConversionFeeString()))
    {
      CurrencyIfc fee = getBaseCurrencyInstance(alternateCurrency.getConversionFeeString());

      baseCurrency = baseCurrency.add(fee);
    }
    return baseCurrency;
  }

  public static CurrencyIfc convertFromBase(CurrencyIfc baseCurrency, String key)
  {
    CurrencyIfc altCurrency = getAlternateCurrencyInstance(key);
    return convertFromBase(baseCurrency, altCurrency);
  }

  public static CurrencyIfc convertFromBase(CurrencyIfc baseCurrency, CurrencyIfc altCurrency)
  {
    altCurrency.setStringValue(baseCurrency.getStringValue());
    altCurrency = altCurrency.divide(altCurrency.getBaseConversionRate());
    return altCurrency;
  }

  public static boolean sendAdminEmail()
  {
    boolean sendEmail = false;

    String tempFlag = getProperty("SendAdminEmail", "N");

    if (tempFlag.equalsIgnoreCase("Y"))
    {
      sendEmail = true;
    }
    return sendEmail;
  }

  public static String[] getAdminEmails()
  {
    String tempEmails = getProperty("AdminEmail");
    String[] adminEmails = null;

    StringTokenizer stEmails = new StringTokenizer(tempEmails, ",", false);
    adminEmails = new String[stEmails.countTokens()];
    int count = 0;
    while (stEmails.hasMoreElements())
    {
      adminEmails[count] = stEmails.nextToken();
      count++;
    }

    return adminEmails;
  }

  public static DomainObjectFactoryIfc getFactory()
  {
    if (domainObjectFactory == null)
    {
      String className = getProperty("DomainObjectFactory", "oracle.retail.stores.domain.factory.DomainObjectFactory");

      if (logger.isInfoEnabled()) {
        logger.info(new StringBuilder().append("Factory class name retrieved by Gateway: ").append(className).append("").toString());
      }
      try
      {
        Class domainObjectFactoryClass = Class.forName(className);
        domainObjectFactory = (DomainObjectFactoryIfc)domainObjectFactoryClass.newInstance();
      }
      catch (Exception e)
      {
        logger.error(new StringBuilder().append("Requested domain object factory cannot be instantiated; ").append(e.toString()).append("Default object factory will be used.").toString());

        domainObjectFactory = new DomainObjectFactory();
      }
    }
    return domainObjectFactory;
  }

  public static String getProperty(String key)
  {
    return getProperty(key, (String)null);
  }

  public static String getProperty(String key, String defaultValue)
  {
    String propName = Gateway.getProperty("domain", key, defaultValue);
    return propName;
  }

  public String toString()
  {
    StringBuilder strResult = Util.classToStringHeader("DomainGateway", "$Revision: /rgbustores_13.4.6_generic_branch/1 $", hashCode());

    strResult.append(Util.formatToStringEntry("getBaseCurrencyType()", getBaseCurrencyType()));
    if (alternateCurrencyTypes == null)
    {
      strResult.append("alternateCurrencyTypes:                [null]").append(Util.EOL);
    }
    else
    {
      if (alternateCurrencyTypes.size() == 0)
      {
        strResult.append("alternateCurrencyTypes:").append("                    [null]").append(Util.EOL);
      }
      for (int i = 0; i < alternateCurrencyTypes.size(); i++)
      {
        strResult.append("Alternate Currency ").append(i + 1).append(Util.EOL).append(((CurrencyTypeIfc)alternateCurrencyTypes.get(i)).toString()).append(Util.EOL);
      }

    }

    return strResult.toString();
  }
}