package com.hyt.base.view;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.hyt.base.core.BaseView;
import com.hyt.base.core.SpringUtils;
import com.hyt.base.entity.Sample1;
import com.hyt.base.persistence.support.QueryAssistant;
import com.hyt.base.service.SampleService;
import com.hyt.base.viewform.SampleView;

/**
 * Title: Sample
 * Description: 範例用Sample，將所有畫面要用到的Function 及頁面邏輯寫在此處
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/28
 */
@ManagedBean
@ViewScoped
public class Sample extends BaseView<SampleView> {
	
	private static Logger logger = Logger.getLogger(Sample.class);
	/* (non-Javadoc)
	 * @see com.hyt.base.view.BaseView#initViewForm(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void initViewForm(SampleView viewForm) {
		
		//方法1：使用JPA進行Query
		SampleService sampleService = SpringUtils.getBean("sampleService");
		getViewForm().setS1t1(sampleService.findSample1ById(1L));
		
		//方法2：使用Native SQL進行Query，結果回填至JPA Entity
		List<Sample1> ls2 = null;
		try {
			ls2 = (List<Sample1>)(Object)QueryAssistant.getInstance().nativeQueryWithClass("select * from " + Sample1.TABLE_NAME + " where id = ?1", Sample1.class, 2L);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ls2 != null && ls2.size() > 0) {
			getViewForm().setS1t2(ls2.get(0));	
		}
		
		//方法3：使用Native SQL進行Query，結果回填至Map
		List<Map<String, Object>> ls3 = null;
		try {
			ls3 = QueryAssistant.getInstance().nativeQuery("select * from " + Sample1.TABLE_NAME + " where id = ?1", 3L);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ls3 != null && ls3.size() > 0) {
			Sample1 s1t3 = new Sample1();
			s1t3.setText1((String)ls3.get(0).get("text1"));
			getViewForm().setS1t3(s1t3);
		}
	}
}
