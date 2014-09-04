package com.hyt.base.core;

import javax.el.ELContext;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

/**
 * Title: ApplyRequestPhaseListener
 * Description: Listener 在每個畫面前呼叫，初始化工作
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/24
 */
public class ApplyRequestPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -4340798381693945009L;

	protected static Logger logger = Logger.getLogger(ApplyRequestPhaseListener.class);

	/**
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
	
	/**
	 * 每個Request 呼叫前執行
	 */
	public void beforePhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		
		UIViewRoot view = FacesContext.getCurrentInstance().getViewRoot();
		String sViewId = "";
		if (view != null) {
			sViewId = view.getViewId();
			sViewId = "#{" + sViewId.replace(".xhtml", "").substring(sViewId.lastIndexOf("/") + 1) + "}";
		}
		
		if (context.getCurrentPhaseId().getOrdinal() == PhaseId.RENDER_RESPONSE.getOrdinal()
				|| context.getCurrentPhaseId().getOrdinal() == PhaseId.UPDATE_MODEL_VALUES.getOrdinal()) {
			//將currBean定位為當前畫面的bean name
			event.getFacesContext().getExternalContext().getRequestMap().put("currBean", resolveExpression(context, sViewId));
		}
	}

	/**
	 * 每個Request 呼叫後執行
	 */
	public void afterPhase(PhaseEvent event) {
	}
	
    /**
     * 取得目前的bean
     * @param sExpression
     * @return
     */
    public static Object resolveExpression(FacesContext context, String sExpression) {
        ELContext elc = context.getELContext();
        Object value = context.getApplication().getExpressionFactory().createValueExpression(elc, sExpression, Object.class).getValue(elc);

        return value;
    }
}
