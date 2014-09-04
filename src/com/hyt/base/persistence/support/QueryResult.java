package com.hyt.base.persistence.support;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


public class QueryResult {
	private Map<String, Object> m_mapProp = new LinkedHashMap<String, Object>();

	/**
	 * 建構子
	 */
	public QueryResult() {
	}

	/**
	 * 取得欄位資料Map
	 * @return
	 */
	public Map<String, Object> getPropertyMap() {
		return m_mapProp;
	}

	/**
	 * 設定欄位資料
	 * @param sFieldName 欄位名稱
	 * @param aValue 欄位值
	 */
	public void put(Object sFieldName, Object aValue) {
		getPropertyMap().put(((String) sFieldName).toUpperCase(), aValue);
	}

	/**
	 * 取得欄位資料
	 * @param sFieldName 欄位名稱
	 * @return 欄位值
	 */
	public Object get(Object sFieldName) {
		return getPropertyMap().get(((String) sFieldName).toUpperCase());
	}	
	
	/**
	 * 取得欄位資料
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return 欄位值
	 */
	public Object get(Object sFieldName,Object defaultValue) {
		Object value = getPropertyMap().get(((String) sFieldName).toUpperCase());
		if(value!=null) {
			return value;
		}
		else {
			return defaultValue;
		}
	}	

	/**
	 * 取得字串型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public String getString(String sFieldName) {
		return this.getString(sFieldName, "");
	}
	
	/**
	 * 取得字串型態欄位值
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return
	 */
	public String getString(String sFieldName,String defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return defaultValue;
		} else {
			return oValue.toString();
		}
	}	

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public int getInt(String sFieldName) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return 0;
		} else if (oValue instanceof Integer) {
			return (Integer) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).intValue();
		} else {
			return Integer.valueOf(oValue.toString(), 10);
		}
	}

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public Integer getInteger(String sFieldName) {
		return this.getInteger(sFieldName, null);
	}
	
	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return
	 */
	public Integer getInteger(String sFieldName,Integer defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return defaultValue;
		} else if (oValue instanceof Integer) {
			return (Integer) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).intValue();
		} else {
			return Integer.valueOf(oValue.toString(), 10);
		}
	}	

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public Long getLong(String sFieldName) {
		return this.getLong(sFieldName,null);
	}
	
	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return
	 */
	public Long getLong(String sFieldName,Long defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return null;
		} else if (oValue instanceof Long) {
			return (Long) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).longValue();
		} else {
			return Long.valueOf(oValue.toString(), 10);
		}
	}	

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public long getLongValue(String sFieldName) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return 0;
		} else if (oValue instanceof Long) {
			return (Long) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).longValue();
		} else {
			return Long.valueOf(oValue.toString(), 10);
		}
	}

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public Double getDouble(String sFieldName) {
		return this.getDouble(sFieldName, null);
	}
	
	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public Double getDouble(String sFieldName,Double defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return defaultValue;
		} else if (oValue instanceof Double) {
			return (Double) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).doubleValue();
		} else {
			return Double.valueOf(oValue.toString());
		}
	}	

	/**
	 * 取得數值型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public double getDoubleValue(String sFieldName) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return 0;
		} else if (oValue instanceof Double) {
			return (Double) oValue;
		} else if (oValue instanceof Number) {
			return ((Number) oValue).doubleValue();
		} else {
			return Double.valueOf(oValue.toString());
		}
	}

	/**
	 * 取得日期型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public Date getDate(String sFieldName) {
		return this.getDate(sFieldName, null);
	}
	
	/**
	 * 取得日期型態欄位值
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return
	 */
	public Date getDate(String sFieldName,Date defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return defaultValue;
		} else {
			return (Date) oValue;
		}
	}	

	/**
	 * 取得 BigDecimal 型態欄位值
	 * @param sFieldName 欄位名稱
	 * @return
	 */
	public BigDecimal getBigDecimal(String sFieldName) {
		return this.getBigDecimal(sFieldName, null);
	}
	
	/**
	 * 取得 BigDecimal 型態欄位值
	 * @param sFieldName 欄位名稱
	 * @param defaultValue 預設值
	 * @return
	 */
	public BigDecimal getBigDecimal(String sFieldName,BigDecimal defaultValue) {
		Object oValue = get(sFieldName);
		if (oValue == null) {
			return defaultValue;
		} else {
			return new BigDecimal(String.valueOf(oValue));
		}
	}	
}
