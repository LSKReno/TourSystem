package datastructure;


public class VNode {
	private int index; //另一个景点在景点数组中的位置
	private int dist; //两个景点的距离
	private int time; //所需时间

	public VNode(int index, int dist, int time) {
		this.index = index;
		this.dist = dist;
		this.time = time;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
