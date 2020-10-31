package homeservices.dto;

import io.swagger.annotations.ApiModelProperty;

public class FacilityDataDTO {

	@ApiModelProperty(name = "facilityname", required = false)
	private String facilityname;
	@ApiModelProperty(name = "faciltyImage", required = false)
	private String faciltyImage;
	@ApiModelProperty(name = "locationId", required = false)
	private Integer locationId;

	public String getFacilityname() {
		return facilityname;
	}

	public void setFacilityname(String facilityname) {
		this.facilityname = facilityname;
	}

	public String getFaciltyImage() {
		return faciltyImage;
	}

	public void setFaciltyImage(String faciltyImage) {
		this.faciltyImage = faciltyImage;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

}
