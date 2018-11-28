package com.sudhindra.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sudhindra.app.model.UserDetails;
import com.sudhindra.app.service.CityService;

@RestController
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<UserDetails>> userDetails() {
        
		List<UserDetails> userDetails = cityService.getUserDetails();
		return new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
	}

}
