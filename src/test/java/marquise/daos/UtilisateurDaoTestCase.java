package marquise.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import marquise.daos.impl.DataSourceProvider;
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
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (1,'Drama','Drama')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (2,'Comedy','Comedy')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (3,'Action','Action')");
		}
	}
	@Test
	public void shouldListUtilisateurs() {
		// WHEN
		List<Utilisateur> utilisateurs = utilisateurDao.listUtilisateurs();
		// THEN
		assertThat(utilisateurs).hasSize(3);
		assertThat(utilisateurs).extracting("id", "nom", "prenom").containsOnly(tuple(3, "Action", "Action"), tuple(2, "Comedy","Comedy"),
				tuple(1, "Drama","Drama"));
	}
	
	@Test
	public void shouldGetUtilisateur() {
		// WHEN
		Utilisateur utilisateur = utilisateurDao.getUtilisateur(1);
		// THEN
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isEqualTo(1);
		assertThat(utilisateur.getNom()).isEqualTo("Drama");
		assertThat(utilisateur.getPrenom()).isEqualTo("Drama");
	}

	@Test
	public void shouldAddUtilisateur() throws Exception {
		// WHEN
		Utilisateur utilisateur = utilisateurDao.addUtilisateur("test", "test");
		// THEN
		assertThat(utilisateur.getId()).isNotNull();
		assertThat(utilisateur.getNom()).isEqualTo("test");
		assertThat(utilisateur.getPrenom()).isEqualTo("test");

		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM utilisateur WHERE utilisateur_id = ?")) {
			stmt.setInt(1, utilisateur.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("utilisateur_id")).isEqualTo(utilisateur.getId());
				assertThat(rs.getString("nom")).isEqualTo("test");
				assertThat(rs.getString("prenom")).isEqualTo("test");
				assertThat(rs.next()).isFalse();
			}
		}
	}
}
