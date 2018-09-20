/**
 * 
 */
package com.oms.rtlog;



import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import oracle.retail.stores.domain.DomainGateway;
import oracle.retail.stores.domain.utility.EYSTime;
import oracle.retail.stores.exportfile.CurrencyAdapterIfc;
import oracle.retail.stores.exportfile.DatabaseEntityAdapterIfc;
import oracle.retail.stores.exportfile.EncryptionAdapterIfc;
import oracle.retail.stores.exportfile.EntityMappingObjectFactoryIfc;
import oracle.retail.stores.exportfile.ExportFileConfigurationIfc;
import oracle.retail.stores.exportfile.ExportFileException;
import oracle.retail.stores.exportfile.ExportFileResultAuditLogIfc;
import oracle.retail.stores.exportfile.MappingObjectFactoryContainer;
import oracle.retail.stores.exportfile.RecordFormatObjectFactoryIfc;
import oracle.retail.stores.exportfile.rtlog.RTLogExportResultIndicator;
import oracle.retail.stores.exportfile.rtlog.RTLogExportResultIndicatorIfc;
import oracle.retail.stores.exportfile.rtlog.RTLogOutputAdapterIfc;
import oracle.retail.stores.foundation.utility.config.ConfigurationException;
import oracle.retail.stores.xmlreplication.ExtractorObjectFactoryIfc;
import oracle.retail.stores.xmlreplication.ReplicationObjectFactoryContainer;

/**
 * @author Jigar
 *
 */

@Component
public class RTLogExportConfiguration
{

    private static Logger logger =  Logger.getLogger(RTLogExportConfiguration.class.getName());
    
    @Value("${rtlog.config.errorBatchID}")
    protected  Long errorBatchID = new Long(-2L);

    @Value("${rtlog.config.blockedBatchID}")
    protected  Long blockedBatchID = new Long(-100L);
    

    @Value("${rtlog.config.exportDirectoryName}")
    protected String exportDirectoryName;

    @Value("${rtlog.config.formatConfigurationFileName}")
    protected String formatConfigurationFileName;

    @Value("${rtlog.config.entityReaderConfigurationFileName}")
    protected String entityReaderConfigurationFileName;

    @Value("${rtlog.config.entityMappingConfigurationFileName}")
    protected String entityMappingConfigurationFileName;

    
    
    @Value("${rtlog.config.mappingObjectFactoryClassName}")
    protected String mappingObjectFactoryClassName;

    @Value("${rtlog.config.recordFormatObjectFactoryClassName}")
    protected String recordFormatObjectFactoryClassName;

    @Value("${rtlog.config.extractorObjectFactoryClassName}")
    protected String extractorObjectFactoryClassName;

    @Value("${rtlog.config.databaseAdapterClassName}")
    protected String databaseAdapterClassName;

    @Value("${rtlog.config.encryptionAdapterClassName}")
    protected String encryptionAdapterClassName;

    @Value("${rtlog.config.outputAdapterClassName}")
    protected String outputAdapterClassName;

    @Value("${rtlog.config.currencyAdapterClassName}")
    protected String currencyAdapterClassName;

    @Value("${rtlog.config.resultAuditLogClassName}")
    protected String resultAuditLogClassName;

    @Value("${rtlog.config.exportFileConfigurationClassName}")
    protected String exportFileConfigurationClassName;

    protected ExportFileConfigurationIfc exportFileConfig = null;

    @Value("${rtlog.config.maximumTransactionsToExport}")
    protected int maximumTransactionsToExport = 1;
    
    /**
     * RTLog Export Configuration
     */
    public RTLogExportConfiguration()
    {
        logger.info("Loading OMSRTLogExportConfiguration!");
    }

    
    /**
     * RTLog Export Configuration
     */
    @PostConstruct
    public void init()
    {
        try
        {
            setupExportFileConfiguration();
            logger.info("Successfully set  OMS RTLog Export Configuration!");
        }
        catch (ConfigurationException e)
        {
            logger.error("Unable to load OMS RTLog Configuration!");
        }
    }
    
    protected void setupExportFileConfiguration() throws ConfigurationException 
    {
        instantiateExportFileClasses();

        instantiateObjectFactories();

        this.exportFileConfig.setFileFormatConfiguration(this.formatConfigurationFileName);
        this.exportFileConfig.setEntityReaderConfiguration(this.entityReaderConfigurationFileName);
        this.exportFileConfig.setMappingConfiguration(this.entityMappingConfigurationFileName);

        RTLogExportResultIndicatorIfc ind = new RTLogExportResultIndicator();
        ind.setResult(errorBatchID);
        this.exportFileConfig.setFailureIndicator(ind);

        ind = new RTLogExportResultIndicator();
        ind.setResult(blockedBatchID);
        this.exportFileConfig.setExportBlockedIndicator(ind);
        try 
        {
            this.exportFileConfig.init();
        } 
        catch (ExportFileException efe) 
        {
            throw new ConfigurationException(efe.getMessage(), efe);
        }
        
    }
    
    protected void instantiateExportFileClasses() throws ConfigurationException 
    {
        try 
        {
            this.exportFileConfig = ((ExportFileConfigurationIfc) instantiateClass(
                    this.exportFileConfigurationClassName));
        } catch (ClassCastException cce) 
        {
            String message = "Error in the conduit script .  Class from name '" + this.exportFileConfigurationClassName
                    + "' is not an instance of ExportFileConfigurationIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }

        try {
            this.exportFileConfig
                    .setEntityAdapter((DatabaseEntityAdapterIfc) instantiateClass(this.databaseAdapterClassName));
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '" + this.databaseAdapterClassName
                    + "' is not an instance of DatabaseEntityAdapterIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }

        try {
            this.exportFileConfig
                    .setEncryptionAdapter((EncryptionAdapterIfc) instantiateClass(this.encryptionAdapterClassName));
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '" + this.encryptionAdapterClassName
                    + "' is not an instance of EncryptionAdapterIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }

        try {
            String storeID = "OMS";
            if (storeID.length() == 0) {
                String message = "Error error retriving Store ID from properties.";
                logger.error(message);
                throw new ConfigurationException(message);
            }
            RTLogOutputAdapterIfc adapter = (RTLogOutputAdapterIfc) instantiateClass(this.outputAdapterClassName);
            adapter.setExportDirectoryName(this.exportDirectoryName);
            adapter.setStoreID(storeID);
            this.exportFileConfig.setOutputAdapter(adapter);
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '" + this.databaseAdapterClassName
                    + "' is not an instance of OutputAdapterIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }

        try {
            this.exportFileConfig
                    .setResultLogger((ExportFileResultAuditLogIfc) instantiateClass(this.resultAuditLogClassName));
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '" + this.resultAuditLogClassName
                    + "' is not an instance of ExportFileResultAuditLogIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }

        try {
            this.exportFileConfig
                    .setCurrencyAdapter((CurrencyAdapterIfc) instantiateClass(this.currencyAdapterClassName));
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '" + this.resultAuditLogClassName
                    + "' is not an instance of ExportFileResultAuditLogIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }
    }

    private void instantiateObjectFactories() throws ConfigurationException {
        if (ReplicationObjectFactoryContainer.getInstance().getExtractorObjectFactory() == null) {
            try {
                ExtractorObjectFactoryIfc factory = (ExtractorObjectFactoryIfc) instantiateClass(
                        this.extractorObjectFactoryClassName);

                ReplicationObjectFactoryContainer.getInstance().setExtractorObjectFactory(factory);
            } catch (ClassCastException cce) {
                String message = "Error in the conduit script .  Class from name '"
                        + this.extractorObjectFactoryClassName + "' is not an instance of ExtractorObjectFactoryIfc.";

                logger.error(message);
                throw new ConfigurationException(message);
            }
        }
        if (MappingObjectFactoryContainer.getInstance().getMappingObjectFactory() == null) {
            try {
                EntityMappingObjectFactoryIfc factory = (EntityMappingObjectFactoryIfc) instantiateClass(
                        this.mappingObjectFactoryClassName);

                MappingObjectFactoryContainer.getInstance().setMappingObjectFactory(factory);
            } catch (ClassCastException cce) {
                String message = "Error in the conduit script .  Class from name '" + this.mappingObjectFactoryClassName
                        + "' is not an instance of EntityMappingObjectFactoryIfc.";

                logger.error(message);
                throw new ConfigurationException(message);
            }
        }
        if (MappingObjectFactoryContainer.getInstance().getFormatObjectFactory() != null)
            return;
        try {
            RecordFormatObjectFactoryIfc factory = (RecordFormatObjectFactoryIfc) instantiateClass(
                    this.recordFormatObjectFactoryClassName);

            MappingObjectFactoryContainer.getInstance().setFormatObjectFactory(factory);
        } catch (ClassCastException cce) {
            String message = "Error in the conduit script .  Class from name '"
                    + this.recordFormatObjectFactoryClassName + "' is not an instance of RecordFormatObjectFactoryIfc.";

            logger.error(message);
            throw new ConfigurationException(message);
        }
    }

    @SuppressWarnings("rawtypes")
    protected Object instantiateClass(String className) throws ConfigurationException {
        try {
            Class processClass = Class.forName(className);
            return processClass.newInstance();
        } catch (Exception e) {
            String message = "Error in the conduit script .  Failed to instantiate class from name '" + className
                    + "'.";

            logger.error(message, e);
            throw new ConfigurationException(message, e);
        }
    }
    
    
    public int getMaximumTransactionsToExport()
    {
        return maximumTransactionsToExport;
    }



    public ExportFileConfigurationIfc getExportFileConfig() {
        return this.exportFileConfig;
    }

    public void setExportFileConfig(ExportFileConfigurationIfc exportFileConfig) {
        this.exportFileConfig = exportFileConfig;
    }
    
 
    protected EYSTime convertToTime(String time) {
        EYSTime eTime = null;
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3, 5));
            int sec = Integer.parseInt(time.substring(6, 8));
            eTime = DomainGateway.getFactory().getEYSTimeInstance();
            eTime.setHour(hour);
            eTime.setMinute(min);
            eTime.setSecond(sec);
            eTime.setMillisecond(0);
        } catch (Exception e) {
        }

        return eTime;
    }



}
