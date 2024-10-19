import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

//============================================================================================================================================================================//	
//CLASS FOR THE MATHS PANEL TO DISPLAY CALCULATIONS IN REAL TIME AS WELL AS GRAPHING//
    	 class MathPanel extends JPanel{
    		 private List<Body> bodies;
    
    		 private boolean showGraph = false;  // Variable to track whether to show the graph

    		 private List<Double> forceValues = new ArrayList<>();
    		 private List<Double> distanceValues = new ArrayList<>();
    		  public MathPanel(List<Body> bodies) {
    		        this.bodies = bodies;
    		        JButton showGraphButton = new JButton("Show Force distance graph");
    		        showGraphButton.addActionListener(new ActionListener() {
    		            @Override
    		            public void actionPerformed(ActionEvent e) {
    		                showGraph = !showGraph;  // Toggle the showGraph variable on button click, the ! just like sets it to the opposite
    		                repaint();  // Repaint
    		            }
    		        });
    		        Font buttonFont = new Font(showGraphButton.getFont().getName(), Font.PLAIN, 7); 
    		        showGraphButton.setFont(buttonFont);
    		        
    		        Insets buttonInsets = new Insets(2, 2, 2, 2);
    		        showGraphButton.setMargin(buttonInsets);
    		        
    		        showGraphButton.setBounds(10, 10, 100, 50); 

    		        setLayout(null); 
    		        add(showGraphButton);    		  }
    	
    		  @Override
    		    protected void paintComponent(Graphics g) {
    		        super.paintComponent(g);
    		     
    		        if (showGraph) {
    		            drawGraph(g);
    		        }
    		        
    		    }
    		  
    		  private void drawGraph(Graphics g) {
    		        if (bodies.size() < 2) {
    		            return; // Need at least two bodies to calculate force and distance
    		        }
    		      if(GravitationSimulation.isRunning == true) {  // has to be running to draw
    		        Graphics2D g2d = (Graphics2D) g;
    		        g2d.setColor(Color.BLUE);
    		      
    		        int panelWidth = getWidth();
    		        int panelHeight = getHeight();
    		        g.drawLine(5, 100, 5, 400);
    		        g.drawLine(5, 400, panelWidth, 400);
    		   
    		        // Extract the first two bodies
    		        Body body1 = bodies.get(0);
    		        Body body2 = bodies.get(1);

    		        // Calculate force and distance between the first two bodies
    		        double force = calculateForce(body1, body2);
    		        double distance = body1.distanceTo(body2);
    		        
    		        // Draw a line representing force based on the distance
    		        forceValues.add(force);
    		        distanceValues.add(distance);
    		       
    		        // Draw all the points in the graph
    		        for (int i = 1; i < forceValues.size(); i++) {
    		            double force1 = forceValues.get(i - 1);
    		            double distance1 = distanceValues.get(i - 1);

    		            double force2 = forceValues.get(i);
    		            double distance2 = distanceValues.get(i);

    		            // Draw the line with the yOffset
    		            if(body1.mass < 60 * 1e14) { // when mass gets too high the x values draw too far along so the else reduces x values further
    		            	int x1 = (int) (force1 / 1e14)+ 7;
    		            	int y1 = (int) ((1 - distance1 / getMaxDistance()) * 400) ; // distance/maxdistance gives a normalised value betweeon 0 and 1 * by panel Height gives a value in that area 

	    		            int x2 = (int) (force2 / 1e14)+ 7;
	    		            int y2 = (int) ((1 - distance2 / getMaxDistance()) * 400);
    		    
	    		       
    		            
    		            g2d.drawLine(x1, y1, x2, y2);
    		          
    		            }
    		            else {
    		            		int x1 = (int) (force1 / 1e15)+ 7;
    		            		int y1 = (int) ((1 - distance1 / getMaxDistance()) * 400); // distance/maxdistance gives a normalised value betweeon 0 and 1 * by panel Height gives a value in that area 

    	    		            int x2 = (int) (force2 / 1e15)+ 7;
    	    		            int y2 = (int) ((1 - distance2 / getMaxDistance()) * 400);
    	    		       
    	    		            
    	    		            g2d.drawLine(x1, y1, x2, y2);
    		            }
    		        }
    		      }
    		  }
    		    private double calculateForce(Body body1, Body body2) { 
    		        return (6.67e-11* body1.mass * body2.mass) / Math.pow(body1.distanceTo(body2), 2);
    		    }

    		   
    		    private double getMaxDistance() {
    		   
    		        return (Math.sqrt(Math.pow(1000, 2) + Math.pow(2000, 2)));
    		    }
    		    
    		    public void resetGraph() { // gets rid of graph 
    		        forceValues.clear();
    		        distanceValues.clear();
    		
            repaint();
    		    }
}