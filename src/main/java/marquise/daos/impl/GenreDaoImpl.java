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
		String query = "SELECT * FROM genre ORDER BY name";
		List<Genre> genres = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Genre genre = new Genre(resultSet.getInt("genre_id"), resultSet.getString("name"));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre addGenre(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
