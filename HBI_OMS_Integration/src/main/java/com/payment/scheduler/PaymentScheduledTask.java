package com.payment.scheduler;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.payment.dao.PaymentManager;

@Component
//@ConditionalOnProperty(name = "oms.payment.scheduler.enable", havingValue = "true", matchIfMissing = true)
public class PaymentScheduledTask
{

    protected static final Logger logger = Logger.getLogger(PaymentScheduledTask.class);

    @Autowired
    PaymentManager paymentManager;

    @Scheduled(cron = "${payment.cron.expression}")
    public void paymentSettlement()
    {
        logger.info(" Payment Settlement Generation Started");

        paymentManager.paymentSettlement();
        logger.info("Payment Settlement Generation Completed");

    }
}
