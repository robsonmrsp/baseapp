package br.com.mr.app.persistence;

import javax.inject.Named;

import org.hibernate.criterion.Restrictions;

import br.com.mr.app.model.AppUser;

@Named
public class DaoUser extends HibernateDao<AppUser> {

	public DaoUser() {
		super(AppUser.class);
	}

	public AppUser findByUserName(String username) {
		AppUser user = null;
		try {
			user = (AppUser) criteria().add(Restrictions.eq("username", username)).list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
