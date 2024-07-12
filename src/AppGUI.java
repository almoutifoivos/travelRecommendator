import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class AppGUI {

	protected static JFrame frame;
	private static JTextField nameTextField;
	private static JTextField cityTextField;
	private static JTextField ageText;
	private static JSpinner cafe = new JSpinner();
	private static JSpinner sea = new JSpinner();
	private static JSpinner museum = new JSpinner();
	private static JSpinner restaurant = new JSpinner();
	private static JSpinner stadium = new JSpinner();
	private static JSpinner parks = new JSpinner();
	private static JSpinner hotWeather = new JSpinner();
	private static JSpinner sports = new JSpinner();
	private static JSpinner music = new JSpinner();
	private static JSpinner technology= new JSpinner();
	private static JLabel topCity1;
	private static JLabel topCity2;
	private static JLabel topCity3;
	private static JLabel lblYourTopCity;
	private static JLabel lblYourTop2City;
	private static JLabel lblYourTop3City;
	private TravInfoJFrame statsFrame = new TravInfoJFrame();
    private ImageIcon frameBackgroundImg = new ImageIcon(this.getClass().getResource("/space.jpg"));
    private ImageIcon iconImg = new ImageIcon(this.getClass().getResource("/icon.png"));
    private JLabel frameBackground;
    private static JLabel dbWarning = new JLabel("");
    private static JLabel lblColabView;
    private static JLabel lblColab;

	
	public static JLabel getDbWarning() {
		return dbWarning;
	}


	public AppGUI() {
		initialize();
	}

	protected void initialize() {
		frame = new JFrame();


		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setBounds(100, 100, 764, 500);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Travelling Assistant");
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconImg.getImage());
        
        lblColabView = new JLabel("Based on Community:");
        lblColabView.setForeground(new Color(240, 248, 255));
        lblColabView.setVisible(false);
        lblColabView.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblColabView.setBounds(5, 369, 150, 35);
        frame.getContentPane().add(lblColabView);
        
        lblColab = new JLabel("");
        lblColab.setForeground(new Color(240, 248, 255));
        lblColab.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblColab.setBounds(5, 391, 150, 35);
        frame.getContentPane().add(lblColab);
        
        
        dbWarning.setHorizontalAlignment(SwingConstants.LEFT);
        dbWarning.setForeground(new Color(240, 248, 255));
        dbWarning.setFont(new Font("Tahoma", Font.PLAIN, 11));
        dbWarning.setBounds(515, 436, 234, 35);
        frame.getContentPane().add(dbWarning);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        nameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        nameLabel.setForeground(new Color(240, 248, 255));
        nameLabel.setBounds(5, 54, 129, 35);
        frame.getContentPane().add(nameLabel);
        
        nameTextField = new JTextField();
        nameTextField.setBackground(new Color(255, 255, 255));
        nameTextField.setBounds(130, 60, 123, 25);
        frame.getContentPane().add(nameTextField);
        nameTextField.setColumns(4);
        
        JLabel cityLabel = new JLabel("City of residence:");
        cityLabel.setForeground(new Color(240, 248, 255));
        cityLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        cityLabel.setBounds(5, 146, 155, 35);
        frame.getContentPane().add(cityLabel);
        
        cityTextField = new JTextField();
        cityTextField.setColumns(10);
        cityTextField.setBounds(130, 152, 123, 25);
        frame.getContentPane().add(cityTextField);
        
        JLabel welcomeLabel = new JLabel("YOUR TRAVELLING ASSISTANT");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        welcomeLabel.setForeground(new Color(37, 150, 190));
        welcomeLabel.setBounds(5, 10, 359, 33);
        frame.getContentPane().add(welcomeLabel);
        
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(new Color(240, 248, 255));
        ageLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        ageLabel.setBounds(5, 100, 129, 35);
        frame.getContentPane().add(ageLabel);
        
        ageText = new JTextField();
        ageText.setColumns(10);
        ageText.setBounds(130, 106, 123, 25);
        frame.getContentPane().add(ageText);
        
        JButton submit = new JButton("Submit Info");
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setBackground(new Color(255, 255, 255));
        submit.setBounds(320, 276, 123, 20);
        submit.addActionListener(new LoginAction());
        frame.getContentPane().add(submit);
        
        cafe = new JSpinner();
        cafe.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        cafe.setBounds(522, 67, 45, 20);
        ((JSpinner.DefaultEditor)cafe.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(cafe);
        
        sea = new JSpinner();
        sea.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        sea.setBounds(522, 105, 45, 20);
        ((JSpinner.DefaultEditor)sea.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(sea);
        
        museum = new JSpinner();
        museum.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        museum.setBounds(522, 144, 45, 20);
        ((JSpinner.DefaultEditor)museum.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(museum);
        
        restaurant = new JSpinner();
        restaurant.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        restaurant.setBounds(522, 191, 45, 20);
        ((JSpinner.DefaultEditor)restaurant.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(restaurant);
        
        stadium = new JSpinner();
        stadium.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        stadium.setBounds(522, 235, 45, 20);
        ((JSpinner.DefaultEditor)stadium.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(stadium);
        
        parks = new JSpinner();
        parks.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        parks.setBounds(695, 67, 45, 20);
        ((JSpinner.DefaultEditor)parks.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(parks);
        
        hotWeather = new JSpinner();
        hotWeather.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        hotWeather.setBounds(695, 105, 45, 20);
        ((JSpinner.DefaultEditor)hotWeather.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(hotWeather);
        
        sports = new JSpinner();
        sports.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        sports.setBounds(695, 144, 45, 20);
        ((JSpinner.DefaultEditor)sports.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(sports);
        
        music = new JSpinner();
        music.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        music.setBounds(695, 191, 45, 20);
        ((JSpinner.DefaultEditor)music.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(music);
        
        technology = new JSpinner();
        technology.setModel(new SpinnerNumberModel(5, 0, 10, 1));
        technology.setBounds(695, 235, 45, 20);
        ((JSpinner.DefaultEditor)technology.getEditor()).getTextField().setEditable(false);
        frame.getContentPane().add(technology);
        
        JLabel lblCafes = new JLabel("Cafes:");
        lblCafes.setForeground(new Color(240, 248, 255));
        lblCafes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCafes.setBounds(470, 59, 54, 35);
        frame.getContentPane().add(lblCafes);
        
        JLabel lblCafes_1 = new JLabel("Sea:");
        lblCafes_1.setForeground(new Color(240, 248, 255));
        lblCafes_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCafes_1.setBounds(485, 97, 54, 35);
        frame.getContentPane().add(lblCafes_1);
        
        JLabel lblCafes_2 = new JLabel("Museum:");
        lblCafes_2.setForeground(new Color(240, 248, 255));
        lblCafes_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblCafes_2.setBounds(450, 136, 77, 35);
        frame.getContentPane().add(lblCafes_2);
        
        JLabel lblRestaurants = new JLabel("Restaurants:");
        lblRestaurants.setForeground(new Color(240, 248, 255));
        lblRestaurants.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRestaurants.setBounds(425, 183, 110, 35);
        frame.getContentPane().add(lblRestaurants);
        
        JLabel lblStadium = new JLabel("Stadium:");
        lblStadium.setForeground(new Color(240, 248, 255));
        lblStadium.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStadium.setBounds(452, 227, 77, 35);
        frame.getContentPane().add(lblStadium);
        
        JLabel lblParks = new JLabel("Parks:");
        lblParks.setForeground(new Color(240, 248, 255));
        lblParks.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblParks.setBounds(645, 59, 54, 35);
        frame.getContentPane().add(lblParks);
        
        JLabel lblHotWeather = new JLabel("Hot Weather:");
        lblHotWeather.setForeground(new Color(240, 248, 255));
        lblHotWeather.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHotWeather.setBounds(595, 97, 100, 35);
        frame.getContentPane().add(lblHotWeather);
        
        JLabel lblSports = new JLabel("Sports:");
        lblSports.setForeground(new Color(240, 248, 255));
        lblSports.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSports.setBounds(640, 136, 68, 35);
        frame.getContentPane().add(lblSports);
        
        JLabel lblMusic = new JLabel("Music:");
        lblMusic.setForeground(new Color(240, 248, 255));
        lblMusic.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblMusic.setBounds(645, 183, 54, 35);
        frame.getContentPane().add(lblMusic);
        
        JLabel lblTechnology = new JLabel("Technology:");
        lblTechnology.setForeground(new Color(240, 248, 255));
        lblTechnology.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTechnology.setBounds(603, 227, 92, 35);
        frame.getContentPane().add(lblTechnology);
        
        JLabel lblRateTheFollowing = new JLabel("RATE THE FOLLOWING 0/10");
        lblRateTheFollowing.setHorizontalAlignment(SwingConstants.LEFT);
        lblRateTheFollowing.setForeground(new Color(240, 248, 255));
        lblRateTheFollowing.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRateTheFollowing.setBounds(524, 10, 225, 35);
        frame.getContentPane().add(lblRateTheFollowing);
        
        lblYourTop2City = new JLabel("YOUR TOP 2 CITY");
        lblYourTop2City.setForeground(new Color(240, 248, 255));
        lblYourTop2City.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYourTop2City.setBounds(200, 369, 159, 35);
        lblYourTop2City.setVisible(false);
        frame.getContentPane().add(lblYourTop2City);
        
        topCity1 = new JLabel("");
        topCity1.setHorizontalAlignment(SwingConstants.LEFT);
        topCity1.setForeground(new Color(240, 248, 255));
        topCity1.setFont(new Font("Tahoma", Font.BOLD, 13));
        topCity1.setBounds(353, 333, 129, 35);
        frame.getContentPane().add(topCity1);
        
        lblYourTopCity = new JLabel("YOUR TOP CITY");
        lblYourTopCity.setForeground(new Color(240, 248, 255));
        lblYourTopCity.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYourTopCity.setBounds(326, 307, 127, 35);
        lblYourTopCity.setVisible(false);
        frame.getContentPane().add(lblYourTopCity);
        
        lblYourTop3City = new JLabel("YOUR TOP 3 CITY");
        lblYourTop3City.setForeground(new Color(240, 248, 255));
        lblYourTop3City.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYourTop3City.setBounds(435, 369, 164, 35);
        lblYourTop3City.setVisible(false);
        frame.getContentPane().add(lblYourTop3City);
        
        topCity2 = new JLabel("");
        topCity2.setForeground(new Color(240, 248, 255));
        topCity2.setFont(new Font("Tahoma", Font.BOLD, 13));
        topCity2.setBounds(229, 402, 150, 35);
        frame.getContentPane().add(topCity2);
        
        topCity3 = new JLabel("");
        topCity3.setForeground(new Color(240, 248, 255));
        topCity3.setFont(new Font("Tahoma", Font.BOLD, 13));
        topCity3.setBounds(471, 402, 139, 35);
        frame.getContentPane().add(topCity3);


        JButton changeFrameBtn = new JButton("See All Travellers"); //change frame action
        changeFrameBtn.setBackground(new Color(255, 255, 255));
        changeFrameBtn.setBounds(0, 437, 160, 23);
        changeFrameBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.setVisible(false);
    			statsFrame.setVisible(true);
    			Traveller.sortTravellers();

    			
    			statsFrame.nameLabel.setText("Total Travellers: " + Main.allTravellers.size());
    			
    			String[] str = new String[Traveller.getTravellerNames().size()];
    			JList<String> travList = new JList<>(Traveller.getTravellerNames().toArray(str));
    			travList.setOpaque(false);
    			travList.setForeground(new Color(37, 150, 190));
    			travList.setBackground(new Color(35,30,33,255));
    			((DefaultListCellRenderer) travList.getCellRenderer()).setOpaque(false);
    			statsFrame.scrollPane.setViewportView(travList);

                //Background
    			statsFrame.getContentPane().add(frameBackground);
        	}
        });
        frame.getContentPane().add(changeFrameBtn);

        //Background
        frameBackground = new JLabel(frameBackgroundImg);
        frameBackground.setBounds(0, 0, 765, 500);
        frame.getContentPane().add(frameBackground);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
					Traveller.writeTravellersToJson();
				} catch (Exception e1) {}
                frame.dispose();
                System.exit(0);
            }
        });

        //Background reinitialization for when returning from statsFrame
        frameBackground = new JLabel(frameBackgroundImg);
        frameBackground.setBounds(0, 0, 765, 500);
        frame.getContentPane().add(frameBackground);
    }
	
	
	static int flag = 1; //1 is good , 0 is bad
	private class LoginAction implements ActionListener {
		
		
		@Override
		public void actionPerformed(ActionEvent e){
			
			
			Traveller traveller;
			int age;
			try {
				age = Integer.parseInt(ageText.getText());
				}
				catch(Exception e2) {
				  JOptionPane.showMessageDialog(null, "Not a valid number for age. Try again!");
				  return;
				}
			if (age < 16) {
				JOptionPane.showMessageDialog(null, "Too young. Try again!");
				return;
	        }
			
			if (age > 130) {
				JOptionPane.showMessageDialog(null, "Too old. Try again!");
				return;
	        }
			
			String name = nameTextField.getText();
			if (age >= 16 && age <= 25) {
	            traveller = new YoungTraveller(name, age);

	        } else if (age > 25 && age <= 60) {
	            traveller = new MiddleTraveller(name, age);

	        } else {
	            traveller = new ElderTraveller(name, age);

	        }
		
			
		    
			String city = cityTextField.getText();
			
			if (!Main.allCities.containsKey(city)) {
	            City searchCity = new City(city);
	            Main.allCities.put(city,searchCity);
	            
	            
	            ThreadTerms myThreadTerms = new ThreadTerms();
	            ThreadGeo myThreadGeo = new ThreadGeo();
	            myThreadTerms.start();
	            myThreadGeo.start();
	            
	            try {
					myThreadTerms.join();
					myThreadGeo.join();
				} catch (InterruptedException e2) {
					System.out.println("Interrupted");
				}
	            
	            
	            if(AppGUI.flag == 0) {
	            	JOptionPane.showMessageDialog(null, "City does not exist , please try again");
	            	return;
	            }
	            
	            try {
					City.writeToDB(searchCity);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed To Access DB");
				}
	            
	            Main.allCities.put(city, searchCity);
	            traveller.setGeodesic_pref(searchCity.getGeodesic_vector());
	        }else {
	        	traveller.setGeodesic_pref(Main.allCities.get(city).getGeodesic_vector());
	        }
			int [] termsPref = new int[10];
			termsPref[0] = (int) cafe.getValue();
			termsPref[1] = (int) sea.getValue();
			termsPref[2] = (int) museum.getValue();
			termsPref[3] = (int) restaurant.getValue();
			termsPref[4] = (int) stadium.getValue();
			termsPref[5] = (int) parks.getValue();
			termsPref[6] = (int) hotWeather.getValue();
			termsPref[7] = (int) sports.getValue();
			termsPref[8] = (int) music.getValue();
			termsPref[9] = (int) technology.getValue();
			
	        traveller.setTerms_pref(termsPref);
	        
	        Traveller colabTraveller = Main.allTravellers.stream().max(Comparator
					.comparingInt(trav->{
						int sum=0;
						for(int i=0;i<10;i++) {
							sum = sum+(trav.getTerms_pref()[i]*traveller.getTerms_pref()[i]);
						}
						return sum;
					}))
					.orElse(null);
	        Main.allTravellers.add(traveller);
	        if(colabTraveller!=null) {
	        	lblColab.setText(colabTraveller.getVisit());
		        lblColabView.setVisible(true);
	        }
	        
	        
	        ArrayList<City> tempCities = new ArrayList<>(Main.allCities.values());

	        
	        String[] topCities = traveller.compareCities(tempCities, 3);
	        traveller.setVisit(topCities[0]);
	        lblYourTopCity.setVisible(true);
	        topCity1.setText(topCities[0]);
	        lblYourTop2City.setVisible(true);
	        topCity2.setText(topCities[1]);
	        lblYourTop3City.setVisible(true);
	        topCity3.setText(topCities[2]);  
	        
		}
		
    }
	
	private class ThreadTerms extends Thread{
		
		String city = cityTextField.getText();
		City searchCity = Main.allCities.get(city);
		
		
		@Override
		public void run(){
			try {
				searchCity.setTerms_vector();
				AppGUI.flag = 1;
			} catch (Exception e) {
				Main.allCities.remove(city);
				AppGUI.flag = 0;
				
			}
			
		}
	}
	
	private class ThreadGeo extends Thread{
		
		String city = cityTextField.getText();
		City searchCity = Main.allCities.get(city);
		
		@Override
		public void run(){
			try {
				searchCity.setGeodesic_vector();
				AppGUI.flag =1 ;
			} catch (Exception e) {
				Main.allCities.remove(city);
				AppGUI.flag = 0;
				
			}
			
		}
	}
}
