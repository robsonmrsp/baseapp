package br.com.mr.app.persistence;

import javax.inject.Named;

import br.com.mr.app.model.Role;

@Named
public class DaoRole extends HibernateDao<Role> {
	public DaoRole() {
		super(Role.class);
	}
}
