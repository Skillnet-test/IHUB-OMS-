package com.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import oracle.retail.stores.common.data.JdbcUtilities;
import oracle.retail.stores.common.data.jdbchelper.OracleHelper;

@SpringBootApplication
@ComponentScan({ "com.oms", "oracle", "com.payment", "payment","nbty.webservice" })
@EnableScheduling
public class Application
{

    public static void main(String[] args)
    {
        JdbcUtilities.setJdbcHelperClass(new OracleHelper());
        SpringApplication.run(Application.class, args);
        System.out.println("I-HUB Application Started.....");
    }

}