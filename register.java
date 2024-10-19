import java.awt.EventQueue;

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

public class register {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Connection con = null;
	PreparedStatement pst = null;
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setBounds(217, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(73, 74, 110, 20);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(73, 152, 110, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(313, 74, 110, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(313, 152, 110, 20);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(10, 77, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(10, 155, 62, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(257, 77, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(257, 155, 62, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnRegister = new JButton("register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					  int randomNum;
					    String ID;
					 do {
	                        randomNum = 1 + (int) (Math.random() * 10000);
	                        ID = Integer.toString(randomNum);
	                    } while (!isUniqueId(ID));

					String query = "INSERT INTO logindetails(userID, Name, Username, Email, Password) VALUES (?,?,?,?,?)";
					con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
					pst = con.prepareStatement(query);
					pst.setString(1, ID );
					pst.setString(2, txtName.getText() );
					pst.setString(3, txtUsername.getText() );
					pst.setString(4, txtEmail.getText());
					pst.setString(5,  txtPassword.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "You have registered");
					frame.dispose();
					logins.main(null);
					
				;
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnRegister.setBounds(198, 211, 89, 23);
		frame.getContentPane().add(btnRegister);
	}
	
	  private boolean isUniqueId(String id) {
	        try {
	            String query = "SELECT userID FROM logindetails WHERE userID=?";
	            con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
	            PreparedStatement pst = con.prepareStatement(query);
	            pst.setString(1, id);
	            ResultSet rs = pst.executeQuery();
	            return !rs.next(); // If ResultSet is empty, ID is unique
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }
	
}

