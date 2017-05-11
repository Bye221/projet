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

import marquise.daos.impl.CommentaireArticleDaoImpl;
import marquise.daos.impl.DataSourceProvider;

import marquise.projos.CommentaireArticle;


public class CommentaireArticleDaoTestCase {

	private CommentaireArticleDao commentaireArticleDao = new CommentaireArticleDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM commentairesarticles");
			
			stmt.executeUpdate("INSERT INTO `commentairesarticles`(`idCommentaireArticles`,`pseudo`,`texte`) VALUES (1,'pseudo1','Commentaire n° 1')");
			stmt.executeUpdate("INSERT INTO `commentairesarticles`(`idCommentaireArticles`,`pseudo`,`texte`) VALUES (2,'pseudo2','Commentaire n° 2')");
			stmt.executeUpdate("INSERT INTO `commentairesarticles`(`idCommentaireArticles`,`pseudo`,`texte`) VALUES (3,'pseudo3','Commentaire n° 3')");
			
		}
	}
	
	//avoir la liste de tout les commentaires
	@Test
	public void shouldListCommentairesArticles() {
		// WHEN
		List<CommentaireArticle> commentairesArticles = commentaireArticleDao.listCommentairesArticles();
		// THEN
		assertThat(commentairesArticles).hasSize(3);
		assertThat(commentairesArticles).extracting("id","pseudo", "commentaire").containsOnly(tuple(3,"pseudo3" ,"Commentaire n° 3"), tuple(2,"pseudo2", "Commentaire n° 2"),
				tuple(1,"pseudo1", "Commentaire n° 1"));
	}
	
	//Test pour pouvoir ajouter un commentaire
	@Test
	public void shouldAddCommentaireArticle() throws Exception {
		// WHEN
		CommentaireArticle commentaireArticle = commentaireArticleDao.addCommentaireArticle("test", "test");
		// THEN
		assertThat(commentaireArticle.getId()).isNotNull();
		assertThat(commentaireArticle.getPseudo()).isEqualTo("test");
		assertThat(commentaireArticle.getCommentaire()).isEqualTo("test");
		

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM commentairesarticles WHERE idCommentaireArticles = ?")) {
			stmt.setInt(1, commentaireArticle.getId());
			
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("idCommentaireArticles")).isEqualTo(commentaireArticle.getId());
				assertThat(rs.getString("pseudo")).isEqualTo("test");
				assertThat(rs.getString("texte")).isEqualTo("test");
				assertThat(rs.next()).isFalse();
			}
		}
	}
}
