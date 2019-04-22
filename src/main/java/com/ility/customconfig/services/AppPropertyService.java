package com.ility.customconfig.services;

import java.util.List;

import com.ility.customconfig.beans.AppProperty;

public interface AppPropertyService {
    List<AppProperty> showAllProperties();
    AppProperty save(AppProperty appProperty);
}
