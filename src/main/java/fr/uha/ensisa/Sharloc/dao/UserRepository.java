package fr.uha.ensisa.Sharloc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uha.ensisa.Sharloc.metier.User;

public interface UserRepository extends JpaRepository<User, String >{

}
