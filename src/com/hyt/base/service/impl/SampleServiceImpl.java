package com.hyt.base.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyt.base.dao.Sample1Dao;
import com.hyt.base.dao.Sample2Dao;
import com.hyt.base.entity.Sample1;
import com.hyt.base.entity.Sample2;
import com.hyt.base.service.SampleService;

@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private Sample1Dao sample1Dao;
	
	@Autowired
	private Sample2Dao sample2Dao;

	@Override
	public Sample1 findSample1ById(Long id) {
		return sample1Dao.findById(id);
	}

	@Override
	public Sample2 findSample2ById(Long id) {
		return sample2Dao.findById(id);
	}
	
	@Override
	@Transactional
	public void save(Sample1 sample1, Sample2 sample2) {
		if (sample1 != null) {
			sample1Dao.save(sample1);
		}
		if (sample2 != null) {
			sample2Dao.save(sample2);
		}
	}
}
