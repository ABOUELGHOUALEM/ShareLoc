package fr.uha.ensisa.Sharloc.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.User;

public interface UserRepository extends JpaRepository<User, String >{

	@Query("select a from User a where a.email=:x and a.password=:y")
	public User login(@Param("x")String email,@Param("y")String pass);
	
	public User findByEmail(String email);
}
