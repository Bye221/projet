package marquise.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.CommentaireDao;
import marquise.daos.UtilisateurDao;
import marquise.projos.Commentaire;
import marquise.projos.Utilisateur;

public class CommentaireDaoImpl implements CommentaireDao {

	@Override
	public List<Commentaire> listCommentaires() {
		String query = "SELECT * FROM jyz1vhfvffbmzqa3.commentaires ORDER BY idcommentaires";
		List<Commentaire> commentaires = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						Commentaire commentaire = new Commentaire(resultSet.getInt("idcommentaires"), resultSet.getString("email"),  resultSet.getString("commentaireColonne"));
						commentaires.add(commentaire);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentaires;
	}




	// A voir si l'on ajoute une methode pour retourner des commentaires specifiques


	/*@Override
	public Utilisateur getUtilisateur(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM jyz1vhfvffbmzqa3.utilisateur WHERE utilisateur_id = ?")) {
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
	}*/

	@Override
	public Commentaire addCommentaire(String email, String commentaire) {

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO jyz1vhfvffbmzqa3.commentaires(email,commentaireColonne) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {

				statement.setString(1, email);
				statement.setString(2, commentaire);
				statement.executeUpdate();

				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new Commentaire(resultSet.getInt(1), email, commentaire);
					}				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
