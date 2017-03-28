package com.capgemini.service;

import java.util.List;

import com.capgemini.service.to.MatchTO;

public interface MatchHistoryService {
	
	public List<MatchTO> getUserMatchHistory(Long id);
	public MatchTO addMatchToHistory(MatchTO match);
	
}
