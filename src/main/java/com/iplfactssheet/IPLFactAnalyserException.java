package com.iplfactssheet;

public class IPLFactAnalyserException extends Exception {
    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE,NO_CENSUS_DATA,SOME_FILE_ISSUE,NO_SUCH_FIELD,INVALID_COUNTRY
    }

    ExceptionType type;

    public IPLFactAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public IPLFactAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }

    public IPLFactAnalyserException(String message, ExceptionType censusFileProblem, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }
}
