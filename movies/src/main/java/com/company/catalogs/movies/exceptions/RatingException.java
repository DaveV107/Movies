package com.company.catalogs.movies.exceptions;

public class RatingException extends RuntimeException {
    public RatingException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ExceptionErrorCodes.NO_SUCH_RATING_EXCEPTION.getErrorMessge();
    }

}
