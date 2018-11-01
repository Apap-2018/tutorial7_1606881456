package com.apap.tutorial7.service;

import java.util.Optional;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
        return pilotDb.findByLicenseNumber(licenseNumber);
    }

    @Override
    public PilotModel addPilot(PilotModel pilot) {
    	pilotDb.save(pilot);
        return pilot;
    }


    @Override
    public PilotModel getPilotDetailById(long id) {
        return pilotDb.findPilotById(id);
    }

	@Override
	public void deletePilot(PilotModel pilot) {
		// TODO Auto-generated method stub
		pilotDb.deleteById(pilot.getId());
		
	}

	@Override
	public void updatePilot(Long id, PilotModel pilot) {
		// TODO Auto-generated method stub
		PilotModel updatePilot = pilotDb.findPilotById(id);
		updatePilot.setFlyHour(pilot.getFlyHour());
		updatePilot.setName(pilot.getName());
		pilotDb.save(updatePilot);
	}
}