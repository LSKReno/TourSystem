package datastructure;

public class MyList<T> {

    private class Node{
        private T data; //节点数据
        private Node next;//指向节点下一个节点的索引

        public Node(T data,Node next) {
            this.data = data;
            this.next = next;
        }

        //判断是否有下一个节点
        public boolean hasNext() {
            if(this.next == null)
                return false;
            return true;
        }

    }

    private Node head = null; //头节点
    private Node current = null;
    private int size = 0;

    public MyList(){
        head = new Node(null,null);
        current = head;
    }


    //在链表末尾添加节点
    public void add(T data) {
        Node addData = new Node(data,null);
        current.next = addData;
        current = addData;
        size++;
    }

    public void add(T data, int pos) {
        // 想要插入的位置大于链表大小，则插入末尾
        if(pos >= size){
            add(data);
        }
        Node addData = new Node(data,null);
        Node pre = getNode(pos-1);
        Node current = getNode(pos);

        pre.next = addData;
        addData.next = current;
        size++;
    }

    public void addFront(T data){
        Node addData = new Node(data,null);

        if (size == 0){
            head.next = addData;
            current = addData;
        }
        else{
            addData.next = head.next;
            head.next = addData;
        }
        size++;

    }

    //通过链接位置索引获取节点
    public Node getNode(int index){
        if(index == -1)
            return head;
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


    public int getIndex(T data){
        Node pNode = this.head.next;
        int index = 0;
        while(size != 0){
            if (data.equals(pNode.data)){
                return index;
            }
            if(pNode.hasNext()){
                pNode = pNode.next;
                index ++;
            }
            else {
                break;
            }
        }
        return -1;
    }


    //通过链接位置索引获取数据
    public T getData(int index){
        int k = 0;
        Node pNode = this.head.next;
        while(true){
            if(k == index){
                return pNode.data;
            }
            pNode = pNode.next;
            k++;

        }
    }


    //删除在位置index的节点
    public boolean delete(int index) {
        if(this.size == 0){
            System.out.println("链表为空，删除失败");
            return  false;
        }
        else if(index + 1 > size){
            System.out.println("越界，删除失败");
            return  false;
        }
        else {
            Node next = this.getNode(index+1);//需要删除的目标节点后一个节点
            Node pervious = null;
            if(index == 0)
                pervious = head;//目标节点的前一个节点
            else
                pervious = this.getNode(index-1);//目标节点的前一个节点
            pervious.next = next;
            size--;

            if(size == 0){
                current = head;
            }
            return  true;
        }

    }

    //遍历节点
    public void display(){
        Node pNode = this.head.next;
        while(size != 0){
            System.out.print(pNode.data + "->");
            if(pNode.hasNext()){
                pNode = pNode.next;
            }else {
                break;
            }
        }
    }

    public void exchange(int i, int j){
        T temp = getData(i);
        add(getData(j),i);
        delete(i+1);
        delete(j);
        add(temp,j);
    }

    public boolean empty(){
        return size == 0;
    }

    //获取链接的大小
    public int getSize(){
        return size;
    }

    //清空链表
    public void clear(){
        this.head.next = null;
        size = 0;
    }


}
