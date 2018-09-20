package com.oms.order.invoiceout.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.OrderConstantIfc;

import oracle.retail.stores.domain.arts.JdbcSaveOrderByTransaction;
import oracle.retail.stores.domain.arts.JdbcSaveRetailTransaction;
import oracle.retail.stores.domain.arts.JdbcSaveRetailTransactionLineItems;
import oracle.retail.stores.domain.arts.JdbcSaveTenderLineItems;
import oracle.retail.stores.domain.arts.JdbcUpdateOrderByTransaction;
import oracle.retail.stores.domain.transaction.OrderTransactionIfc;
import oracle.retail.stores.domain.transaction.SaleReturnTransactionIfc;
import oracle.retail.stores.domain.transaction.TransactionIfc;

@Repository
public class InvoiceOutDAO implements OrderConstantIfc
{
    private static final Logger logger = Logger.getLogger(InvoiceOutDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void persistOMSInvoiceOut(OrderTransactionIfc orderTransaction) throws OMSException
    {
        if (orderTransaction != null)
        {
            JdbcSaveRetailTransaction jdbcTransaction = new JdbcSaveRetailTransaction();
            JdbcSaveRetailTransactionLineItems jdbcLineItemsTransaction = new JdbcSaveRetailTransactionLineItems();
            JdbcSaveOrderByTransaction jdbcSaveOrderByTransaction = new JdbcSaveOrderByTransaction();
            JdbcUpdateOrderByTransaction jdbcUpdateOrderByTransaction = new JdbcUpdateOrderByTransaction();
            JdbcSaveTenderLineItems jdbcSaveTenderLineItems = new JdbcSaveTenderLineItems();
            try
            {
                jdbcTransaction.execute(orderTransaction, jdbcTemplate);
                jdbcLineItemsTransaction.execute(orderTransaction, jdbcTemplate);
                jdbcSaveTenderLineItems.execute(orderTransaction, jdbcTemplate);

                switch (orderTransaction.getTransactionType())
                { // begin set operations by type
                    case TransactionIfc.TYPE_ORDER_INITIATE:
                        jdbcSaveOrderByTransaction.execute((OrderTransactionIfc)orderTransaction, jdbcTemplate);
                        break;
                    case TransactionIfc.TYPE_ORDER_PARTIAL:
                    case TransactionIfc.TYPE_ORDER_COMPLETE:
                    case TransactionIfc.TYPE_ORDER_CANCEL:
                        jdbcUpdateOrderByTransaction.execute((OrderTransactionIfc)orderTransaction, jdbcTemplate);
                        break;
                    default:
                        break;
                }
            }
            catch (Exception e)
            {
                logger.error("Exception Caught while persisting Order Invoice Out transaction to database. " + e);
                throw new OMSException(OMSErrorCodes.DATABASE.getCode(), OMSErrorCodes.DATABASE.getDescription());
            }
        }
        else
        {
            logger.error("No Order Transaction to persist.");
        }
    }

    public String getTransactionSequenceNo(String storeId) throws OMSException
    {
        String transId = null;
        JdbcSaveRetailTransaction jdbcTransaction = new JdbcSaveRetailTransaction();
        try
        {
            transId = jdbcTransaction.getTransactionSequenceNumber(jdbcTemplate, storeId);
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new OMSException(OMSErrorCodes.DATABASE.getCode(), OMSErrorCodes.DATABASE.getDescription());
        }
        return transId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void persistOMSInvoiceOut(SaleReturnTransactionIfc orderReturnTransaction) throws OMSException
    {
        JdbcSaveRetailTransaction jdbcTransaction = new JdbcSaveRetailTransaction();
        JdbcSaveRetailTransactionLineItems jdbcLineItemsTransaction = new JdbcSaveRetailTransactionLineItems();
        JdbcSaveTenderLineItems jdbcSaveTenderLineItems = new JdbcSaveTenderLineItems();

        try
        {
            jdbcTransaction.execute(orderReturnTransaction, jdbcTemplate);
            jdbcLineItemsTransaction.execute(orderReturnTransaction, jdbcTemplate);
            jdbcSaveTenderLineItems.execute(orderReturnTransaction, jdbcTemplate);
        }
        catch (Exception e)
        {
            logger.error(e);
            throw new OMSException(OMSErrorCodes.DATABASE.getCode(), OMSErrorCodes.DATABASE.getDescription());
        }
    }
}
