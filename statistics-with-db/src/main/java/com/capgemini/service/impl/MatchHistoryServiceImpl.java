package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dataaccess.dao.MatchDao;
import com.capgemini.service.MatchHistoryService;
import com.capgemini.service.mapper.MatchMapper;
import com.capgemini.service.to.MatchTO;
@Service
@Transactional(readOnly=true)
public class MatchHistoryServiceImpl implements MatchHistoryService {

	@Autowired
	MatchDao matchDao;

	@Override
	public List<MatchTO> getUserMatchHistory(Long id) {
		return MatchMapper.map2TOs(matchDao.getUserMatchHistory(id));
	}

	@Override
	@Transactional(readOnly=false)
	public MatchTO addMatchToHistory(MatchTO match) {
		return MatchMapper.map(matchDao.save(MatchMapper.map(match)));
	}
	
}
