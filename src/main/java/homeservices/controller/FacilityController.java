package homeservices.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import homeservices.dto.FacilityDataDTO;
import homeservices.dto.LocationsResponseDTO;
import homeservices.model.FacilityVO;
import homeservices.service.FacilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/area")
@Api(tags = "facilities")
public class FacilityController {

	@Autowired
	private FacilityService facilityService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/facilities/{locationId}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	@ApiOperation(value = "${FacilityController.getFacilities}", response = LocationsResponseDTO.class, authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public List<FacilityDataDTO> getFacilities(
			@PathVariable("locationId") Integer locationId,
			HttpServletRequest req) {
		return facilityService.getFacilitiesByLocationId(req, locationId);
		// modelMapper.map(locationService.getLocations(req),
		// LocationsResponseDTO.class);
	}

	@PostMapping("/facilities")
	@ApiOperation(value = "${FacilityController.saveFacility}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Location is already in use") })
	public String saveFacility(@ApiParam("Save Location") @RequestBody FacilityDataDTO facility) {
		System.out.println("facility : " + facility);
		System.out.println("Facility Nmae: " + facility.getFacilityname());
		return facilityService.saveFacility(modelMapper.map(facility, FacilityVO.class));
	}
	
	@PostMapping("/facilities/addAll")
	@ApiOperation(value = "${LocationController.saveLocation}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Location is already in use") })
	public String saveFacilities(@ApiParam("Save Location") @RequestBody List<FacilityDataDTO> facilities) {
		
		System.out.println("facilities : " + facilities);
		System.out.println("Facilities Size: " + facilities.size());
		List<FacilityVO> facilitiesList= facilities
				  .stream()
				  .map(facility -> modelMapper.map(facility, FacilityVO.class))
				  .collect(Collectors.toList());
		System.out.println("Facilities Size after conversion: " + facilitiesList.size());
		return facilityService.saveFacilities(facilitiesList);
	}
}
