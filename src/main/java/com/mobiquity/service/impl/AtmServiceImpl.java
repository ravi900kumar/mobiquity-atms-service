package com.mobiquity.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.constants.Const;
import com.mobiquity.exception.NoAtmsAvilable;
import com.mobiquity.model.Atm;
import com.mobiquity.service.AtmService;
@Service
public class AtmServiceImpl implements AtmService{
    private static final Logger LOG = Logger.getLogger(AtmServiceImpl.class);
    @Autowired
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    @Resource 
    Environment env;
    
	@Override
	public List<Atm> getAllAtms() throws IOException {
		LOG.info("inside getAllAtms" );
		 List<Atm> cityATMs = getAtmsList();
	        if (cityATMs.size() == 0) {
	            throw new NoAtmsAvilable();
	        }
	        return cityATMs;
 	}
	@Override
	public List<Atm> getCityAtms(String city) throws IOException { 
			LOG.info("inside getCityAtms" );
	        List<Atm> cityATMs = getCityAtmslist(city);
	       return cityATMs;
 	}
	
	private  List<Atm> getAtmsList() throws IOException {
		LOG.info("inside getAtmsList" );
		 List<Atm> cityATMs = Arrays.stream(getATMs(env.getProperty(Const.ATMS_URL))).sorted().collect(Collectors.toList());
	        if (cityATMs.size() == 0) {
	           throw new NoAtmsAvilable();
	        }
	        return cityATMs;
	}
	

    private  List<Atm> getCityAtmslist(String city) throws IOException {
    	LOG.info("inside getCityAtmslist" );
        List<Atm> cityATMs = getAtmsList().stream().filter(atm -> atm.getAddress().getCity().equals(city)).sorted().collect(Collectors.toList());
        if (cityATMs.size() == 0) {
           throw new NoAtmsAvilable();
        }
        return cityATMs;
    }
	private static Atm[] getATMs(String Url) throws IOException {
		LOG.info("inside getATMs" );
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(Url, String.class);
        String data = result.getBody().substring(result.getBody().indexOf('['));
        Atm[] atms = objectMapper.readValue(data, Atm[].class);

        if (atms.length == 0) {
            LOG.warn("No result found");
        }
        return atms;
    }



}
