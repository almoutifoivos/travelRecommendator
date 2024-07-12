
public class YoungTraveller extends Traveller {

	public YoungTraveller(String name, int age) {
		super(name, age);
	}
	
	public YoungTraveller () {
		super();
	}
	@Override
	public double calculateSimilarity(City curCity) {
		double result = 0;
		double temp;
		for(int i=0;i<10;i++) {
			temp = this.getTerms_pref()[i] - curCity.getTerms_vector()[i];
			result += temp * temp;
		}

		result = 1 / (Math.sqrt(result) + 1);
		return result;
	}
		
}
