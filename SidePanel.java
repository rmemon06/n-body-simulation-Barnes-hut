import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;



//============================================================================================================================================================================//	
	    //CLASS FOR THE SIDE PANEL WHICH HOLDS ALL THE USER INTERACTIVITY//
    	 class SidePanel extends JPanel {
 	    	 JButton addBody;
 	    	 JSlider xVelocitySlider;
 	         JSlider yVelocitySlider;
 	         JTextField xPositionField;
 	         JTextField yPositionField;
 	         JTextField amount;
 	         JCheckBox random;
 	         JCheckBox tracers;
 	         JCheckBox vectors;
 	         JCheckBox elastic;
	         JSlider timeStepSlider;
 	    	 JSlider massSlider;
 	         JSlider radiusSlider;
 	         JSlider thetaSlider;
 	         MathPanel mathPanel;
 	        
 	      // these buttons are used to pop up an explanation for stuff that you wont be able to understand ithout context
 	         JButton thetaExplanation;
 	         JButton velocityExplanation;
 	         JButton vectorsExplanation;
 	         JButton elasticCollisionExplanation;
 	       
 	    	public SidePanel(MathPanel mathPanel)
 	    	{
 	    	   this.mathPanel = mathPanel;
 	    		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	// box layout being all components stacked on top of eachother
 	    		//buttons and checkbox initialisation
 	    		 addBody = new JButton("Add body");
 	    		 random = new JCheckBox("Random values");
 	     		 tracers = new JCheckBox("Tracers");
 	     		 vectors = new JCheckBox("Vectors");
 	     		 elastic = new JCheckBox("elastic collision");
 	     		
 	     		thetaExplanation = new JButton("?");
 	     		velocityExplanation = new JButton("?");
 	     		vectorsExplanation = new JButton("?");
 	     		elasticCollisionExplanation = new JButton("?");
 	     		thetaExplanation.setFont(new Font("Arial", Font.PLAIN, 12));
 	     		velocityExplanation.setFont(new Font("Arial", Font.PLAIN, 12));
 	     		vectorsExplanation.setFont(new Font("Arial", Font.PLAIN, 12));
 	     		elasticCollisionExplanation.setFont(new Font("Arial", Font.PLAIN, 12));
 	     		
 	     		elasticCollisionExplanation = new JButton("?");
 	    		 //slider initialisation and variable initialisation 
 	    		 xVelocitySlider = new JSlider(JSlider.HORIZONTAL, -50, 50, 0);
 	             yVelocitySlider = new JSlider(JSlider.HORIZONTAL, -50, 50, 0);
 	             
 	             xVelocitySlider.setMajorTickSpacing(10);
 	             xVelocitySlider.setMinorTickSpacing(5);
 	             xVelocitySlider.setPaintTicks(true);
 	             xVelocitySlider.setPaintLabels(true);

 	             yVelocitySlider.setMajorTickSpacing(10);
 	             yVelocitySlider.setMinorTickSpacing(5);
 	             yVelocitySlider.setPaintTicks(true);
 	             yVelocitySlider.setPaintLabels(true);
 	             
 	             massSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
 	             radiusSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
 	             massSlider.setMajorTickSpacing(10);
 	             massSlider.setMinorTickSpacing(5);
 	             massSlider.setPaintTicks(true);
 	             massSlider.setPaintLabels(true);
 	             
 	            
 	             
 	             radiusSlider.setMajorTickSpacing(5);
 	             radiusSlider.setMinorTickSpacing(1);
 	             radiusSlider.setPaintTicks(true);
 	             radiusSlider.setPaintLabels(true);
 	             
 	             timeStepSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 0);
 	             timeStepSlider.setMajorTickSpacing(25);
 	             timeStepSlider.setMinorTickSpacing(5);
 	             timeStepSlider.setPaintTicks(true);
 	             timeStepSlider.setPaintLabels(true);
 	             
 	             
 	             thetaSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
 	             thetaSlider.setMajorTickSpacing(2);
 	             thetaSlider.setMinorTickSpacing(1);
 	             thetaSlider.setPaintTicks(true);
 	             thetaSlider.setPaintLabels(true);
 	             
 	             xPositionField = new JTextField("0"); // users can set their bodies velocities
 	             yPositionField = new JTextField("0"); 
 	             amount = new JTextField("0");
 	             //setting their size
 	             Dimension textFieldSize = new Dimension(120, 20);
 	        
 	            
 	             xPositionField.setMaximumSize(textFieldSize);
 	             yPositionField.setMaximumSize(textFieldSize);
 	             amount.setMaximumSize(textFieldSize);
 	         
 	             
 	             
 	             Color textColor = Color.white;
 	             Color sliderTrackColor = Color.black;
 	       

 	             // Set label and button colors
 	             addBody.setForeground(textColor);
 	             random.setForeground(textColor);
 	             tracers.setForeground(textColor);
 	             vectors.setForeground(textColor);	   
 	             elastic.setForeground(textColor);
	             
 	             
 	        	thetaExplanation.setForeground(textColor); 
 	     		velocityExplanation.setForeground(textColor);  
 	     		vectorsExplanation.setForeground(textColor);  
 	     		elasticCollisionExplanation.setForeground(textColor);  
 	             
 	             addBody.setBackground(Color.black);
 	             random.setBackground(Color.black);
 	             tracers.setBackground(Color.black);
 	             vectors.setBackground(Color.black);
 	             elastic.setBackground(Color.black);
	            
 	            thetaExplanation.setBackground(Color.black);
 	     		velocityExplanation.setBackground(Color.black);
 	     		vectorsExplanation.setBackground(Color.black);
 	     		elasticCollisionExplanation.setBackground(Color.black);
 	             
 	             // Set slider colors
 	             xVelocitySlider.setForeground(textColor);
 	             xVelocitySlider.setBackground(sliderTrackColor);
 	             xVelocitySlider.setOpaque(true);
 	             xVelocitySlider.setPaintTrack(true);
 	             xVelocitySlider.setPaintTicks(true);
 	             xVelocitySlider.setPaintLabels(true);

 	             yVelocitySlider.setForeground(textColor);
 	             yVelocitySlider.setBackground(sliderTrackColor);
 	             yVelocitySlider.setOpaque(true);
 	             yVelocitySlider.setPaintTrack(true);
 	             yVelocitySlider.setPaintTicks(true);
 	             yVelocitySlider.setPaintLabels(true);

 	             
 	             massSlider.setForeground(textColor);
 	             massSlider.setBackground(sliderTrackColor);
 	             massSlider.setOpaque(true);
 	             massSlider.setPaintTrack(true);
 	             massSlider.setPaintTicks(true);
 	             massSlider.setPaintLabels(true);

 	             radiusSlider.setForeground(textColor);
 	             radiusSlider.setBackground(sliderTrackColor);
 	             radiusSlider.setOpaque(true);
 	             radiusSlider.setPaintTrack(true);
 	             radiusSlider.setPaintTicks(true);
 	             radiusSlider.setPaintLabels(true);

 	             timeStepSlider.setForeground(textColor);
 	             timeStepSlider.setBackground(sliderTrackColor);
 	             timeStepSlider.setOpaque(true);
 	             timeStepSlider.setPaintTrack(true);
 	             timeStepSlider.setPaintTicks(true);
 	             timeStepSlider.setPaintLabels(true);
 	             
 	             thetaSlider.setForeground(textColor);
 	             thetaSlider.setBackground(sliderTrackColor);
 	             thetaSlider.setOpaque(true);
 	             thetaSlider.setPaintTrack(true);
 	             thetaSlider.setPaintTicks(true);
 	             thetaSlider.setPaintLabels(true);

 	             xPositionField.setForeground(Color.black);
 	             yPositionField.setForeground(Color.black);
 	             amount.setForeground(Color.black);
 	             
 	             
 	             //adding the fucntionality for adding a body
 	             addBody.addActionListener(e -> {
 	            	 
 	            	 
 	            	 double xVelocity = xVelocitySlider.getValue();
 	                 double yVelocity = yVelocitySlider.getValue();
 	                 double mass = massSlider.getValue();
 	                 double radius = radiusSlider.getValue();
 	            	 double xPosition, yPosition;
 	            	 //makes the positiosn random
 	            	 if(random.isSelected()) {
 	            		 xPosition = Math.random() * 2000;
 	            		 yPosition = Math.random() * 1000;
 	            	 }
 	            	 //gets position from the text field
 	            	 else {
 	            		 xPosition = Double.parseDouble(xPositionField.getText());
 	            		 yPosition = Double.parseDouble(yPositionField.getText());
 	            	 }
 	            	 //how many they want to add
 	            	String amountValue = amount.getText();
 	            	
 	                 for(int i = 0 ; i < Integer.parseInt(amountValue); i++) {
 	                	 if(random.isSelected()) {
 	                		int x = (int)(Math.random() * 2000);
 	                		int y = (int)(Math.random() * 1000);
 	                		 Body b = new Body(mass * 1e14,x,y,xVelocity,yVelocity, radius, System.currentTimeMillis());
 	                		 GravitationSimulation.bodies.add(b);
 	                	 }
 	                	 else {
 	                		 Body newBody = new Body(mass * 1e14, xPosition, yPosition, xVelocity, yVelocity, radius, GravitationSimulation.currentTime);
 	                		 GravitationSimulation.bodies.add(newBody);
 	                	 }
 	                 }
 	            	 Body newBody = new Body(mass * 1e14, xPosition, yPosition, xVelocity, yVelocity, radius,  GravitationSimulation.currentTime);
 	            	 GravitationSimulation.bodies.add(newBody);
 	            	 
 	             });
 	           
 	             //these 4 methods pop up the info about the certain part of the simulation
 	            thetaExplanation.addActionListener(e -> {		
 	            	UIManager.put("OptionPane.background", Color.BLACK);		//sets the color of the option pane using ui manager
 	            	UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK); // sets the rest around the textbox
 	               JOptionPane.showMessageDialog(this, new thetaExplanationPanel(), "Theta Explanation", JOptionPane.INFORMATION_MESSAGE);
 	           });

 	            
 	           vectorsExplanation.addActionListener(e -> {
 	        	  UIManager.put("OptionPane.background", Color.BLACK);
	            	UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK);
 	               JOptionPane.showMessageDialog(this, new vectorExplanationPanel(), "Vectors Explanation", JOptionPane.INFORMATION_MESSAGE);
 	           });

 	           velocityExplanation.addActionListener(e -> {
 	        	  UIManager.put("OptionPane.background", Color.BLACK);
	            	UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK);
 	               JOptionPane.showMessageDialog(this, new velocityExplanationPanel(), "Velocity Explanation", JOptionPane.INFORMATION_MESSAGE);
 	           });

 	           elasticCollisionExplanation.addActionListener(e -> {
 	        	  UIManager.put("OptionPane.background", Color.BLACK);
	            	UIManager.getLookAndFeelDefaults().put("Panel.background", Color.BLACK);
 	        	   JOptionPane.showMessageDialog(this, new elasticExplanationPanel(), "Elastic Collision Explanation", JOptionPane.INFORMATION_MESSAGE);
 	           	});
 	             
 	         
 	             //if it is random then you cant specifically choose the location
 	             random.addItemListener(e ->{
 	            	 if(random.isSelected()) {
 	            		 xPositionField.setEnabled(false);
 	            		 yPositionField.setEnabled(false);
 	            	 }
 	            	 else {
 	            		 xPositionField.setEnabled(true);
 	            		 yPositionField.setEnabled(true);
 	            	 }
 	             });
 	             
 	           
 	            
 	            tracers.addItemListener(e -> {
 	               if(tracers.isSelected() ) {
 	            		 GravitationSimulation.drawTracers = true;
 	               }
 	               else {
 	            		 GravitationSimulation.drawTracers = false;
 	               }
 	          
 	           });
 	            
 	           vectors.addItemListener(e -> {
 	               if(vectors.isSelected() ) {
 	            		 GravitationSimulation.drawVectors = true;
 	               }
 	               else {
 	            		 GravitationSimulation.drawVectors = false;
 	               }
 	          
 	           });
 	           
 	          elastic.addItemListener(e -> {
	               if(elastic.isSelected() ) {
	            		 GravitationSimulation.checkElastic = true;
	               }
	               else {
	            		 GravitationSimulation.checkElastic = false;
	               }
	          
	           });
	            
	           
	           
 	           
 	       
 	             //setting the labels and their colors
 	             JLabel xVelocityLabel = new JLabel("X Velocity:");
 	             xVelocityLabel.setForeground(Color.white);
 	             JLabel yVelocityLabel = new JLabel("Y Velocity:");
 	             yVelocityLabel.setForeground(Color.white);
 	             JLabel massLabel = new JLabel("mass:");
 	             massLabel.setForeground(Color.white);
 	             JLabel timeStepsLabel = new JLabel("Time steps:");
 	             timeStepsLabel.setForeground(Color.white);
 	             JLabel radiusLabel = new JLabel("radius:");
 	             radiusLabel.setForeground(Color.white);
 	             JLabel xPositionLabel = new JLabel("X Position:");
 	             xPositionLabel.setForeground(Color.white);
 	             JLabel yPositionLabel = new JLabel("Y Position:");
 	             yPositionLabel.setForeground(Color.white);
 	             JLabel amountLabel = new JLabel("Number of bodies:");
 	             amountLabel.setForeground(Color.white);
 	             JLabel randomLabel = new JLabel("Random positions:");
 	             randomLabel.setForeground(Color.white);
 	             JLabel thetaLabel = new JLabel("Theta:");
 	             thetaLabel.setForeground(Color.white);
 	           
 	         
 	             
 	             
 	             
 	             JLabel addBodyLabel = new JLabel("add body");
 	             addBodyLabel.setForeground(Color.white);
 	             
 	             //adding the labels and their respective functions
 	             add(xVelocityLabel);
 	             add(xVelocitySlider);
 	             add(yVelocityLabel);
 	             add(yVelocitySlider);
 	             add(velocityExplanation);
 	             
 	             add(massLabel);
 	             add(massSlider);
 	             add(timeStepsLabel);
 	             add(timeStepSlider);
 	             add(radiusLabel);
 	             add(radiusSlider);
 	             add(thetaLabel);
 	             add(thetaSlider);
 	             add(thetaExplanation);
 	             add(xPositionLabel);
 	             add(xPositionField);
 	             add(yPositionLabel);
 	             add(yPositionField);
 	             add(amountLabel);
 	             add(amount);
 	             
 	        
 	             add(random); // checkboxes do not need labels
 	             add(tracers);
 	             add(vectors);
 	             add(vectorsExplanation);
 	             add(elastic);
 	             add(elasticCollisionExplanation);
 	             add(addBodyLabel);
 	             add(addBody);
 	        
 	            
 	             // Add empty space top top and bottom
 	             setBorder(new EmptyBorder(10,0,0,10));
 	            
 	           
 	    	}
 	    	
 	    	public class thetaExplanationPanel extends JPanel{
 	    		public thetaExplanationPanel() {
 	    			setBackground(Color.black);

 	    		       
 	    			//using a text area so all the text fits within the box
 	    			 String explanationText ="Theta is the variable which discerns the amount of approximation which is done, the greater theta is the less approximation is done. ";
 	    			 JTextArea explanationTextArea = new JTextArea(explanationText);
	  	    	      
    	    	        explanationTextArea.setBackground(Color.BLACK);
    	    	        explanationTextArea.setForeground(Color.WHITE);
    	    	        explanationTextArea.setLineWrap(true);
    	    	        explanationTextArea.setWrapStyleWord(true);
    	    	        explanationTextArea.setSize(200,200);
    	    	        add(explanationTextArea);
 	    		 
 	    	}
 	    	}
 	    	
 	    	public class velocityExplanationPanel extends JPanel{
 	    		public velocityExplanationPanel() {
 	    			 	setBackground(Color.black);
 	    			 	setForeground(Color.white);
 	    			 
 	    			 String explanationText = "The positive value is to the right and down negative values are left and up.";
 	    		   
 	    			 JTextArea explanationTextArea = new JTextArea(explanationText);
 	  	    	      
   	    	        explanationTextArea.setBackground(Color.BLACK);
   	    	        explanationTextArea.setForeground(Color.WHITE);
   	    	        explanationTextArea.setLineWrap(true);
   	    	        explanationTextArea.setWrapStyleWord(true);
   	    	     explanationTextArea.setSize(200,200);

   	    	        add(explanationTextArea);
 	    		}
 	    	}
 	    	
 	    	public class vectorExplanationPanel extends JPanel{
 	    		public vectorExplanationPanel() {
 	    			setBackground(Color.black);
 	    			setForeground(Color.white);
 	    			 
 	    	
 	    			String explanationText = "The vector points to the direction of the resultant force acting on the body.";
  	    	        
  	    	        JTextArea explanationTextArea = new JTextArea(explanationText);
  	    	      
  	    	        explanationTextArea.setBackground(Color.BLACK);
  	    	        explanationTextArea.setForeground(Color.WHITE);
  	    	        explanationTextArea.setLineWrap(true);
  	    	        explanationTextArea.setWrapStyleWord(true);
  	    	      explanationTextArea.setSize(200,200);

  	    	        add(explanationTextArea);
 	    		}
 	    	}
 	    	
 	    	public class elasticExplanationPanel extends JPanel{
 	    		public elasticExplanationPanel() {
 	    			setBackground(Color.black);
 	    			setForeground(Color.white);
 	    			 String explanationText = "When bodies collide, they will not combine but rather bounce off of each other, maintaining their kinetic energy.";
 	    	        
 	    	        JTextArea explanationTextArea = new JTextArea(explanationText);
 	    	      
 	    	        explanationTextArea.setBackground(Color.BLACK);
 	    	        explanationTextArea.setForeground(Color.WHITE);
 	    	        explanationTextArea.setLineWrap(true);
 	    	        explanationTextArea.setWrapStyleWord(true);
 	    	       explanationTextArea.setSize(200,200);

 	    	        add(explanationTextArea);
	    		       
	    		       
 	    		
 	    	}}
 	    }
	    
	 