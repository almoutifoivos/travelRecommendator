import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class Main {
    static final String appid = "7ee00d1719bab23e58e8acfad960fa89";
    static ArrayList<Traveller> allTravellers = new ArrayList<>();
    static HashMap<String, City> allCities = new HashMap<>();
    static String city;
    

    public static void main(String[] args) throws Exception {

    	//flatlaf theme installation
		try {
			UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
			System.err.println( "Failed to initialize LaF" );
		}

    	Traveller.loadTravellersFromJson();
    	City.readFromDB();
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
    }
}
