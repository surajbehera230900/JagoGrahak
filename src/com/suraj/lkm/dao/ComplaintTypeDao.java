package com.suraj.lkm.dao;


import java.util.List;

import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import com.suraj.lkm.entity.ComplaintTypeEntity;

/**
To-Do Item 1.2: Define a custom repository for managing ComplaintTypes.
	TODO:
	--Use custom repository.
	--Add a method to get all ComplaintTypes details from the database.
*/
@RepositoryDefinition(idClass = Integer.class,domainClass = ComplaintTypeEntity.class)
@Transactional(value = "txManager")
public interface ComplaintTypeDao {
	public List<ComplaintTypeEntity> findAll();
}
