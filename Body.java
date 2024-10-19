import java.awt.Point;
import java.util.ArrayList;

//============================================================================================================================================================================//
	    //CLASS FOR BODY AMD THEIR VARIABLES, AND THE METHODS NEEDED TO FIND THE CHANGES IN THEIR VARIABLES. 
	    
	     class Body {
	    	
	        double mass;
	        double x, y;  // Position
	        double vx, vy; // Velocity
	        double fx , fy; // forces
	        double softening = 1e-30;
	        double radius;
	        double time;
	        double G = 6.67*  Math.pow(10, -11);
	        ArrayList<Point> tracerPoints = new ArrayList<>();
	        
	        
	        //Constructor for every body 
	        public Body(double mass, double x, double y, double vx, double vy, double radius, double time) {
	            this.mass = mass;
	            this.x = x;
	            this.y = y;
	            this.vx = vx;
	            this.vy = vy;
	            this.radius = radius;
	            this.time = time;
	        }
	        
	        public Body(Body other) {
	            this.mass = other.mass;
	            this.x = other.x;
	            this.y = other.y;
	            this.vx = other.vx;
	            this.vy = other.vy;
	            this.radius = other.radius;
	            this.time = other.time;
	        }
	       
	        
	        
	        //used to find the distance between 2 bodies 
	        public double distanceTo(Body b) {
	            double dx = x - b.x;
	            double dy = y - b.y;
	            return Math.sqrt(dx*dx + dy*dy);
	        }
	      
	        //checks if the body is in a specific quadrant
	        public boolean in(Quadrant q) {
	        	return q.contains(this.x, this.y);	//contains is a quadrant method with a x value and y value of the body being checked
	        }
	        
	        //does all the logic for finding the velocities and positions
	        public void update(double timeStep) {
	        	vx += timeStep * fx / mass;
	        	vy += timeStep * fy / mass;
	        	x += timeStep * vx;
	            y += timeStep * vy;
	            if(GravitationSimulation.drawTracers == true) {
	            	tracerPoints.add(new Point((int)x , (int) y)); // each body has their path inputted into an array so that this can be drawn out while drawing each body
	            }
	            if(GravitationSimulation.drawTracers == false) {
	            	tracerPoints.removeAll(tracerPoints);
	            }
	            
	           
	        }
	        
	        // bodies get "combined" to form internal nodes , these internal nodes are used to approximate 
	        public Body combine(Body b) {
	        	double CombinedMass = this.mass + b.mass;
	        	double x = (this.x * this.mass + b.x * b.mass) / CombinedMass;
	            double y = (this.y * this.mass + b.y * b.mass) / CombinedMass;
	            return new Body(CombinedMass, x,y, this.vx, this.vy, this.radius, this.time);
	        }
	        
	        //addForce computed the force on a body due to each node, it is called in the updateForce method in the tree class and the update force is called in the thread//
	        public void addForce(Body b) {
	        	Body a = this;
	        	double dx = b.x - a.x;
	        	double dy = b.y - a.y;
	        	double dist = Math.sqrt(dx * dx + dy * dy + softening * softening);
	        	double F = ((G * a.mass * b.mass) / (dist*dist));
	        	a.fx += F * dx / dist; 	// dx/dist gives the cos so force * cos will give the adjacent which is the x component of the force
	        	a.fy += F * dy / dist;
	        }
	        
	        
	        
	    
	        //forces are reset before being computed again 
	        public void resetForce() {
	            fx = 0.0;
	            fy = 0.0;
	        }
	        
	        public double getTime(){
	        	return time;
	        }
	        
	    }
	    
	    