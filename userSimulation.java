import java.util.ArrayList;

public class userSimulation {
	private  String simulationName;
	private int ID;
	private static  ArrayList<Body> bodies = new ArrayList<>();
	private int timeStep;
	private int theta;
	  
	
	public userSimulation(int ID, String simulationName, ArrayList<Body> bodies, int timeStep, int theta) {
		this.bodies = bodies;
		this.ID = ID;
		this.simulationName = simulationName;
		this.timeStep = timeStep;
		this.theta = theta;
	}
	
	public String getSimulationName() {
        return simulationName;
    }
	public int getID() {
        return ID;
    }
	public int getTimestep() {
        return timeStep;
    }
	
	public int getTheta() {
        return theta;
    }
	
	
	 public ArrayList<Body> getBodies() {
	        return bodies;
	    }
	
	 public void setSimulationName(String simulationName) {
	        this.simulationName = simulationName;
	    }
	 
	 public void setBodies(ArrayList<Body> bodies) {
	        this.bodies = bodies;
	    }
	 
}
