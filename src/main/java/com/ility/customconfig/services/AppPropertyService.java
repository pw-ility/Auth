package com.ility.customconfig.services;

import java.util.List;

import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.exception.ConfigServerException;

public interface AppPropertyService {
    List<AppProperty> showAllProperties();
    AppProperty save(AppProperty appProperty);
    AppProperty updateById(AppProperty appProperty);
    void delete(String applications,String profile,String label,String key);
}
