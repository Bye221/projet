
package marquise.daos;


import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.Before;
import org.junit.Test;

import marquise.daos.impl.DataSourceProvider;

import marquise.daos.UtilisateurDao;

import marquise.daos.impl.UtilisateurDaoImpl;
import marquise.projos.Utilisateur;


public class UtilisateurDaoTestCase {

	private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM information");
			stmt.executeUpdate("DELETE FROM utilisateur");
			stmt.executeUpdate("DELETE FROM informations");
			stmt.executeUpdate("DELETE FROM utilisateurs");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`) VALUES (1,'Drama')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`) VALUES (2,'Comedy')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`) VALUES (3,'Action')");
		}
	}

	@Test
	public void shouldListGenres() {
		// WHEN
		List<Utilisateur> genres = utilisateurDao.listUtilisateurs();
		// THEN
		assertThat(genres).hasSize(3);
		assertThat(genres).extracting("id", "nom").containsOnly(tuple(3, "Action"), tuple(2, "Comedy"),
				tuple(1, "Drama"));
	}
	
	
	
	
	


	
	




}
