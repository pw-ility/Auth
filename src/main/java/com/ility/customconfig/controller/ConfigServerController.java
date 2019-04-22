package com.ility.customconfig.controller;

import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.services.AppPropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "Cloud config server integrated with JDBC")
@RestController
public class ConfigServerController {

    AppPropertyService appPropertyService;

    @Autowired
    ConfigServerController(AppPropertyService appPropertyService){
        this.appPropertyService = appPropertyService;
    }

/*    @GetMapping("/properties")
    public List<AppProperty> allApplicationsProperties(){
        return appPropertyService.showAllProperties();
    }*/

    @GetMapping("/properties")
    @ApiOperation("shows all the properties configured for all the applications")
    public List<AppProperty> allApplicationsProperties(){
        return appPropertyService.showAllProperties();
    }

    @PostMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("shows all the properties configured for all the applications")
    public AppProperty save(@RequestBody AppProperty appProperty){
        return appPropertyService.save(appProperty);
    }

    @DeleteMapping("/property/{application}/{profile}/{label}/{key}")
    @ApiOperation("delete the property based for given application, profile, label, key")
    public List<AppProperty> deleteProperty(){
        return appPropertyService.showAllProperties();
    }
}
