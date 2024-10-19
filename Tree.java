

//============================================================================================================================================================================//	    
	    //CLASS FOR THE TREE/BUILDING THE TREE AND THE METHOD TO UPDATE THE FORCE ON A BODY DUE TO EACH NODE ALONGSIDE THE APPROXIMATION//
	     class Tree {

	    	private Body body;
	    	private Quadrant quadrant;
	    	private Tree NW;
	    	private Tree NE;
	    	private Tree SW;
	    	private Tree SE;
	    	public double Theta;
	    	
	    	
	    	//constructor for tree//
	    	public Tree(Quadrant q, double theta) {
	    		this.quadrant = q;
	    		this.body = null;
	    		this.NW = null;
	    		this.NE = null;
	    		this.SW = null;
	    		this.SE = null;
	    		this.Theta = theta / 10; // as on the slider it has to be between 0  and 10
	    		}
	    	
	    	//recursive method to add bodies into the quad tree//
	    	public void insert(Body b ) {
	    		
	    		// if node empty add the body
	    		if(body == null) {
	    			body = b;
	    			return;
	    		}
	    		
	    		if(!externalNode()) {
	    			body  = body.combine(b); // internal node has to hold the combination
	    			newQuadrant(b); // Recursively add the body (body changes the specific nodes mass and centre of mass as its an internal node )
	    		}
	    		else{
	    			NW = new Tree(quadrant.NW(), Theta);
	    			NE = new Tree(quadrant.NE(), Theta);
	    			SW = new Tree(quadrant.SW(), Theta);
	    			SE = new Tree(quadrant.SE(), Theta);
	    			
	    			newQuadrant(this.body); // the body in this external node needs to be added to one of its children of course and its going to be put into one of the 4 new quadrants created 
	    			newQuadrant(b); // the new body which couldn't go in the external node because this.body was in it 
	    			
	    			body = body.combine(b); // this external node is now an internal node therefore we need to update its mass and centre of mass
	    		}
	    		
	    	}
	    	
	    	//actually inserts the bodies with some extra validation to be careful//
	    	private void newQuadrant(Body b) {
	    		if(b.in(quadrant.NW())) {
	    			NW.insert(b); // Recursively insert with a new quadrant
	    		}
	    		else if(b.in(quadrant.NE())) {
	    			NE.insert(b); // Recursively insert with a new quadrant
	    		}
	    		else if(b.in(quadrant.SW())) {
	    			SW.insert(b); // Recursively insert with a new quadrant
	    		}
	    		else if(b.in(quadrant.SE())) {
	    			SE.insert(b); // Recursively insert with a new quadrant
	    		}
	    		
	    		}
	    	
	    	//check to see if its an externalNode
	    	private boolean externalNode() {
	    		
	    		return (NW == null && NE == null && SW == null && SE == null);

	    		}
	    	
	    	
	    	public void updateForce(Body b) {
	    		//if node empty or if the body finding force on is the node then cant find force
	    		if(body == null || b.equals(body)) {
	    			return;
	    		}
	    		if(externalNode()) {
	    			b.addForce(body);
	    			
	    		}
	    		
	    		else {
	    			double s  = quadrant.width;
	    			double d = body.distanceTo(b);
	    			
	    			if((s/d) < Theta ) {
	    				b.addForce(body);
	    			}
	    			//if its not viable for approximation then recursively update force from each smaller quadrant, each recursive call is less approximation.
	    			else {
	    				if(NW != null) {
	    					NW.updateForce(b);
	    				}
	    				if(NE != null) {
	    					NE.updateForce(b);
	    				}
	    				if(SW != null) {
	    					SW.updateForce(b);
	    				}
	    				if(SE != null) {
	    					SE.updateForce(b);
	    				}
	    				
	    				
	    			}
	    		}
	    		
	    		
	    	}
	    }
	    