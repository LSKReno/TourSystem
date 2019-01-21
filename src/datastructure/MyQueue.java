package datastructure;


/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class MyQueue<T> {

	public class Node{
		public T data; //节点数据
		public Node next;//指向节点下一个节点的索引

		public Node(T data,Node next)
		{
			this.data = data;
			this.next = next;
		}

		//判断是否有下一个节点
		public boolean hasNext()
		{
			if(this.next == null)
				return false;
			return true;
		}

	}

	private Node head = null; //头节点
	private Node current = null;
	private int size = 0;

	public MyQueue(){
		head = new Node(null,null);
		current = head;
	}


	//在链表末尾添加节点
	public void add(T data)
	{

		Node adddata = new Node(data,null);
		current.next = adddata;
		current = adddata;
		if (size == 0){
			head.next = current;
		}
		size++;

	}



	//通过链接位置索引获取节点
	public Node getNode(int index){
		int k = 0;
		Node pNode = this.head.next;
		while(true){
			if(k == index){
				return pNode;
			}
			pNode = pNode.next;
			k++;

		}
	}



	//删除在位置index的节点
	public boolean pop() {
		if(this.size == 0){
			System.out.println("链表为空，删除失败");
			return  false;
		}

		head.next = head.next.next;
		size--;
		return true;

	}

	public T front(){
		if(size == 0) return null;
		System.out.println(head.next);
		return head.next.data;
	}

	//获取链接的大小
	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public Node getHeadNode(){
		return head;
	}

	//清空链表
	public void clear(){
		this.head.next = null;
		size = 0;
	}

	public boolean contains(T data){
		boolean exist = false;

		Node node = head.next;
		while(node != null){
			if(node.data.equals(data)){
				exist = true;
				break;
			}
			node = node.next;
		}

		return exist;
	}


}
