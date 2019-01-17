package datastructure;

import java.util.Date;

// 汽车的数据结构

public class Car {
	private String number; //汽车车号
	private Date arrive_time; //汽车到达时间
	
	public Car(String number, Date arrive_time) {
		this.number = number;
		this.arrive_time = arrive_time;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getArrive_time() {
		return arrive_time;
	}

	public void setArrive_time(Date arrive_time) {
		this.arrive_time = arrive_time;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			Car car= (Car) obj;
			if (car.getNumber() == null || number == null) {
				return false;
			}else{
				return number.equalsIgnoreCase(car.getNumber());
			}
		}
		return false;
	}
}
