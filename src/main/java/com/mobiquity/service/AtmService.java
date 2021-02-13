package com.mobiquity.service;

import java.io.IOException;
import java.util.List;

import com.mobiquity.model.Atm;

public interface AtmService {
	public List<Atm> getAllAtms() throws IOException;
	
	public List<Atm> getCityAtms(String city) throws IOException;
}
