/**
 * 
 */
package com.oms.rtlog.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import oracle.retail.stores.domain.manager.rtlog.RTLogExportBatchGeneratorIfc;
import oracle.retail.stores.domain.manager.rtlog.RTLogExportException;

/**
 * @author Jigar
 */
@Component
//@ConditionalOnProperty(name = "oms.rtlog.scheduler.enable", havingValue = "true", matchIfMissing = true)  
public class RTLogScheduledTask
{
    protected static final Logger logger = Logger.getLogger(RTLogScheduledTask.class);

    @Autowired
    RTLogExportBatchGeneratorIfc batchGenerator;

    /**
     * 
     */
    public RTLogScheduledTask()
    {
        logger.info("OMSRTLogScheduledTask Initialised");
    }

    @Scheduled(cron = "${rtlog.cron.expression}")
    public void generateRTLog()
    {
        logger.info("RTLOG Generation Started");

        try
        {
            batchGenerator.generateBatch();
            logger.info("RTLOG Generation Completed");
        }
        catch (RTLogExportException e)
        {
            logger.error("Error while RTLog Generation");
        }

    }

}
