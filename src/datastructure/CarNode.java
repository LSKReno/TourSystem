package datastructure;


public class CarNode {
	private Car car; //汽车信息
	private CarNode next; //链表中下一个汽车结点的指针
	
	public CarNode(Car car, CarNode next) {
		this.car = car;
		this.next = next;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public CarNode getNext() {
		return next;
	}

	public void setNext(CarNode next) {
		this.next = next;
	}
}
