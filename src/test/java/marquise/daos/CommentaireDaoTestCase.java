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

import marquise.daos.impl.CommentaireDaoImpl;
import marquise.daos.impl.DataSourceProvider;

import marquise.projos.Commentaire;


public class CommentaireDaoTestCase {

	private CommentaireDao commentaireDao = new CommentaireDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM commentaires");
			
			stmt.executeUpdate("INSERT INTO `commentaires`(`idcommentaires`,`commentaireColonne`) VALUES (1,'Commentaire n° 1')");
			stmt.executeUpdate("INSERT INTO `commentaires`(`idcommentaires`,`commentaireColonne`) VALUES (2,'Commentaire n° 2')");
			stmt.executeUpdate("INSERT INTO `commentaires`(`idcommentaires`,`commentaireColonne`) VALUES (3,'Commentaire n° 3')");
		}
	}
	@Test
	public void shouldListCommentaires() {
		// WHEN
		List<Commentaire> commentaires = commentaireDao.listCommentaires();
		// THEN
		assertThat(commentaires).hasSize(3);
		assertThat(commentaires).extracting("id", "commentaire").containsOnly(tuple(3, "Commentaire n° 3"), tuple(2, "Commentaire n° 2"),
				tuple(1, "Commentaire n° 1"));
	}
	
	/*@Test
	public void shouldGetUtilisateur() {
		// WHEN
		Utilisateur utilisateur = utilisateurDao.getUtilisateur(1);
		// THEN
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isEqualTo(1);
		assertThat(utilisateur.getNom()).isEqualTo("Martin");
		assertThat(utilisateur.getPrenom()).isEqualTo("Pierre");
	}*/

	@Test
	public void shouldAddCommentaire() throws Exception {
		// WHEN
		Commentaire commentaire = commentaireDao.addCommentaire("test");
		// THEN
		assertThat(commentaire.getId()).isNotNull();
		assertThat(commentaire.getCommentaire()).isEqualTo("test");
		

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM commentaires WHERE idcommentaires = ?")) {
			stmt.setInt(1, commentaire.getId());
			
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("idcommentaires")).isEqualTo(commentaire.getId());
				assertThat(rs.getString("commentaireColonne")).isEqualTo("test");
				assertThat(rs.next()).isFalse();
			}
		}
	}
}
