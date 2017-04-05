
package marquise.daos;


import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;
import org.junit.Before;
import org.junit.Test;

import marquise.daos.impl.DataSourceProvider;
import marquise.daos.impl.UtilisateurDaoImpl;
import marquise.projos.Utilisateur;


public class UtilisateurDaoTestCase {

	private UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
	
	@Before
	public void initDb() throws Exception{
		try(Connection connection = DataSourceProvider.getDataSource().getConnection();
				Statement stmt = connection.createStatement()){
			stmt.executeUpdate("delete from informations");
			stmt.executeUpdate("delete from utilisateurs");
			stmt.executeUpdate("INSERT INTO `marquiseBase`.`utilisateurs` (`idUtilisateurs`, `nom`, `prenom`) VALUES ('1', 'auvray', 'louis-come');"
					);
			stmt.executeUpdate("INSERT INTO `marquiseBase`.`utilisateurs` (`idUtilisateurs`, `nom`, `prenom`) VALUES ('2', 'tamisier', 'Axel');"
					);
			stmt.executeUpdate("INSERT INTO `marquiseBase`.`utilisateurs` (`idUtilisateurs`, `nom`, `prenom`) VALUES ('3', 'TRUMP', 'Donald');"
					);

		}
	}
	
	
	@Test 
	public void shouldlisteUtilisateurs() {
		//WHEN
		List<Utilisateur> utilisateurs = utilisateurDao.listeUtilisateurs();
		//THEN

		//Assertions.assertThat(utilisateurs).hasSize(3);
			//Assertions.assertThat(utilisateurs).extracting("idUtilisateurs", "nom", "prenom").containsOnly( 
				//Assertions.tuple('1', "auvray", "louis-come"),
				//Assertions.tuple('2', "tamisier", "Axel"),
				//Assertions.tuple('3', "TRUMP", "Donald")
				//);

		//assertThat(utilisateurs).hasSize(3);

		//assertThat(utilisateurs).extracting("idUtilisateurs", "nom", "prenom").containsOnly(tuple('1', "auvray", "louis-come"),tuple('2', "tamisier", "Axel"),tuple('3', "TRUMP", "Donald"));


		//assertThat(utilisateurs).extracting("idUtilisateurs", "nom", "prenom").containsOnly(tuple('1', "auvray", "louis-come"),tuple('2', "tamisier", "Axel"),tuple('3', "TRUMP", "Donald"));


		// a derterminer 
	}
	
	@Test
	public void shouldGetUtilisateurs(){
		//WHEN
		Utilisateur utilisateur = utilisateurDao.getUtilisateur("auvray");
		//Then
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isEqualTo(1);
		assertThat(utilisateur.getNom()).isEqualTo("auvray");
		assertThat(utilisateur.getPrenom()).isEqualTo("louis-come");
		
		
	}
	
	


	
	




}
