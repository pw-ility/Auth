package com.ility.customconfig.util;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.ility.config.encryption")
@Slf4j
public class EncryptionUtil {

    @Setter
    private String key;
    @Setter @Getter
    private String keyPrefix;
    @Setter
    private String algorithm;

    public String encrypt(String plainData){
        String[] dataArray=plainData.split("encrypt.");
        if(dataArray.length !=2 || dataArray[1].length()==0){
            throw new IllegalStateException("Invalid input, no values after encrypt. !!! ");
        }
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        encryptor.setAlgorithm(algorithm);

        return keyPrefix+encryptor.encrypt(dataArray[1]);
    }

    public String decrypt(String encryptedData){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        encryptor.setAlgorithm(algorithm);
        return encryptor.decrypt(encryptedData);
    }
}
