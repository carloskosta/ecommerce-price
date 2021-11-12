package com.teciopgrid.ecommerceprice.exception;

public class NotFoundEcommerceEntityException extends RuntimeException {

    public NotFoundEcommerceEntityException() {
        super("There arent result for the request");
    }

}
