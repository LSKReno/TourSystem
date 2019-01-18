package datastructure;


public class VehicleNode {
	private Vehicle vehicle; //汽车信息
	private VehicleNode next; //链表中下一个汽车结点的指针
	
	public VehicleNode(Vehicle vehicle, VehicleNode next) {
		this.vehicle = vehicle;
		this.next = next;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleNode getNext() {
		return next;
	}

	public void setNext(VehicleNode next) {
		this.next = next;
	}
}
