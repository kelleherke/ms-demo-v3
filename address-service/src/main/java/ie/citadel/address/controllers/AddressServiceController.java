package ie.citadel.address.controllers;


import ie.citadel.address.model.Address;
import ie.citadel.address.services.AddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value="v1/addresses")
public class AddressServiceController {
    
	@Autowired
    private AddressService addressService;

    @RequestMapping(value="/{eircode}",method = RequestMethod.GET)
    public Address getAddress( @PathVariable("eircode") String eircode) {
        return addressService.getAddressByEircode(eircode);
    }

}
