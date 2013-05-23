package br.com.sints.app.persistence;

import javax.inject.Named;

import br.com.sints.app.model.Role;

@Named
public class DaoRole extends HibernateDao<Role> {
	public DaoRole() {
		super(Role.class);
	}
}
