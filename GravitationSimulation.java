import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;



public class GravitationSimulation  extends JFrame{
	
	
	   static final int width = 2000;
	     static final int height = 1000;
	     static final double length = width ;
	     static double G = 6.67430e-11;  // Gravitational constant
	     static double timeStep = 0;   // Simulation time step
	     static  Tree barnesHutTree; // tree which will be built as bodies are added
	     static volatile boolean isRunning = false;	
	    static boolean drawTracers; // used within the tracers check box, if users want tracers its on and this will be true else its false
	    static   boolean drawVectors; // used within the vectors check box, if users want vectors its on and this will be true else its false
	    static   SidePanel sidePanel; // declared panels up here as they are reference between multiple classes
	    static   MathPanel mathPanel;
	    static   boolean checkElastic;
	    static   boolean isSidePanelVisible = true;
	    static  JTabbedPane tabbedPane;
	    static double currentTime = 0;
	    static save Save;
	    static load Load;
	    static controls Controls;
	    
	    static Body earth = new Body(6e20,500 ,400,0,0, 50, 0 ); //  making bodies up here so i can access them in the presetsims class and simulation class to write text to them
	    static Body satellite1 = new Body(10e10, 100,400,0,10e3,5, 0);
		
		//solar system planets//
	    static	Body sun = new Body(2e20, 700,400,0,0,10, 0); // 150 ==300 
	    static	Body mercury = new Body(0.33e14,760, 400,0, 16e3,1 , 0); 
	    static	Body venus =  new Body(4.87e14, 810,400,0,11e3,3, 0); 
	    static	Body earthSS = new Body(5.97e14,870, 400, 0, 9e3,3, 0 ); 
	    static	Body mars = new Body(1e14, 950,400,0,8e3,1, 0); 			
	    static	Body jupiter = new Body(1500e14, 1100, 400, 0, 6e3, 6, 0);			
		static	Body saturn = new Body(500e14, 1300, 400,0,5e3, 6, 0);
		static	Body uranus = new Body(90e14, 1400,400,0,5e3,4, 0);
		static	Body neptune= new Body(102e14, 1500,400, 0, 5e3, 4, 0); // out of visible range sadly
		
		static	Body BIGSTAR = new Body(1e18, width / 2 - 300, height / 2 -200, 0, 0, 20, 0);
		  public static SimulationPanel simulationPanel = new SimulationPanel();
	    static ArrayList<Body> bodies = new ArrayList<>();
	    static ArrayList<Body> initialBodies = new ArrayList<>();
 	    
	    
	    
	 
	//============================================================================================================================================================================//	    
	  //CLASS FOR THE DRAWING OF THE BODIES ON THE SIMULATION JPANEL//  

	    public static class SimulationPanel extends JPanel {
	    	
	        
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	          
	       

	            // Draw bodies and paths
	            for (Body body : bodies) {
	                g.setColor(Color.white);
	                g.fillOval((int) body.x, (int) body.y, (int) body.radius * 2, (int) body.radius * 2);

	                g.setColor(Color.GRAY); 
	                ArrayList<Point> tracerPoints = body.tracerPoints;
	               
	                //this is for adding text to the bodies in the preset sims
	                g.setColor(Color.white);
	                if (body.equals(earth)) {
	                    g.drawString("Earth", (int) body.x, (int) body.y - 10);
	                } else if (body.equals(satellite1)) {
	                    g.drawString("Hubble", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(sun)) {
	                    g.drawString("sun", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(earthSS)) {
	                    g.drawString("earth", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(neptune)) {
	                    g.drawString("neptune", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(uranus)) {
	                    g.drawString("uranus", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(mercury)) {
	                    g.drawString("mercury", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(mars)) {
	                    g.drawString("mars", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(venus)) {
	                    g.drawString("venus", (int) body.x, (int) body.y - 10);
	                    
	                }
	                else if (body.equals(jupiter)) {
	                    g.drawString("jupiter", (int) body.x, (int) body.y - 10);
	                }
	                else if (body.equals(saturn)) {
	                    g.drawString("saturn", (int) body.x, (int) body.y - 10);
	                }
	                
	                
	                
	                if(drawVectors == true) {
	                	   drawForceArrow(g, body);
	                }
	                //will only draw tracers if the box is ticked
	                if (drawTracers == true) {
	                for (int i = 1; i < tracerPoints.size(); i++) {
	                
	                    Point point1 = tracerPoints.get(i - 1);
	                    Point point2 = tracerPoints.get(i);
	                    
	                    g.setColor(Color.green); 
	                    
	                    int bodyRaidus= (int)body.radius;
	      
	                    g.drawLine(point1.x + bodyRaidus/2, point1.y + bodyRaidus/2, point2.x + bodyRaidus/2, point2.y + bodyRaidus/2); // + r/2 as its usually in the top left corner so gotta move it to the middle
	                }
	                }
	            }
	        }
	        
	        
	       
	        public void drawForceArrow(Graphics g, Body body) {
	           double arrowLength = body.radius + 10; // make it slightly stick out of body, more effect on smaller bodies
	           int bodyRadius = (int)body.radius /2;
	               int xStart = (int) (body.x  +body.radius);
	               int yStart = (int) (body.y  +body.radius);	
	                Point tip = findTip(body, arrowLength);

	                g.setColor(Color.RED); // Set arrow color
	                g.drawLine(xStart, yStart, tip.x, tip.y);
	            
	        }

	        public Point findTip(Body Body1, double arrowLength) {
	            double netForceX = 0.0;
	            double netForceY = 0.0;

	            // Softening factor
	            double softening = 1e-9;

	            // Calculate the net force from all other bodies
	            for (Body Body2 : bodies) {
	                if (Body2 != Body1) { // cant calculate if the distance is 0 = division by 0 
	                    double distance = Body1.distanceTo(Body2); // distance between body through and each other body

	                    // Calculate the force components with softening factor
	                    double forceX = Body1.G * Body1.mass * Body2.mass * (Body2.x - Body1.x) / ((distance * distance) + softening); // using cos the get the x component
	                    double forceY = Body1.G * Body1.mass * Body2.mass * (Body2.y - Body1.y) / ((distance * distance) + softening); // using sin to get the y component

	                    netForceX += forceX;
	                    netForceY += forceY;
	                }
	            }

	            // Normalize the net force components
	            double ForceMagnitude = Math.sqrt(netForceX * netForceX + netForceY * netForceY); //calculates the magnitude of the forces (pythagoras)

	            // Limit the arrow length
	            if (ForceMagnitude > 0) {
	            	 double scaleFactorX = arrowLength / ForceMagnitude ;
	            	 double scaleFactorY = arrowLength / ForceMagnitude ;

	            	 netForceX = netForceX * scaleFactorX;
	            	 netForceY = netForceY * scaleFactorY;
	            }

	            // Calculate the arrow endpoint
	            double arrowX = Body1.x + netForceX + Body1.radius /2;   //adding radius /2 onto it as the com of each body is in the top left so i need to slightly move it so its pointing to the middle of the bodies 
	            double arrowY = Body1.y + netForceY + Body1.radius /2;

	            return new Point((int) arrowX, (int) arrowY);
	        }
	    }

//============================================================================================================================================================================//	    	        
	  //CLASS FOR CREATING THE FRAME AND THE LOGIC OF THE ADDING BODIES AND THE ANIMATION THREAD//

	    public void initializeUI(user currentUser) {
	     	//sets the basics of the frame such as the layout and the panels within the tabbed frame
	    		setExtendedState(JFrame.MAXIMIZED_BOTH);  // Set the frame to full screen
	    	    setUndecorated(true); 
	    	    setTitle("Gravitational Simulation");
	    	    setSize(width, height);
	    	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    	    // Use BorderLayout for the main frame
	    	    setLayout(new BorderLayout());

	    	    // Create a new panel for mathematical calculations
	    	    MathPanel mathPanel = new MathPanel(bodies);
	    	    mathPanel.setLayout(new BorderLayout());
	    	    // Add mathematical calculation components to this pane
	    	   
	    	 
	    	    mathPanel.setBackground(Color.black);
	    	    // Add the existing SidePanel to one tab
	    	    SidePanel sidePanel = new SidePanel(mathPanel);
	    	    
	    	    presetSimsPanel presetSimsPanel = new presetSimsPanel();
	    	   
	    	    // Create a JTabbedPane and add SidePanel and mathPanel to it
	    	    tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
	    	    tabbedPane.addTab("Simulation Controls", sidePanel);
	    	    tabbedPane.addTab("Mathematics", mathPanel);
	    	    tabbedPane.addTab("Preset simulations", presetSimsPanel);
	    	
	    	    
	    
	    	    tabbedPane.setBackground(Color.black);
	    	    // Add the tabbedPane to the east (right) side
	    	    add(tabbedPane, BorderLayout.EAST);
	    	    
	    	    //adding the whole system controls
	    	  
	    	    Controls = new controls(this);
	    	    Save = new save(currentUser, sidePanel, this);
	    	    Load = new load(currentUser);
	    	    
	    	    
	    	    JTabbedPane topTabs = new JTabbedPane(JTabbedPane.TOP);
	    	
	    	    topTabs.add("System Controls" ,Controls );
	    	    topTabs.add("Save", Save);
	    	    topTabs.add("Load", Load);
	    	    add(topTabs, BorderLayout.NORTH);
	    	    

	    } 
	    
	  

	    

	   
	    
	    
	    
	    public GravitationSimulation(user currentuser) {
	    	  initializeUI(currentuser);
	    	  
	    	    JTabbedPane tabbedPane = (JTabbedPane) getContentPane().getComponent(0);  // getting the tabbed pane within the content pane
	    	    tabbedPane.setOpaque(true);
	    	    tabbedPane.setBackground(Color.black);
	    	    tabbedPane.setForeground(Color.white); // Set the text color of the tabs
	    	    tabbedPane.setBackgroundAt(tabbedPane.getSelectedIndex(), Color.black);
	    	   
	    	    JTabbedPane topTab = (JTabbedPane) getContentPane().getComponent(1); // the second thing after the first tabbed pane so index = 1
	    	    topTab.setOpaque(true);
	    	    topTab.setBackground(Color.black);
	    	    topTab.setForeground(Color.white); // Set the text color of the tabs
	    	    topTab.setBackgroundAt(tabbedPane.getSelectedIndex(), Color.black);
	    	    
	    	    SidePanel sidePanel = (SidePanel) tabbedPane.getComponentAt(0);
	    	    sidePanel.setOpaque(true);
	    	    sidePanel.setBackground(Color.black);
	    	    
	    	    MathPanel mathPanel = (MathPanel) tabbedPane.getComponentAt(1);
	    	    mathPanel.setOpaque(true);
	    	    mathPanel.setBackground(Color.black);
	    	    
	    	    SimulationPanel simulationPanel = new SimulationPanel();
	    	    simulationPanel.setBackground(Color.black);
	    	    simulationPanel.setOpaque(true);
	    	    
	    	    presetSimsPanel presetSims = (presetSimsPanel) tabbedPane.getComponentAt(2);
	    	    presetSims.setOpaque(true);
	    	    presetSims.setBackground(Color.black);
	    	    
	    	 
	    	    
	    	    
	    	    // Add the simulation panel to the centre
	    	    add(simulationPanel, BorderLayout.CENTER);

	    	    setLocationRelativeTo(null);
	    	    setVisible(true);

	        addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	            //if the random checkbox is ticked then the x and y values r given random positions else its the position clicked by the mouse
	            if(sidePanel.random.isSelected()) {
	            	int x = (int)(Math.random() * width);
	            	int y = (int)(Math.random() * height);
	            	Body b = new Body(sidePanel.massSlider.getValue() *1e14, x ,y , sidePanel.xVelocitySlider.getValue(),sidePanel.yVelocitySlider.getValue(), sidePanel.radiusSlider.getValue(), currentTime);
	            	addBodyToSimulation(b);
	            	
	            	 
	            }
	      	   
	            else {
	            	Point pos = e.getPoint();
	            	int x = pos.x;
	            	//-30 on the y as the panel at the top for buttons messes it up
	            	int y = pos.y-60;
	      	        Body b = new Body(sidePanel.massSlider.getValue() *1e14, x ,y, sidePanel.xVelocitySlider.getValue(),sidePanel.yVelocitySlider.getValue(), sidePanel.radiusSlider.getValue(),currentTime);
	      	  	
	      	        addBodyToSimulation(b);
	      	        
	      	        
	            }
	            }
	        });
	        
	        
	      
	        setVisible(true);
	        // tree starts as the whole area of screen and then will slowly be broken down
	        barnesHutTree = new Tree(new Quadrant(0, 0, width), sidePanel.thetaSlider.getValue()/ 10);

	        Thread simulationThread = new Thread(() -> {
	            while (true) {
	            	  // Update Barnes-Hut tree only once
	            	Quadrant quad  = new Quadrant(0,0,width);
	            	barnesHutTree = new Tree(quad,sidePanel.thetaSlider.getValue() /10 );
	            	
	            	
	            	//using the checkbox
	            	 if(checkElastic == true) {
	            		
		            	handleCollisionElastic(bodies,0.1);
		            	
	            	}
	            	 else {
	            		 handleCollisionCombine();
	            	 }
	            	
	            	//building the tree, if a body is in the whole quadrant recursivly insert it into the tree
	            	for(int i = 0; i < bodies.size();i ++) {
	            		if(bodies.get(i).in(quad)) {
	            			barnesHutTree.insert(bodies.get(i));
	            		}
	            	}
	            	//resetting the force before recalculating and then updating force on each body due to the tree and updating their v and positions
	            	for(int i = 0; i < bodies.size(); i ++) {
	            		bodies.get(i).resetForce();
	            		barnesHutTree.updateForce(bodies.get(i)); 
	            		bodies.get(i).update(timeStep);
	            	}
	            	
	            	// Update force information in the MathPanel
	            	if(isRunning == true) {
	            	currentTime += 16;
	            	}
	            	
	                SwingUtilities.invokeLater(() -> {
	                    simulationPanel.repaint();
	                    mathPanel.repaint();
	                });

	                try {
	                    Thread.sleep(16);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	            }
	        });

	        simulationThread.start();
	    }
	    
	    public void toggleSidePanel() {
	        if (isSidePanelVisible) {
	            // Hide the side panel
	            remove(tabbedPane);
	            isSidePanelVisible = false;
	        } else {
	            // Show the side panel
	            add(tabbedPane, BorderLayout.EAST);
	            isSidePanelVisible = true;
	        }

	        SwingUtilities.invokeLater(() -> {
	            revalidate();
	            repaint();
	        });
	    }
	    
	    
	    //Method to check for bodies colliding by checking if the distance between them is less than or equals to the radii(also equal as when they get that close it just causes problems)
	    public static boolean checkCollision(Body a, Body b) {
      	double distance = a.distanceTo(b);
      	double radii = a.radius + b.radius;
      	return distance <= radii;
      }
      
	    //my choice of collision responce was combination this causes more interesting movement as , 
      public Body combineBodies(Body a, Body b) {
          double combinedMass = a.mass + b.mass;
          double combinedX = (a.x * a.mass + b.x * b.mass) / combinedMass;
          double combinedY = (a.y * a.mass + b.y * b.mass) / combinedMass;
          double combinedVx = (a.vx * a.mass + b.vx * b.mass) / combinedMass;
          double combinedVy = (a.vy * a.mass + b.vy * b.mass) / combinedMass;
          double combinedRadius = a.radius + b.radius;

          if(a.radius > b.radius) {
          return new Body(combinedMass, combinedX, combinedY, combinedVx, combinedVy, a.radius, currentTime);
          }
          else {
              return new Body(combinedMass, combinedX, combinedY, combinedVx, combinedVy, b.radius, currentTime);
          }
      }
      //two for loops to compare each body to eachother to check if theyre colliding and if they are combine the bodies and remove the two previous bodies from the array
      public void handleCollisionCombine() {
	    	   for (int i = 0; i < bodies.size(); i++) {
	    	        for (int j = i + 1; j < bodies.size(); j++) {
	    	            Body bodyA = bodies.get(i);
	    	            Body bodyB = bodies.get(j);

	    	            if (checkCollision(bodyA, bodyB)) {
	    	                Body newBody = combineBodies(bodyA, bodyB);
	    	                bodies.remove(bodyA);
	    	                bodies.remove(bodyB);
	    	                bodies.add(newBody);
	    	            }
	    	        }
	    	   }
	    }
      
      
      public static void handleCollisionElastic(ArrayList<Body> bodies, double restitution) {
          for (int i = 0; i < bodies.size(); i++) {
              for (int j = i + 1; j < bodies.size(); j++) {
                  Body a = bodies.get(i);
                  Body b = bodies.get(j);

                  if (checkCollision(a, b)) {
                      double dx = b.x - a.x;
                      double dy = b.y - a.y;
                      double distance = Math.sqrt(dx * dx + dy * dy);
                      double minDistance = a.radius + b.radius;

                      double nx = dx / distance; // gives vector directionality along the x axis so we know which way to move the bodies apart
                      double ny = dy / distance;
                      double overlap = minDistance - distance;

                      // Move the bodies apart
                      a.x -= overlap * 0.5 * nx; // moves a in the opposite direction to the nx vector , by half the overlap distance 
                      a.y -= overlap * 0.5 * ny;
                      b.x += overlap * 0.5 * nx;
                      b.y += overlap * 0.5 * ny;

                      // Calculate relative velocity
                      double dvx = b.vx - a.vx;
                      double dvy = b.vy - a.vy;

                      // Dot product of relative velocity and normal vector
                      double dotProduct = dvx * nx + dvy * ny; //The dot product is a scalar quantity calculated as the sum of the products of corresponding components.

                      // Calculate impulse
                      double impulse = (2.0 * dotProduct) / (a.mass + b.mass);

                      // Update velocities after collision with restitution
                      a.vx += impulse * b.mass * nx * restitution;
                      a.vy += impulse * b.mass * ny * restitution;
                      b.vx -= impulse * a.mass * nx * restitution;
                      b.vy -= impulse * a.mass * ny * restitution;
                      //restituation is a value to determine how much kinetic energy is retained, 0  = perfectly inelastic 1 = perfectly elastic 
                  }
              }
          }
      }
      
          
      
      
      public void addBodyToSimulation(Body body) {
    	    bodies.add(body);
    	    initialBodies.add(new Body(body));  //Body(boyd) uses the copy constructor to have it be a seperate body wiht the same vlaues 
    	}
      
      public static void updateSimulationPanel() {
     
          simulationPanel.repaint();
      }
}
