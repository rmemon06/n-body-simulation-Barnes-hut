import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class save extends JPanel{
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs=null;
	
	public save(user currentUser, SidePanel sidePanel, GravitationSimulation gravitationSimulation) {
		setBackground(Color.black);
		setForeground(Color.black);
		
		JTextField simName = new JTextField("Enter simulation name here:");
		add(simName);
		
		JButton saveButton = new JButton("Save");
		add(saveButton);
		
		//when save button is pressed, add a record into simulations table which will have the users id as the foreign key, the simulation name in the text field, data and time idk some other data
		 saveButton.addActionListener(e -> {
				try {
					if (currentUser != null) { 
					con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
				   
						int userID = currentUser.getId();

		                // Generate a random simulation ID
		                int simulationID = (int) (Math.random() * 10000);

		                // Get the simulation name from the text field
		                String simulationName = simName.getText();

		                // Insert a new record into the simulations table
		                String insertQuery = "INSERT INTO simulations (simulationID, simulationName, userID, timestep, theta, vectors, tracers, elastic) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		                pst = con.prepareStatement(insertQuery);
		                pst.setInt(1, simulationID);
		            
		                pst.setString(2, simulationName);
		                pst.setInt(3, userID);
		                //GETTING THESE VALUES FROM THE SLIDERS, 1-50 FOR TIME AND THETA IS 1-10 so when loading need to divide
		                pst.setInt(4,  sidePanel.timeStepSlider.getValue());
		                pst.setInt(5,  sidePanel.thetaSlider.getValue());
		                pst.setBoolean(6, GravitationSimulation.drawVectors);
		                pst.setBoolean(7, GravitationSimulation.drawTracers);
		                pst.setBoolean(8, GravitationSimulation.checkElastic);
		                
		                pst.executeUpdate();
  
		                
		                for(Body body: GravitationSimulation.initialBodies) {
		                	 saveBodyData(simulationID, body);
		                }     
		                pst.close();
		               
		               	JOptionPane.showMessageDialog(null, "Saved!");

				}
					else {
						JOptionPane.showMessageDialog(null, "not logged in can not SAVE"); // of not logged in cant save
		            
		            }
				}
				
				catch(Exception ex) {
					 ex.printStackTrace();
				}
			 
		 });
		
	}
	
	
	
	public void saveBodyData(int simulationID, Body body) throws SQLException {
		//saving boides data, simulationID being the foreign key and the data
		  String insertBodyQuery = "INSERT INTO bodies (simulationID, mass, x, y, xVelocity, yVelocity, time, radius) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		  
		  	pst = con.prepareStatement(insertBodyQuery);
	        pst.setInt(1, simulationID);
	        pst.setDouble(2, body.mass);
	        pst.setDouble(3, body.x);
	        pst.setDouble(4, body.y);
	        pst.setDouble(5, body.vx);
	        pst.setDouble(6, body.vy);
	        pst.setDouble(7, body.time);
	        pst.setDouble(8, body.radius);
	        pst.executeUpdate();
	        pst.close();
		
		
	}
	
}
