package marquise.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.IdentifiantDao;

import marquise.projos.Identifiant;
import marquise.projos.Utilisateur;

public class IdentifiantDaoImpl implements IdentifiantDao {

	@Override
	public List<Identifiant> listIdentifiants() {
		String query = "SELECT * FROM jyz1vhfvffbmzqa3.identifiant ORDER BY login";
		List<Identifiant> identifiants = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Identifiant identifiant = new Identifiant(resultSet.getString("login"), resultSet.getString("motDePasse"));
						identifiants.add(identifiant);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return identifiants;
	}

	@Override
	public Identifiant getIdentifiant(String login, String motDePasse) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.identifiant WHERE login = ?")) {
				statement.setString(1, login);
				try (ResultSet resultSet = statement.executeQuery()) {
					if(resultSet.next()) {
						return new Identifiant(resultSet.getString("login"), resultSet.getString("motDePasse"));
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Identifiant addIdentifiant(String login, String motDePasse) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO jyz1vhfvffbmzqa3.identifiant(login, motDePasse) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, login);
				statement.setString(2, motDePasse);
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new Identifiant(login, motDePasse);
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
