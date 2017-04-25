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
import marquise.daos.impl.InformationDaoImpl;
import marquise.projos.Information;
import marquise.projos.Utilisateur;

public class InformationDaoTestCase {
	
	private InformationDao informationDao = new InformationDaoImpl();

	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM information");
			stmt.executeUpdate("DELETE FROM utilisateur");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (1,'Auvray','Louis-Come')");
			stmt.executeUpdate("INSERT INTO `utilisateur`(`utilisateur_id`,`nom`,`prenom`) VALUES (2,'Toulemonde','Jean')");
			stmt.executeUpdate(
					"INSERT INTO `information`(`information_id`,`sexe`, date_naissance, utilisateur_id, prix, numSecu, adresse) "
							+ "VALUES (1, 'my title 1', '2014-11-26', 1, 120, '1234567890', 'summary')");
			stmt.executeUpdate(
					"INSERT INTO `information`(`information_id`,`sexe`, date_naissance, utilisateur_id, prix, numSecu, adresse) "
							+ "VALUES (2, 'my title 2', '2014-10-26', 2, 165, '1234567890', 'summary')");
		}
	}

	@Test
	public void shouldListInformation() {
		// WHEN
		List<Information> informations = informationDao.listInformations();
		// THEN
		Assertions.assertThat(informations).hasSize(2);
		Assertions.assertThat(informations).extracting("id", "sexe", "dateNaissance", "utilisateur.id", "utilisateur.nom", "utilisateur.prenom", "tarif", "numSecu", "adresse").containsOnly(
			Assertions.tuple(1, "my title 1", LocalDate.of(2014, 11, 26), 1, "Auvray", "Louis-Come", 120, "1234567890", "summary"),
			Assertions.tuple(2, "my title 2", LocalDate.of(2014, 10, 26), 2, "Toulemonde","Jean", 165, "1234567890", "summary")
		);

	}
	
	@Test
	public void shouldGetInformation() {
		// WHEN
		Information information = informationDao.getInformation(1);
		// THEN
		Assertions.assertThat(information).isNotNull();
		Assertions.assertThat(information.getId()).isEqualTo(1);
		Assertions.assertThat(information.getSexe()).isEqualTo("my title 1");
		Assertions.assertThat(information.getDateNaissance()).isEqualTo(LocalDate.of(2014, 11, 26));
		Assertions.assertThat(information.getUtilisateur().getId()).isEqualTo(1);
		Assertions.assertThat(information.getUtilisateur().getNom()).isEqualTo("Auvray");
		Assertions.assertThat(information.getUtilisateur().getPrenom()).isEqualTo("Louis-Come");
		Assertions.assertThat(information.getTarif()).isEqualTo(120);
		Assertions.assertThat(information.getNumSecu()).isEqualTo("1234567890");
		Assertions.assertThat(information.getAdresse()).isEqualTo("summary");
	}
	
	@Test
	public void shouldNotGetInformation() {
		// WHEN
		Information information = informationDao.getInformation(0);
		// THEN
		Assertions.assertThat(information).isNull();
	}

	@Test
	public void shouldAddInformation() throws Exception {
		// GIVEN
		Information informationToAdd = new Information(null, "Homme", LocalDate.of(2016, 11, 16), new Utilisateur(1, "Auvray", "Louis-Come"), 123, "1234567890", "New summary");
		// WHEN
		Information informationAdded = informationDao.addInformation(informationToAdd);
		// THEN
		Assertions.assertThat(informationAdded).isNotNull();
		Assertions.assertThat(informationAdded.getId()).isNotNull();
		Assertions.assertThat(informationAdded.getSexe()).isEqualTo("Homme");
		Assertions.assertThat(informationAdded.getDateNaissance()).isEqualTo(LocalDate.of(2016, 11, 16));
		Assertions.assertThat(informationAdded.getUtilisateur().getId()).isEqualTo(1);
		Assertions.assertThat(informationAdded.getUtilisateur().getNom()).isEqualTo("Auvray");
		Assertions.assertThat(informationAdded.getUtilisateur().getPrenom()).isEqualTo("Louis-Come");
		Assertions.assertThat(informationAdded.getTarif()).isEqualTo(123);
		Assertions.assertThat(informationAdded.getNumSecu()).isEqualTo("1234567890");
		Assertions.assertThat(informationAdded.getAdresse()).isEqualTo("New summary");
		
		try (Connection connection = DataSourceProvider.getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM information WHERE information_id = ?")) {
			stmt.setInt(1, informationAdded.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("information_id")).isEqualTo(informationAdded.getId());
				assertThat(rs.getString("sexe")).isEqualTo("Homme");
				assertThat(rs.getDate("date_naissance").toLocalDate()).isEqualTo(LocalDate.of(2016, 11, 16));
				assertThat(rs.getInt("utilisateur_id")).isEqualTo(1);
				assertThat(rs.getInt("prix")).isEqualTo(123);
				assertThat(rs.getString("numSecu")).isEqualTo("1234567890");
				assertThat(rs.getString("adresse")).isEqualTo("New summary");
				assertThat(rs.next()).isFalse();
			}
		}
		
	}

}
