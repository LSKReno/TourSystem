package algorithm;

import datastructure.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Prim
{
    private Graph graph = Critical.getGraph();

    public List<VData> prim(String start){
        List<VData> results = new ArrayList<>(); //用于保存结果最小生成树的边
        int[] lowCost = new int[graph.getArcNum()];  //到新集合的最小权
        int[] pre= new int[graph.getArcNum()];//存取前驱结点
        int i, j, min, minIndex , sum = 0;
        int startIndex= getPos(start);

        if (startIndex == -1){
            return null;
        }

        //初始化辅助数组
        for(i=0;i<graph.getArcNum();i++)
        {
            lowCost[i]=getLength(startIndex,i);
            pre[i]=startIndex;
        }
        //一共需要加入n-1个点
        for(i=1;i<graph.getArcNum();i++)
        {
            min=9999;
            minIndex=-1;
            //每次找到距离集合最近的点
            for(j=0;j<graph.getArcNum();j++)
            {
                if(lowCost[j]!=0&&lowCost[j]<min && j != startIndex)
                {
                    min=lowCost[j];
                    minIndex=j;
                }
            }

            results.add(new VData(pre[minIndex], minIndex,min ));
            lowCost[minIndex]=0;
            sum += min;

            //加入该点后，更新其它点到集合的距离
            for(j=0;j<graph.getArcNum();j++)
            {
                if(lowCost[j]!=0 && lowCost[j]>getLength(minIndex, j))
                {
                    lowCost[j]=getLength(minIndex, j);
                    pre[j]=minIndex;
                }
            }
        }
        return results;
    }

    //根据景点名称寻找景点的位置

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


    // 根据起始点位置和终止点位置得到两点间路径长度
    private int getLength(int fromIndex, int toIndex){
        MyList<VNode> list = graph.getNodes().getData(fromIndex).getVlist();
        int length = Constants.INFINITY;

        for (int i = 0; i < list.getSize(); i++) {
            if (list.getData(i).getIndex() == toIndex) {
                length = list.getData(i).getDist();
                break;
            }
        }
        return length;
    }


}
