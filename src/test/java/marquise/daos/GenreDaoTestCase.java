package marquise.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.sql.Connection;
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
			stmt.executeUpdate("DELETE FROM film");
			stmt.executeUpdate("DELETE FROM genre");
			stmt.executeUpdate("INSERT INTO `genre`(`genre_id`,`name`) VALUES (1,'Drama')");
			stmt.executeUpdate("INSERT INTO `genre`(`genre_id`,`name`) VALUES (2,'Comedy')");
			stmt.executeUpdate("INSERT INTO `genre`(`genre_id`,`name`) VALUES (3,'Action')");
		}
	}
	@Test
	public void shouldListGenres() {
		// WHEN
		List<Genre> genres = genreDao.listGenres();
		// THEN
		assertThat(genres).hasSize(3);
		assertThat(genres).extracting("id", "name").containsOnly(tuple(3, "Action"), tuple(2, "Comedy"),
				tuple(1, "Drama"));
	}
}
