package algorithm;

import java.util.ArrayList;
import java.util.List;

import datastructure.*;

//根据输入的起始点生成旅游路线图，并判断是否有回路

public class TourMap {
	private Graph graph;
	private Graph directGraph;
	private boolean[] visited;
	private List<Integer> tourIndexList;
	private int startIndex;
	private int endIndex;
	private int length;
	int count=0;

    public TourMap(Graph graph) {
        this.graph = graph;
        directGraph = new Graph(graph.getArcNum());
        visited = new boolean[graph.getArcNum()];
        tourIndexList = new ArrayList<>();
    }
	public List<Integer> bdfsplus(String start,String end){
		clear();
		// 获得起始节点索引
		startIndex = getPos(start);
		endIndex = getPos(end);

		double p = 0.01;   // 回溯因子
		double q = 10;   // 深度优先因子调节
		double f = 0.001;  // 终点因子调节
		double x = 0.01;  // 已访问节点因子
		if(startIndex != endIndex){
			// 使用地杰斯特拉算法求最短路径
			ShortestPath shortest = new ShortestPath(graph);
			shortest.dijkstra(start, end);
			MyList<Integer> temp = shortest.outputShortestPath();

			// 首位为路径长度，去除
			temp.delete(0);

			// 获取最短路径途经的节点个数
			q = 4 * temp.getSize();
		}


		MyStack<Integer> traverseNodes = new MyStack<Integer>(Integer.class, graph.getArcNum());

		// 深度优先节点存储
		traverseNodes.push(startIndex);
		// 最终路径存储
		tourIndexList.add(startIndex);

		// 设置终点为已访问，其实是伪已访问 ， 按照设计时的思路，f是控制终点因子访问的参数，看似终点不应该设置为已访问。
		// 这里之所以这样做， 是一个小trick，只要判断最终点的语句在判断节点是否已经访问的前面，就可以成功进入判断终点的语句。
		// 这样的好处是不需要再每次循环中都判断是否只有终点还未访问（当f足够小的时候，最后只会剩下终点没访问）

		// 如果深度优先栈不为空并且还有节点未访问
		while(!traverseNodes.isEmpty() && !hasAllVisited()){
			// 获得栈顶节点
			int arcNodeIndex = traverseNodes.peek();
			// 当前节点的上一个节点
			int preNodeIndex ;

			// 如果当前节点为起点
			if(tourIndexList.size() == 1)
				preNodeIndex = startIndex;
				// 如果当前节点不是起点，正常获得preNodeIndex
			else
				preNodeIndex = tourIndexList.get(tourIndexList.size()-1);
			// 已访问
			visited[arcNodeIndex] = true;
			MyList<VNode> list = graph.getNodes().getData(arcNodeIndex).getVlist();
			// 储存下一步待选择的节点经过处理后的权重
			double[] select = new double[list.getSize()];
			boolean allVisited = true;
			for (int i = 0; i < list.getSize(); i++) {
				// 如果该边终点为前面的节点
				if (list.getData(i).getIndex() == preNodeIndex){
					// 如果倒数第二个节点和该边终点相等，即有形成死环的可能
					if(tourIndexList.get(tourIndexList.size()-2) == list.getData(i).getIndex() ){
						select[i] = (1.0/(p*0.001)) *list.getData(i).getDist();
					}else{
						select[i] = (1.0/p) *list.getData(i).getDist();

					}
					allVisited = false;

				}
				// 该边终点是路线的最终点
				else if(list.getData(i).getIndex() == endIndex){
					//只有终点没访问
					if(checkFinal(endIndex))
						f = 10000;

					select[i] = (1.0/f) *list.getData(i).getDist();
					allVisited = false;
				}
				// 如果该边的终点已经访问
				else if(visited[list.getData(i).getIndex()] == true){
					select[i] =  (1.0/x) * list.getData(i).getDist();
					continue;
				}
				// 如果未访问，前一个节点和该边终点有边，并且该边终点不是路线的最终点
				else if(getDis(preNodeIndex, list.getData(i).getIndex()) < Constants.INFINITY && list.getData(i).getIndex() != endIndex){
					select[i] = 1.0 *list.getData(i).getDist();
					allVisited = false;
				}
				//如果未访问，并且前一个节点和该节点无边
				else {
					select[i] = (1.0 / q) * list.getData(i).getDist();
					allVisited = false;
				}
			}
			// 如果当前节点所连接的边全部已经访问
			if(allVisited == true){
				traverseNodes.pop();
			}else{
				// 获得乘以权重后最小的边
				int minIndex = 0;
				double min = select[0];
				for(int i = 1; i < select.length; i++){
					if(min > select[i]){
						min = select[i];
						minIndex = i;
					}
				}
				traverseNodes.push(list.getData(minIndex).getIndex());
			}

			tourIndexList.add(arcNodeIndex);
		}
		// 删除刚开始加入的冗余的起点
		tourIndexList.remove(0);

		// 使用地杰斯特拉算法求最短路径
		ShortestPath shortest = new ShortestPath(graph);
		String tempStartName = graph.getNodes().getData(tourIndexList.get(tourIndexList.size()-1)).getName();
		shortest.dijkstra(tempStartName, end);
		MyList<Integer> temp = shortest.outputShortestPath();

		if(temp == null){
			return  tourIndexList;
		}
		// 首位为路径长度，去除
		temp.delete(0);
		// 最后一位与原list的最后一位重复，去除
		temp.delete(temp.getSize()-1);

		for(int i = temp.getSize() -1; i >= 0; i--){
			tourIndexList.add(temp.getData(i));
		}
		return tourIndexList;
	}

	public boolean checkFinal(int end){
		boolean flag = false;
		int count = 0;
		for(int i = 0; i < visited.length; i++){
			if(i == end){
				if(visited[i] == false)
					flag = true;
				continue;
			}
			if(visited[i] == true){
				count++;
			}
		}

		if(count == visited.length-1 && flag == true)
			return true;

		return false;

	}

	public void clear(){
		visited = new boolean[graph.getArcNum()];
		tourIndexList = new ArrayList<>();
		startIndex = -1;
		length = 0;
		count = 0;
		endIndex= -1;
	}


	//利用深度遍历得到旅游路线图
	public List<Integer> DFS(String start, String end){
		clear();
		MyStack<Integer> traverseNodes = new MyStack<Integer>(Integer.class, graph.getArcNum());

		startIndex = getPos(start);
		endIndex = getPos(start);

		//防止终点被提前访问
		visited[endIndex] = true;

		traverseNodes.push(startIndex);
		while(!traverseNodes.isEmpty() && !hasAllVisited()){
			int arcNodeIndex = traverseNodes.peek();
			visited[arcNodeIndex] = true;
			MyList<VNode> list = graph.getNodes().getData(arcNodeIndex).getVlist();

			for (int i = 0; i < list.getSize(); i++) {
				// 如果未访问，直接访问
				if(!visited[list.getData(i).getIndex()]){
					traverseNodes.push(list.getData(i).getIndex());
					break;
				}
				if(i == list.getSize() - 1){
					traverseNodes.pop();
				}
			}
			tourIndexList.add(arcNodeIndex);
		}

		if(tourIndexList.get(tourIndexList.size()-1) == endIndex){
			tourIndexList.add(endIndex);
			return tourIndexList;
		}


		ShortestPath shortest = new ShortestPath(graph);
		String tempStartName = graph.getNodes().getData(tourIndexList.get(tourIndexList.size()-1)).getName();
		shortest.dijkstra(tempStartName, end);
		MyList<Integer> temp = shortest.outputShortestPath();

		// 首位为路径长度，去除
		temp.delete(0);
		// 最后一位与原list的最后一位重复，去除
		temp.delete(temp.getSize()-1);

		for(int i = temp.getSize() -1; i >= 0; i--){
			tourIndexList.add(temp.getData(i));
		}
		return tourIndexList;
	}

	public List<Integer> getHamilton(String start, String end) {
		clear();
		startIndex = getPos(start);
		endIndex = getPos(end);

		int[] path = new int[graph.getArcNum()+1];       //记录哈密顿路径
		for(int i = 0;i < graph.getArcNum();i++) {
			visited[i] = false;     //初始化，所有顶点均未被遍历
			path[i] = -1;          //初始化，未选中起点及到达任何顶点
		}
		visited[startIndex] = true;
		visited[endIndex] = true;  //防止终点被提前访问
		path[0] = startIndex;      //表示哈密顿起点为第0个顶点
		dfs(path, 1, startIndex == endIndex);     //从第0个顶点开始进行深度优先遍历,如果存在哈密顿路径，输出一条路径，否则无输出
		boolean haspath = true;

		//如果是环路
		if(startIndex == endIndex){
			path[graph.getArcNum()] = endIndex;
			convertToList(path);

			for(int i = 0 ; i < tourIndexList.size(); i++){
				if(tourIndexList.get(i) == -1)
					haspath = false;
			}
		}else{ // 非环路
			path[graph.getArcNum()-1] = endIndex;
			convertToList(path);
			for(int i = 0 ; i < tourIndexList.size() - 1; i++){
				if(tourIndexList.get(i) == -1)
					haspath = false;
			}
			tourIndexList.remove(tourIndexList.size()-1);

		}

		return haspath == false ? null:tourIndexList;
	}

	private boolean dfs(int[] path,int step, boolean cycle) {
		//如果起点和终点不同，则需要遍历节点数为节点总数-2。  相同则为 节点总数-1    此处的step从1开始
		if((step == graph.getArcNum() && cycle) || (step == (graph.getArcNum() - 1) && !cycle)) {
			if(getDis(path[step - 1], endIndex) < Constants.INFINITY) { //最后一步到达的顶点能够到终点
				return true;
			}
			return false;
		} else {
			if(judgeEqual(path)){
				System.out.println("1212");
			}

			for(int i = 0;i < graph.getArcNum();i++) {
				if(!visited[i] && getDis(path[step - 1], i) < Constants.INFINITY) {
					visited[i] = true;
					path[step] = i;
					if(dfs(path, step + 1,cycle))
						return true;
					else {
						visited[i] = false;    //进行回溯处理
						path[step] = -1;
					}
				}
			}
		}
		return false;
	}

	public List<Integer> EulerTour(String start){
		// 判断是否有欧拉回路
		for(int i = 0 ; i < graph.getArcNum(); i++){
			if(graph.getNodes().getData(i).getVlist().getSize() % 2 != 0){
				return null;
			}
		}
		clear();
		startIndex = getPos(start);
		tourIndexList.add(startIndex);
		int flag=0;
		MyList<ArcNode> list = new MyList<>();
		MyList<ArcNode> nodes = graph.getNodes();

		// 复制list
		for(int i = 0; i < nodes.getSize(); i++){
			list.add(nodes.getData(i).copy());
		}

		while(flag<tourIndexList.size()) {
			if(!list.getData(tourIndexList.get(flag)).getVlist().empty()) {
				count =flag+1;  //定位加入路线图的list的位置
				findEulerTour(tourIndexList.get(flag), list);//flag用来不断在队列中推进寻找还有边的路径点
			}
			flag++;
		}

		return tourIndexList;

	}

	private void findEulerTour(int index, MyList<ArcNode> list) {
		MyList<VNode> vlist = list.getData(index).getVlist();
		if (!vlist.empty()) {//随机找一条边，终结点一定会绕回到出发点，并且出发点的边会遍历完毕
			VNode s = vlist.getData(0);
			//删除第一条边
			vlist.delete(0);
			for(int i = 0; i < list.getData(s.getIndex()).getVlist().getSize(); i++) {
				if(list.getData(s.getIndex()).getVlist().getData(i).getIndex() == index){
					list.getData(s.getIndex()).getVlist().delete(i);
					break;
				}
			}
			tourIndexList.add(count, s.getIndex());
			count++;
			findEulerTour(s.getIndex(), list);
		}
	}

	//得到固定起始点在最小生成树中的终点位置
	private int getEnd(int[] ends, int arcIndex){
		// 如果该边仍然是初始化的值
		if(ends[arcIndex] != 0){
			arcIndex = ends[arcIndex];
		}

		return arcIndex;
	}

	private void convertToList(int[] path){
		for (int i = 0; i < path.length; i++){
			tourIndexList.add(path[i]);
		}
	}

	public int getPathLength(){
		for(int i = 0 ; i < tourIndexList.size()-1; i++){
			length += getDis(tourIndexList.get(i),tourIndexList.get(i+1));
		}
		return length;
	}

	// 生成有 向图
	private void initNewGraph(){
		for(int i=0; i<graph.getArcNum(); i++){
			ArcNode arcNode = graph.getNodes().getData(i);
			ArcNode newArcNode = new ArcNode(arcNode.getName(), arcNode.getDes(), arcNode.getPopularity(),
					arcNode.isHasRest(), arcNode.isHasToilet());
			directGraph.getNodes().add(newArcNode);
		}

		// 取得时候永远只取链表最尾部的边，这样就可以解决两次经过一个点的问题
		for(int i=0; i<tourIndexList.size()-1; i++){
			VNode newVNode = getVNode(tourIndexList.get(i), tourIndexList.get(i+1));
			directGraph.getNodes().getData(tourIndexList.get(i)).getVlist().add(newVNode);
		}
	}
	
	// 获取指点景点名称的位置
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
	
	// 判断是否所有景点已经被访问
	private boolean hasAllVisited(){
		for(boolean visit : visited){
			if(!visit){
				return false;
			}
		}
		
		return true;
	}
	
	// 获取边节点信息
	private VNode getVNode(int fromIndex, int toIndex){
		VNode newVNode = null;

		MyList<VNode> list = graph.getNodes().getData(fromIndex).getVlist();

		for (int i = 0; i < list.getSize(); i++) {
			if (list.getData(i).getIndex() == toIndex) {
				newVNode = new VNode(list.getData(i).getIndex(), list.getData(i).getDist(), list.getData(i).getTime());
				break;
			}
		}
		
		return newVNode;
	}

	private int getDis (int fromIndex, int toIndex){
		int dis = Constants.INFINITY;
		MyList<VNode> list = graph.getNodes().getData(fromIndex).getVlist();
		for (int i = 0; i < list.getSize(); i++) {
			if(list.getData(i).getIndex() == toIndex){
				dis = list.getData(i).getDist();
				break;
			}
		}
		if(fromIndex == toIndex)
			dis = 0;
		return dis;
	}

	private  boolean judgeEqual(int [] path){
		int [] a = {1,4,7,3,9,10,11};
		boolean judge = true;
		for(int i = 0; i<a.length ; i++){
			if(a[i]!=path[i]) {
                judge = false;
            }
		}
		return  judge;
	}
}
