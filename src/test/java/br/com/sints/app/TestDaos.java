package br.com.sints.app;

import javax.inject.Inject;

import org.junit.Test;

import br.com.sints.app.persistence.DaoUser;

public class TestDaos {

	@Inject
	DaoUser daoPanel;

	@Test
	public void savePanel() {

		// Panel panel = new Panel();
		//
		// panel.setCode("PANEL_01");
		// panel.setDescription("Painel de teste 01");
		// // panel.setEndomarketingTime(600l);
		// panel.setName("Painel 01 de teste");
		// Media media = new Media();
		// media.setDescription("imagem da propaganda 1");
		// panel.addMedia(media);
		//
		// daoPanel.save(panel);
		// System.out.println("TestDaos.savePanel()" + panel.getId());
		// assertTrue("Deveria ter salvo o painel: ", panel.getId() > 0);
	}
}
