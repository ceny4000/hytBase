package com.hyt.base.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Title: Sample1
 * Description: 範例用Entity Sample1
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/21
 */
@Entity  
@Table(name="base_sample1")
public class Sample1 {
	
	/** TABLE NAME**/
	public static String TABLE_NAME = "base_sample1";
	
	/** ID **/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 文字1 **/
	@Column(name="text1")
	private String text1;
	
	/** 文字2 **/
	@Column(name="text2")
	private String text2;
	
	/** 日期1 **/
	@Column(name="date1")
	private Date date1;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}
}
