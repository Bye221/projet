package marquise.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import marquise.daos.impl.DataSourceProvider;
import marquise.projos.Image;

public class ImageDaoTestCase extends AbstractDaoTestCase {
	
	private ImageDao imageDao = new ImageDao();

	@Override
	public void insertDataSet(Statement statement) throws Exception {
		statement.executeUpdate("INSERT INTO image(id, name, summary, picture) VALUES(1, 'Image 1', 'Summary 1', '/path/to/image1.png')");
		statement.executeUpdate("INSERT INTO image(id, name, summary, picture) VALUES(2, 'Image 2', 'Summary 2', '/path/to/image2.png')");
		statement.executeUpdate("INSERT INTO image(id, name, summary, picture) VALUES(3, 'Image 3', 'Summary 3', '/path/to/image3.png')");
	}

	@Test
	public void shouldListImages() throws Exception {
		// WHEN
		List<Image> images = imageDao.listImages();
		// THEN
		Assertions.assertThat(images).hasSize(3);
		Assertions.assertThat(images).extracting("id", "name", "summary").containsOnly(
				Assertions.tuple(1, "Image 1", "Summary 1"),
				Assertions.tuple(2, "Image 2", "Summary 2"),
				Assertions.tuple(3, "Image 3", "Summary 3")
		);
	}
	
	

	@Test
	public void shouldGetImage() throws Exception {
		// WHEN
		Image image = imageDao.getImage(1);
		// THEN
		Assertions.assertThat(image).isNotNull();
		Assertions.assertThat(image.getId()).isEqualTo(1);
		Assertions.assertThat(image.getName()).isEqualTo("Image 1");
		Assertions.assertThat(image.getSummary()).isEqualTo("Summary 1");
		
		
	}

	
	@Test
	public void shouldAddImage() throws Exception {
		// GIVEN 
		Image newImage = new Image(null, "My new image", "Summary for my new image");
		// WHEN
		imageDao.addImage(newImage, "/path/to/picture.png");
		// THEN
		try(Connection connection = DataSourceProvider.getInstance().getDataSource().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM image WHERE name='My new image'")){
			Assertions.assertThat(resultSet.next()).isTrue();
			Assertions.assertThat(resultSet.getInt("id")).isNotNull();
			Assertions.assertThat(resultSet.getString("name")).isEqualTo("My new image");
			Assertions.assertThat(resultSet.getString("summary")).isEqualTo("Summary for my new image");
			Assertions.assertThat(resultSet.getString("picture")).isEqualTo("/path/to/picture.png");
			Assertions.assertThat(resultSet.next()).isFalse();
			
		}
	}
	
	@Test
	public void shouldGetPicturePath() throws Exception {
		// WHEN
		String picturePath = imageDao.getPicturePath(1);
		// THEN
		Assertions.assertThat(picturePath).isEqualTo("/path/to/image1.png");
		
	}
	

}
