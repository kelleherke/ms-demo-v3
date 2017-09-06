package ie.citadel.address.services;

import ie.citadel.address.model.Address;

import org.springframework.stereotype.Service;

@Service
public class AddressService {

	
    public Address getAddressByEircode(String eircode) {
        
    	//TODO : Real world, use eircode to pull back address from repository.
    	return  new Address().withEircode("D20 AB12").withAddress1("Any House").withAddress2("Any Street")
    			             .withAddress3("Any Town").withAddress4("Anywhere");
    }

}
