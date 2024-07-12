import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;


public class TravInfoJFrame extends JFrame {
	protected JLabel nameLabel;
	protected JScrollPane scrollPane;
	private ImageIcon iconImg = new ImageIcon(this.getClass().getResource("/icon.png"));
	
	
	public TravInfoJFrame() {
		
		getContentPane().setForeground(Color.BLACK);
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
	    getContentPane().setBackground(new Color(153, 153,204));
		setBounds(100, 100, 765, 500);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Travelling Assistant");
        setResizable(false);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setIconImage(iconImg.getImage());
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
					Traveller.writeTravellersToJson();
				} catch (Exception e1) {}
                dispose();
                System.exit(0);
            }
        });
        
        nameLabel = new JLabel();
        nameLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
        nameLabel.setForeground(new Color(240, 248, 255));
        nameLabel.setBounds(10, 11, 150, 35);
        getContentPane().add(nameLabel);
        
        JButton btnNewButton = new JButton("Back");
        btnNewButton.setBackground(new Color(255, 255, 255));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		AppGUI.frame.setVisible(true);
        	}
        });
        btnNewButton.setBounds(0, 437, 89, 23);
        getContentPane().add(btnNewButton);
        
        scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setToolTipText("");
        scrollPane.setBounds(10, 36, 408, 369);
        getContentPane().add(scrollPane);
        
	}
}
