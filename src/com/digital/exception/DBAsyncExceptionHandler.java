package com.digital.exception;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digital.audit.service.DBLoggingHandler;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class DBAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Autowired
    DBLoggingHandler dbLoggingHandler;

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... parameter) {
        log.error("Exception in Async Method : " + method.getName(), throwable);
        dbLoggingHandler.handleException(throwable, method, parameter);
    }

}

