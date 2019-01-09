package fr.uha.ensisa.Sharloc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uha.ensisa.Sharloc.metier.Colocation;
import fr.uha.ensisa.Sharloc.metier.User;

public interface UserRepository extends JpaRepository<User, Long >{

	@Query("select a from User a where a.email=:x and a.password=:y")
	public User login(@Param("x")String email,@Param("y")String pass);
	
	@Query("SELECT COUNT(colocation) FROM User a WHERE a.colocation.name=:x")
	public int countUserPerColocation(@Param("x")String colocationname);
	
	@Query("SELECT a FROM User a WHERE a.colocation.name=:x")
	public List<User> getUsersPerColocation(@Param("x")String colocationname);
	
	@Query("SELECT a FROM User a WHERE a.userID=:userID")
	public User findUserById(@Param("userID")Long userID);
	
	@Query("SELECT a FROM User a WHERE a.username=:username")
	User  findByUsername(@Param("username") String username);
	
	@Query("SELECT a FROM User a WHERE a.username=:username AND a.password=:password")
	public Optional<User> findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	User  findByEmail( String username);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User u SET u.colocation=:colocation WHERE userID =:userID")
	public int updateUserColocation(@Param("colocation") Colocation colocation, @Param("userID") Long userID);
}
