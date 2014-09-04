package com.hyt.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyt.base.entity.Sample2;

/**
 * Title: Sample2Dao
 * Description: 範例用 Dao Sample2
 * Company: HYT
 * @author Windows
 * @date 2014/7/21
 */
@Repository
public interface Sample2Dao extends JpaRepository<Sample2, Long> {
	public Sample2 findById(Long id);
}
