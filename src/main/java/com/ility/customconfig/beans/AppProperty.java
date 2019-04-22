package com.ility.customconfig.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.ility.customconfig.converter.EncryptedStringConverter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "properties")
public class AppProperty {

    @Column
    @Id
    private int id;

    @Column
    private String application;

    @Column
    private String profile;

    @Column(name = "KY")
    private String key;

    @Column
    @Convert(converter = EncryptedStringConverter.class)
    private String value;

    @Column
    private String label;
}
