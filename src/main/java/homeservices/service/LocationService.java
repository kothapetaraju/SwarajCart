package homeservices.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import homeservices.dto.LocationsResponseDTO;
import homeservices.exception.CustomException;
import homeservices.model.LocationsRequestDTO;
import homeservices.repository.LocationRepository;
import io.jsonwebtoken.lang.Collections;

@Service
public class LocationService {
	
	  @Autowired
	  private LocationRepository locationRepository;
	  
	  @Autowired
	  private ModelMapper modelMapper;
	
	
	public String saveLocation(LocationsRequestDTO location) {
	    if (null!=location && null!=location.getLocationname()) {
	    	List<LocationsRequestDTO> locations= locationRepository.findByLocationnameAndCity(location.getLocationname(),location.getCity());
	    	if(Collections.isEmpty(locations)){
	    		locationRepository.save(location);
	    		return "Success";
	    	} else {
	  	      throw new CustomException("LocationName is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		    }      
	    } else {
		      throw new CustomException("LocationName is required", HttpStatus.UNPROCESSABLE_ENTITY);
		    }
	  }


	public List<LocationsResponseDTO> getLocations(HttpServletRequest req) {		
		List<LocationsRequestDTO> locations= locationRepository.findAll();
		
		List<LocationsResponseDTO> locationsList= locations
				  .stream()
				  .map(location -> modelMapper.map(location, LocationsResponseDTO.class))
				  .collect(Collectors.toList());
		return locationsList;
		
		
	}

}
