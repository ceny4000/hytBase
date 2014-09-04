package com.hyt.base.viewform;

import com.hyt.base.entity.Sample1;
import com.hyt.base.entity.Sample2;

/**
 * Title: SampleView
 * Description: 範例用Sample View，將所有畫面要用到的變數及getter/setter寫在此處
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/28
 */
public class SampleView {
	
	/** 範例用Sample1 **/
	private Sample1 s1t1;
	private Sample1 s1t2;
	private Sample1 s1t3;
	private Sample1 s1t4;
	
	/** 範例用Sample2 **/
	private Sample2 s2;

	public Sample1 getS1t1() {
		return s1t1;
	}

	public void setS1t1(Sample1 s1t1) {
		this.s1t1 = s1t1;
	}

	public Sample1 getS1t2() {
		return s1t2;
	}

	public void setS1t2(Sample1 s1t2) {
		this.s1t2 = s1t2;
	}

	public Sample1 getS1t3() {
		return s1t3;
	}

	public void setS1t3(Sample1 s1t3) {
		this.s1t3 = s1t3;
	}

	public Sample1 getS1t4() {
		return s1t4;
	}

	public void setS1t4(Sample1 s1t4) {
		this.s1t4 = s1t4;
	}

	public Sample2 getS2() {
		return s2;
	}

	public void setS2(Sample2 s2) {
		this.s2 = s2;
	}
}
