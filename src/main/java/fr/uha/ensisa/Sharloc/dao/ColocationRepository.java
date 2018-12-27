package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

public interface ColocationRepository extends JpaRepository<Colocation, Integer>{

	@Query("select a from Colocation a where a.name=:x")
	public Colocation findColocationByName(@Param("x")String name);
	
}
