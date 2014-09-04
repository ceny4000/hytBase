package com.hyt.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Title: Sample2
 * Description: 範例用Entity Sample2
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/21
 */
@Entity  
@Table(name="base_sample2")
public class Sample2 {
	
	/** TABLE NAME**/
	public static String TABLE_NAME = "base_sample2";
	
	/** ID **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 串接Sample1的ID **/
	@Column(name="sample1Id")
	private Long sample1Id;
	
	/** 文字1 **/
	@Column(name="text1")
	private String text1;
	
	/** 數字1 **/
	@Column(name="integer1")
	private Integer integer1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSample1Id() {
		return sample1Id;
	}

	public void setSample1Id(Long sample1Id) {
		this.sample1Id = sample1Id;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public Integer getInteger1() {
		return integer1;
	}

	public void setInteger1(Integer integer1) {
		this.integer1 = integer1;
	}
}
