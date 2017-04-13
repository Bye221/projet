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
import marquise.daos.impl.GenreDaoImpl;
import marquise.projos.Genre;

public class GenreDaoTestCase {

	private GenreDao genreDao = new GenreDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM information");
			stmt.executeUpdate("DELETE FROM utilisateur");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`genre_id`,`nom`,`prenom`) VALUES (1,'Drama','Drama')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`genre_id`,`nom`,`prenom`) VALUES (2,'Comedy','Comedy')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`genre_id`,`nom`,`prenom`) VALUES (3,'Action','Action')");
		}
	}
	@Test
	public void shouldListGenres() {
		// WHEN
		List<Genre> genres = genreDao.listGenres();
		// THEN
		assertThat(genres).hasSize(3);
		assertThat(genres).extracting("id", "nom", "prenom").containsOnly(tuple(3, "Action", "Action"), tuple(2, "Comedy","Comedy"),
				tuple(1, "Drama","Drama"));
	}
	
	@Test
	public void shouldGetGenre() {
		// WHEN
		Genre genre = genreDao.getGenre(1);
		// THEN
		assertThat(genre).isNotNull();
		assertThat(genre.getId()).isEqualTo(1);
		assertThat(genre.getNom()).isEqualTo("Drama");
		assertThat(genre.getPrenom()).isEqualTo("Drama");
	}

	@Test
	public void shouldAddGenre() throws Exception {
		// WHEN
		Genre genre = genreDao.addGenre("test", "test");
		// THEN
		assertThat(genre.getId()).isNotNull();
		assertThat(genre.getNom()).isEqualTo("test");
		assertThat(genre.getPrenom()).isEqualTo("test");

		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM utilisateur WHERE genre_id = ?")) {
			stmt.setInt(1, genre.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("genre_id")).isEqualTo(genre.getId());
				assertThat(rs.getString("nom")).isEqualTo("test");
				assertThat(rs.getString("prenom")).isEqualTo("test");
				assertThat(rs.next()).isFalse();
			}
		}
	}
}
