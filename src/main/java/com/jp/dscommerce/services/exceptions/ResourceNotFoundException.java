package com.jp.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException { //runtime não exige o try/catch

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
