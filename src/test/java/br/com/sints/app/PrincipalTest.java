package br.com.sints.app;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.sints.app.persistence.DaoUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
@Transactional
public class PrincipalTest {

	@Inject
	DaoUser daoUser;

	@Test
	public void insertUser() {
		System.out.println("PrincipalTest.insertUser()");
	}
}
