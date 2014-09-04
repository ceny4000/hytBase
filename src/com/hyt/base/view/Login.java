package com.hyt.base.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.hyt.base.core.BaseView;
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
public class Login extends BaseView<SampleView> {

	@SuppressWarnings("unchecked")
	@Override
	protected void initViewForm(SampleView viewForm) {
	
	}
}
