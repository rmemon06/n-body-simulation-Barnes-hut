import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class logins {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	public user loggedInUser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		logins login = new logins();
		login.frame.setVisible(true);
			
		
		
	}

	/**
	 * Create the application.
	 */
	public logins() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * 
	 */
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Login = new JLabel("Login ");
		Login.setBounds(228, 27, 46, 14);
		frame.getContentPane().add(Login);
		
		JLabel Username = new JLabel("Username");
		Username.setBounds(69, 88, 57, 14);
		frame.getContentPane().add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(69, 152, 46, 14);
		frame.getContentPane().add(Password);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(136, 85, 86, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(136, 149, 86, 20);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = txtPassword.getText();
				String user = txtUsername.getText();
				String databasePassword = "";
				String databaseUsername = "";
				int userID =  0;
				try {
					con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
					java.sql.Statement stmt = con.createStatement();
				    String SQL = "SELECT * FROM logindetails WHERE Username='" + user + "' AND Password='" + pass+ "'";
				    ResultSet rs = stmt.executeQuery(SQL);
				    while (rs.next()) {
				        databaseUsername = rs.getString("Username");
				        databasePassword = rs.getString("Password");
				        userID = rs.getInt("userID");
				    }

				    if (user.equals(databaseUsername) && pass.equals(databasePassword)) {
				    	JOptionPane.showMessageDialog(null, "SUCCESS");
				    	
				    	  loggedInUser = new user(userID, databaseUsername); // store the user 
				    	  
				    	frame.dispose();
				    	GravitationSimulation sim = new GravitationSimulation(loggedInUser);
				    } else {
				    	JOptionPane.showMessageDialog(null, "Error try again");
				    }
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
					
				}
			
			}
		});
		btnLogin.setBounds(21, 215, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset.main(null);
				frame.dispose();
				
				
				
				
				
				
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(136, 215, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frmLoginSystem = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login Systems", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setBounds(369, 215, 89, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent e) {
				register.main(null);
				
			}
		});
		btnRegister.setBounds(252, 215, 89, 23);
		frame.getContentPane().add(btnRegister);
	}
}
