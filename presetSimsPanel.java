import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



//============================================================================================================================================================================//	
//CLASS FOR THE PRESET SIMULATIONS WHICH CAN BE LOADED IN//
    	 
    	  class presetSimsPanel extends JPanel{
    		 private JButton solarSystem;
    		 private JButton EarthSatellite;
    		 private JButton SpiralGalaxy;
    		 private JTextField numberOfBodiesInGalaxy;
    		 private JButton spinning;
    		 public presetSimsPanel() {
    			 
    		  solarSystem = new JButton("Solar System");
    		  solarSystem.addActionListener(new ActionListener() {
 		            @Override
 		            public void actionPerformed(ActionEvent e) {
 		              addSolarSystemBodies(GravitationSimulation.bodies);
 		              GravitationSimulation.timeStep = 0.001;
 		            }
 		        });
    		  
    		  Font buttonFont = new Font(solarSystem.getFont().getName(), Font.PLAIN, 10); 
    		  solarSystem.setFont(buttonFont);
		        
		        Insets buttonInsets = new Insets(2, 2, 2, 2);
		        solarSystem.setMargin(buttonInsets);
		        solarSystem.setBounds(10, 10, 100, 50);
		        solarSystem.setBackground(Color.black);
		        solarSystem.setForeground(Color.white);
		        setLayout(null); 
		        add(solarSystem);    
		        
    		 
		        EarthSatellite = new JButton("Earth Satellite");
		        EarthSatellite.addActionListener(new ActionListener() {
	 		            @Override
	 		            public void actionPerformed(ActionEvent e) {
	 		            	addEarthSatellite(GravitationSimulation.bodies);
	 		            	GravitationSimulation. timeStep = 0.001;
	 		            }
	 		        });
		        	
		        EarthSatellite.setFont(buttonFont);				        				      
		        EarthSatellite.setMargin(buttonInsets);
		        EarthSatellite.setBounds(10, 100, 100, 50); 
		        EarthSatellite.setBackground(Color.black);
		        EarthSatellite.setForeground(Color.white);
				add(EarthSatellite);    
				

				SpiralGalaxy = new JButton("Spiral Galaxy");
				SpiralGalaxy.addActionListener(new ActionListener() {
	 		            @Override
	 		            public void actionPerformed(ActionEvent e) {
	 		            	SpiralGalaxy(GravitationSimulation.bodies);
	 		            	GravitationSimulation.timeStep = 0.01;
	 		            }
	 		        });
		        	
				SpiralGalaxy.setFont(buttonFont);				        				      
				SpiralGalaxy.setMargin(buttonInsets);
				SpiralGalaxy.setBounds(10, 200, 200, 50);
				SpiralGalaxy.setBackground(Color.black);
				SpiralGalaxy.setForeground(Color.white);
				add(SpiralGalaxy);    
				
				spinning = new JButton("Spinning");
				spinning.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Spinning(GravitationSimulation.bodies);
						GravitationSimulation.timeStep = 1;
					}
					
				});
				spinning.setFont(buttonFont);				        				      
				spinning.setMargin(buttonInsets);
				spinning.setBounds(10, 300, 200, 50);
				spinning.setBackground(Color.black);
				spinning.setForeground(Color.white);
				add(spinning);    
				
				JLabel nBodiesInGalaxy = new JLabel("Numbers of bodies : ");
				nBodiesInGalaxy.setBounds(5 , 250, 120, 30);
				nBodiesInGalaxy.setForeground(Color.white); 
				this.numberOfBodiesInGalaxy = new JTextField("0");
				numberOfBodiesInGalaxy.setBounds(120, 250, 30, 30);
				numberOfBodiesInGalaxy.setBackground(Color.white);
				numberOfBodiesInGalaxy.setForeground(Color.black);
				add(nBodiesInGalaxy);
				add(numberOfBodiesInGalaxy);
    		 
    		 }
    		 
    		  private void addSolarSystemBodies(ArrayList<Body> bodies) {
    			bodies.clear();

    			
      			
      			bodies.add(GravitationSimulation.sun); 

      			bodies.add(GravitationSimulation.mercury); 

      			bodies.add(GravitationSimulation.venus); 

      			bodies.add(GravitationSimulation.earthSS); 

      			bodies.add(GravitationSimulation.mars); 
      			
      			bodies.add(GravitationSimulation.jupiter);
      			
      			bodies.add(GravitationSimulation.saturn);
      			
      			bodies.add(GravitationSimulation.uranus);
      			
      			bodies.add(GravitationSimulation.neptune);
    		    }
    		 
    	 
    		  private void addEarthSatellite(ArrayList<Body> bodies) {
    			bodies.clear();
    		
    			
    			bodies.add(GravitationSimulation.satellite1);
    			bodies.add(GravitationSimulation.earth);
    			
    		  }
    		  
    		  private void SpiralGalaxy(ArrayList<Body> bodies) {
    			  bodies.clear();
    			
    			    double satelliteMass = 1e12; // Mass of the orbiting bodies
    			    double galaxyRadius = 400; // Radius of the galaxy
    			    int numberOfBodies = (int) Double.parseDouble(numberOfBodiesInGalaxy.getText());; // Number of orbiting bodies

    			    // Create the central body (galactic center)
    			   
    			    bodies.add(GravitationSimulation.BIGSTAR);

    			    // Create orbiting bodies in an arc with higher initial velocities
    			    for (int i = 0; i < numberOfBodies; i++) {
    			        double angle = (Math.PI / numberOfBodies) * i; // Use Math.PI for an arc
    			     
    			      

    			        // Increase the scaling factor for higher initial velocities
    			        double vx = 0.8 * galaxyRadius * Math.sin(angle);
    			        double x = WIDTH / 2 + galaxyRadius * Math.cos(angle) + 500;
    			        double vy = -0.8* galaxyRadius * Math.cos(angle);
    			        double y = HEIGHT / 2 + galaxyRadius * Math.sin(angle) + 500;

    			        Body lilStar = new Body(satelliteMass, x, y, vx, vy, 2, 0);
    			        
    			        bodies.add(lilStar);
    			    }
    			}
    		  private void Spinning(ArrayList<Body> bodies) {
    			  bodies.clear();
    			  
    			  Body body1 = new Body(10e12, 500, 500, 0, 2.2, 5, 0);
    			  Body body2 = new Body(10e12, 600, 500, 0, -2.2, 5, 0);
    			  bodies.add(body1);
    			  bodies.add(body2);
    		  }
    	 }
    	 
