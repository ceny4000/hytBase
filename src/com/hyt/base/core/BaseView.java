package com.hyt.base.core;

import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.apache.log4j.Logger;

/**
 * Title: BaseView
 * Description: Base View 基本View
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/25
 */
public abstract class BaseView<V> {
	protected static Logger logger = Logger.getLogger(BaseView.class);
	
	/** View Form Class */
	protected Class<V> viewFormClazz = null;

	/** 存放目前頁面內所需要的所有資料 */
	protected V viewForm = null;
	
	/**
	 * 初始化每個頁面的顯示資料(ViewForm)
	 * @param viewForm
	 * @throws ActionException
	 */
	protected abstract void initViewForm(V viewForm);
	
	/**
	 * 建構子
	 */
	@SuppressWarnings("unchecked")
	public BaseView() {
		ParameterizedType aParameterizedType = getParameterizedType(getClass());// (ParameterizedType)
		// View Class 建立 _______________________________________________________________
		viewFormClazz = (Class<V>) aParameterizedType.getActualTypeArguments()[0];
		viewForm = newViewForm();
	}
	
	/**
	 * bean建立前執行
	 */
	@PostConstruct
	public void init() {
		PhaseId phaseId = FacesContext.getCurrentInstance().getCurrentPhaseId();
		if (phaseId.getOrdinal() == PhaseId.RENDER_RESPONSE.getOrdinal()) {
			initViewForm(viewForm);
		}
	}
	
	/**
	 * getParameterizedType
	 * @param pageCodeClazz
	 * @return
	 */
	protected ParameterizedType getParameterizedType(Class<?> pageCodeClazz) {
		if (pageCodeClazz.getGenericSuperclass() instanceof ParameterizedType) {
			return (ParameterizedType) pageCodeClazz.getGenericSuperclass();
		} else {
			return getParameterizedType(pageCodeClazz.getSuperclass());
		}
	}

	/**
	 * 新建立viewForm
	 * @return
	 */
	protected V newViewForm() {
		V aJsfViewForm = null;
		try {
			aJsfViewForm = (V)viewFormClazz.newInstance();
		} catch (Exception e) {
		}
		return aJsfViewForm;
	}
	
	public V getViewForm() {
		return viewForm;
	}
	
	public void setViewForm(V viewForm){
		this.viewForm = viewForm;
	}
}
