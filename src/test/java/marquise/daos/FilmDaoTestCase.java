package marquise.daos;

import static org.assertj.core.api.Assertions.assertThat;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import marquise.daos.impl.DataSourceProvider;
import marquise.daos.impl.FilmDaoImpl;
import marquise.projos.Film;
import marquise.projos.Utilisateur;

public class FilmDaoTestCase {
	
	private FilmDao filmDao = new FilmDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM information");
			stmt.executeUpdate("DELETE FROM utilisateur");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (1,'Drama','Drama')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (2,'Comedy','Comedy')");
			stmt.executeUpdate(
					"INSERT INTO `information`(`information_id`,`sexe`, date_naissance, utilisateur_id, prix, numSecu, adresse) "
							+ "VALUES (1, 'my title 1', '2014-11-26', 1, 120, 'director #1', 'summary')");
			stmt.executeUpdate(
					"INSERT INTO `information`(`information_id`,`sexe`, date_naissance, utilisateur_id, prix, numSecu, adresse) "
							+ "VALUES (2, 'my title 2', '2014-10-26', 2, 165, 'director #2', 'summary')");
		}
	}

	@Test
	public void shouldListFilm() {
		// WHEN
		List<Film> films = filmDao.listFilms();
		// THEN
		Assertions.assertThat(films).hasSize(2);
		Assertions.assertThat(films).extracting("id", "sexe", "dateNaissance", "utilisateur.id", "utilisateur.nom", "utilisateur.prenom", "tarif", "numSecu", "adresse").containsOnly(
			Assertions.tuple(1, "my title 1", LocalDate.of(2014, 11, 26), 1, "Drama", "Drama", 120, "director #1", "summary"),
			Assertions.tuple(2, "my title 2", LocalDate.of(2014, 10, 26), 2, "Comedy","Comedy", 165, "director #2", "summary")
		);

	}
	
	@Test
	public void shouldGetFilm() {
		// WHEN
		Film film = filmDao.getFilm(1);
		// THEN
		Assertions.assertThat(film).isNotNull();
		Assertions.assertThat(film.getId()).isEqualTo(1);
		Assertions.assertThat(film.getSexe()).isEqualTo("my title 1");
		Assertions.assertThat(film.getDateNaissance()).isEqualTo(LocalDate.of(2014, 11, 26));
		Assertions.assertThat(film.getUtilisateur().getId()).isEqualTo(1);
		Assertions.assertThat(film.getUtilisateur().getNom()).isEqualTo("Drama");
		Assertions.assertThat(film.getUtilisateur().getPrenom()).isEqualTo("Drama");
		Assertions.assertThat(film.getTarif()).isEqualTo(120);
		Assertions.assertThat(film.getNumSecu()).isEqualTo("director #1");
		Assertions.assertThat(film.getAdresse()).isEqualTo("summary");
	}
	
	@Test
	public void shouldNotGetFilm() {
		// WHEN
		Film film = filmDao.getFilm(0);
		// THEN
		Assertions.assertThat(film).isNull();
	}

	@Test
	public void shouldAddFilm() throws Exception {
		// GIVEN
		Film filmToAdd = new Film(null, "New title", LocalDate.of(2016, 11, 16), new Utilisateur(1, "Drama", "Drama"), 123, "New director", "New summary");
		// WHEN
		Film filmAdded = filmDao.addFilm(filmToAdd);
		// THEN
		Assertions.assertThat(filmAdded).isNotNull();
		Assertions.assertThat(filmAdded.getId()).isNotNull();
		Assertions.assertThat(filmAdded.getSexe()).isEqualTo("New title");
		Assertions.assertThat(filmAdded.getDateNaissance()).isEqualTo(LocalDate.of(2016, 11, 16));
		Assertions.assertThat(filmAdded.getUtilisateur().getId()).isEqualTo(1);
		Assertions.assertThat(filmAdded.getUtilisateur().getNom()).isEqualTo("Drama");
		Assertions.assertThat(filmAdded.getUtilisateur().getPrenom()).isEqualTo("Drama");
		Assertions.assertThat(filmAdded.getTarif()).isEqualTo(123);
		Assertions.assertThat(filmAdded.getNumSecu()).isEqualTo("New director");
		Assertions.assertThat(filmAdded.getAdresse()).isEqualTo("New summary");
		
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM information WHERE information_id = ?")) {
			stmt.setInt(1, filmAdded.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("information_id")).isEqualTo(filmAdded.getId());
				assertThat(rs.getString("sexe")).isEqualTo("New title");
				assertThat(rs.getDate("date_naissance").toLocalDate()).isEqualTo(LocalDate.of(2016, 11, 16));
				assertThat(rs.getInt("utilisateur_id")).isEqualTo(1);
				assertThat(rs.getInt("prix")).isEqualTo(123);
				assertThat(rs.getString("numSecu")).isEqualTo("New director");
				assertThat(rs.getString("adresse")).isEqualTo("New summary");
				assertThat(rs.next()).isFalse();
			}
		}
		
	}

}
