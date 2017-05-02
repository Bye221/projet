package marquise.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import marquise.daos.impl.ArticleDaoImpl;
import marquise.daos.impl.DataSourceProvider;
import marquise.projos.Article;



public class ArticleDaoTestCase {

	private ArticleDao articleDao = new ArticleDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			
			stmt.executeUpdate("DELETE FROM article");
			stmt.executeUpdate("INSERT INTO `article`(`idArticle`,`titreArticle`,`texteArticle`, `date`, `auteur`) VALUES (1,'Running1','Tout le monde a courru, c etait au top !', '2017-04-20', 'louis-come')");
			stmt.executeUpdate("INSERT INTO `article`(`idArticle`,`titreArticle`,`texteArticle`, `date`, `auteur`) VALUES (2,'Running2','Tout le monde a courru, c etait au top !', '2017-04-22', 'louis-come')");
			stmt.executeUpdate("INSERT INTO `article`(`idArticle`,`titreArticle`,`texteArticle`, `date`, `auteur`) VALUES (3,'Running3','Tout le monde a courru, c etait au top !', '2017-04-24', 'louis-come')");
		}
	}
	@Test
	public void shouldListArticles() {
		// WHEN
		List<Article> articles = articleDao.listArticles();
		// THEN
		assertThat(articles).hasSize(3);
		assertThat(articles).extracting("id", "title", "texte", "datePublication", "auteur").containsOnly(
				tuple(3,"Running3","Tout le monde a courru, c etait au top !",LocalDate.of(2017, 04, 24), "louis-come"), 
				tuple(2,"Running2","Tout le monde a courru, c etait au top !",LocalDate.of(2017, 04, 22), "louis-come"),
				tuple(1,"Running1","Tout le monde a courru, c etait au top !",LocalDate.of(2017, 04, 20), "louis-come"));
	}
	
	
	@Test
	public void shouldAddArticle() throws Exception {
		// WHEN
		Article article = articleDao.addArticle("titre test", "texte test", LocalDate.of(2017, 04, 01), "auteur");
		// THEN
		assertThat(article.getId()).isNotNull();
		assertThat(article.getTitle()).isEqualTo("titre test");
		assertThat(article.getTexte()).isEqualTo("texte test");
		assertThat(article.getDatePublication()).isEqualTo(LocalDate.of(2017, 04, 01));
		assertThat(article.getAuteur()).isEqualTo("auteur");

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM article WHERE idArticle = ?")) {
			stmt.setInt(1, article.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("idArticle")).isEqualTo(article.getId());
				assertThat(rs.getString("titreArticle")).isEqualTo("titre test");
				assertThat(rs.getString("texteArticle")).isEqualTo("texte test");
				assertThat(rs.getDate("date").toLocalDate()).isEqualTo(LocalDate.of(2017, 04, 01));
				assertThat(rs.getString("auteur")).isEqualTo("auteur");
				assertThat(rs.next()).isFalse();
			}
		}
	}
}
