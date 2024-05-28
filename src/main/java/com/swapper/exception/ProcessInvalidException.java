package com.swapper.exception;

public class ProcessInvalidException extends Exception {

    private static final String ERROR_MAP_KEY = "errors";

    public ProcessInvalidException(String message) {
        super(message);
    }

    public String getError() {
        return String.format("{\"%s\": [\"%s\"]}", ERROR_MAP_KEY, super.getMessage());
    }
}