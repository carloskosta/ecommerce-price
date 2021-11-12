package com.teciopgrid.ecommerceprice.exception;

public class NotFoundEcommercePriceCriteriaDtoException extends RuntimeException {

    public NotFoundEcommercePriceCriteriaDtoException() {
        super("PriceCriteriaDto was null or empty during the process");
    }

}
