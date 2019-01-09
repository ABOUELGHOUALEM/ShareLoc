package fr.uha.ensisa.Sharloc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.ChatColocation;


public interface ChatColocationRepository extends JpaRepository<ChatColocation, Integer>{

	@Query("SELECT a FROM ChatColocation a WHERE a.colocation.name=:x")
	public List<ChatColocation> getMessagePerColocation(@Param("x")String colocationname);
}
