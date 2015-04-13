package com.dihaw.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dihaw.entity.City;
import com.dihaw.entity.Gender;
import com.dihaw.entity.User;
import com.dihaw.entity.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
    /**
     * Retrieve the list of {@link User}s currently accessible from provided user.
     * 
     * @param pageable the paging specification
     * @return the {@link Page} of {@link User}s
     */
    @Query("select u from User u")
    Page<User> findAllUsers(Pageable pageable);

    /**
     * Update the {@link User} by his unique id.
     * 
     * @param id the id to update
     * @param firstName the user first name
     * @param username the user last name
     * @param gender the user gender (enum value)
     * @param city the user city
     */
    @Modifying
    @Query("update User u set u.firstName= :firstName, u.username= :username, u.gender= :gender, u.city= :city, " +
    		"u.email= :email, u.password= :password where u.id= :id")	
	void updateUser(@Param("id") 		int id, 
					@Param("firstName") String firstName, 
					@Param("username") 	String username, 
					@Param("gender") 	Gender gender, 
					@Param("city") 		City city,
					@Param("email") 	String email, 
					@Param("password") 	String password
				);
    
    /**
     * Find a single {@link User} by his unique id.
     * 
     * @param id the user name to look the User for
     * @return the {@link User} or <code>null</code>
     */
    @Query("select u from User u where u.id= :id")
    User findByid(@Param("id") int id);
    
	@Query("from User u where u.username = :username or u.email = :username")
	User findByUsername(@Param("username") String username);
	
    @Modifying
    @Query("update User u set u.status= :status where u.id= :id")	
	void changeStatus(@Param("id") int id, @Param("status") UserStatus userStatus);
    
	@Query("select count(u) from User u where u.username = :username")
	int count(@Param("username") String username);

    @Modifying
    @Query("update User u set u.lastConnection= :lastConnection where u.username= :username or u.email = :username")	
	void updateLastConnection(@Param("username") String username, @Param("lastConnection") Date lastConnection);
    
    @Query("from User u where u.username = :username or u.email = :username and u.password= :password")
	User findByUsernameAndPAssword(@Param("username") String username, @Param("password") String password);
    
    @Modifying
    @Query("update User u set u.accountNonExpired= :accountNonExpired where u.id= :id")	
	void changeAccountExpired(@Param("id") int id, @Param("accountNonExpired") int accountNonExpired);

    @Modifying
    @Query("update User u set u.accountNonLocked= :accountNonLocked where u.id= :id")	
	void changeAccountLocked(@Param("id") int id, @Param("accountNonLocked") int accountNonLocked);

    @Modifying
    @Query("update User u set u.credentialsNonExpired= :credentialsNonExpired where u.id= :id")	
	void changeCredentialsExpired(@Param("id") int id, @Param("credentialsNonExpired") int credentialsNonExpired);
    
}
