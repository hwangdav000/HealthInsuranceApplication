package com.synex.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	List<Role> findByRoleName(String name);
}
