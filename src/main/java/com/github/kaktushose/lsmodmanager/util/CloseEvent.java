package com.github.kaktushose.lsmodmanager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// helper class to log (unwanted) termination of the app properly
public class CloseEvent {

    private String reason;
    private Throwable cause;
    private final int status;
    private final Logger logger;

    public CloseEvent(int status) {
        this.status = status;
        logger = LoggerFactory.getLogger(CloseEvent.class);
    }

    public CloseEvent(String reason, int status) {
        this.reason = reason;
        this.status = status;
        logger = LoggerFactory.getLogger(CloseEvent.class);
    }

    public CloseEvent(Throwable exception, int status) {
        this.cause = exception;
        this.status = status;
        logger = LoggerFactory.getLogger(CloseEvent.class);
    }

    public void perform() {
        if (cause != null) {
            logger.error("LS-ModManger has crashed! Stacktrace:", cause);
            System.exit(status);
        }
        logger.info(reason == null ? "Program exited with code " + status : reason + ". Program exited with code " + status);
        System.exit(status);
    }

}

