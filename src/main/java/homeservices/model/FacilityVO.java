package homeservices.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Table(name = "Facility")
@Entity
public class FacilityVO {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Size(min = 4, max = 255, message = "Minimum locationname length: 4 characters")
	@Column(unique = true, nullable = false)
	private String facilityname;

	@Column(unique = false, nullable = false)
	private Integer locationId;

	@Column(unique = false, nullable = false)
	private String faciltyImage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacilityname() {
		return facilityname;
	}

	public void setFacilityname(String facilityname) {
		this.facilityname = facilityname;
	}

	

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getFaciltyImage() {
		return faciltyImage;
	}

	public void setFaciltyImage(String faciltyImage) {
		this.faciltyImage = faciltyImage;
	}
	
	

}
