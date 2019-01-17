package datastructure;

import algorithm.Parking;
/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Critical {
	private static Graph graph;
	private static Parking parking;
	
	public static Graph getGraph(){
		return graph;
	}
	
	public static Parking getParking(){
		return parking;
	}
	
	public static void setGraph(){
		graph = null;
	}
	
	public static void setGraph(int arcNum, int vetNum){
		graph = new Graph(arcNum, vetNum);
	}
	
	public static void setParking(int maxParkingNum){
		parking = new Parking(maxParkingNum);
	}
}
