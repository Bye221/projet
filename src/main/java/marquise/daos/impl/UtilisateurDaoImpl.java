package marquise.daos.impl;
import java.io.*;

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
		String query = "select * from utilisateur order by nom";
		List <Utilisateur> utilisateurs = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getDataSource().getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
						Utilisateur utilisateur = new Utilisateur(resultSet.getInt("utilisateur_id"), resultSet.getString("nom"));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur addUtilisateur(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
