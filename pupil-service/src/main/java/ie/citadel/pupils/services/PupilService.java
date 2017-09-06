package ie.citadel.pupils.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.citadel.pupils.clients.AddressRestTemplateClient;
import ie.citadel.pupils.model.Address;
import ie.citadel.pupils.model.Pupil;

@Service
public class PupilService {

    @Autowired
    AddressRestTemplateClient addressRestTemplateClient;
    
    private static final Logger logger = LoggerFactory.getLogger(PupilService.class);

    private Address retrieveAddressByEircode(String addressId){
    	logger.info("About to make rest template client call");
        return addressRestTemplateClient.getAddressFromEircode(addressId);
    }

    public Pupil getPupil(String pupilId) {

    	//TODO : get pupil from repository using pupilId
    	Pupil pupil = new Pupil().withPupilId("f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a").withForename("Joe")
    			.withSurname("Bloggs").withEircode("D20 AB01");

        Address address = retrieveAddressByEircode(pupil.getEircode());

        return pupil
                .withAddress1( address.getAddress1())
                .withAddress2( address.getAddress2())
                .withAddress3( address.getAddress3() )
                .withAddress4( address.getAddress4() );
    }


}
