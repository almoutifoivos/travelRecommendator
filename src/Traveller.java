import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class Traveller implements Comparable<Traveller> {

	protected static ObjectMapper mapper = new ObjectMapper();

	Scanner r = new Scanner(System.in);
	protected int[] terms_pref = new int[10];
	protected double[] geodesic_pref = new double[2];
	protected String name;
	protected int age;
	protected Timestamp timestamp;
	protected String visit;

	public Traveller() {

	}

	public Traveller(String name, int age) {
		this.name = name;
		this.age = age;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public String getName() {
		return this.name;
	}

	public void setTerms_pref(int[] terms_pref) {
		this.terms_pref = terms_pref;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getAge() {
		return this.age;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String city) {
		this.visit = city;
	}

	public abstract double calculateSimilarity(City curCity);

	public int[] getTerms_pref() {
		return terms_pref;
	}
	
	public static ArrayList<String>  getTravellerNames() {
		Traveller.sortTravellers();
		ArrayList<String> travNames = new ArrayList<>();
		Date myDate;
		for(int i=0;i<Main.allTravellers.size();i++) {
			myDate = new Date(Main.allTravellers.get(i).getTimestamp().getTime());
			travNames.add("Name : " + Main.allTravellers.get(i).getName() + " || Age : " + Main.allTravellers.get(i).getAge() + " || Date : " + myDate);
		}
		return travNames;
	}
	
	private int checkInput(int input) {

		while (input < 0 || input > 10) {
			System.out.println("Wrong input, try again!");
			input = r.nextInt();
		}
		return input;

	}

	public double[] getGeodesic_pref() {
		return geodesic_pref;
	}

	public void setGeodesic_pref(double[] geodesic_pref) {
		this.geodesic_pref = geodesic_pref;
	}

	public double similarityGeodesicVector(City myCity) {
		int maxdist = 15317;
		return Math.log(2 / (2 - distance(myCity.getGeodesic_vector()[0], myCity.getGeodesic_vector()[1],
				this.geodesic_pref[0], this.geodesic_pref[1], "K") / maxdist)) / Math.log(2);

	}

	private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		} else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
					+ Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			if (unit.equals("K")) {
				dist = dist * 1.609344;
			} else if (unit.equals("N")) {
				dist = dist * 0.8684;
			}
			return dist;
		}
	}

	public City compareCities(ArrayList<City> cities) {

		City probableCity = null;
		double res;
		double max = -1;
		double p = 0;
		if (this instanceof YoungTraveller) {
			p = 0.7;
		} else if (this instanceof MiddleTraveller) {
			p = 0.5;
		} else if (this instanceof ElderTraveller) {
			p = 0.3;
		}

		for (int i = 0; i < cities.size(); i++) {
			res = p * this.calculateSimilarity(cities.get(i)) + (1 - p) * this.similarityGeodesicVector(cities.get(i));
			if (max < res) {
				max = res;
				probableCity = cities.get(i);
			}

		}
		if(this.visit==null) {
			this.visit = probableCity.getName();
		}
		return probableCity;
	}

	public String[] compareCities(ArrayList<City> cities, int x) {
		String[] topCities = new String[3];
		if (x > cities.size()) {
			x = cities.size();
		}
		City[] bestCities = new City[x];
		ArrayList<City> tempCities = new ArrayList<City>(cities);

		int i = 0;
		while (i < x) {
			bestCities[i] = compareCities(tempCities);
			tempCities.remove(bestCities[i]);
			i++;
		}
		for (int j = 0; j < x; j++) {
			topCities[j] = bestCities[j].getName();
		}
		if(this.visit==null) {
			this.visit = bestCities[0].getName();
		}
		
		return topCities;
	}

	public double calculateFreeTicket(City myCity) {
		double p = 0;
		if (this instanceof YoungTraveller) {
			p = 0.7;
		} else if (this instanceof MiddleTraveller) {
			p = 0.5;
		} else if (this instanceof ElderTraveller) {
			p = 0.3;
		}

		return p * this.calculateSimilarity(myCity) + (1 - p) * this.similarityGeodesicVector(myCity);
	}

	public static void sortTravellers() {
		ArrayList<Traveller> uniqueElements = new ArrayList<>();

		for (Traveller element : Main.allTravellers) {
			if (!uniqueElements.contains(element))
				uniqueElements.add(element);
		}
		Collections.sort(uniqueElements);
		Main.allTravellers = uniqueElements; 
	}

	@Override
	public boolean equals(Object o) { // needed for duplicate removal

		if (o == this) {
			return true;
		}

		if (!(o instanceof Traveller)) {
			return false;
		}

		Traveller c = (Traveller) o;

		return name.equals(c.name);
	}

	@Override
	public int compareTo(Traveller traveller) { // needed for sort
		if (timestamp.getTime() <traveller.timestamp.getTime()) {

			return -1;
		} else if (timestamp.getTime() > traveller.timestamp.getTime()) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void writeTravellersToJson() throws JsonGenerationException, JsonMappingException, IOException {
		File jsonFile = new File("travellers.json");
		mapper.writeValue(jsonFile, Main.allTravellers);
	}

	public static void loadTravellersFromJson() throws JsonGenerationException, JsonMappingException, IOException { //THIS IS VEEEEEEERY BAD!
		File jsonFile = new File("travellers.json");
		
		if(!jsonFile.exists()) {
			jsonFile.createNewFile();	
		}
		if(jsonFile.length()==0) {
			return;
		}
		ArrayList<YoungTraveller> newList = new ArrayList<>();
		newList = mapper.readValue(jsonFile, new TypeReference<List<YoungTraveller>>() {
		});

		for (int i = 0; i < newList.size(); i++) {
			
			int age = newList.get(i).getAge();
			if (age >= 16 && age <= 25) {
				Main.allTravellers.add(newList.get(i));

			} else if (age > 25 && age <= 60) {
				MiddleTraveller mTrav = new MiddleTraveller(null, 0);
				mTrav.setAge(age);
				mTrav.setGeodesic_pref(newList.get(i).getGeodesic_pref());
				mTrav.setName(newList.get(i).getName());
				mTrav.setTerms_pref(newList.get(i).getTerms_pref());
				mTrav.setTimestamp(newList.get(i).getTimestamp());
				mTrav.setVisit(newList.get(i).getVisit());
				Main.allTravellers.add(mTrav);
			} else {
				ElderTraveller eTrav = new ElderTraveller(null, 0);
				eTrav.setAge(age);
				eTrav.setGeodesic_pref(newList.get(i).getGeodesic_pref());
				eTrav.setName(newList.get(i).getName());
				eTrav.setTerms_pref(newList.get(i).getTerms_pref());
				eTrav.setTimestamp(newList.get(i).getTimestamp());
				eTrav.setVisit(newList.get(i).getVisit());
				Main.allTravellers.add(eTrav);
			}
		}

	}
}
