package br.com.sints.app.persistence;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@SuppressWarnings("unchecked")
public class HibernateDao<Entity> {

	Class<Entity> clazz;

	public HibernateDao(Class<Entity> clazz) {
		this.clazz = clazz;
	}

	@Inject
	SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Entity find(Serializable key) {
		return (Entity) sessionFactory.getCurrentSession().get(clazz, key);
	}

	public List<Entity> getAll() {
		List<Entity> entities = sessionFactory.getCurrentSession().createCriteria(clazz).list();
		return entities;
	}

	public Entity save(Entity entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	public Boolean delete(Serializable entityId) {
		Entity entity = find(entityId);
		if (entity != null) {
			sessionFactory.getCurrentSession().delete(entity);
		}
		return Boolean.TRUE;
	}

	public Criteria criteria() {
		return sessionFactory.getCurrentSession().createCriteria(clazz);
	}

	public Query createQuery(String hql) {
		return getSession().createQuery(hql);
	}
}
