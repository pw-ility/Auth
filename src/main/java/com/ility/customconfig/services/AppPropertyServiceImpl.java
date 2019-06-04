package com.ility.customconfig.services;

import com.ility.customconfig.Exception.ConfigServerException;
import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.repo.AppPropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppPropertyServiceImpl implements AppPropertyService {

    @Autowired
    AppPropertyRepository appPropertyRepository;

    @Override
    public List<AppProperty> showAllProperties() {
        return appPropertyRepository.findAll();
    }

    @Override
    public AppProperty save(AppProperty appProperty){
        return appPropertyRepository.save(appProperty);
    }

    @Override
    public AppProperty updateById(AppProperty appProperty) throws ConfigServerException {
        if(appProperty.getId()==0){
            throw new ConfigServerException("property id can not be 0 or empty");
        }
        appPropertyRepository.save(appProperty);  //call save method to replace(update) original one
        return appProperty;
    }

    @Override
    public void delete(String applications,String profile,String label,String key) throws ConfigServerException{
        AppProperty appProperty=appPropertyRepository.findByCriteria(applications,profile,label,key,false);
        if (appProperty==null){
            throw new ConfigServerException("this property does not exist");
        }
        appPropertyRepository.deleteById(appProperty.getId());
    }
}
