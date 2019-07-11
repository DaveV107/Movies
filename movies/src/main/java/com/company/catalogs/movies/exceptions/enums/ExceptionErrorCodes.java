package com.company.catalogs.movies.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionErrorCodes {

    NO_SUCH_DIRECTOR_EXCEPTION(-100, "director not found"),
    CANNOT_SAVE_DIRECTOR_EXCEPTION( -101, "cannot save director"),
    NO_SUCH_MOVIE_EXCEPTION( -200, "movie not found"),
    CANNOT_SAVE_MOVIE_EXCEPTION( -101, "cannot save movie"),
    NO_SUCH_RATING_EXCEPTION( -300, "rating not found"),
    CANNOT_SAVE_RATING_EXCEPTION( -101, "cannot save rating"),
    GENERAL_EXCEPTION( -1, "general exception - unknown");

    private final Integer errorCode;
    private final String errorMessge;

}
