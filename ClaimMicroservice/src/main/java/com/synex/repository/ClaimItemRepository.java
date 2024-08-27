package com.synex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.Claim;
import com.synex.domain.ClaimItem;

@Repository
public interface ClaimItemRepository extends JpaRepository<ClaimItem, Integer>{
	public ClaimItem findById(int id);
}
