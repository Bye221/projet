package marquise.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.GenreDao;
import marquise.projos.Genre;

public class GenreDaoImpl implements GenreDao {

	@Override
	public List<Genre> listGenres() {
		String query = "SELECT * FROM utilisateur ORDER BY nom";
		List<Genre> genres = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Genre genre = new Genre(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"), resultSet.getString("prenom"));
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
	public Genre getGenre(Integer id) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM utilisateur WHERE utilisateur_id = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new Genre(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"), resultSet.getString("prenom"));
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genre addGenre(String nom, String prenom) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO utilisateur(nom, prenom) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, nom);
				statement.setString(2, prenom);
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new Genre(resultSet.getInt(1), nom, prenom);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	

}
