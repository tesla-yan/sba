package com.sba.training.repo;

import com.sba.training.model.TrainingDtls;

public interface TrainingsRepositoryCustom {
	
	TrainingDtls findAvgRating(Long mentorId, Long skillId);

}
