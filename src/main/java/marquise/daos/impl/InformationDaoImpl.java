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
	public List<Information> listInformations() {
		
		String query = "SELECT * FROM information JOIN utilisateur ON information.utilisateur_id = utilisateur.utilisateur_id ORDER BY sexe";
		List<Information> informations = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
						Utilisateur utilisateur = new Utilisateur(resultSet.getInt("utilisateur_id"),
								resultSet.getString("nom"), resultSet.getString("prenom"));
						Information information = new Information(resultSet.getInt("information_id"),
								resultSet.getString("sexe"), 
								resultSet.getDate("date_naissance").toLocalDate(), 
								utilisateur, 
								resultSet.getInt("prix"), 
								resultSet.getString("numSecu"), 
								resultSet.getString("adresse"));
						informations.add(information);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return informations;
	}

	@Override
	public Information getInformation(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.information JOIN jyz1vhfvffbmzqa3.utilisateur ON jyz1vhfvffbmzqa3.information.utilisateur_id = jyz1vhfvffbmzqa3.utilisateur.utilisateur_id WHERE information_id = ?")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						Utilisateur utilisateur = new Utilisateur(resultSet.getInt("utilisateur_id"), 
								resultSet.getString("nom"), 
								resultSet.getString("prenom"));
						
						return  new Information(
								resultSet.getInt("information_id"), 
								resultSet.getString("sexe"), 
								resultSet.getDate("date_naissance").toLocalDate(), 
								utilisateur, 
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
	public Information addInformation(Information information) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO information(sexe, date_naissance, utilisateur_id, prix, numSecu, adresse) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, information.getSexe());
				statement.setDate(2, Date.valueOf(information.getDateNaissance()));
				statement.setInt(3, information.getUtilisateur().getId());
				statement.setInt(4, information.getTarif());
				statement.setString(5, information.getNumSecu());
				statement.setString(6, information.getAdresse());
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						information.setId(resultSet.getInt(1));
						return information;
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
