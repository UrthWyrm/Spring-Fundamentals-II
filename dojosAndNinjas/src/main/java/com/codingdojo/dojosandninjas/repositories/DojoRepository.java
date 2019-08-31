package com.codingdojo.dojosandninjas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codingdojo.dojosandninjas.models.Dojo;

public interface DojoRepository extends CrudRepository<Dojo, Long>{
 List<Dojo> findAll();
 
 // get all dojos
 @Query("SELECT d FROM Dojo d")
 List<Dojo> findAllDojos();
 
 // get all the names of the dojos
 @Query("SELECT d.name From Dojo d")
 List<String> findAllDojosNames();
 
 // passing params and filtering (still retrieves a list)copy
 @Query("SELECT d FROM Dojo d WHERE id = ?1")
 List<Dojo> getDojoWhereId(Long id);
 
 // passing params and filtering
 @Query("SELECT d FROM Dojo d WHERE id = ?1")
 Dojo getSingleDojoWhereId(Long id);
 
//Note the int type. It is because it returns the number of rows affected
 @Modifying
 @Query("update Dojo d set d.name = ?1 WHERE d.id = ?2")
 int setNameForOne(String name, Long id);
 
 @Modifying
 @Query("update Dojo d set d.name = ?1")
 int setNameForAll(String name);
 
 @Modifying
 @Query("delete Dojo d WHERE d.id = ?1")
 int deleteOneDojo(Long id);
}
