package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uha.ensisa.Sharloc.metier.ChatColocation;
import fr.uha.ensisa.Sharloc.metier.Colocation;

public interface ChatColocationRepository extends JpaRepository<ChatColocation, Integer>{

	
}
