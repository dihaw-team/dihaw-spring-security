package com.dihaw.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dihaw.entity.UserAttempts;

@Repository
public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Integer> {
	
    @Query("update UserAttempts ua set ua.attempts = 0, ua.lastModified = null WHERE ua.username = :username")
	void resetFailAttempts(@Param("username") String username);

    @Modifying
    @Query("update UserAttempts ua set ua.lastModified= :lastModified, ua.attempts = ua.attempts + 1 where ua.username= :username")
	void updateFailAttempts(@Param("lastModified") Date lastModified, @Param("username") String username);

	@Query("select ua from UserAttempts ua where ua.username = :username")
	UserAttempts findUserAttemptsByUsername(@Param("username") String username);
	
	@Modifying
	@Query("update UserAttempts ua set ua.attempts= :attempts, ua.lastModified = :lastModified where ua.username= :username")
	void updateLoginFailureCount(	@Param("username") String username, 
									@Param("attempts") int attempts, 
									@Param("lastModified") Date lastModified);
	@Modifying
	@Query("delete from UserAttempts ua where ua.username= :username")
	void deleteLoginFailureCount(@Param("username") String username);
	
}
