package marquise.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





import marquise.daos.impl.DataSourceProvider;
import marquise.exceptions.CityExplorerRuntimeException;
import marquise.projos.City;

public class CityDao {
	
	public List<City> listCities() {
		List<City> cities = new ArrayList<City>();

		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM city ORDER BY name")) {
			while (resultSet.next()) {
				
				cities.add(
						new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary")));
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting cities", e);
		}

		return cities;
	}
	
	public City getCity(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					
					return new City(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("summary"));
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting cities", e);
		}
		return null;
	}
	
	public void addCity(City newCity, String picturePath) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO city(name, summary, picture) VALUES (?, ?, ?)")) {
			statement.setString(1, newCity.getName());
			statement.setString(2, newCity.getSummary());
			statement.setString(3, picturePath);
			

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting cities", e);
		}
	}
	public String getPicturePath(Integer id) {
		try (Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT picture FROM city WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getString("picture");
				}
			}
		} catch (SQLException e) {
			throw new CityExplorerRuntimeException("Error when getting cities", e);
		}
		return null;
	}

}
