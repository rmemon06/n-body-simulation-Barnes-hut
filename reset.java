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
import java.awt.event.ActionEvent;

public class reset {

	private JFrame frame;
	private JTextField txtPass;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reset window = new reset();
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
	public reset() {
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
		
		JLabel lblNewLabel = new JLabel("Reset");
		lblNewLabel.setBounds(217, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtPass = new JTextField();
		txtPass.setBounds(73, 74, 110, 20);
		frame.getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		
		txtEmail = new JTextField();
		txtEmail.setBounds(73, 134, 110, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(10, 77, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("email");
		lblNewLabel_2.setBounds(10, 137, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		JButton btnReset = new JButton("reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    String newPassword = txtPass.getText();
		            String email = txtEmail.getText();
		            
		            // Update query to set the new password based on email
		            String query = "UPDATE logindetails SET Password = ? WHERE Email = ?";
		            
		            con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
		            pst = con.prepareStatement(query);
		            pst.setString(1, newPassword);
		            pst.setString(2, email);
		            
		            int rowsAffected = pst.executeUpdate();
		            if (rowsAffected > 0) {
		                JOptionPane.showMessageDialog(null, "Password reset successfully!");
		                frame.dispose(); // Dispose the frame
		                logins.main(null);
		            } else {
		                JOptionPane.showMessageDialog(null, "Password reset failed. Email not found!");
		            }
					
					
					
				;
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnReset.setBounds(198, 211, 89, 23);
		frame.getContentPane().add(btnReset);
	}
}

