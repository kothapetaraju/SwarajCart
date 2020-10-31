package homeservices.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import homeservices.model.LocationsRequestDTO;



public interface LocationRepository extends JpaRepository<LocationsRequestDTO, Integer> {

		  boolean existsByLocationname(String locationname);

		 // LocationsRequestDTO findByLocationnameAndCity(String locationname,String city);
		  
		 //@Query("select p from Location p where upper(p.locationname) like concat('%', upper(?1), '%') and upper(p.city) like concat('%', upper(?2), '%')")
		  List<LocationsRequestDTO> findByLocationnameAndCity(String locationname,String city);

		  @Transactional
		  void deleteByLocationnameAndCity(String locationname,String city);
		  
		  

		}