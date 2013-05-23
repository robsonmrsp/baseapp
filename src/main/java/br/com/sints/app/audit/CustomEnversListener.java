package br.com.sints.app.audit;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.sints.app.model.CustomRevisionEntity;

public class CustomEnversListener implements RevisionListener {
	@Override
	public void newRevision(Object revisionEntity) {

		CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		customRevisionEntity.setUsername(authentication.getName());
	}
}
