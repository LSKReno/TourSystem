package algorithm;

import datastructure.*;

//寻找两个景点间的最短路径和最短距离

public class ShortestPath {
	private Graph graph;
	private int dis[]; //顶点与起始点之间的最短距离
	private int vis[]; //判断顶点是否被访问
	private int fath[]; //父结点的位置
	private int sourceIndex; //起始点
	private int desIndex; //目标点
	
	public ShortestPath(Graph graph) {
		this.graph = graph;
		dis = new int[graph.getArcNum()];
		vis = new int[graph.getArcNum()]; //初始化vis数组，将所有顶点设为未访问
		fath = new int[graph.getArcNum()];
	}


	//利用迪杰斯特拉算法寻找两个景点间的最短路径和最短距离
	public void dijkstra(String source, String des){
		sourceIndex = getPos(source);
		desIndex = getPos(des);
		clear();

		//初始化最短距离数组为INF,初始点到初始点为0
		for(int i=0; i<graph.getArcNum(); i++){
			dis[i] = (i==sourceIndex ? 0 : Constants.INFINITY);
		}
		
		for(int i=0; i<graph.getArcNum(); i++){	// 遍历各个节点
			int minPos = -1, m = Constants.INFINITY;
			for(int j=0; j<graph.getArcNum(); j++){	// 遍历节点
				if(vis[j]==0 && dis[j]<m){			// 如果节点未访问并且有边相连，寻找最小的边
					m = dis[minPos=j];
				}
			}
			if(minPos == -1){
				continue;
			}
			vis[minPos] = 1;
			for(int j=0; j<graph.getArcNum(); j++){
				if(vis[j]==0 && dis[minPos]+getLength(minPos, j)<dis[j]){
					dis[j] = dis[minPos]+getLength(minPos, j);
					fath[j] = minPos;
				}
			}
		}
	}


	//利用Floyd算法计算两点之间的最短距离

	public void Floyd(String source, String des){
		sourceIndex = getPos(source);
		desIndex = getPos(des);
		clear();
		int distance[][] = new int[graph.getArcNum()][graph.getArcNum()], path[][] = new int[graph.getArcNum()][graph.getArcNum()];

		// 初始化
		for (int i = 0; i < graph.getArcNum(); i++) {
			for (int j = 0; j < graph.getArcNum(); j++) {
				distance[i][j] = getLength(i, j);
				path[i][j] = j;
			}
		}

		for (int k = 0; k < graph.getArcNum(); k++) {
			for (int i = 0; i < graph.getArcNum(); i++) {
				for (int j = 0; j < graph.getArcNum(); j++) {
					int tmp_value;
					if(distance[i][k] == Constants.INFINITY || distance[k][j]==Constants.INFINITY){
						tmp_value = Constants.INFINITY;
					}
					else{
					    tmp_value = distance[i][k] + distance[k][j];
                    }
					if (distance[i][j] > tmp_value) {
						distance[i][j] = tmp_value;
						path[i][j] = path[i][k];
					}
				}
			}
		}

		// 获取最短距离
		dis = distance[sourceIndex];

		// 获取路径
		int current = sourceIndex;
		MyList<Integer> list  = new MyList<>();
		while(true){
			current = path[current][desIndex];
			if(current == desIndex)
				break;
			list.add(current);
		}

		// 将头尾节点加入链表
		list.addFront(sourceIndex);
		list.add(desIndex);

		for(int i = list.getSize(); i > 1; i--){
			fath[list.getData(i-1)] = list.getData(i-2);
		}

	}

	public void spfa(String source, String des){
		sourceIndex = getPos(source);
		desIndex = getPos(des);
		MyQueue<Integer> queue = new MyQueue();
		clear();

		//初始化最短距离数组为INF
		for(int i=0; i<graph.getArcNum(); i++){
			dis[i] = (i==sourceIndex ? 0 : Constants.INFINITY);
		}

		//将起始点加入队列
		queue.add(sourceIndex);
		int k = 1;
		while(true){
			int nodeIndex = queue.front();
			queue.pop();
			MyList<VNode> list = graph.getNodes().getData(nodeIndex).getVlist();

			for(int i = 0; i < list.getSize(); i++){
				// 如果边距离+起始点距离 < 终点距离
				if(list.getData(i).getDist() + dis[nodeIndex] < dis[list.getData(i).getIndex()]){
					dis[list.getData(i).getIndex()] = list.getData(i).getDist() + dis[nodeIndex];
					fath[list.getData(i).getIndex()] = nodeIndex;
					//不存在队列中，加入队列
					if(!queue.contains(list.getData(i).getIndex())){
						queue.add(list.getData(i).getIndex());
					}
				}
			}

			//如果队列空，退出循环
			if(queue.isEmpty()){
				break;
			}
		}

	}

	public boolean BellmanFord(String source, String des){
		sourceIndex = getPos(source);
		desIndex = getPos(des);
		clear();

		//初始化最短距离数组为INF
		for(int i=0; i<graph.getArcNum(); i++){
			dis[i] = (i==sourceIndex ? 0 : Constants.INFINITY);
		}

		for (int i = 0; i < graph.getArcNum(); i++) {
			for (int j = 0; j < graph.getArcNum(); j++) {
				if (getLength(i,j)!=0) {
					if(dis[i] + getLength(i,j) < dis[j]){
						dis[j] = dis[i] + getLength(i,j);
						fath[j] = i;
					}
				}
			}
		}

		for (int i = 0; i < graph.getArcNum(); i++) {
			for (int j = 0; j < graph.getArcNum(); j++) {
				if (dis[j]>dis[i]+getLength(i,j)) {
					return false;
				}
			}
		}
		return true;
	}

	//输出最短路径
	public MyList<Integer> outputShortestPath(){
		if(desIndex == sourceIndex)
			return  null;
		MyList<Integer> path = new MyList<Integer>();
		//如果节点不可达
		if(dis[desIndex] == Constants.INFINITY)
			return null;
		//放入最短路径长度
		path.add(dis[desIndex]);
		//逆序放入最短路径中结点的位置
		int t = desIndex;
		while(t != sourceIndex){
			path.add(t);
			t = fath[t];
		}
		path.add(t);

		// 重置数组
		dis = new int[graph.getArcNum()];
		vis = new int[graph.getArcNum()];
		fath = new int[graph.getArcNum()];

		return path;
	}

	private void clear(){
		dis = new int[graph.getArcNum()];
		vis = new int[graph.getArcNum()]; //初始化vis数组，将所有顶点设为未访问
		fath = new int[graph.getArcNum()];
	}
	
	//根据景点名称寻找景点的位置

	public int getPos(String name){
		int pos = -1;
		for(int i=0; i<graph.getArcNum(); i++){
			if(name.equals(graph.getNodes().getData(i).getName())){
				pos = i;
				break;
			}
		}
		
		return pos;
	}
	
	// 根据起始点位置和终止点位置得到两点间路径长度
	private int getLength(int fromIndex, int toIndex){
		MyList<VNode> list = graph.getNodes().getData(fromIndex).getVlist();
		int length = Constants.INFINITY;

		for (int i = 0; i < list.getSize(); i++) {
			if (list.getData(i).getIndex() == toIndex) {
				length = list.getData(i).getDist();
				break;
			}
		}
		return length;
	}

}
