package datastructure;

import java.lang.reflect.Array;

import datastructure.Constants;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class MyStack<T> {

	private class Node{
		private T data; //节点数据
		private Node next;//指向节点下一个节点的索引

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
	private int size = 0;
	private int capacity = 0;
	Class<T> type;

	public MyStack(Class<T> type,int capacity){
		head = new Node(null,null);
		this.capacity = capacity;
		this.type = type;
	}


	//链表最前头添加节点
	public void push(T data)
	{
		Node adddata = new Node(data,null);
		adddata.next = head.next;
		head.next = adddata;
		size++;
	}

	public void pop(){
		head.next = head.next.next;
		size--;
	}

	public T peek(){
		if(size == 0){
			return null;
		}
		return head.next.data;
	}

	public int size(){
		return size;
	}

	public boolean isEmpty(){
		return size==0 ? true : false;
	}

	public boolean isFull(){
		return size==capacity ? true : false;
	}

	public T[] getAll(){
		T[] myArray = (T[]) Array.newInstance(type, size);
		Node current = head.next;
		for(int i = 0; i < size; i++){
			myArray[i] = current.data;
			current = current.next;
		}
		return myArray;
	}



}
