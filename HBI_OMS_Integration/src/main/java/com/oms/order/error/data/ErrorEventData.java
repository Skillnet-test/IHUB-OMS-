package com.oms.order.error.data;

import java.io.Serializable;

public class ErrorEventData implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String requestType;

    private int eventId;

    private String contentType;

    private String payload;

    private String errorMessage;

    private String eventDate;
    
    private int processFlag;

    public int getProcessFlag()
    {
        return processFlag;
    }

    public void setProcessFlag(int processFlag)
    {
        this.processFlag = processFlag;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType(String requestType)
    {
        this.requestType = requestType;
    }

    public int getEventId()
    {
        return eventId;
    }

    public void setEventId(int eventId)
    {
        this.eventId = eventId;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getPayload()
    {
        return payload;
    }

    public void setPayload(String payload)
    {
        this.payload = payload;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(String eventDate)
    {
        this.eventDate = eventDate;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requestType == null) ? 0 : requestType.hashCode());
        result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
        result = prime * result + ((eventDate == null) ? 0 : eventDate.hashCode());
        result = prime * result + eventId;
        result = prime * result + processFlag;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorEventData other = (ErrorEventData)obj;
        if (requestType == null)
        {
            if (other.requestType != null)
                return false;
        }
        else if (!requestType.equals(other.requestType))
            return false;
        if (contentType == null)
        {
            if (other.contentType != null)
                return false;
        }
        else if (!contentType.equals(other.contentType))
            return false;
        if (payload == null)
        {
            if (other.payload != null)
                return false;
        }
        else if (!payload.equals(other.payload))
            return false;
        if (errorMessage == null)
        {
            if (other.errorMessage != null)
                return false;
        }
        else if (!errorMessage.equals(other.errorMessage))
            return false;
        if (eventDate == null)
        {
            if (other.eventDate != null)
                return false;
        }
        else if (!eventDate.equals(other.eventDate))
            return false;
        if (eventId != other.eventId)
            return false;
        if (processFlag != other.processFlag)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ErrorEventData [requestType=" + requestType + ", eventId=" + eventId + ", contentType=" + contentType
                + ", payload=" + payload + ", errorMessage=" + errorMessage + ", eventDate=" + eventDate + ", processFlag=" + processFlag +"]";
    }

}
