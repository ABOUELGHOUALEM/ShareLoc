package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.AchievedService;


public interface AchievedServiceRepository extends JpaRepository<AchievedService, Long>{
	
	@Query("SELECT a FROM AchievedService a WHERE a.achieved_id=:achieved_id")
	public AchievedService findAchievedServiceById(@Param("achieved_id")Long achieved_id);
}
