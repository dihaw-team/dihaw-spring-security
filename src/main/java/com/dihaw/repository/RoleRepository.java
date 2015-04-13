package com.dihaw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dihaw.entity.UserRole;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {

}
