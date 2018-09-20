package com.oms.order.error.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.oms.exception.OMSErrorCodes;
import com.oms.exception.OMSException;
import com.oms.order.common.ErrorEventSqlConstantIfc;
import com.oms.order.error.data.ErrorEventData;

@Repository
public class ErrorEventDao implements ErrorEventSqlConstantIfc
{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ErrorEventData> getErrorEventByProcessingFlag() throws OMSException
    {
        List<ErrorEventData> eventList = null;
        eventList = jdbcTemplate.query(SQL_SELECT_ERROR_EVENT, new BeanPropertyRowMapper(ErrorEventData.class));
        if (eventList.isEmpty())
        {
            throw new OMSException(OMSErrorCodes.NO_RECORDS_FOUND.getCode(),
                    OMSErrorCodes.NO_RECORDS_FOUND.getDescription());
        }
        return eventList;
    }
    
    public void insertErrorEventData(ErrorEventData eventData)
    {
        jdbcTemplate.update(SQL_INSERT_ERROR_EVENT, new Object[] { eventData.getRequestType(),
                eventData.getContentType(), eventData.getPayload(), eventData.getErrorMessage() });
    }
    
    @Transactional(readOnly = true)
    public void updateErrorEvent(ErrorEventData eventData)
    {
        jdbcTemplate.update(SQL_UPDATE_ERROR_EVENT, new Object[] { eventData.getProcessFlag(), eventData.getEventId(), eventData.getEventDate() });
    }

}
