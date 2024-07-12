
public class MiddleTraveller extends Traveller {

	public MiddleTraveller(String name, int age) {
		super(name, age);
	}

	@Override
	public double calculateSimilarity(City curCity) {
		double dotProduct = 0.0;
	    double normA = 0.0;
	    double normB = 0.0;
	    for(int i =0 ;i<10;i++) {
	    	dotProduct += this.getTerms_pref()[i] * curCity.getTerms_vector()[i];
	    	normA += Math.pow(this.getTerms_pref()[i], 2);
	        normB += Math.pow(curCity.getTerms_vector()[i], 2);
	    }
		
	    return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}

}
