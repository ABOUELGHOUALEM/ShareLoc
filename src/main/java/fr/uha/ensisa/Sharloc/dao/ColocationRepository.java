package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uha.ensisa.Sharloc.metier.Colocation;

public interface ColocationRepository extends JpaRepository<Colocation, Integer>{

}
