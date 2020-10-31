package homeservices.dto;

import io.swagger.annotations.ApiModelProperty;

public class LocationDataDTO {

	
	 @ApiModelProperty(name="locationname", required=false)
	  private String locationname;
	  @ApiModelProperty(name="city", required=false)
	  private String city;
	  @ApiModelProperty(name="state", required=false)
	  private String state;
	  
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
