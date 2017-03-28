package com.capgemini.service;

import com.capgemini.service.to.UserTO;

public interface StatisticsService {

	void updateStatistics(UserTO winner, UserTO looser);
	
}
