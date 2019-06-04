package com.ility.customconfig.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.ility.customconfig.converter.EncryptedStringConverter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "properties")
@Table(name = "properties")
@SQLDelete(sql = "update properties set is_delete = 1 where id = ?")
@Where(clause = "is_delete = 0")
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

    @Column(name="is_delete")
    private Boolean delete;
}
