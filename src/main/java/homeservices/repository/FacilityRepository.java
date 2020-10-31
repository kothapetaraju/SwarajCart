package homeservices.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import homeservices.model.FacilityVO;

public interface FacilityRepository extends JpaRepository<FacilityVO, Integer> {

	  boolean existsByFacilityname(String facilityname);
	  
	  FacilityVO findTopByOrderById();

	 // LocationsRequestDTO findByLocationnameAndCity(String locationname,String city);
	  
	 //@Query("select p from Location p where upper(p.locationname) like concat('%', upper(?1), '%') and upper(p.city) like concat('%', upper(?2), '%')")
	 // List<FacilityVO> findByFacilityname(String facilityname);
	  
	  List<FacilityVO> findByLocationId(Integer locationId);
	  
	  List<FacilityVO> findByLocationIdAndFacilityname(Integer locationId,String facilityname);

	  @Transactional
	  void deleteByLocationId(Integer locationId);
	  
	  

	}
