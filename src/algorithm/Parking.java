package algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;

import datastructure.*;
/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Parking {
	private MyStack<Vehicle> parking;
	private MyQueue<Vehicle> shortcut;
	private MyStack<Vehicle> tempParking;
	private MyStack<Vehicle> existMyStack;
	
	public Parking(int maxParkingNum){
		parking = new MyStack<Vehicle>(Vehicle.class, maxParkingNum);	// 车场内
		shortcut = new MyQueue();				// 排队车辆
		tempParking = new MyStack<Vehicle>(Vehicle.class, maxParkingNum);	// 临时
		existMyStack = new MyStack<Vehicle>(Vehicle.class, maxParkingNum);
	}



	//车辆到达，添加进停车场或候车道
	public String arrivePark(String number){
		//判断输入车牌号是否存在
		if(parking.size() != 0){
			//对停车场遍历
			while(parking.peek() != null){
				if(parking.peek().getNumber().equals(number)){
					break;
				}
				existMyStack.push(parking.peek());
				parking.pop();
			}
			//判断停车场中是否存在
			if(parking.size() == 0){ // 若停车场不存在
				// 恢复parking
				while(existMyStack.size() != 0){
					parking.push(existMyStack.peek());
					existMyStack.pop();
				}
				//判断候车道是否存在
				if(shortcut.contains(new Vehicle(number,null))){
					return "{\"exist\":true}";
				}	
			}else{
				// 停车场存在，
				while(existMyStack.size() != 0){
					parking.push(existMyStack.peek());
					existMyStack.pop();
				}
				return "{\"exist\":true}";
			}
		}
		
		//判断停车场是否满了，若满了则添加车到候车道，否则直接添加		
		if(parking.isFull()){
			shortcut.add(new Vehicle(number, null));
		}
		else{
			parking.push(new Vehicle(number, new Date()));
		}
		
		String JSONString = "{\"exist\":false,"
							+ "\"parking\":" + generateStackJSON(parking) 
							+ ",\"tempParking\":" + generateStackJSON(tempParking)
							+ ",\"shortcut\":" + generateQueueJSON(shortcut) + "}";
		
		return JSONString;
	}
	
	//车辆离开
	public String leavePark(String number, Date l_time){
		//判断停车场中是否存在输入车牌号
		if(parking.size() == 0){
			return "{\"exist\":null}";
		}
		else{
			//对停车场遍历
			while(parking.peek() != null){
				if(parking.peek().getNumber().equals(number)){
					break;
				}
				existMyStack.push(parking.peek());
				parking.pop();
			}
			//判断停车场中是否存在
			if(parking.size() == 0){
				while(existMyStack.size() != 0){
					parking.push(existMyStack.peek());
					existMyStack.pop();
				}
				return "{\"exist\":false}";
			}
			while(existMyStack.size() != 0){
				parking.push(existMyStack.peek());
				existMyStack.pop();
			}
		}

		MyList<String> parkingList = new MyList<String>(); //存储停车场信息
		MyList<String> tempParkingList = new MyList<String>(); //存储暂时停车场信息
		MyList<String> shortcutList = new MyList<String>(); //存储候车道信息
		//查找指定车牌号的car，将其后的car停入暂时的停车场,并且每次都更新停车场状态
		while(!parking.peek().getNumber().equals(number)){
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
			tempParking.push(parking.peek());
			parking.pop();
		}
		parkingList.add(generateStackJSON(parking));
		tempParkingList.add(generateStackJSON(tempParking));
		shortcutList.add(generateQueueJSON(shortcut));
		
		//计算停车时间和应缴费用
		float parkTime = (float) ((l_time.getTime() - parking.peek().getArrive_time().getTime())/1000.0/60.0);
		int cost = (int)(Constants.PARKING_FEE*parkTime);
		parking.pop();
		parkingList.add(generateStackJSON(parking));
		tempParkingList.add(generateStackJSON(tempParking));
		shortcutList.add(generateQueueJSON(shortcut));
		
		//将暂时停车场中的车辆再次停进车场
		while(!tempParking.isEmpty()){
			parking.push(tempParking.peek());
			tempParking.pop();
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
		}
		
		//判断候车道
		if(!shortcut.isEmpty()){
			shortcut.front().setArrive_time(l_time);
			parking.push(shortcut.front());
			shortcut.pop();
			parkingList.add(generateStackJSON(parking));
			tempParkingList.add(generateStackJSON(tempParking));
			shortcutList.add(generateQueueJSON(shortcut));
		}
		
		String JSONString = generateDynamicJSONString(parkingList, tempParkingList, shortcutList, parkTime, cost);

		return JSONString;
	}


	public void saveParking (String parkingInfo)  throws IOException {
		File fileOutput = new File("D:\\TourSystem\\TourSystem\\parking.txt");
		FileOutputStream fos = new FileOutputStream(fileOutput,false);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

		bw.write(parkingInfo);
		bw.newLine();
        bw.close();
	}

	public void saveShortcut (String shortcutInfo)  throws IOException {
        File fileOutput = new File("D:\\TourSystem\\TourSystem\\shortcut.txt");
        FileOutputStream fos = new FileOutputStream(fileOutput,false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write(shortcutInfo);
        bw.newLine();
        bw.close();
    }


	//对栈数据结构中的数据进行json格式化
	private String generateStackJSON(MyStack<Vehicle> myStack){
		Vehicle[] vehicles = myStack.getAll();
		List<Vehicle> list = new ArrayList<Vehicle>();
		for(int i = 0; i< vehicles.length; i++){
			if(vehicles[i] != null){
				list.add(vehicles[i]);
			}
		}
		
		return JSON.toJSONString(list);
	}

	//对队列数据结构中的数据进行json格式化
	private String generateQueueJSON(MyQueue myQueue){
		List<Vehicle> list = new ArrayList<>();
		for(int i = 0; i < myQueue.size(); i++){
			MyQueue.Node carNode = myQueue.getNode(i);
			list.add((Vehicle)carNode.data);
		}
		
		return JSON.toJSONString(list);
	}
	
	//对整个停车场车辆调动情况产生json结果
	private String generateDynamicJSONString(MyList<String> parkingList, MyList<String> tempParkingList,
											 MyList<String> shortcutList, float parkTime, double cost){
		String JSONString = "{" + "\"exist\":true,"
				+ "\"length\":" + parkingList.getSize() + ","
				+ "\"parking\":[";
		
		for(int i=0; i<parkingList.getSize()-1; i++){
			JSONString += parkingList.getData(i) + ",";
		}
		JSONString += parkingList.getData(parkingList.getSize()-1) + "],";
				
		JSONString += "\"tempParking\":[";
		
		for(int i=0; i<tempParkingList.getSize()-1; i++){
			JSONString += tempParkingList.getData(i) + ",";
		}
		JSONString += tempParkingList.getData(tempParkingList.getSize()-1) + "],";
				
		JSONString += "\"shortcut\":[";
		
		for(int i=0; i<shortcutList.getSize()-1; i++){
			JSONString += shortcutList.getData(i) + ",";
		}
		JSONString += shortcutList.getData(shortcutList.getSize()-1) + "],";
		
		JSONString += "\"parkTime\":" + parkTime + ",";
		JSONString += "\"cost\":" + cost + "}";
		
		return JSONString;
	}
}




















