package com.company.catalogs.movies.exceptions;

import com.company.catalogs.movies.exceptions.enums.ExceptionErrorCodes;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ExceptionErrorCodes.NO_SUCH_MOVIE_EXCEPTION.getErrorMessge();
    }
}
