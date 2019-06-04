package com.ility.customconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.exception.ResponseWrapper;
import com.ility.customconfig.services.AppPropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "Cloud config server integrated with JDBC")
@RestController
@RequestMapping("api")
public class ConfigServerController extends BaseController{

    AppPropertyService appPropertyService;

    @Autowired
    ConfigServerController(AppPropertyService appPropertyService){
        this.appPropertyService = appPropertyService;
    }


    @GetMapping("/properties")
    @ApiOperation("shows all the properties configured for all the applications")
    public ResponseEntity<ResponseWrapper> allApplicationsProperties(){
    	 return ResponseEntity.ok(ResponseWrapper.setResponse(appPropertyService.showAllProperties(), HttpStatus.OK,  HttpStatus.OK.getReasonPhrase(), null));
    }

    @PostMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("create a property")
    public ResponseEntity<ResponseWrapper> save(@RequestBody AppProperty appProperty){
        appProperty.setDelete(false);
        appPropertyService.save(appProperty);
        return ResponseEntity.ok(ResponseWrapper.setResponse(appPropertyService.showAllProperties(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null));
    }




    @PutMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("update a property")
    public ResponseEntity<ResponseWrapper> update(@RequestBody AppProperty appProperty){
        appPropertyService.updateById(appProperty);
        return ResponseEntity.ok(ResponseWrapper.setResponse(appPropertyService.showAllProperties(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null));
    }




    @PutMapping(path="/properties", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("update a property")
    public AppProperty update(@RequestBody AppProperty appProperty) throws ConfigServerException {

        return appPropertyService.updateById(appProperty);
    }

    @DeleteMapping("/property/{application}/{profile}/{label}/{key}")
    @ApiOperation("delete the property based for given application, profile, label, key")
    public ResponseEntity<ResponseWrapper> deleteProperty(@PathVariable("application")String application,@PathVariable("profile")String profile,@PathVariable("label")String label,@PathVariable("key")String key) throws Exception{
        appPropertyService.delete(application,profile,label,key);
        return ResponseEntity.ok(ResponseWrapper.setResponse(appPropertyService.showAllProperties(), HttpStatus.OK, HttpStatus.OK.getReasonPhrase(), null));
    }


}
