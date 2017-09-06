package ie.citadel.pupils.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ie.citadel.pupils.model.Address;

@Component
public class AddressRestTemplateClient {
    @Autowired
    RestTemplate restTemplate;
    
    private static final Logger logger = LoggerFactory.getLogger(AddressRestTemplateClient.class);

    public Address getAddressFromEircode(String eircode){
    	
    	logger.debug("About to call restTemplate exchange method");
        ResponseEntity<Address> restExchange =
                restTemplate.exchange(
                        "http://zuulservice/api/address/v1/addresses/{eircode}",
                        HttpMethod.GET,
                        null, Address.class, eircode);

        return restExchange.getBody();
    }
}
