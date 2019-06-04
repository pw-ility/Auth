package com.ility.customconfig.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfigServerException extends Exception {


    public ConfigServerException(String message){
        super(message);
    }
}