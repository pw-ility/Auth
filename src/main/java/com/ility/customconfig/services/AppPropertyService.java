package com.ility.customconfig.services;

import java.util.List;

import com.ility.customconfig.Exception.ConfigServerException;
import com.ility.customconfig.beans.AppProperty;

public interface AppPropertyService {
    List<AppProperty> showAllProperties();
    AppProperty save(AppProperty appProperty);
    AppProperty updateById(AppProperty appProperty) throws ConfigServerException;
    void delete(String applications,String profile,String label,String key) throws ConfigServerException;
}
