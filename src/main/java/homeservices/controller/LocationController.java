package homeservices.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import homeservices.dto.LocationDataDTO;
import homeservices.dto.LocationsResponseDTO;
import homeservices.model.LocationsRequestDTO;
import homeservices.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/area")
@Api(tags = "locations")
public class LocationController {

	
	  @Autowired
	  private LocationService locationService;

	  @Autowired
	  private ModelMapper modelMapper;
	  
	  @GetMapping(value = "/locations")
	  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	  @ApiOperation(value = "${LocationController.locations}", response = LocationsResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"), //
	      @ApiResponse(code = 403, message = "Access denied"), //
	      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	  public List<LocationsResponseDTO> whoami(HttpServletRequest req) {
	    return locationService.getLocations(req);
	    //modelMapper.map(locationService.getLocations(req), LocationsResponseDTO.class);
	  }
	  
	  @PostMapping("/locations")
	  @ApiOperation(value = "${LocationController.saveLocation}")
	  @ApiResponses(value = {//
	      @ApiResponse(code = 400, message = "Something went wrong"), //
	      @ApiResponse(code = 403, message = "Access denied"), //
	      @ApiResponse(code = 422, message = "Location is already in use")})
	  public String saveLocation(@ApiParam("Save Location") @RequestBody LocationDataDTO location) {
		  System.out.println("location : "+location);
		  System.out.println("location Nmae: "+location.getLocationname());
	    return locationService.saveLocation(modelMapper.map(location, LocationsRequestDTO.class));
	  }
}
