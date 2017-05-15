package marquise.daos.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.InformationUtilisateurDao;

import marquise.projos.InformationUtilisateur;
import marquise.projos.Utilisateur;




public class InformationUtilisateurDaoImpl implements InformationUtilisateurDao {

	@Override
	public List<InformationUtilisateur> listInformationsUtilisateurs() {
		String query = "SELECT * FROM jyz1vhfvffbmzqa3.informationutil;";
		List<InformationUtilisateur> informationsUtilisateurs = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
						
						InformationUtilisateur informationUtilisateur = new InformationUtilisateur(
								resultSet.getInt("idinformationUtil"),
								resultSet.getString("nom"),
								resultSet.getString("prenom"),
								resultSet.getString("sexe"), 
								resultSet.getDate("dateNaissance").toLocalDate(), 	 
								resultSet.getInt("prix"), 
								resultSet.getString("numSecu"), 
								resultSet.getString("adresse"));
						informationsUtilisateurs.add(informationUtilisateur);
					}
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("Une erreur est appaarue lors de la recuperation des infomations");
		}
		
		
		return informationsUtilisateurs;
	}

	@Override
	public InformationUtilisateur getInformationUtilisateurById(Integer id) {
		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.informationutil WHERE idinformationUtil = ? ;")) {
				statement.setInt(1, id);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new InformationUtilisateur(
								resultSet.getInt("idinformationUtil"),
								resultSet.getString("nom"),
								resultSet.getString("prenom"),
								resultSet.getString("sexe"),
								resultSet.getDate("dateNaissance").toLocalDate(),
								resultSet.getInt("prix"),
								resultSet.getString("numsecu"),
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
	public void deleteInformationUtilisateur(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("DELETE * FROM jyz1vhfvffbmzqa3.informationutil WHERE idinformationUtil = ? ;")) {
				statement.setInt(1, id);
				statement.executeUpdate();
				statement.close();
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}



	@Override
	public InformationUtilisateur getInformationUtilisateurByName(String nom) {
		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.informationutil WHERE nom like ? ;")) {
				statement.setString(1, "%"+nom+"%");
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new InformationUtilisateur(
								resultSet.getInt("idinformationUtil"),
								resultSet.getString("nom"),
								resultSet.getString("prenom"),
								resultSet.getString("sexe"),
								resultSet.getDate("dateNaissance").toLocalDate(),
								resultSet.getInt("prix"),
								resultSet.getString("numsecu"),
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
	public InformationUtilisateur addInformationUtilisateur(String nom, String prenom, String sexe,
			LocalDate date, Integer tarif, String numSecu, String adresse) {
		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO jyz1vhfvffbmzqa3.informationutil(nom, prenom, sexe, dateNaissance, prix, numSecu, adresse) VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, nom);
				statement.setString(2, prenom);
				statement.setString(3, sexe);	
				statement.setDate(4, Date.valueOf(date));
				statement.setInt(5, tarif);
				statement.setString(6, numSecu);
				statement.setString(7, adresse);
				statement.executeUpdate();
				
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new InformationUtilisateur(resultSet.getInt(1), nom, prenom, sexe, date, tarif, numSecu, adresse);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InformationUtilisateur updateInformationUtilisateur(Integer id, String nom, String prenom, String sexe,
			LocalDate date, Integer tarif, String numSecu, String adresse) {
		
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("update jyz1vhfvffbmzqa3.informationutil set nom = ? ,  prenom = ? , sexe = ?, dateNaissance = ?, prix = ?, numSecu = ?, adresse = ? where idinformationUtil = ?;")) {
				//statement.setInt(8, id);
				statement.setString(1, nom);
				statement.setString(2, prenom);
				statement.setString(3, sexe);	
				statement.setDate(4, Date.valueOf(date));
				statement.setInt(5, tarif);
				statement.setString(6, numSecu);
				statement.setString(7, adresse);
				statement.setInt(8, id);
				statement.executeUpdate();

				if(statement.getUpdateCount() > 0) {
						System.out.println("Update done with sucess ! ");
						return new InformationUtilisateur(id, nom, prenom, sexe, date, tarif, numSecu, adresse);
				}
			}
		}catch (SQLException e) {
			System.err.println("An occured into the query ! with "+id + nom + prenom + sexe + date + tarif + numSecu + adresse);
			e.printStackTrace();
		}
		return null;
	}

}
