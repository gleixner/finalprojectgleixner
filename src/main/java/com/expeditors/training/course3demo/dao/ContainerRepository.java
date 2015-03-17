package com.expeditors.training.course3demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;

public interface ContainerRepository extends JpaRepository<Container, Long> {
	
	public List<Container> findAll();
	
	public Container findOne(Long id);
	
	public Container save(Container container);

	public List<Container> findByNameContainingIgnoreCase(String name);
	
	public List<Container> findByLocationAndDestinationAndStatus(String location, String destination, Status status);

	@Query("SELECT c FROM Container c WHERE c.location = :loc AND c.status = :status AND"
			+ " c.destination = ''")
	public List<Container> findByLocationAndStatusAndDestinationIsEmpty(@Param("loc") String location, 
			@Param("status") Status status);
	
	/**
	 * location should be passed in as location.toUpperCase()
	 * to allow a case insensitive search
	 * @param location
	 * @return
	 */
	@Query("SELECT c FROM Container c WHERE (c.location = :loc AND status = 'READY')"
			+ " OR (c.destination = :loc AND status = 'ARRIVED')")
	public List<Container> findByCurrentLocation(@Param("loc") String location);
	
	
	/**
	 * Both parameters should be passed in with a call to .toUpperCase()
	 * to allow a case insensitive search
	 * @param name
	 * @param location
	 * @return
	 */
	@Query("SELECT c FROM Container c WHERE UPPER(c.name) LIKE %:name%"
			+ " AND ((c.location = :loc AND status = 'READY') OR (c.destination = :loc AND status = 'ARRIVED'))")
	public List<Container> findByNameAndCurrentLocation(@Param("name") String name,
			@Param("loc") String location);

	
}
