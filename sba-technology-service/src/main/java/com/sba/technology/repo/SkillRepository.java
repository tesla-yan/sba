package com.sba.technology.repo;

import com.sba.technology.entity.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

	@Query(value = "SELECT s FROM Skill s")
	Page<Skill> findAll(Pageable pageable);

	@Query(value = "SELECT s FROM Skill s WHERE LOWER(name)=?1")
	public Skill findByName(String skillName);

	@Query(value = "SELECT s FROM Skill s WHERE LOWER(name) like ?1")	
	public List<Skill> findByLikeName(String skillName);

}
