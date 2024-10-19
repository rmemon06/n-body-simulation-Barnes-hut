import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class load extends JPanel{
	int vectors= 0 ;
	int tracers= 0;
	int elastic= 0;
	
	public load(user User) {
		setBackground(Color.black);
		setForeground(Color.black);
		
		JButton showUsersFiles = new JButton("Show files");
		add(showUsersFiles);
	//make a class for userSimulations which has the simukation name, list of bodies, time step, theta
	//have a loop o make a list of simulation names using a getter, , in that looop give that button a action performed
	//that button will load the bodies using a getter for the bodies, so like gravitaitonSimulation clear and then gravitationalSimulation.addbodies
	//remove the panel of simulations buttons
		showUsersFiles.addActionListener(e -> {
			try {
				if(User != null)
				showUserSims(User);
				else {
					JOptionPane.showMessageDialog(null, "not logged in can not LOAD"); // cant load if not logged in
				}
			}
			

			catch(Exception ex) {
				 ex.printStackTrace();
			}
			
		});
	}
	
	
	public void showUserSims(user User) {
		List<userSimulation> userSims =  getUserSims(User); // write a method to make a bunch of userSim objects which reads in sim name from simulations table and bodies from bodies table where the user Id = user Id logged in
		  JFrame simsFrame = new JFrame("User Simulations");
		  //frame that displays all the simulations created by user
		  simsFrame.setLayout(new GridLayout(userSims.size(), 1));
		  simsFrame.setBackground(Color.black);
		  simsFrame.setForeground(Color.black);
		  for (userSimulation userSim : userSims) {
			  JButton simulationButton = new JButton(userSim.getSimulationName());
			  simulationButton.setBackground(Color.black);
			  simulationButton.setForeground(Color.white);
			  simulationButton.setFont(new Font("Arial", Font.PLAIN, 12));
			  simulationButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				
					 ArrayList<Body> bodies = getBodies(userSim.getID());
		               loadBodies(bodies, userSim.getTimestep(), userSim.getTheta()); // LOAD BODIES LOADS EVERYTHING INTO THE GRAVITATIONAL SIMULATION CLASS WITH TIMESTEPS AND THETA

		                // Close the frame after loading bodies
					   simsFrame.dispose();
				}
				  
			  });
			  simsFrame.add(simulationButton);
			  }
		  
		  simsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		    // Pack and set the frame visible
		    simsFrame.pack();
		    simsFrame.setLocationRelativeTo(null); // Center the frame on screen
		    simsFrame.setVisible(true);
		  }
	
	
	
	
	public void loadBodies(ArrayList<Body> bodies, int timeStep, int theta) {
		GravitationSimulation.bodies.clear();

	    // Repaint the panel to reflect the changes
   	 repaint();
   	 //need to get math panel before using it
   	 MathPanel mathPanel = (MathPanel) GravitationSimulation.tabbedPane.getComponentAt(1);
   	 mathPanel.resetGraph();
     GravitationSimulation.timeStep = timeStep / (timeStep / 0.2);
     GravitationSimulation.barnesHutTree.Theta = theta/10;
     if(vectors == 1) {
    	 GravitationSimulation.drawVectors = true;
     }
     if(tracers == 1) {
    	 GravitationSimulation.drawTracers = true;
     }
     if(elastic == 1) {
    	 GravitationSimulation.checkElastic = true;
     }
     
     GravitationSimulation.isRunning = true;
     GravitationSimulation.bodies.addAll(bodies);
     
	}
	
	public  List<userSimulation> getUserSims(user currentUser){
		ArrayList<userSimulation> userSimulations = new ArrayList<>();
		try {
		Connection 	con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
		
		String selectQuery = "SELECT * FROM simulations WHERE userID = ?";
		PreparedStatement pst = con.prepareStatement(selectQuery);
	    pst.setInt(1, currentUser.getId());
	    ResultSet rs = pst.executeQuery();
		//getting all the simulations by the user which is logged in hence the Currentuser.getID and while loop
		 while (rs.next()) {
			 
             int simulationID = rs.getInt("simulationID");
             String simulationName = rs.getString("simulationName");
             int timestep = rs.getInt("timestep") ;
             int theta = rs.getInt("theta") ;
             vectors = rs.getInt("vectors");
             tracers = rs.getInt("tracers");
             elastic = rs.getInt("elastic");
             // Retrieve bodies associated with the simulation
             ArrayList<Body> bodies = getBodies(simulationID);

             userSimulation userSimulation = new userSimulation(simulationID, simulationName, bodies, timestep, theta);
             userSimulations.add(userSimulation);
         }
		 rs.close();
         pst.close();
         con.close();
		
	}
		
		 catch (Exception ex) {
	            ex.printStackTrace();
	        }
	     return userSimulations;
	     
}
	
	public ArrayList<Body> getBodies(int simID){
		ArrayList<Body> bodies = new ArrayList<>();
		   try {
			   Connection con = DriverManager.getConnection("jdbc:mysql://localhost/login", "root", "");
			   String selectQuery = "SELECT * FROM bodies WHERE simulationID = ? ORDER BY time"; // ordered by time so i can add them by time
				   PreparedStatement  pst = con.prepareStatement(selectQuery);
		           pst.setInt(1, simID);
		           ResultSet    rs = pst.executeQuery();
		            //while loop to get every body linked to the simulationID
		            while (rs.next()) {
		                double mass = rs.getDouble("mass");
		                double x = rs.getDouble("x");
		                double y = rs.getDouble("y");
		                double xVelocity = rs.getDouble("xVelocity");
		                double yVelocity = rs.getDouble("yVelocity");
		                double time = rs.getDouble("time");
		                double radius = rs.getDouble("radius");

		                Body body = new Body(mass, x, y, xVelocity, yVelocity, radius, time);
		                bodies.add(body);
		            }
		            rs.close();
		            pst.close();
		            con.close();
		            
		   }
		   
		   
		   
		   catch (Exception ex) {
	            ex.printStackTrace();
	        }
		   return bodies;
	}
	
}
