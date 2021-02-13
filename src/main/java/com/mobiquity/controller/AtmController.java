package com.mobiquity.controller;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobiquity.model.Atm;
import com.mobiquity.service.AtmService;
import com.mobiquity.service.impl.AtmServiceImpl;
import com.mobiquity.util.HeaderModel;
import com.mobiquity.util.Response;

@RestController
@RequestMapping(value = "atms")
public class AtmController {
    private static final Logger LOG = Logger.getLogger(AtmController.class);

	@Autowired	
	 private AtmService atmService;
	
	@GetMapping(value = "all")
	public ResponseEntity<Response<List<Atm>>> getAllAtms() throws IOException {
		LOG.info("inside getAllAtms" );
		Response<List<Atm>> response = new Response<>();
		/*HeaderModel hm = new HeaderModel(); 
		hm.setAppName("Mobiquity App ");
		hm.setLanguage("ENG");
		hm.setUserId("123");
		response.setHeader(hm);*/
 		List<Atm> plist = atmService.getAllAtms();		
		response.setResponse(plist);
 		return new ResponseEntity<Response<List<Atm>>>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/city/{city}")
	public  ResponseEntity<Response<List<Atm>>> getCityAtms(@PathVariable String city) throws IOException {
		LOG.info("inside getCityAtms" );
		Response<List<Atm>> response = new Response<>();
		/*HeaderModel hm = new HeaderModel();
		hm.setAppName("Mobiquity App ");
		hm.setLanguage("ENG");
		hm.setUserId("123");
		response.setHeader(hm);*/
		if(city == null || city.length() == 0) {
		     return new ResponseEntity<Response<List<Atm>>>(HttpStatus.BAD_REQUEST);
		}
		List<Atm> plist = atmService.getCityAtms(city);
		response.setResponse(plist);
 		return new ResponseEntity<Response<List<Atm>>>(response, HttpStatus.OK);
	}
}
