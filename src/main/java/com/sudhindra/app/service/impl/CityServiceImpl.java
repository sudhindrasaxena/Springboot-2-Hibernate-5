/**
 * 
 */
package com.sudhindra.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudhindra.app.dao.CityDao;
import com.sudhindra.app.model.UserDetails;
import com.sudhindra.app.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;

	public List<UserDetails> getUserDetails() {
		return cityDao.getUserDetails();

	}

}
