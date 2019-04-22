package com.ility.customconfig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ility.customconfig.beans.AppProperty;

public interface AppPropertyRepository extends JpaRepository<AppProperty,Integer> {

}

