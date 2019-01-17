package algorithm;

import java.util.ArrayList;
import java.util.List;

import datastructure.*;

// 用于得到道路改进的建议方法

public class Kruskal {
	private Graph graph;
	
	public Kruskal(Graph graph) {
		this.graph = graph;
	}
	

	 //kruskal算法获取最小生成树
	public List<VData> kruskal(){
		int index = 0;
		int[] ends = new int[graph.getRoadNum()]; //用于保存已有最小生成树中每个顶点在该最小生成树中的终点
		VData[] results = new VData[graph.getRoadNum()]; //用于保存结果最小生成树的边
		
		//获取图中所有的边
		VData[] edges = getEdges();

		for(int i = 0; i < edges.length; i++){
			System.out.println(edges[i]);
		}
		//将边按照权的大小进行排序
		sortEdges(edges);
		//对所有边进行遍历
		for(int i=0; i<graph.getRoadNum(); i++){
			int m = getEnd(ends, edges[i].getStart()); //获取该边起点在已有最小生成树中的终点
			int n = getEnd(ends, edges[i].getEnd()); //获取该边终点在已有最小生成树中的终点
			//如果m!=n，说明在已有最小生成树中添加该边不会形成回路
			if(m != n){
				ends[m] = n;
				results[index++] = edges[i];
			}
		}
		
		List<VData> result = new ArrayList<VData>();
		for(int i=0; i<index; i++){
			result.add(results[i]);
		}
		
		return result;
	}

	//获取所有边的信息
	private VData[] getEdges(){
		int index = 0; 
		VData[] edges;
		
		edges = new VData[graph.getRoadNum()];
		for(int i=0; i<graph.getArcNum(); i++){
			MyList<VNode> list = graph.getNodes().getData(i).getVlist();
			for (int j = 0 ; j < list.getSize(); j++){
				if (list.getData(j).getIndex() > i){
					VData a = new VData(i, list.getData(j).getIndex(), list.getData(j).getDist());
					edges[index++] = new VData(i, list.getData(j).getIndex(), list.getData(j).getDist());
				}
			}
		}
		
		return edges;
	}
	
	// 按照边的权值进行排序
	private void sortEdges(VData[] edges){
		for(int i=0; i<edges.length; i++){
			for(int j=i+1; j<edges.length; j++){
				if(edges[i].getWeight() > edges[j].getWeight()){
					VData tmp = edges[i];
					edges[i] = edges[j];
					edges[j] = tmp;
				}
			}
		}
	}
	
	//得到固定起始点在最小生成树中的终点位置
	private int getEnd(int[] ends, int arcIndex){
		// 如果该边仍然是初始化的值
		while(ends[arcIndex] != 0){
			arcIndex = ends[arcIndex];
		}
		
		return arcIndex;
	}

}
