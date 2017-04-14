package marquise.daos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.InformationDao;
import marquise.projos.Information;
import marquise.projos.Utilisateur;

public class InformationDaoImpl implements InformationDao {

	@Override
	public List<Information> listFilms() {
		
		String query = "SELECT * FROM information JOIN utilisateur ON information.utilisateur_id = utilisateur.utilisateur_id ORDER BY sexe";
		List<Information> films = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
						Utilisateur genre = new Utilisateur(resultSet.getInt("utilisateur_id"),
								resultSet.getString("nom"), resultSet.getString("prenom"));
						Information film = new Information(resultSet.getInt("information_id"),
								resultSet.getString("sexe"), 
								resultSet.getDate("date_naissance").toLocalDate(), genre, 
								resultSet.getInt("prix"), 
								resultSet.getString("numSecu"), 
								resultSet.getString("adresse"));
						films.add(film);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return films;
	}

	@Override
	public Information getFilm(Integer id) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM information JOIN utilisateur ON information.utilisateur_id = utilisateur.utilisateur_id WHERE information_id = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						Utilisateur genre = new Utilisateur(resultSet.getInt("utilisateur_id"), 
								resultSet.getString("nom"), 
								resultSet.getString("prenom"));
						
						return  new Information(
								resultSet.getInt("information_id"), 
								resultSet.getString("sexe"), 
								resultSet.getDate("date_naissance").toLocalDate(), 
								genre, 
								resultSet.getInt("prix"), 
								resultSet.getString("numSecu"), 
								resultSet.getString("adresse"));
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Information addFilm(Information film) {
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO information(sexe, date_naissance, utilisateur_id, prix, numSecu, adresse) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, film.getSexe());
				statement.setDate(2, Date.valueOf(film.getDateNaissance()));
				statement.setInt(3, film.getUtilisateur().getId());
				statement.setInt(4, film.getTarif());
				statement.setString(5, film.getNumSecu());
				statement.setString(6, film.getAdresse());
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						film.setId(resultSet.getInt(1));
						return film;
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
