package com.codingdojo.driverslicense.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.driverslicense.models.DriversLicense;

public interface DriversLicenseRepository extends CrudRepository <DriversLicense, Long> {
	
	DriversLicense getDriversLicenseByUserId(Long id);

}
