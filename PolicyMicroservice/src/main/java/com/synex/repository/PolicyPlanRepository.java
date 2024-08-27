package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synex.domain.PolicyPlan;

@Repository 
public interface PolicyPlanRepository extends JpaRepository<PolicyPlan, Integer>{
	List<PolicyPlan> findByName(String name);
	public List<PolicyPlan> findByNameLike(String policyName);
	PolicyPlan findByPolicyId(int id);
}
