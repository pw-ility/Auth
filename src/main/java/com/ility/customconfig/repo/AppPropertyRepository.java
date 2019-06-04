package com.ility.customconfig.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ility.customconfig.beans.AppProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppPropertyRepository extends JpaRepository<AppProperty,Integer> {

    @Query(value = "SELECT p.* FROM properties p WHERE p.application=?1 and p.profile=?2 and p.LABEL=?3 and p.KY=?4 and p.is_delete=?5",nativeQuery = true)
    public AppProperty findByCriteria(@Param("application")String application, @Param("profile")String profile,
                                      @Param("label")String label, @Param("KY")String key,@Param("is_delete")boolean is_delete);
    @Query(value = "SELECT p.* FROM properties p WHERE p.id=?1",nativeQuery = true)
    public AppProperty findOneById(@Param("id")int id);
}

