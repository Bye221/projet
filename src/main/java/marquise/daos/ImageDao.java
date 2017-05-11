package marquise.daos;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





import marquise.daos.impl.DataSourceProvider;
import marquise.exceptions.CityExplorerRuntimeException;
import marquise.projos.Image;

public class ImageDao {
	
	public List<Image> listImages() {
		List<Image> images = new ArrayList<Image>();

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM image ORDER BY name")) {
			while (resultSet.next()) {
				
				images.add(
						new Image(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary")));
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}

		return images;
	}
	
	public Image getImage(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM image WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					
					return new Image(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary"));
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
		return null;
	}
	
	public void addImage(Image newImage, String picturePath) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO image(name, summary, picture) VALUES (?, ?, ?)")) {
			statement.setString(1, newImage.getName());
			statement.setString(2, newImage.getSummary());
			statement.setString(3, picturePath);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
	}
	
	public void addImage(Image img, InputStream is){
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO image(name, summary, image) VALUES (?, ?, ?)")) {
			statement.setString(1, img.getName());
			statement.setString(2, img.getSummary());
			statement.setBinaryStream(3, is);	
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
	}
	public String getPicturePath(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT picture FROM image WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString("picture");
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
		return null;
	}  
	public InputStream getPicture(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT image FROM image WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getBinaryStream("image");
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
		return null;
	}
	/*public InputStream getPicture(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT image FROM image WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getBlob("image") == null ? null : resultSet.getBlob("image").getBinaryStream();
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting images", e);
		}
		return null;
	}*/



}
