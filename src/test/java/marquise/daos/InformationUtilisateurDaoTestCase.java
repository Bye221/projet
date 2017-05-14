package marquise.daos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
import marquise.daos.impl.InformationUtilisateurDaoImpl;
import marquise.projos.InformationUtilisateur;



public class InformationUtilisateurDaoTestCase {
	
	private InformationUtilisateurDao informationUtilisateurDao = new InformationUtilisateurDaoImpl();
	
	@Before
	public void initDb() throws Exception {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement stmt = connection.createStatement()) {
			stmt.executeUpdate("DELETE FROM informationutil");
			
			stmt.executeUpdate("INSERT INTO `informationutil`(`idinformationUtil`,`nom`,`prenom`,`sexe`,`dateNaissance`,`prix`,`numSecu`,`adresse`) VALUES (1,'Martin','Pierre','homme','2014-11-26',10,'1234567890','adresse1')");
			stmt.executeUpdate("INSERT INTO `informationutil`(`idinformationUtil`,`nom`,`prenom`,`sexe`,`dateNaissance`,`prix`,`numSecu`,`adresse`) VALUES (2,'Toulemonde','Victoria','femme','2014-11-25',10,'1234567890','adresse2')");
			stmt.executeUpdate("INSERT INTO `informationutil`(`idinformationUtil`,`nom`,`prenom`,`sexe`,`dateNaissance`,`prix`,`numSecu`,`adresse`) VALUES (3,'Chirac','Jacques','homme','2014-11-24',10,'1234567890','adresse3')");
			
		}
	}
	@Test
	public void shouldListUtilisateurs() {
		// WHEN
		List<InformationUtilisateur> informationsUtilisateurs = informationUtilisateurDao.listInformationsUtilisateurs();
		// THEN
		assertThat(informationsUtilisateurs).hasSize(3);
		assertThat(informationsUtilisateurs).extracting("id", "nom", "prenom","sexe","date","tarif","numSecu","adresse").containsOnly(tuple(3,"Chirac","Jacques","homme",LocalDate.of(2014, 11, 24),10,"1234567890","adresse3"), tuple(2,"Toulemonde","Victoria","femme",LocalDate.of(2014, 11, 25),10,"1234567890","adresse2"),
				tuple(1,"Martin","Pierre","homme",LocalDate.of(2014, 11, 26),10,"1234567890","adresse1"));
	}
	
	@Test
	public void shouldGetInformationUtilisateurByName() {
		// WHEN
				InformationUtilisateur informationUtilisateur = informationUtilisateurDao.getInformationUtilisateurByName("arti");
				// THEN
				assertThat(informationUtilisateur).isNotNull();
				assertThat(informationUtilisateur.getId()).isEqualTo(1);
				assertThat(informationUtilisateur.getNom()).isEqualTo("Martin");
				assertThat(informationUtilisateur.getPrenom()).isEqualTo("Pierre");
				assertThat(informationUtilisateur.getSexe()).isEqualTo("homme");
				assertThat(informationUtilisateur.getDate()).isEqualTo(LocalDate.of(2014, 11, 26));
				assertThat(informationUtilisateur.getTarif()).isEqualTo(10);
				assertThat(informationUtilisateur.getNumSecu()).isEqualTo("1234567890");
				assertThat(informationUtilisateur.getAdresse()).isEqualTo("adresse1");
				
	}
	
	@Test
	public void shouldGetInformationUtilisateurById() {
		// WHEN
				InformationUtilisateur informationUtilisateur = informationUtilisateurDao.getInformationUtilisateurById(1);
				// THEN
				assertThat(informationUtilisateur).isNotNull();
				assertThat(informationUtilisateur.getId()).isEqualTo(1);
				assertThat(informationUtilisateur.getNom()).isEqualTo("Martin");
				assertThat(informationUtilisateur.getPrenom()).isEqualTo("Pierre");
				assertThat(informationUtilisateur.getSexe()).isEqualTo("homme");
				assertThat(informationUtilisateur.getDate()).isEqualTo(LocalDate.of(2014, 11, 26));
				assertThat(informationUtilisateur.getTarif()).isEqualTo(10);
				assertThat(informationUtilisateur.getNumSecu()).isEqualTo("1234567890");
				assertThat(informationUtilisateur.getAdresse()).isEqualTo("adresse1");
				
	}

	@Test
	public void shouldAddInformationUtilisateur() throws Exception {
		// WHEN
		InformationUtilisateur informationUtilisateur = informationUtilisateurDao.addInformationUtilisateur("nom", "Prenom", "sexe", LocalDate.of(2016, 11, 16), 99, "numSecu", "adresse");
		// THEN
		
		assertThat(informationUtilisateur.getId()).isNotNull();
		assertThat(informationUtilisateur.getNom()).isEqualTo("nom");
		assertThat(informationUtilisateur.getPrenom()).isEqualTo("Prenom");
		assertThat(informationUtilisateur.getSexe()).isEqualTo("sexe");
		assertThat(informationUtilisateur.getDate()).isEqualTo(LocalDate.of(2016, 11, 16));
		assertThat(informationUtilisateur.getTarif()).isEqualTo(99);
		assertThat(informationUtilisateur.getNumSecu()).isEqualTo("numSecu");
		assertThat(informationUtilisateur.getAdresse()).isEqualTo("adresse");
		

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM informationutil WHERE idinformationUtil = ?")) {
			stmt.setInt(1, informationUtilisateur.getId());
			try (ResultSet rs = stmt.executeQuery()) {
				assertThat(rs.next()).isTrue();
				assertThat(rs.getInt("idinformationUtil")).isEqualTo(informationUtilisateur.getId());
				assertThat(rs.getString("nom")).isEqualTo(informationUtilisateur.getNom());
				assertThat(rs.getString("prenom")).isEqualTo(informationUtilisateur.getPrenom());
				assertThat(rs.getString("sexe")).isEqualTo(informationUtilisateur.getSexe());
				assertThat(rs.getDate("dateNaissance").toLocalDate()).isEqualTo(LocalDate.of(2016, 11, 16));
				assertThat(rs.getInt("prix")).isEqualTo(informationUtilisateur.getTarif());
				assertThat(rs.getString("numSecu")).isEqualTo(informationUtilisateur.getNumSecu());
				assertThat(rs.getString("adresse")).isEqualTo(informationUtilisateur.getAdresse());	
				assertThat(rs.next()).isFalse();
			}
		}
	}
	
	

}
