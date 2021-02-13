package com.mobiquity.controller;
 
import static org.springframework.test.util.AssertionErrors.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mobiquity.AbstractWebTest;
import com.mobiquity.MobiquityApplication;
import com.mobiquity.model.Address;
import com.mobiquity.model.Atm;
import com.mobiquity.model.GeoLocation;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MobiquityApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class AtmControllerTest extends AbstractWebTest {
    private static final Logger LOG = Logger.getLogger(AtmControllerTest.class);
 
    private String city = "Assendelft";
    private String wrongCity = "AssenwrongCitydelft";
    private Atm[] atms = new Atm[2];

    @Before
    public void setup() throws IOException {
        Address address = new Address();
        address.setCity(city);
        address.setHousenumber("10A");
        address.setPostalcode("1567 JP");
        address.setStreet("Kaaikhof");
        address.setGeoLocation(new GeoLocation());
        Atm atm = new Atm();
        atm.setAddress(address);
        atm.setDistance(0);
        atm.setType("ING");
        Atm anotherAtm = new Atm();
        Address anotherAddress = new Address();
        anotherAddress.setCity(city);
        anotherAddress.setHousenumber("20A");
        anotherAddress.setPostalcode("1567 JP");
        anotherAddress.setStreet("Kaaikhof");
        anotherAddress.setGeoLocation(new GeoLocation());
        anotherAtm.setAddress(anotherAddress);
        atms[0] = atm;
        atms[1] = anotherAtm;
         MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetValidCityATMs() {
        try {
            mockMvc.perform(get("/atms/city/" + city)).
                    andExpect(status().isOk()).
                    andExpect((jsonPath("$.*", Matchers.hasSize(2)))).
                    andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        } catch (Exception ex) {
            LOG.error(ex);
            fail(ex.getMessage());
        }
    }

    @Test
    public void testGetNotValidCityATMsReturnNoContent() {
        try {
            mockMvc.perform(get("/atms/city/" + wrongCity)).
                    andExpect(status().isNotFound());
        } catch (Exception ex) {
            LOG.error(ex);
            fail(ex.getMessage());
        }
    }
    @Test
    public void testGetAtms() {
        try {
              mockMvc.perform(get("/atms/all/")).
                    andExpect(status().isOk());
        } catch (Exception ex) {
            LOG.error(ex);
            fail(ex.getMessage());
        }
    }

    
}