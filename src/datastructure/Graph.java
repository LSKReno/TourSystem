package datastructure;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Graph {
	private int arcNum; 	//景点数量
    private int roadNum; 	//路的数量
    private MyList<ArcNode> nodes; 	//存储景点的list
    
    public Graph(int arcNum){
    	this.arcNum = arcNum;
    	nodes = new MyList<ArcNode>();
    }
    
	public Graph(int arcNum, int vetNum) {
		this.arcNum = arcNum;
		this.roadNum = vetNum;
		nodes = new MyList<ArcNode>();
	}
	
	public int getArcNum() {
		return arcNum;
	}
	
	public void setArcNum(int arcNum) {
		this.arcNum = arcNum;
	}
	
	public int getRoadNum() {
		return roadNum;
	}
	
	public void setVetNum(int vetNum) {
		this.roadNum = vetNum;
	}

	public void setRoadNum(int roadNum) {
		 this.roadNum = roadNum;
	}


	public MyList<ArcNode> getNodes() {
		return nodes;
	}

	public void setNodes(MyList<ArcNode> nodes) {
		this.nodes = nodes;
	}
}
