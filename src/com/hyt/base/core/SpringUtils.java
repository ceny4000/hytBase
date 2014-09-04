package com.hyt.base.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Named
public class SpringUtils implements ApplicationContextAware, BeanPostProcessor {
	
	private static ApplicationContext rootContext;
	private static ApplicationContext context;
	private static AutowireCapableBeanFactory autowireCapableBeanFactory;

	/** Spring config path **/
	private static String configLocation = "classpath*:spring-context.xml";

	/**
	 * 取得Application Context
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		if (context == null) {
			synchronized (SpringUtils.class) {
				if (context == null) {
					if (HTTPUtils.getServletContext() != null) {
						try {
							context = WebApplicationContextUtils.getRequiredWebApplicationContext(HTTPUtils.getServletContext());
						} catch (Throwable e) {
						}
					}
					if (context == null) {
						context = new ClassPathXmlApplicationContext(configLocation);
					}
				}
			}
		}
		return context;
	}

	/**
	 * 取得Bean
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return (T) getApplicationContext().getBean(clazz);
	}

	public static Properties getProperties(String name) {
		return (Properties) getBean(name);
	}

	public static DataSource getDataSource() {
		return getBean("dataSource");
	}

	public static PlatformTransactionManager getTransactionManager() {
		return getBean("transactionManager");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return getBean("entityManagerFactory");
	}

	public static Connection getConnection() throws SQLException {
		return DataSourceUtils.getConnection(getDataSource());
	}

	public static String getMessage(String key, String defaultValue, String... params) {
		return getMessage((Locale) null, key, defaultValue, (Object[]) params);
	}

	public static String getMessage(Locale locale, String key, String defaultValue, Object... params) {
		if (context != null) {
			return getApplicationContext().getMessage(key, params, defaultValue, locale);
		} else {
			return null;
		}
	}

	private static AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
		if (autowireCapableBeanFactory == null) {
			synchronized (SpringUtils.class) {
				if (autowireCapableBeanFactory == null) {
					autowireCapableBeanFactory = findAutoWiringBeanFactory(null);
				}
			}
		}
		return autowireCapableBeanFactory;
	}

	private static AutowireCapableBeanFactory findAutoWiringBeanFactory(ApplicationContext context) {
		if (context == null) {
			context = getApplicationContext();
		}
		if (context instanceof AutowireCapableBeanFactory) {
			return ((AutowireCapableBeanFactory) context);
		}
		if (context instanceof ConfigurableApplicationContext) {
			return ((ConfigurableApplicationContext) context).getBeanFactory();
		}
		if (context.getParent() != null) {
			return findAutoWiringBeanFactory(context.getParent());
		}
		return null;
	}

	public static void autowireBean(Object bean) {
		if (bean != null) {
			AutowireCapableBeanFactory beanFactory = getAutowireCapableBeanFactory();
			if (beanFactory != null) {
				beanFactory.autowireBean(bean);
			}
		}
	}

	public static void close() {
		if (context != null && context instanceof DisposableBean) {
			try {
				((DisposableBean) context).destroy();
			} catch (Throwable e) {
			}
			context = null;
		}
		if (rootContext != null && rootContext instanceof DisposableBean) {
			try {
				((DisposableBean) rootContext).destroy();
			} catch (Throwable e) {
			}
			rootContext = null;
		}
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		if (SpringUtils.context == null) {
			SpringUtils.context = context;
		}
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public static TransactionStatus createTransaction(String name, int bropagationBehavior) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(bropagationBehavior);
		def.setName(name);
		return getTransactionManager().getTransaction(def);
	}
	
	public static TransactionStatus createTransaction(String name, Propagation bropagationBehavior) {
		return createTransaction(name, bropagationBehavior.value());
	}

	public static TransactionStatus createTransaction(String name) {
		return createTransaction(name, TransactionDefinition.PROPAGATION_REQUIRED);
	}
	
	public static void commitTransaction(TransactionStatus status) {
		getTransactionManager().commit(status);
	}
	
	public static void rollbackTransaction(TransactionStatus status) {
		getTransactionManager().rollback(status);
	}
	
	public static <T> T executeTransaction(TransactionCallback<T> action) {
		return new TransactionTemplate(getTransactionManager()).execute(action);
	}
}
