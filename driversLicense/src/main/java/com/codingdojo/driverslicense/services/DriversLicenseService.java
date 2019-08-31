package com.codingdojo.driverslicense.services;

import org.springframework.stereotype.Service;

import com.codingdojo.driverslicense.models.DriversLicense;
import com.codingdojo.driverslicense.repositories.DriversLicenseRepository;

@Service
public class DriversLicenseService {
	private static String newNumber = "000000";
	private DriversLicenseRepository driversLicenseRepository;
	private DriversLicenseService(DriversLicenseRepository driversLicenseRepository) {
		this.driversLicenseRepository = driversLicenseRepository;
	}
	public void addDriversLicense(DriversLicense driversLicense) {
		driversLicense.setNumber(newNumber);
		newNumber = String.format("%06d", Integer.parseInt(newNumber)+1);
		driversLicenseRepository.save(driversLicense);
	}
	public DriversLicense getDriversLicenseById(Long id) {
		return driversLicenseRepository.getDriversLicenseByUserId(id);
	}
	public void deleteDriversLicense(Long id) {
		driversLicenseRepository.deleteById(id);
	}

}
