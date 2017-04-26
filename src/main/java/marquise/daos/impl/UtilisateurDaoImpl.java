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
	public List<Utilisateur> listUtilisateurs() {
		String query = "SELECT * FROM jyz1vhfvffbmzqa3.utilisateur ORDER BY nom";
		List<Utilisateur> utilisateurs = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Utilisateur utilisateur = new Utilisateur(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"), resultSet.getString("prenom"));
						utilisateurs.add(utilisateur);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}

	@Override
	public Utilisateur getUtilisateur(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.utilisateur WHERE jyz1vhfvffbmzqa3.utilisateur_id = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new Utilisateur(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"), resultSet.getString("prenom"));
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Utilisateur addUtilisateur(String nom, String prenom) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO jyz1vhfvffbmzqa3.utilisateur(nom, prenom) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, nom);
				statement.setString(2, prenom);
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new Utilisateur(resultSet.getInt(1), nom, prenom);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	

}
