/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package oracle.retail.stores.commerceservices.common.currency;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import oracle.retail.stores.common.utility.LocaleMap;
import oracle.retail.stores.common.utility.Util;

public class CurrencyType implements Serializable, CurrencyTypeIfc {
	private static final long serialVersionUID = 1580729848124975929L;
	public static final CurrencyType USD = new CurrencyType("US", "USD", "840");
	public static final CurrencyType GBP = new CurrencyType("UK", "GBP", "826");
	public static final CurrencyType EUR = new CurrencyType("EU", "EUR", "978");
	private String countryCode;
	private int currencyId;
	private String currencyCode;
	private String currencyNumber;
	private String nationality;
	private String description;
	private BigDecimal conversionToBase;
	private String conversionFeeString;
	private int scale;
	private Collection<DenominationIfc> denominations;
	private boolean baseFlag;
	private String[] denominationNames;
	private Hashtable<Locale, String[]> denominationDisplayNames;
	private String[] denominationValues;
	public static final int EXCHANGE_RATE_SCALE = 6;

	public CurrencyType() {
		this.countryCode = null;

		this.currencyCode = "";

		this.currencyNumber = "";

		this.nationality = "";

		this.description = "";

		this.conversionToBase = BigDecimal.ONE;

		this.conversionFeeString = "";

		this.scale = 2;

		this.denominations = new ArrayList();
	}

	public CurrencyType(String k, String desc, String nat, BigDecimal rate, String fee) {
		this.countryCode = null;

		this.currencyCode = "";

		this.currencyNumber = "";

		this.nationality = "";

		this.description = "";

		this.conversionToBase = BigDecimal.ONE;

		this.conversionFeeString = "";

		this.scale = 2;

		this.denominations = new ArrayList();

		this.countryCode = k;
		this.description = desc;
		this.nationality = nat;
		this.conversionToBase = rate;
		setConversionFeeString(fee);
	}

	/** @deprecated */
	public CurrencyType(String countryCode, String currencyCode) {
		this.countryCode = null;

		this.currencyCode = "";

		this.currencyNumber = "";

		this.nationality = "";

		this.description = "";

		this.conversionToBase = BigDecimal.ONE;

		this.conversionFeeString = "";

		this.scale = 2;

		this.denominations = new ArrayList();

		this.countryCode = countryCode;
		this.currencyCode = currencyCode;
	}

	public CurrencyType(String countryCode, String currencyCode, String currencyNumber) {
		this.countryCode = null;

		this.currencyCode = "";

		this.currencyNumber = "";

		this.nationality = "";

		this.description = "";

		this.conversionToBase = BigDecimal.ONE;

		this.conversionFeeString = "";

		this.scale = 2;

		this.denominations = new ArrayList();

		this.countryCode = countryCode;
		this.currencyCode = currencyCode;
		this.currencyNumber = currencyNumber;
	}

	public CurrencyType(String k, String desc, String nat, BigDecimal rate) {
		this(k, desc, nat, rate, (String) null);
	}

	public Object clone() {
		CurrencyType c = new CurrencyType();

		setCloneAttributes(c);

		return c;
	}

	public void setCloneAttributes(CurrencyType newClass) {
		if (this.countryCode != null) {
			newClass.setCountryCode(this.countryCode);
		}
		if (this.description != null) {
			newClass.setDescription(this.description);
		}
		if (this.nationality != null) {
			newClass.setNationality(this.nationality);
		}
		newClass.setConversionToBase(this.conversionToBase);

		if (getConversionFeeString() != null) {
			newClass.setConversionFeeString(getConversionFeeString());
		}

		newClass.setScale(getScale());
		newClass.setCurrencyCode(getCurrencyCode());
		newClass.setCurrencyNumber(getCurrencyNumber());
		newClass.setCurrencyId(getCurrencyId());

		if (this.denominations != null) {
			newClass.setDenominations(this.denominations);
		}
		if (getDenominationNames() != null) {
			newClass.setDenominationNames(getDenominationNames());
		}
		if (getDenominationValues() != null) {
			newClass.setDenominationValues(getDenominationValues());
		}
		if (getLocalizedDenominationDisplayNames() == null)
			return;
		newClass.setLocalizedDenominationDisplayNames(getLocalizedDenominationDisplayNames());
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;

		boolean isEqual = false;

		if (obj instanceof CurrencyType) {
			CurrencyType c = (CurrencyType) obj;

			if ((Util.isObjectEqual(getCountryCode(), c.getCountryCode()))
					&& (Util.isObjectEqual(this.description, c.getDescription()))
					&& (Util.isObjectEqual(this.nationality, c.getNationality()))
					&& (Util.isObjectEqual(getConversionFeeString(), c.getConversionFeeString()))
					&& (Util.isObjectEqual(this.conversionToBase, c.getConversionToBase()))
					&& (Util.isObjectEqual(Integer.valueOf(getCurrencyId()), Integer.valueOf(c.getCurrencyId())))
					&& (Util.isObjectEqual(getCurrencyNumber(), c.getCurrencyNumber()))
					&& (Util.isObjectEqual(getCurrencyCode(), c.getCurrencyCode()))) {
				isEqual = true;
			} else {
				isEqual = false;
			}
		}
		return isEqual;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String value) {
		this.countryCode = value;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String value) {
		this.description = value;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String value) {
		this.nationality = value;
	}

	public BigDecimal getConversionToBase() {
		return this.conversionToBase;
	}

	public void setConversionToBase(BigDecimal value) {
		this.conversionToBase = value;
		if ((this.conversionToBase == null) || (this.conversionToBase.scale() == 6))
			return;
		this.conversionToBase = this.conversionToBase.setScale(6);
	}

	public String getConversionFeeString() {
		return this.conversionFeeString;
	}

	public void setConversionFeeString(String value) {
		this.conversionFeeString = value;
	}

	public int getScale() {
		return this.scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String[] getDenominationValues() {
		if ((this.denominationValues == null) || (this.denominationValues.length == 0)) {
			populateDenominationArrays();
		}

		return this.denominationValues;
	}

	public String[] getDenominationNames() {
		if ((this.denominationNames == null) || (this.denominationNames.length == 0)) {
			populateDenominationArrays();
		}

		return this.denominationNames;
	}

	public String[] getDenominationDisplayNames(Locale locale) {
		if ((this.denominationDisplayNames == null) || (this.denominationDisplayNames.isEmpty())) {
			populateDenominationArrays();
		}
		return ((String[]) this.denominationDisplayNames.get(LocaleMap.getBestMatch(locale)));
	}

	public Hashtable<Locale, String[]> getLocalizedDenominationDisplayNames() {
		return this.denominationDisplayNames;
	}

	public String getDenominationValue(String denominationName) {
		String denominationValue = "";
		Iterator it = this.denominations.iterator();
		while (it.hasNext()) {
			DenominationIfc den = (DenominationIfc) it.next();

			if (den.getDenominationName().equals(denominationName)) {
				denominationValue = den.getDenominationValue();
				break;
			}
		}

		return denominationValue;
	}

	public void setDenominations(Collection<DenominationIfc> denominations) {
		this.denominations = denominations;
	}

	public Collection<DenominationIfc> getDenominations() {
		return this.denominations;
	}

	public void addDenomination(DenominationIfc object) {
		this.denominations.add(object);
	}

	public int getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public boolean isBaseFlag() {
		return this.baseFlag;
	}

	public void setBaseFlag(boolean baseFlag) {
		this.baseFlag = baseFlag;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyNumber() {
		return this.currencyNumber;
	}

	public void setCurrencyNumber(String currencyNumber) {
		this.currencyNumber = currencyNumber;
	}

	public void setDenominationNames(String[] denominationNames) {
		this.denominationNames = denominationNames;
	}

	public void setLocalizedDenominationDisplayNames(Hashtable<Locale, String[]> denominationDisplayNames) {
		this.denominationDisplayNames = denominationDisplayNames;
	}

	public void setDenominationValues(String[] denominationValues) {
		this.denominationValues = denominationValues;
	}

	public String toString() {
		String EOL = System.getProperty("line.separator");
		StringBuilder strResult = new StringBuilder("Class:  CurrencyType (Revision ");
		strResult.append("Issuing countryCode:                        [").append(getCountryCode()).append("]")
				.append(EOL).append("Description:                        [").append(this.description).append("]")
				.append(EOL).append("Issuing country nationality:                        [").append(this.nationality)
				.append("]").append(EOL).append("ConversionToBase:                   [").append(this.conversionToBase)
				.append("]").append(EOL).append("ConversionFeeString:                [")
				.append(getConversionFeeString()).append("]").append(EOL).append("CurrencyCode:                  [")
				.append(getCurrencyCode()).append("]").append(EOL).append("CurrencyNumber:                [")
				.append(getCurrencyNumber()).append("]").append(EOL).append("Scale:                [")
				.append(getScale()).append("]").append(EOL).append("Currency Id:                [")
				.append(getCurrencyId()).append("]").append(EOL).append("Base Flag:                [")
				.append(isBaseFlag()).append("]").append(EOL);

		return strResult.toString();
	}

	private void populateDenominationArrays() {
		Comparator c = new ComparatorByDisplayPriority();

		List sortedList = new ArrayList(this.denominations);
		Collections.sort(sortedList, c);
		Iterator it = sortedList.iterator();
		this.denominationNames = new String[sortedList.size()];
		this.denominationValues = new String[sortedList.size()];
		this.denominationDisplayNames = new Hashtable();

		Set locales = null;
		if (it.hasNext()) {
			locales = ((DenominationIfc) it.next()).getLocalizedDenominationDisplayNames().getLocales();
			Iterator localeIt = locales.iterator();
			while (localeIt.hasNext()) {
				this.denominationDisplayNames.put((Locale) localeIt.next(), new String[sortedList.size()]);
			}
		}

		int i = 0;
		it = sortedList.iterator();
		while (it.hasNext()) {
			DenominationIfc denomination = (DenominationIfc) it.next();

			this.denominationNames[i] = denomination.getDenominationName();
			this.denominationValues[i] = denomination.getDenominationValue();
			if (locales != null) {
				Iterator localeIt = locales.iterator();
				while (localeIt.hasNext()) {
					Locale locale = (Locale) localeIt.next();
					((String[]) this.denominationDisplayNames.get(locale))[i] = denomination
							.getDenominationDisplayName(locale);
				}
			}

			i += 1;
		}
	}

	public static void main(String[] args) {
		CurrencyType c = new CurrencyType();

		System.out.println(c.toString());
	}

	private class ComparatorByDisplayPriority implements Comparator<DenominationIfc> {
		public int compare(DenominationIfc e1, DenominationIfc e2) {
			int cmpValue = 0;
			if (e1.getDisplayPriority() > e2.getDisplayPriority()) {
				cmpValue = 1;
			} else if (e1.getDisplayPriority() < e2.getDisplayPriority()) {
				cmpValue = -1;
			}

			return cmpValue;
		}
	}
}