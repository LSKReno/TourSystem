package algorithm;

import java.io.*;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

import datastructure.*;

//该类读取文件中景点和路的所有信息，用来构建图和输出图的信息
public class CreateNewGraph {
	private Graph graph;
	private BufferedReader reader;
	private BufferedReader readPos;

//	String filename = "D:\\Vehicle Hiring System\\src\\Vehicle_Hiring_System\\Vehicles.txt";
//	File file = new File(filename);
//	BufferedReader reader = new BufferedReader(new FileReader(file));
//	String temp;
//	int i = 1;
//            while((temp = reader.readLine()) != null) {
//		if(i >= 2 ){
//			String type = temp.split(",")[0];
//			String hireStatus = temp.split(",")[5];
//			if (type.equals("Car") && hireStatus.equals("Available")){
//				String registrationID = temp.split(",")[1];
//				String modelName = temp.split(",")[2];
//				String yearOfRegistration = temp.split(",")[3];
//				String fuelType = temp.split(",")[4];
//				String engineCapacity = temp.split(",")[6];
//
//				SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd-HH:mm:ss");
//				String return_time = temp.split(",")[7];
//
//				Vehicle car = new Car(registrationID,modelName , yearOfRegistration,
//						fuelType ,hireStatus , Double.parseDouble(engineCapacity ),return_time);
//				availableVehicleList.add(car);
//			}
//			else if(type.equals("Van") && hireStatus.equals("Available")){
//				String registrationID = temp.split(",")[1];
//				String modelName = temp.split(",")[2];
//				String yearOfRegistration = temp.split(",")[3];
//				String fuelType = temp.split(",")[4];
//				String maxLoadCapacity = temp.split(",")[6];
//
//				SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd-HH:mm:ss");
//				String return_time = temp.split(",")[7];
//
//				Vehicle van = new Van(registrationID,modelName , yearOfRegistration,
//						fuelType ,hireStatus , Double.parseDouble(maxLoadCapacity),return_time);
//				availableVehicleList.add(van);
//			}
//		}
//
//		i++;
//	}
//            reader.close();
//}
	public CreateNewGraph(int arcNum, int vetNum) {
		Critical.setGraph(arcNum, vetNum);
		graph = Critical.getGraph();
		try {
			File file = new File("D:\\TourSystem\\TourSystem\\Info.txt");
//			reader = new Scanner(file);
            reader = new BufferedReader(new FileReader(file));

			File filePos = new File("D:\\TourSystem\\TourSystem\\position.txt");
			readPos = new BufferedReader(new FileReader(filePos));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//该函数用来读取文件中景点和边的信息，并用邻接链表来存储图
	public void createGraph() throws IOException {
		int arcNum, vetNum;
		String tmp, infos[];
		String name, des;
		int pop;
		boolean hasRest, hasToilet;
		String from, to;
		int dis, time;

		//读取文件中景点的信息描述
		int count = 0;
//            while((tmp = reader.readLine()) != null) {
		while ((tmp = reader.readLine()) != null) {
//			tmp = reader.nextLine();
			if (tmp.equals(""))
				break;
			infos = tmp.split(" ");
			name = infos[0];
			des = infos[1];
			pop = Integer.parseInt(infos[2]);
			hasRest = (infos[3].equals("1"));
			hasToilet = (infos[4].equals("1"));
			// 向图中的nodes列表中添加景点node
			graph.getNodes().add(new ArcNode(name, des, pop, hasRest, hasToilet));
			count++;
		}
		graph.setArcNum(count);
		count = 0;

		//读取文件中路的信息
		while ((tmp = reader.readLine()) != null) {
//			tmp = reader.nextLine();
//            System.out.println(tmp);
			infos = tmp.split(" ");
//            System.out.println(Arrays.toString(infos));
			from = infos[0];
			to = infos[1];
			dis = Integer.parseInt(infos[2]);
			time = Integer.parseInt(infos[3]);

			int toIndex = graph.getNodes().getIndex(new ArcNode(to));
			int fromIndex = graph.getNodes().getIndex(new ArcNode(from));

			VNode node1 = new VNode(toIndex, dis, time);
			graph.getNodes().getData(fromIndex).getVlist().add(node1);
			VNode node2 = new VNode(fromIndex, dis, time);
			graph.getNodes().getData(toIndex).getVlist().add(node2);
			count++;
		}
		graph.setRoadNum(count);

		//读取位置信息
//		tmp = readPos.nextLine();
        tmp = readPos.readLine();
		MyList<Integer> xPos = new MyList<>();
		infos = tmp.split(" ");

		for(int i = 0; i < infos.length; i++){
			int temp = Integer.parseInt(infos[i]);
			xPos.add(temp);
		}

//		tmp = readPos.nextLine();
        tmp = readPos.readLine();
        MyList<Integer> yPos = new MyList<>();
		infos = tmp.split(" ");

		for(int i = 0; i < infos.length; i++){
			int temp = Integer.parseInt(infos[i]);
			yPos.add(temp);
		}

		Position.setxPos(xPos);
		Position.setyPos(yPos);

		readPos.close();
		reader.close();

	}


	public Graph getGraph(){
		return graph;
	}



}
