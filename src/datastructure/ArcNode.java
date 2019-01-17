package datastructure;

public class ArcNode {
	private String name; //景点名称
	private String des; //景点描述
	private int popularity; //景点欢迎度
	private boolean hasRest; //有无休息区
	private boolean hasToilet; //有无公厕
	private MyList<VNode> vlist; // 景点的边
	
	public ArcNode(String name, String des, int popularity, boolean hasRest, boolean hasToilet) {
		this.name = name;
		this.des = des;
		this.popularity = popularity;
		this.hasRest = hasRest;
		this.hasToilet = hasToilet;
		this.vlist = new MyList<>();
	}
	public ArcNode(String name, String des, int popularity, boolean hasRest, boolean hasToilet, MyList vlist) {
		this.name = name;
		this.des = des;
		this.popularity = popularity;
		this.hasRest = hasRest;
		this.hasToilet = hasToilet;
		this.vlist = vlist;
	}
	public ArcNode(String name) {
		this.name = name;
		this.des = null;
		this.popularity = 0;
		this.hasRest = false;
		this.hasToilet = false;
		this.vlist = new MyList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public boolean isHasRest() {
		return hasRest;
	}

	public void setHasRest(boolean hasRest) {
		this.hasRest = hasRest;
	}

	public boolean isHasToilet() {
		return hasToilet;
	}

	public void setHasToilet(boolean hasToilet) {
		this.hasToilet = hasToilet;
	}

	public MyList<VNode> getVlist() {
		return vlist;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArcNode) {
			ArcNode arcNode= (ArcNode) obj;
			if (arcNode.getName() == null || name == null) {
				return false;
			}else{
				return name.equalsIgnoreCase(arcNode.getName());
			}
		}
		return false;
	}

	public ArcNode copy(){
		MyList<VNode> newList = new MyList<>();
		for(int i  = 0; i < this.vlist.getSize(); i++){
			newList.add(new VNode(vlist.getData(i).getIndex(), vlist.getData(i).getDist(),vlist.getData(i).getTime()));
		}
		ArcNode newNode = new ArcNode(this.name, this.des, this.popularity, this.hasRest,this.hasToilet,newList);
		return newNode;
	}
}
