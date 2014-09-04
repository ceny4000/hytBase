package com.hyt.base.persistence.support;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.hyt.base.core.SpringUtils;

/**
 * Title: QueryAssistant
 * Description: 執行 Native 使用的function
 * Company: HYT
 * @author liyard.yang
 * @date 2014/7/28
 */
@Named
public class QueryAssistant {

	/** Entity Manager **/
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * 利用 NativeSql 查詢，預設回傳Map
	 * @param nativeSql
	 * @param parameters
	 * @return
	 * @throws ActionException
	 */
	public List<Map<String, Object>> nativeQuery(String nativeSql, Object... parameters) throws Exception {
		// 產生 Query 物件並執行回傳 ________________________________________________________________
		Query query = entityManager.createNativeQuery(nativeSql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> resultList = query.getResultList();
		entityManager.clear();
		
		return resultList;
	}
	
	/**
	 * 利用 NativeSql查詢資料，回傳原生Array
	 * **此method不帶map key 不需轉換，效能較好
	 * @param nativeSql
	 * @param parameters
	 * @return
	 * @throws ActionException
	 */
	public List<Object[]> nativeQueryWithArray(String nativeSql, Object... parameters) throws Exception {
		// 產生 Query 物件並執行回傳 ________________________________________________________________
		Query query = entityManager.createNativeQuery(nativeSql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		entityManager.clear();
		
		return resultList;
	}
	
	/**
	 * 利用 NativeSql查詢資料，回傳Class
	 * **此method不帶map key 不需轉換，效能較好
	 * @param nativeSql
	 * @param parameters
	 * @return
	 * @throws ActionException
	 */
	@SuppressWarnings("rawtypes")
	public List<Object> nativeQueryWithClass(String nativeSql, Class resultClass, Object... parameters) throws Exception {
		// 產生 Query 物件並執行回傳 ________________________________________________________________
		Query query = entityManager.createNativeQuery(nativeSql, resultClass);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		
		@SuppressWarnings("unchecked")
		List<Object> resultList = (List<Object>)query.getResultList();
		entityManager.clear();
		
		return resultList;
	}

	/**
	 * 利用 NativeSql 查詢分頁
	 * @param nativeSql nativeSql
	 * @param pageIndex 第幾頁 index
	 * @param pageSize 每頁大小(單頁最多幾筆資料)
	 * @param parameters
	 * @return
	 * @throws ActionException
	 */
	public List<Map<String, Object>> nativeQuery(String nativeSql, int pageIndex, int pageSize, Object... parameters) throws Exception {
		// 產生 Query 物件並執行回傳 _______________________________________________________________
		Query query = entityManager.createNativeQuery(nativeSql, QueryResult.class);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		query.setMaxResults(pageSize);
		query.setFirstResult((pageIndex - 1) * pageSize);
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> resultList = query.getResultList();
		entityManager.clear();
		
		return resultList;
	}

	/**
	 * 利用 NativeSql 查詢，只傳回第一筆資料
	 * @param nativeSql
	 * @param parameters
	 * @return
	 * @throws ActionException
	 */
	public Map<String, Object> nativeQuerySingleResult(String nativeSql, Object... parameters) throws Exception {
		List<Map<String, Object>> result = nativeQuery(nativeSql, 1, 1, parameters);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 計算NativeSql查詢的總筆數
	 * @param nativeSql
	 * @param parameters
	 * @return 總筆數
	 * @throws ActionException
	 */
	public int nativeQueryCount(String nativeSql, Object... parameters) throws Exception {
		String countSql = MessageFormat.format("select count(*) as cnt from ({0})", nativeSql);
		Map<String, Object> result = nativeQuerySingleResult(countSql, parameters);
		if (result != null && result.get("cnt") != null) {
			return (int) result.get("cnt");
		} else {
			return 0;
		}
	}

	/**
	 * 利用 NativeSql 執行資料庫更新
	 * @param nativeSql SQL語法
	 * @param parameters 查詢參數
	 * @return 更新筆數
	 * @throws ActionException
	 */
	public int executeUpdate(String nativeSql, Object... parameters) throws Exception {
		Query query = entityManager.createNativeQuery(nativeSql);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				query.setParameter(i + 1, parameters[i]);
			}
		}
		return query.executeUpdate();
	}

	/**
	 * 取得實體
	 * @return
	 */
	public static QueryAssistant getInstance() {
		return SpringUtils.getBean("queryAssistant");
	}

	/**
	 * 取得 PersistenceContext-EntityManager
	 * @return
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
