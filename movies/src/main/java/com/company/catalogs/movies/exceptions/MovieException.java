package com.company.catalogs.movies.exceptions;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ExceptionErrorCodes.NO_SUCH_MOVIE_EXCEPTION.getErrorMessge();
    }
}
