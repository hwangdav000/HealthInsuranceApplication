package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.synex.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {
	
	@Query(value = "SELECT COUNT(*) FROM Claim WHERE userId = :userId", nativeQuery = true)
	public int getUserClaimCount(@Param("userId") int userId);
	
	public List<Claim> findByUserId(int userId);
	public Claim findById(int userId);
}
