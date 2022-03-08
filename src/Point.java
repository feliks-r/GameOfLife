import java.util.ArrayList;
import java.util.Random;

public class Point {
	private ArrayList<Point> neighbors;
	private int currentState;
	private int nextState;
	private int numStates = 6;
	Random rand = new Random();
	
	public Point() {
		currentState = 0;
		nextState = 0;
		neighbors = new ArrayList<Point>();
	}

	public void clicked() {
		currentState=(++currentState)%numStates;	
	}
	
	public int getState() {
		return currentState;
	}

	public void setState(int s) {
		currentState = s;
	}

	public void calculateNewState() {
		//updates according to currentState and
		//number of active neighbors

		int activeNeighbors = countActiveNeighbors();

		//game of life
		/*
		if (activeNeighbors == 3 || (currentState == 1 && activeNeighbors == 2)){
			nextState = 1;
		}
		else{
			nextState = 0;
		}*/

		//cities
		/*if ((currentState == 1 && activeNeighbors >= 2 && activeNeighbors <= 5) ||
				(currentState == 0 && activeNeighbors >= 4 && activeNeighbors <= 8)){
			nextState = 1;
		}
		else{
			nextState = 0;
		}*/

		//coral
		if ((currentState == 1 && activeNeighbors >= 4 && activeNeighbors <= 8) ||
				(currentState == 0 && activeNeighbors == 3)){
			nextState = 1;
		}
		else{
			nextState = 0;
		}
	}

	public void calculateRainState(){
		//calculates new state for rain
		if(currentState>0){
			nextState=(--currentState)%numStates;
		}
		else if(!neighbors.isEmpty() && neighbors.get(0).getState()>0){
			nextState = 6;
		}
	}

	public void changeState() {
		currentState = nextState;
	}
	
	public void addNeighbor(Point nei) {
		neighbors.add(nei);
	}
	
	//TODO: write method counting all active neighbors of THIS point

	public int countActiveNeighbors(){
		int result = 0;
		for (Point n : neighbors){
			result+=n.currentState;
		}
		return result;
	}

	public void drop(){
		int n = rand.nextInt(100);
		if(n < 5){
			nextState = 6;
		}
	}
}
