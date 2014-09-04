package com.hyt.base.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HTTPUtils {
	private static ServletContext servletContext;
	private static ThreadLocal<HttpServletRequest> threadRequest = new ThreadLocal<HttpServletRequest>();;
	private static ThreadLocal<HttpServletResponse> threadResponse = new ThreadLocal<HttpServletResponse>();
	private static String REQUEST_SCOPE_NAME = "HTTPUtils.RequestScopeMap";
	private static String SESSION_SCOPE_NAME = "HTTPUtils.SessionScopeMap";

	public static ServletContext getServletContext() {
		return servletContext;
	}

	public static void setServletContext(ServletContext servletContext) {
		HTTPUtils.servletContext = servletContext;
	}

	public static HttpServletRequest getRequest() {
		return threadRequest.get();
	}

	public static void setRequest(HttpServletRequest request) {
		if (request == null) {
			threadRequest.remove();
		} else {
			threadRequest.set(request);
		}
	}

	public static HttpServletResponse getResponse() {
		return threadResponse.get();
	}

	public static void setResponse(HttpServletResponse response) {
		if (response == null) {
			threadResponse.remove();
		} else {
			threadResponse.set(response);
		}
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getRequestScope() {
		HttpServletRequest request = getRequest();
		HashMap<String, Object> requestScopeMap = (HashMap<String, Object>) request.getAttribute(REQUEST_SCOPE_NAME);
		if (requestScopeMap == null) {
			requestScopeMap = new HashMap<String, Object>();
			request.setAttribute(REQUEST_SCOPE_NAME, requestScopeMap);
		}
		return requestScopeMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getSessionScope() {
		HttpSession session = getSession();
		HashMap<String, Object> scopeMap = (HashMap<String, Object>) session.getAttribute(SESSION_SCOPE_NAME);
		if (scopeMap == null) {
			scopeMap = new HashMap<String, Object>();
			session.setAttribute(SESSION_SCOPE_NAME, scopeMap);
		}
		return scopeMap;
	}

	public static Object getRequestAttribute(String key) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			return request.getAttribute(key);
		} else {
			return null;
		}
	}

	public static void setRequestAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}

}
