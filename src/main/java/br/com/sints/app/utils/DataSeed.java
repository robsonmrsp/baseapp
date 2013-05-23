package br.com.sints.app.utils;

import java.util.List;

import javax.inject.Named;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Named
@Transactional
public class DataSeed {

	private List<String> viewScripts;
	private SessionFactory sessionFactory;

	public DataSeed() {
		// createOrUpdateDatabaseViews();
	}

	public void createOrUpdateDatabaseViews() {

		for (String script : this.viewScripts) {
			try {
				getSessionFactory().openSession().createSQLQuery(script).executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println("UpdateRequiredViews.createOrUpdateDatabaseViews()");
	}

	public void setViewScripts(List<String> viewScripts) {
		this.viewScripts = viewScripts;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}