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

public class GenreDaoImpl implements UtilisateurDao {

	@Override
	public List<Utilisateur> listGenres() {
		String query = "SELECT * FROM utilisateur ORDER BY nom";
		List<Utilisateur> genres = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Utilisateur genre = new Utilisateur(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"), resultSet.getString("prenom"));
						genres.add(genre);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}

	@Override
	public Utilisateur getGenre(Integer id) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE utilisateur_id = ?")) {
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
	public Utilisateur addGenre(String nom, String prenom) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO utilisateur(nom, prenom) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
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
