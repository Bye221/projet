package marquise.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.UtilisateurDao;
import marquise.projos.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

	@Override
	public List<Utilisateur> listeUtilisateurs() {
		
		String query = "select * from marquiseBase.utilisateurs;";
		List<Utilisateur> utilisateurs = new ArrayList<>();
	
		try (
			Connection connection = DataSourceProvider.getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
					Utilisateur utilisateur = new Utilisateur(resultSet.getInt("idUtilisateurs"), resultSet.getString("nom"), resultSet.getString("prenom"));
					utilisateurs.add(utilisateur);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public Utilisateur getUtilisateur(String nom) {
		//String query = "select * from utilisateurs where nom like '?' ";
		
		try(Connection connection = DataSourceProvider.getDataSource().getConnection()){
			String query = "select * from utilisateurs where nom like '?' ";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setString(2, nom);
				try(ResultSet resultSet = statement.executeQuery()){
					while(resultSet.next()){
						return new Utilisateur(resultSet.getInt("idUtilisateurs"), resultSet.getString("nom"), resultSet.getString("prenom"));
					}
				}
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}



	@Override
	public Utilisateur addUtilisateur(Integer id, String nom, String Prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
