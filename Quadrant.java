
//===========================================================================================================================================================================//
	   //CLASS FOR THE QUADRANTS AND INITIALISING THE 4 SIDES WITH THEIR RESPECTIVE X,Y,LENGTHS. 
	     class Quadrant {
	    	double x, y, width;
	    	
	    	
	    	
	        public Quadrant(double x, double y, double width) {
	            this.x = x;
	            this.y = y;
	            this.width = width;
	        }
	        
	        // checking if a body is contained within its respective quadrant using this. 
	        public boolean contains(double x, double y) {
	        	return(x > this.x && x < this.x   + width && 
	        			y > this.y && y < this.y + width);
	        }
	        
	        //Separates the current quadrant into its 4 sub sections
	        
	        public Quadrant NW() {
	        	double x = this.x;
	        	double y = this.y;
	        	double width = this.width / 2;
	        	Quadrant NW =  new Quadrant(x,y, width);
	        	return NW;
	        }
	        
	        public Quadrant NE() {
	        	double x  = this.x + this.width /2 ;
	        	double y = this.y;
	        	double length = this.width /2;
	        	Quadrant NE =  new Quadrant(x,y, length);
	        	return NE;
	        }
	        public Quadrant SW() {
	        	double x = this.x;
	        	double y = this.y + this.width / 2 ;
	        	double length = this.width / 2;
	        	Quadrant SW =  new Quadrant(x,y, length);
	        	return SW;
	        }
	        public Quadrant SE() {
	        	double x = this.x + this.width / 2;
	        	double y = this.y + this.width / 2;
	        	double length = this.width / 2;
	        	Quadrant SE =  new Quadrant(x,y, length);
	        	return SE;
	        }
	        
	        
	        
	    }
	    