package marquise.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import marquise.daos.CommentaireArticleDao;
import marquise.projos.CommentaireArticle;



public class CommentaireArticleDaoImpl implements CommentaireArticleDao {

	@Override
	public List<CommentaireArticle> listCommentairesArticles() {
		String query = "SELECT * FROM jyz1vhfvffbmzqa3.commentairesarticles ORDER BY idCommentaireArticles";
		List<CommentaireArticle> commentairesArticles = new ArrayList<>(); 
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try (Statement statement = connection.createStatement()) {
				try (ResultSet resultSet = statement.executeQuery(query)) {
					while(resultSet.next()) {
						CommentaireArticle commentaireArticle = new CommentaireArticle(resultSet.getInt("idCommentaireArticles"), resultSet.getString("pseudo"),  resultSet.getString("texte"));
						commentairesArticles.add(commentaireArticle);
					}
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentairesArticles;
	}




	// A voir si l'on ajoute une methode pour retourner des commentaires specifiques


	
//Ajout d'un commentaire sous un article
	@Override
	public CommentaireArticle addCommentaireArticle(String pseudo, String texte) {

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO jyz1vhfvffbmzqa3.commentairesarticles(pseudo,texte) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {

				statement.setString(1, pseudo);
				statement.setString(2, texte);
				statement.executeUpdate();

				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						return new CommentaireArticle(resultSet.getInt(1), pseudo, texte);
					}				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



//Suppression d'un commentaire sous un article
	@Override
	public void deleteCommentaireArticle(int id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("DELETE FROM jyz1vhfvffbmzqa3.commentairesarticles where idCommentaireArticles = ?)")) {

				statement.setInt(1, id);
				statement.executeUpdate();

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

	

}
