package com.jp.dscommerce.services.exceptions;

public class DataBaseException extends RuntimeException { //runtime não exige o try/catch

    public DataBaseException(String msg){
        super(msg);
    }
}
