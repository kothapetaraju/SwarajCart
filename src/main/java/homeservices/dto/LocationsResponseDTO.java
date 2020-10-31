package homeservices.dto;

import io.swagger.annotations.ApiModelProperty;

public class LocationsResponseDTO {

	@ApiModelProperty(name="id")
	private Integer id;
	
	  @ApiModelProperty(name="locationname")
	  private String locationname;
	  @ApiModelProperty(name="city")
	  private String city;
	  @ApiModelProperty(name="state")
	  private String state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	

}
