package com.hyt.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hyt.base.entity.Sample1;

/**
 * Title: Sample1Dao
 * Description: 範例用 Dao Sample1
 * Company: HYT
 * @author Windows
 * @date 2014/7/21
 */
@Repository
public interface Sample1Dao extends JpaRepository<Sample1, Long> {
	public Sample1 findById(Long id);
}
