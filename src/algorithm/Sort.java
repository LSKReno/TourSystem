package algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

import datastructure.Graph;
import datastructure.ArcNode;
import datastructure.MyList;

public class Sort {
	private Graph graph;
	String type = "";
	ArcNode[] nodes ;

	public Sort(Graph graph) {
		this.graph = graph;
		nodes = new ArcNode[graph.getArcNum()];
	}

	//按景点欢迎度对景点进行排序

	public Map<String, Integer> orderByPopular(String algorithm){
		int index = 0;
		type = "pop";

		for(int i = 0; i < graph.getNodes().getSize(); i++){
			ArcNode node = graph.getNodes().getData(i);
			nodes[index++] = new ArcNode(node.getName(), node.getDes(), node.getPopularity(),
					node.isHasRest(), node.isHasToilet());
		}


		if(algorithm.equals("冒泡排序"))
			bubbleSort();
		else if(algorithm.equals("选择排序")){
			selectSort();
		}
		else if(algorithm.equals("插入排序")){
			insertSort();
		}
		else if(algorithm.equals("希尔排序")){
			shellSort();
		}
		else if(algorithm.equals("归并排序")){
			mergeSort(0,nodes.length-1);
		}
		else if(algorithm.equals("快速排序")){
			quickSort(0,nodes.length-1);
		}
		else if(algorithm.equals("堆排序")){
			heapSort();
		}
		else if(algorithm.equals("计数排序")){
			countingSort();
		}
		else if(algorithm.equals("桶排序")){
			bucketSort();
		}
		else if(algorithm.equals("基数排序")){
			radixSort(getMax());
		}
		else{
			System.out.println("出错了！！！");
		}

		Map<String, Integer> orderResults = new LinkedHashMap<String, Integer>();
		for(int i=0; i<graph.getArcNum(); i++){
			orderResults.put(nodes[i].getName(), nodes[i].getPopularity());
		}
		
		return orderResults;
	}
	

	 //按景点的路口数排序
	public Map<String, Integer> orderByPathNum(String algorithm){
		int index = 0;
		type = "road";

		// 获得景点路口数
		for(int i = 0; i < graph.getNodes().getSize(); i++){
			ArcNode node = graph.getNodes().getData(i);
			nodes[i] = new ArcNode(node.getName(), node.getDes(), node.getPopularity(),
					node.isHasRest(), node.isHasToilet(),node.getVlist());

		}

		if(algorithm.equals("冒泡排序"))
			bubbleSort();
		else if(algorithm.equals("选择排序")){
			selectSort();
		}
		else if(algorithm.equals("插入排序")){
			insertSort();
		}
		else if(algorithm.equals("希尔排序")){
			shellSort();
		}
		else if(algorithm.equals("归并排序")){
			mergeSort(0,nodes.length-1);
		}
		else if(algorithm.equals("快速排序")){
			quickSort(0,nodes.length-1);
		}
		else if(algorithm.equals("堆排序")){
			heapSort();
		}
		else if(algorithm.equals("计数排序")){
			countingSort();
		}
		else if(algorithm.equals("桶排序")){
			bucketSort();
		}
		else if(algorithm.equals("基数排序")){
			radixSort(getMax());
		}
		else{
			System.out.println("出错了！！！");
		}
		
		Map<String, Integer> orderResults = new LinkedHashMap<String, Integer>();
		for(int i=0; i<graph.getArcNum(); i++){
			orderResults.put(nodes[i].getName(), nodes[i].getVlist().getSize());
		}
		
		return orderResults;
	}

	//冒泡排序
	private void bubbleSort(){
		for(int i=0; i<graph.getArcNum(); i++){
			for(int j=i+1; j<graph.getArcNum(); j++){
				if(getData(i) < getData(j)){
					exchange(i,j);
				}
			}
		}
	}

	// 选择排序
	private void selectSort(){
		int n = nodes.length;
		for (int i = 0; i < n; i++) {
			int k = i;
			// 找出最大值的下标
			for (int j = i + 1; j < n; j++) {
				if (getData(j) > getData(k)) {
					k = j;
				}
			}
			// 将最大值放最前
			if (k > i) {
				exchange(k,i);
			}
		}
	}

	// 插入排序
	private void insertSort(){
		int slideIndex, current;

		for(int i = 1; i < nodes.length; i++){
			slideIndex = i -1;
			current = i;
			while(getData(current) > getData(slideIndex) && slideIndex >= 0){
				exchange(current,slideIndex);
				slideIndex--;
				current--;

				if (slideIndex < 0)
					break;
			}
		}
	}


	private  void shellSort() {
		// 计算出最大的h值
		int h = 1;
		while (h <= nodes.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (int i = h; i < nodes.length; i += h) {
				int k = i-h;
				while(getData(i) > getData(k)){
					exchange(i,k);
					i = k;
					k -= h;
					if(k < 0)
						break;
				}
			}

			// 计算出下一个h值
			h = (h - 1) / 3;
		}
	}

	private void mergeSort( int low, int high) {

		int mid = (high + low ) / 2;
		if(low < high){
			// 左右递归排序
			mergeSort(low, mid );
			mergeSort(mid+1, high );

			// 左右归并
			ArcNode[] temp = new ArcNode[high - low + 1];
			int i = low;
			int j = mid + 1;
			int k = 0;

			// 先比较两个数组的元素，放入临时数组
			while(i <= mid && j <= high){
				if(getData(i) > getData(j)){
					temp[k++] = nodes[i++];
				}else{
					temp[k++] = nodes[j++];
				}
			}

			// 直接把剩余的数组元素放入临时数组
			while(i <= mid){
				temp[k++] =  nodes[i++];
			}

			while(j <= high){
				temp[k++] =  nodes[j++];
			}

			// 更新实际数组
			for (int m = 0; m < k; m++){
				nodes[m + low] = temp[m];
			}
		}
	}

	//快速排序法
	private  void quickSort( int low, int high){
		//获得key
		int key = low;
		int i = low;
		int j = high;

		if(low >= high)
			return;

		while(i != j){
			while(i < j && getData(i) > getData(key)){
				i++;
			}
			while(i < j && getData(j) <= getData(key)){
				j--;
			}
			if(i < j){
				exchange(i, j);
			}
		}
		// 关键字换位
		exchange(i, key);

		quickSort( low, i-1);
		quickSort(i+1, high);


	}



	void maxheap_down(int start, int end, String type)
	{
		int c = start;            // 当前节点的位置
		int l = 2*c + 1;        // 左孩子的位置
		int tmp = getData(c);            // 当前节点的大小
		for (; l <= end; c=l,l=2*l+1)
		{
			// l : 左孩子，l+1 : 右孩子
			if ( l < end && getData(l) < getData(l+1))
				l++;        // 左右两孩子中选择较大者
			if (tmp >=getData(l))
				break;        // 调整结束
			else            // 交换值
			{
				exchange(l,c);
			}
		}
	}

	void heapSort()
	{
		int i;
		int n = nodes.length;  // 数组的长度

		// 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
		for (i = n / 2 - 1; i >= 0; i--)
			maxheap_down(i, n-1,type);

		// 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
		for (i = n - 1; i > 0; i--)
		{
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
			exchange(0, i);
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
			// 即，保证a[i-1]是a[0...i-1]中的最大值。
			maxheap_down(0, i-1,type);
		}

		// 把数组颠倒顺序
		ArcNode [] temp = nodes.clone();
		for(int k = 0; k < nodes.length; k++){
			nodes[k] = temp[nodes.length - k - 1];
		}
	}


	private void countingSort() {
		ArcNode[] result = new ArcNode[nodes.length];
		int max = getMax();
		int[] temp = new int[max + 1];
		// 以下循环操作完成后，temp的第i个位置保存着array中，值为i的元素的总个数
		for (int i = 0; i < nodes.length ; i++) {
			temp[getData(i)]++;
		}
		// 以下循环操作完成后，temp的第i个位置保存着array中，值小于或等于i的元素的总个数
		for (int i = temp.length-2; i > 0; i--) {
			temp[i] += temp[i + 1];
		}
		for (int i = nodes.length - 1; i > -1; i--) {
			result[temp[getData(i)] - 1] = nodes[i];
			temp[getData(i)]--;
		}

		nodes = result.clone();
	}

	// 桶排序
	private void bucketSort() {
		@SuppressWarnings("unchecked")
		// 构造辅助数组
		MyList<ArcNode> [] aux = new MyList[getMax()+1];
		for(int i = 0; i < aux.length; i++){
			aux[i] = new MyList<ArcNode>();
		}
		for(int i = 0; i < nodes.length; i++){
			// 找元素在桶中的位置，并将其添加
			aux[(int)Math.floor((getData(i) - getMin()) / nodes.length)].add(nodes[i]);
		}
		int index=  0;
		for (int i = 0; i < aux.length; i++) {
			bucketInsertSort(aux[i]);                   // 对每个桶进行排序，这里使用了插入排序
			for (int j = 0; j < aux[i].getSize(); j++) {
				nodes[index] = (aux[i].getData(j));
				index++;
			}
		}

		// 把数组颠倒顺序
		ArcNode [] temp = nodes.clone();
		for(int m = 0; m < nodes.length; m++){
			nodes[m] = temp[nodes.length - m - 1];
		}


	}



	// 插入排序
	private void bucketInsertSort( MyList<ArcNode> a){
		int slideIndex, current;

		for(int i = 1; i < a.getSize(); i++){
			slideIndex = i -1;
			current = i;
			while(getData(a,current) < getData(a,slideIndex) && slideIndex >= 0){
				a.exchange(current,slideIndex);
				slideIndex--;
				current--;

				if (slideIndex < 0)
					break;
			}
		}

	}

	// 基数排序
	private void radixSort(int d)
	{
		int n=1;//代表位数对应的数：1,10,100...
		int k=0;//保存每一位排序后的结果用于下一位的排序输入
		int length=nodes.length;
		ArcNode[][] bucket=new ArcNode[10][length];//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[] order=new int[length];//用于保存每个桶里有多少个数字
		while(n<d)
		{
			for(int i = 0; i <nodes.length; i++) //将数组array里的每个数字放在相应的桶里
			{
				int num = getData(i);
				int digit=(num/n)%10;
				bucket[digit][order[digit]]=nodes[i];
				order[digit]++;
			}
			for(int i=0;i<length;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
			{
				if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中
				{
					for(int j=0;j<order[i];j++)
					{
						nodes[k]=bucket[i][j];
						k++;
					}
				}
				order[i]=0;//将桶里计数器置0，用于下一次位排序
			}
			n*=10;
			k=0;//将k置0，用于下一轮保存位排序结果
		}

		// 把数组颠倒顺序
		ArcNode [] temp = nodes.clone();
		for(int m = 0; m < nodes.length; m++){
			nodes[m] = temp[nodes.length - m - 1];
		}

	}


	private int getMax(){
		int max = 0;
		for(int i = 0 ; i < nodes.length; i++){
			if(max < getData(i)){
				max = getData(i);
			}
		}
		return  max;
	}

	private int getMin(){
		int min = 0;
		for(int i = 0 ; i < nodes.length; i++){
			if(min > getData(i)){
				min = getData(i);
			}
		}
		return  min;
	}


	private int getData(int index){
		if(type.equals("pop")){
			return nodes[index].getPopularity();
		}
		else{
			return nodes[index].getVlist().getSize();
		}
	}

	private int getData(MyList<ArcNode> a ,int index){
		if(type.equals("pop")){
			return a.getData(index).getPopularity();
		}
		else{
			return a.getData(index).getVlist().getSize();
		}
	}

	private void exchange(int i, int j){
		ArcNode tmp = nodes[i];
		nodes[i] = nodes[j];
		nodes[j] = tmp;
	}

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


}
