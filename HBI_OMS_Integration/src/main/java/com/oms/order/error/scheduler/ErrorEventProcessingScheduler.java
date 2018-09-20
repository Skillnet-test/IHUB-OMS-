package com.oms.order.error.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oms.exception.OMSException;
import com.oms.order.common.utility.ErrorEventHandlingService;

@Component
@ConditionalOnProperty(name = "oms.errorevent.scheduler.enable", havingValue = "true", matchIfMissing = true)
public class ErrorEventProcessingScheduler
{
    protected static final Logger logger = Logger.getLogger(ErrorEventProcessingScheduler.class);
    
    @Autowired
    ErrorEventHandlingService errorEvent;
    
    @Scheduled(cron = "${errorEvent.cron.expression}")
    public void processErrorEvents()
    {
        try
        {
            errorEvent.processErrorEvent();
        }
        catch (OMSException e)
        {
           logger.error(e);
        }
    }
}
