package com.ility.customconfig.services;

import java.util.List;

import javax.persistence.NoResultException;
import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.repo.AppPropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.repo.AppPropertyRepository;

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
    public AppProperty updateById(AppProperty appProperty) {
        if(appProperty.getId()==0){
            throw new NoResultException();
        }
        appPropertyRepository.save(appProperty);  //call save method to replace(update) original one
        return appProperty;
    }

    @Override
    public void delete(String applications,String profile,String label,String key){
        AppProperty appProperty=appPropertyRepository.findByCriteria(applications,profile,label,key,false);
        if (appProperty==null){
        	throw new NoResultException();
        }
        appPropertyRepository.deleteById(appProperty.getId());
    }
}
