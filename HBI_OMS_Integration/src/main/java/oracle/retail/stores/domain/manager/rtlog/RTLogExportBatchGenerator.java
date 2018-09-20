/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package oracle.retail.stores.domain.manager.rtlog;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oms.rtlog.RTLogExportConfiguration;
import com.payment.dao.ComanyMappingData;
import com.payment.dao.CompanyCodeDao;

import oracle.retail.stores.common.processcontroller.GenericWorkUnit;
import oracle.retail.stores.common.processcontroller.WorkUnitControllerException;
import oracle.retail.stores.common.processcontroller.WorkUnitIfc;
import oracle.retail.stores.domain.DomainGateway;
import oracle.retail.stores.domain.arts.TransactionReadDataTransaction;
import oracle.retail.stores.domain.ixretail.log.POSLogTransactionEntryIfc;
import oracle.retail.stores.domain.utility.EYSDate;
import oracle.retail.stores.exportfile.ExportFileConfigurationIfc;
import oracle.retail.stores.exportfile.ExportFileGenerator;
import oracle.retail.stores.exportfile.ExportFileGeneratorIfc;
import oracle.retail.stores.exportfile.ExportFileResultAuditLogIfc;
import oracle.retail.stores.exportfile.rtlog.RTLogExportResultIndicator;
import oracle.retail.stores.exportfile.rtlog.RTLogExportResultIndicatorIfc;
import oracle.retail.stores.foundation.manager.data.DataException;
import oracle.retail.stores.foundation.tour.gate.Gateway;
import oracle.retail.stores.xmlreplication.ReplicationObjectFactoryContainer;
import oracle.retail.stores.xmlreplication.extractor.EntitySearchField;
import oracle.retail.stores.xmlreplication.extractor.EntitySearchIfc;

@Component
public class RTLogExportBatchGenerator implements RTLogExportBatchGeneratorIfc {
	private static final Logger logger = Logger.getLogger(RTLogExportBatchGenerator.class);
	public static final String BATCH_ID_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String YYYYMMDD_DATE_FORMAT_STRING = "yyyy-MM-dd";
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	protected ExportFileConfigurationIfc fileConfiguration;
	protected int maximumTransactionsToExport;

	@Autowired
    TransactionReadDataTransaction readDataTransaction;
    
	@Autowired
	ExportFileGenerator generator;
	
	@Autowired
	RTLogExportConfiguration rtLogConfiguration;
	
    @Autowired
    CompanyCodeDao companyCodeDao;
	
	public RTLogExportBatchGenerator() {
		this.fileConfiguration = null;

		this.maximumTransactionsToExport = -1;
	}
	
    /**
     * RTLog Export Configuration
     */
    @PostConstruct
    public void init()
    {
       	this.fileConfiguration = rtLogConfiguration.getExportFileConfig();
   		this.maximumTransactionsToExport = rtLogConfiguration.getMaximumTransactionsToExport();
    }

	public void generateBatch() throws RTLogExportException 
	{
	    try 
        {
            Collection<ComanyMappingData> companyCodes=companyCodeDao.getCompanyCodeMap().values();
            
            for (final ComanyMappingData company : companyCodes)
            {
                String storeID=company.getWarehouseNumber();
                POSLogTransactionEntryIfc[] entries = retrieveTransactionList(null,storeID);
    
                if (entries != null) {
                    WorkUnitIfc workUnit = getWorkUnit(entries, 0);
    
                    RTLogExportResultIndicatorIfc indicator = new RTLogExportResultIndicator();
                    indicator.setResult(getBatchID());
                    this.fileConfiguration.setSuccessIndicator(indicator);
                    this.fileConfiguration.getResultLogger().initialize();
                    generator.setExportFileConfiguration(this.fileConfiguration);
                    generator.setFirstInBatch(true);
                    generator.process(workUnit,storeID);
                }
            }

        } 
        catch (WorkUnitControllerException wuce) 
        {
            Throwable throwable = wuce.getCause();
            if (throwable != null)
                ;
            throw new RTLogExportException(wuce.getMessage(), wuce);
        } 
        catch (RTLogExportException ree) 
        {
            logger.error("Unexpected error exporting batch: " + ree.getMessage());
            throw new RTLogExportException(ree.getMessage(), ree);
        } 
        catch (Exception e) 
        {
            logger.error("Unexpected error exporting batch: " + e.getMessage());
            throw new RTLogExportException(e.getMessage(), e);
        } 
        finally 
        {
            logger.debug("RTLogExportBatchGenerator completed processing batch.");
        }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public ExportFileResultAuditLogIfc[] regenerateBatch(RTLogRegenerationCriteriaIfc criteria)
			throws RTLogExportException {
		ExportFileResultAuditLogIfc[] totalResultsArray = null;
		ArrayList totalResults = new ArrayList();
		try {
			POSLogTransactionEntryIfc[] entries = retrieveTransactionList(criteria);

			if (entries != null) {
				int totalEntries = entries.length;
				int startingIndex = 0;

				while (totalEntries > startingIndex) {
					WorkUnitIfc workUnit = getWorkUnit(entries, startingIndex);

					RTLogExportResultIndicatorIfc indicator = new RTLogExportResultIndicator();
					indicator.setResult(getBatchID());
					this.fileConfiguration.setSuccessIndicator(indicator);
					this.fileConfiguration.getResultLogger().initialize();

					ExportFileGeneratorIfc generator = instanciateExportFileGenerator();
					generator.setExportFileConfiguration(this.fileConfiguration);
					generator.process(workUnit);

					startingIndex += workUnit.getTargets().size();
					totalResults.add(this.fileConfiguration.getResultLogger());
				}

			}

		} catch (WorkUnitControllerException wuce) {
			Throwable throwable = wuce.getCause();
			if (throwable != null) {
				throw new RTLogExportException(throwable.getMessage(), throwable);
			}

			throw new RTLogExportException(wuce.getMessage(), wuce);
		} catch (RTLogExportException ree) {
			throw ree;
		} catch (Exception e) {
			String message = "Unexpected error exporting batch: " + e.getMessage();
			logger.error(message, e);
			throw new RTLogExportException(e.getMessage(), e);
		}

		totalResultsArray = new ExportFileResultAuditLogIfc[totalResults.size()];
		totalResults.toArray(totalResultsArray);

		logger.debug("RTLogExportBatchGenerator completed reprocessing batch.");

		return totalResultsArray;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    protected WorkUnitIfc getWorkUnit(POSLogTransactionEntryIfc[] entries, int startingIndex)
			throws RTLogExportException {
		int maxTrans = this.maximumTransactionsToExport;
		if ((maxTrans == -1) || (maxTrans > entries.length)) {
			maxTrans = entries.length;
		}

		ArrayList workUnitList = new ArrayList();
		EYSDate bDate = entries[startingIndex].getBusinessDate();
		for (int i = startingIndex; i < entries.length; ++i) {
			if (!(bDate.toFormattedString().equalsIgnoreCase(entries[i].getBusinessDate().toFormattedString()))) {
				continue;
			}
			workUnitList.add(convertToEntitySearch(entries[i]));

			if (workUnitList.size() == maxTrans) {
				break;
			}

		}

		if (logger.isDebugEnabled()) {
			StringBuilder builder = new StringBuilder("Converting POSLogTransactionEntries into a work unit.");
			builder.append("\nPOSLogTransactionEntry count: ");
			builder.append(entries.length);
			builder.append("\nWorkUnit target list size: ");
			builder.append(workUnitList.size());
			logger.debug(builder);
		}

		WorkUnitIfc workUnit = instantiateWorkUnit();
		workUnit.setTargets(workUnitList);
		return workUnit;
	}

	protected EntitySearchIfc convertToEntitySearch(POSLogTransactionEntryIfc entry) {
		String store = entry.getTransactionID().getStoreID();
		String workstation = entry.getTransactionID().getWorkstationID();
		String busDate = format.format(entry.getBusinessDate().dateValue());
		Integer seqNbr = new Integer(Long.toString(entry.getTransactionID().getSequenceNumber()));

		EntitySearchIfc searchFields = ReplicationObjectFactoryContainer.getInstance().getExtractorObjectFactory()
				.getEntitySearchInstance();

		searchFields.addSearchField(new EntitySearchField("ID_STR_RT", store));
		searchFields.addSearchField(new EntitySearchField("ID_WS", workstation));
		searchFields.addSearchField(new EntitySearchField("DC_DY_BSN", busDate));
		searchFields.addSearchField(new EntitySearchField("AI_TRN", seqNbr));

		return searchFields;
	}

	protected POSLogTransactionEntryIfc[] retrieveTransactionList(RTLogRegenerationCriteriaIfc criteria)
			throws RTLogExportException {
		if (logger.isDebugEnabled()) {
			logger.debug("RTLogExportBatchGenerator.retrieveTransactionList criteria: " + criteria);
		}



		POSLogTransactionEntryIfc[] entries = null;
		try {
			String storeID = Gateway.getProperty("application", "StoreID", null);
			entries = readDataTransaction.retrieveTransactionsNotInBatch(storeID, 2,
					this.maximumTransactionsToExport);

		} 
		catch (DataException de) {
			if (de.getErrorCode() == 6) {
				logger.info("No transactions were found for export to the RTLog.");
			} else {
				String message = "An error occurred reading the transaction list from the database.";
				logger.error(message, de);
				throw new RTLogExportException(message, de);
			}
		}
        catch (SQLException e)
        {
            e.printStackTrace();
        }

		return entries;
	}
	
	protected POSLogTransactionEntryIfc[] retrieveTransactionList(RTLogRegenerationCriteriaIfc criteria,String storeID)
            throws RTLogExportException {
        if (logger.isDebugEnabled()) {
            logger.debug("RTLogExportBatchGenerator.retrieveTransactionList criteria: " + criteria);
        }



        POSLogTransactionEntryIfc[] entries = null;
        try {
            entries = readDataTransaction.retrieveTransactionsNotInBatch(storeID, 2,
                    this.maximumTransactionsToExport);

        } 
        catch (DataException de) {
            if (de.getErrorCode() == 6) {
                logger.info("No transactions were found for export to the RTLog.");
            } else {
                String message = "An error occurred reading the transaction list from the database.";
                logger.error(message, de);
                throw new RTLogExportException(message, de);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return entries;
    }
	
	protected Long getBatchID() {
		EYSDate currentTime = DomainGateway.getFactory().getEYSDateInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return Long.valueOf(dateFormat.format(currentTime.dateValue()));
	}

	protected ExportFileGeneratorIfc instanciateExportFileGenerator() {
		return new ExportFileGenerator();
	}

	protected WorkUnitIfc instantiateWorkUnit() {
		return new GenericWorkUnit();
	}

	public ExportFileConfigurationIfc getExportFileConfiguration() {
		return this.fileConfiguration;
	}

	public void setExportFileConfiguration(ExportFileConfigurationIfc fileConfiguration) {
		this.fileConfiguration = fileConfiguration;
	}

	public int getMaximumTransactionsToExport() {
		return this.maximumTransactionsToExport;
	}

	public void setMaximumTransactionsToExport(int maximumTransactionsToExport) {
		this.maximumTransactionsToExport = maximumTransactionsToExport;
	}
}