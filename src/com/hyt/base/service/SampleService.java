package com.hyt.base.service;

import com.hyt.base.entity.Sample1;
import com.hyt.base.entity.Sample2;

/**
 * Title: SampleService
 * Description: 範例用Service
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/25
 */
public interface SampleService {

	/**
	 * 使用ID查詢 Sample1資料
	 * @param id id
	 * @return
	 */
	public Sample1 findSample1ById(Long id);

	/**
	 * 使用ID查詢 Sample2資料
	 * @param id id
	 * @return
	 */
	public Sample2 findSample2ById(Long id);

	/**
	 * 儲存Sample相關資料
	 * @param id id
	 * @return
	 */
	public void save(Sample1 sample1, Sample2 sample2);

}
