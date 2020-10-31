package homeservices.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import homeservices.dto.FacilityDataDTO;
import homeservices.exception.CustomException;
import homeservices.model.FacilityVO;
import homeservices.repository.FacilityRepository;
import io.jsonwebtoken.lang.Collections;

@Service
public class FacilityService {
	
	@Autowired
	  private FacilityRepository facilityRepository;
	  
	  @Autowired
	  private ModelMapper modelMapper;
	
	
	public String saveFacility(FacilityVO facility) {
	    if (null!=facility && null!=facility.getLocationId()) {
	    	List<FacilityVO> facilities= facilityRepository.findByLocationIdAndFacilityname(facility.getLocationId(),facility.getFacilityname());
	    	if(Collections.isEmpty(facilities)){
	    		facilityRepository.save(facility);
	    		return "Success";
	    	} else {
	  	      throw new CustomException("LocationName is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		    }      
	    } else {
		      throw new CustomException("LocationName is required", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
	  }
	
	public String saveFacilities(List<FacilityVO> facilities) {
		if(!Collections.isEmpty(facilities)){
			
			//Iterable<FacilityVO> persistedFacilities = facilityRepository.saveAll(facilities);

            for (FacilityVO facility : facilities) {
            	FacilityVO facilityVo = facilityRepository.findTopByOrderById();
            	if(null!=facilityVo && null!=facilityVo.getId()){
            		facilityVo.setId(facilityVo.getId()+1);
            	}
            	// facilityRepository.saveAndFlush(facility);
            	saveFacility(facility);
            	//facilityRepository.
                System.out.println("------------------------------");
                System.out.println("Id : " + facility.getId());
            }
    		//facilityRepository.saveAll(facilities);
    		return "Success";
    	}
		return null;
	  }


	public List<FacilityDataDTO> getFacilitiesByLocationId(HttpServletRequest req,Integer locationId) {		
		List<FacilityVO> facilities= facilityRepository.findByLocationId(locationId);
		
		List<FacilityDataDTO> facilitiesList= facilities
				  .stream()
				  .map(facility -> modelMapper.map(facility, FacilityDataDTO.class))
				  .collect(Collectors.toList());
		return facilitiesList;	
		
	}


}
