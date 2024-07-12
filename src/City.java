import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class City {

	

	private String name;
	private int[] terms_vector = new int[10];
	private double[] geodesic_vector = new double[2];

	public City(String name) {
		this.name = name;
		this.terms_vector = null;
		this.geodesic_vector = null;

	}

	public City() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getTerms_vector() {
		return terms_vector;
	}

	public void setTerms_vector() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		this.terms_vector = calculateTerms(Main.appid);
	}

	public void setTerms_vector(int[] termsVector) {
		this.terms_vector = termsVector;
	}

	public double[] getGeodesic_vector() {
		return geodesic_vector;
	}

	public void setGeodesic_vector() throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		this.geodesic_vector = retrieveUnknownGeo(Main.appid);
	}

	public void setGeodesic_vector(double[] geoVector) {
		this.geodesic_vector = geoVector;
	}

	public Traveller compareTravellers() {
		Traveller bestTraveller = null;
		double result;
		double bestSimilarity = -1;
		for (int i = 0; i < Main.allTravellers.size(); i++) {
			result = Main.allTravellers.get(i).calculateFreeTicket(this);
			if (result > bestSimilarity) {
				bestSimilarity = result;
				bestTraveller = Main.allTravellers.get(i);
			}

		}

		return bestTraveller;
	}

	private double[] retrieveUnknownGeo(String appid)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException { // retrieve Geodesic
																									// info
		ObjectMapper mapper = new ObjectMapper();
		String nameWithoutSpace = this.name.replace(' ', '+');

		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + nameWithoutSpace + "&APPID=" + appid + ""),
				OpenWeatherMap.class);
		double[] tempGeo = { weather_obj.getCoord().getLat(), weather_obj.getCoord().getLon() };
		return tempGeo;

	}

	private int retrieveTemperature(String appid)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String nameWithoutSpace = this.name.replace(' ', '+');
		OpenWeatherMap weather_obj = mapper.readValue(new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=" + nameWithoutSpace + "&APPID=" + appid + ""),
				OpenWeatherMap.class);
		int temperature;

		double celsius = weather_obj.getMain().getTemp() - 273.15; // conversion from kelvin to celsius
		if (celsius >= 40) {
			temperature = 10;
		} else if (celsius >= 35) {
			temperature = 9;
		} else if (celsius >= 30) {
			temperature = 8;
		} else if (celsius >= 25) {
			temperature = 7;
		} else if (celsius >= 20) {
			temperature = 6;
		} else if (celsius >= 15) {
			temperature = 5;
		} else if (celsius >= 10) {
			temperature = 4;
		} else if (celsius >= 5) {
			temperature = 3;
		} else if (celsius >= 0) {
			temperature = 2;
		} else if (celsius >= -5) {
			temperature = 1;
		} else {
			temperature = 0;
		}

		return temperature;

	}

	private int[] calculateTerms(String appid)
			throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
		int[] terms = new int[10];
		ObjectMapper mapper = new ObjectMapper();
		String nameWithoutSpace = this.name.replace(' ', '+');
		MediaWiki mediaWiki_obj = mapper
				.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="
						+ nameWithoutSpace + "&format=json&formatversion=2"), MediaWiki.class);

		terms[0] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "cafe");
		terms[1] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "sea");
		terms[2] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "museum");
		terms[3] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "restaurant");
		terms[4] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "stadium");
		terms[5] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "park");
		terms[6] = retrieveTemperature(appid);
		terms[7] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "sports");
		terms[8] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "music");
		terms[9] = countCriterionCity(mediaWiki_obj.getQuery().getPages().get(0).getExtract(), "technology");

		return terms;

	}

	private static int countCriterionCity(String cityArticle, String criterion) {
		cityArticle = cityArticle.toLowerCase();
		int index = cityArticle.indexOf(criterion);
		int count = 0;
		while (index != -1) {
			count++;
			cityArticle = cityArticle.substring(index + 1);
			index = cityArticle.indexOf(criterion);
		}
		return count;
	}

	public static void writeToDB(City curCity) throws ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl", "IT21941", "otenet10");
			Statement st = (Statement) con.createStatement();
			st.executeUpdate("INSERT INTO Cities VALUES('" + curCity.getName() + "')");
			st.executeUpdate("INSERT INTO Terms_Vector VALUES (" + curCity.getTerms_vector()[0] + ","
					+ curCity.getTerms_vector()[1] + "," + curCity.getTerms_vector()[2] + ","
					+ curCity.getTerms_vector()[3] + "," + curCity.getTerms_vector()[4] + ","
					+ curCity.getTerms_vector()[5] + "," + curCity.getTerms_vector()[6] + ","
					+ curCity.getTerms_vector()[7] + "," + curCity.getTerms_vector()[8] + ","
					+ curCity.getTerms_vector()[9] + ",'" + curCity.getName() + "')");
			st.executeUpdate("INSERT INTO Geo_Vector VALUES (" + curCity.getGeodesic_vector()[0] + ","
					+ curCity.getGeodesic_vector()[1] + ",'" + curCity.getName() + "')");
			con.commit();
			con.close();
		} catch (Exception e) {
		}

	}

	public static void readFromDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl", "IT21941",
					"otenet10");
			Statement st = (Statement) con.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT CITY_NAME , CAFE, SEA,MUSEUM,RESTAURANT,STADIUM,PARK,TEMPERATURE,SPORTS,MUSIC,TECHNOLOGY,LAT,LON FROM Cities JOIN Terms_Vector on Cities.city_name = Terms_Vector.city_terms JOIN Geo_Vector on Terms_Vector.city_terms = Geo_Vector.city_geo_terms");

			while (rs.next()) {
				int[] tempTerms = new int[10];
				double[] tempGeo = new double[2];
				City tempCity = new City();
				tempCity.setName(rs.getString(1));
				for (int i = 2; i <= 11; i++) {
					tempTerms[i - 2] = rs.getInt(i);
				}
				tempCity.setTerms_vector(tempTerms);
				tempGeo[0] = rs.getDouble(12);
				tempGeo[1] = rs.getDouble(13);
				tempCity.setGeodesic_vector(tempGeo);

				Main.allCities.put(tempCity.getName(), tempCity);
			}
			con.commit();
			con.close();
		} catch (Exception e) {
			AppGUI.getDbWarning().setText("Not Connected to Database! Restart Program.");
		}

	}

}
