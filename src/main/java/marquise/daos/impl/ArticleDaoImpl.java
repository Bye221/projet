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

import marquise.daos.ArticleDao;

import marquise.projos.Article;



public class ArticleDaoImpl implements ArticleDao {

	@Override
	public List<Article> listArticles() {
		
		String query = "SELECT * FROM article ORDER BY idArticle DESC";
		List<Article> articles = new ArrayList<>();
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()){
			try (Statement statement = connection.createStatement()){
				try (ResultSet resultSet = statement.executeQuery(query)){
					while(resultSet.next()){
						Article article = new Article(
									resultSet.getInt("idArticle"),
									resultSet.getString("titreArticle"),
									resultSet.getString("texteArticle"), 
									resultSet.getDate("date").toLocalDate(), 
									resultSet.getString("auteur")
														);
						articles.add(article);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return articles;
	}

	@Override
	public Article addArticle(String title, String texte, LocalDate datePublication, String auteur) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection()) {
			try(PreparedStatement statement = connection.prepareStatement("INSERT INTO article(titreArticle, texteArticle, date, auteur) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
				statement.setString(1, title);
				statement.setString(2, texte);
				statement.setDate(3, Date.valueOf(datePublication));
				statement.setString(4, auteur);
				statement.executeUpdate();
				
				try (ResultSet resultSet = statement.getGeneratedKeys()) {
					if(resultSet.next()) {
						
						return new Article(resultSet.getInt(1), title, texte, datePublication, auteur );
					}
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	




	
	


	
}
