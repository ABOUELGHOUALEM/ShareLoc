package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer>{

	@Query("select a from Service a where a.title=:x")
	public Service findServiceByTitle(@Param("x")String title);
}
