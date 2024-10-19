import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class controls extends JPanel {
	private GravitationSimulation gravitationSimulation;


	public controls(GravitationSimulation gravitationSimulation) {
		   this.gravitationSimulation = gravitationSimulation;
		setBackground(Color.black);
		setBackground(Color.black);
		  JButton toggleSidePanelButton = new JButton("Toggle Side Panel");
		  //to toggle the side panel, had to leave the method in gravitationSimulation class as it didnt work in this class, so I had to pass an instance of the other class to this one so i could invoke the method
  	    toggleSidePanelButton.addActionListener(e -> gravitationSimulation.toggleSidePanel());
  	    toggleSidePanelButton.setBackground(Color.black);
  	    toggleSidePanelButton.setForeground(Color.white);
  	    
  	    // Create buttons for Undo, Start, Stop, Reset, and Exit, the general functions which need to always be accessed
  	    JButton undoButton = new JButton("Undo");
  	    undoButton.addActionListener(e -> undoLastBody());
  	    undoButton.setBackground(Color.black);
  	    undoButton.setForeground(Color.white);
  	    
  	    JButton startButton = new JButton("Start");
  	    startButton.addActionListener(e -> startSimulation());
  	    startButton.setBackground(Color.black);
  	    startButton.setForeground(Color.white);
  	    
  	    JButton stopButton = new JButton("Stop");
  	    stopButton.addActionListener(e -> stopSimulation());
  	    stopButton.setBackground(Color.black);
  	    stopButton.setForeground(Color.white);
  	    
  	    JButton resetButton = new JButton("Reset");
  	    resetButton.addActionListener(e -> resetSimulation());
  	    resetButton.setBackground(Color.black);
  	    resetButton.setForeground(Color.white);
  	    
  	    JButton exitButton = new JButton("Exit");
  	   exitButton.addActionListener(e -> System.exit(0));
  	    exitButton.setBackground(Color.black);
  	    exitButton.setForeground(Color.white);
  	    
  	    add(toggleSidePanelButton);
  	    add(undoButton);
  	    add(startButton);
  	    add(stopButton);
  	    add(resetButton);
  	    add(exitButton);
  	  
  	 
	}

	
	
    
    public void startSimulation() {
    	GravitationSimulation.isRunning = true; // Start the simulation
   	 // need to get sidePanel before trying to use it
   	 SidePanel sidePanel = (SidePanel) GravitationSimulation.tabbedPane.getComponentAt(0);
   	GravitationSimulation.timeStep = sidePanel.timeStepSlider.getValue() * 1e-2;
   }

   public void stopSimulation() {
	   GravitationSimulation.isRunning = false; // Pause the simulation
	   GravitationSimulation.timeStep = 0;
   }

   public void resetSimulation() {
   	 GravitationSimulation.bodies.clear();

	    // Repaint the panel to reflect the changes
   	 repaint();
   	 //need to get math panel before using it
   	 MathPanel mathPanel = (MathPanel) GravitationSimulation.tabbedPane.getComponentAt(1);
   	 mathPanel.resetGraph();
   }
   //when undoing need to remove the last body in the array and rebuild the whole tree and repaint
   public void undoLastBody() {
   	SidePanel sidePanel = (SidePanel)  GravitationSimulation.tabbedPane.getComponentAt(0);
   	if(! GravitationSimulation.bodies.isEmpty()) {
   		Body removedBody =  GravitationSimulation.bodies.remove( GravitationSimulation.bodies.size() -1);
   	 GravitationSimulation.barnesHutTree = new Tree(new Quadrant(0,0, GravitationSimulation.width), sidePanel.thetaSlider.getValue());
   		for(Body body :  GravitationSimulation.bodies) {
   			if(body.in(new Quadrant(0,0, GravitationSimulation.width))){
   			 GravitationSimulation.barnesHutTree.insert(body);
   			}
   		}
   		
   		SwingUtilities.invokeLater(() -> {
   			repaint();
   		});
   		
   		
   	}
   }
	
}
