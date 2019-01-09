package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.Service;
import fr.uha.ensisa.Sharloc.metier.User;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

	@Query("select a from Service a where a.title=:x")
	public Service findServiceByTitle(@Param("x")String title);
	
	@Query("SELECT a FROM Service a WHERE a.service_id=:service_id")
	public Service findServiceById(@Param("service_id")Long service_id);
}
