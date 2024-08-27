package com.synex.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.PolicyIssue;


@Repository 
public interface PolicyIssueRepository extends JpaRepository<PolicyIssue, Integer>{
	PolicyIssue findById(int id);
	List<PolicyIssue> findByUserId(int userId);
}
