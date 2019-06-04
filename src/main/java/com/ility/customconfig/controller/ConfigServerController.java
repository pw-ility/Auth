package com.ility.customconfig.controller;

import com.ility.customconfig.Exception.ConfigServerException;
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
@RequestMapping("api")
public class ConfigServerController {

    AppPropertyService appPropertyService;

    @Autowired
    ConfigServerController(AppPropertyService appPropertyService){
        this.appPropertyService = appPropertyService;
    }


    @GetMapping("/properties")
    @ApiOperation("shows all the properties configured for all the applications")
    public List<AppProperty> allApplicationsProperties(){
        return appPropertyService.showAllProperties();
    }

    @PostMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("create a property")
    public AppProperty save(@RequestBody AppProperty appProperty){
        appProperty.setDelete(false);
        return appPropertyService.save(appProperty);
    }




    @PutMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("update a property")
    public AppProperty update(@RequestBody AppProperty appProperty) throws ConfigServerException {

        return appPropertyService.updateById(appProperty);
    }

    @DeleteMapping("/property/{application}/{profile}/{label}/{key}")
    @ApiOperation("delete the property based for given application, profile, label, key")
    public String deleteProperty(@PathVariable("application")String application,@PathVariable("profile")String profile,
                                 @PathVariable("label")String label,@PathVariable("key")String key) throws Exception{

        appPropertyService.delete(application,profile,label,key);
        return "success";
    }


}
