package com.oms.order.webservice.adapter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import nbty.webservice.controller.NbtyWebServiceController;
import nbty.webservice.exception.NbtyWebServiceException;
import nbty.webservice.exception.WSExceptionResponse;
import nbty.webservice.exception.WSResponseErrorCodesEnum;

@Component
public class NbtyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint
{
    private static final Logger logger = Logger.getLogger(NbtyAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException
    {
        logger.error("Unauthorized Request");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(),
                new WSExceptionResponse(String.valueOf(WSResponseErrorCodesEnum.UNAUTHORIZED.getCode()),
                        WSResponseErrorCodesEnum.UNAUTHORIZED.getMessage()));
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        setRealmName("NBTY");
        super.afterPropertiesSet();
    }

}