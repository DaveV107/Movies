package com.company.catalogs.movies.exceptions;

public class DirectorException extends RuntimeException {

    public DirectorException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ExceptionErrorCodes.NO_SUCH_DIRECTOR_EXCEPTION.getErrorMessge();
    }

}
