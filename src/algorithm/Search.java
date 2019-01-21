package algorithm;

import java.util.*;

import datastructure.Critical;
import datastructure.Graph;
import datastructure.ArcNode;

/**
 * Created by LSK.Reno on 2018/01/14.
 */

public class Search {
    private Graph graph = Critical.getGraph();


    //根据搜索关键字搜索匹配景点
    public List<Integer> searchArc(String keyWord, String searchType){
        List<Integer> searchNodes = new ArrayList<>();
        for(int i=0; i<graph.getArcNum(); i++){
            String nameDoc = graph.getNodes().getData(i).getName();
            String desDoc = graph.getNodes().getData(i).getDes();
            //对景点名称和景点描述进行KMP匹配查询
            if(searchType.equals("KMP")){
                if(KMP(nameDoc, keyWord) || KMP(desDoc, keyWord)){
                    searchNodes.add(i);
                }
            }
            else if(searchType.equals("Boyer-Moore")){
                if(boyerMoore(nameDoc, keyWord) || boyerMoore(desDoc, keyWord)){
                    searchNodes.add(i);
                }
            }

        }

        return searchNodes;
    }

    private boolean KMP(String doc, String keyword){
        int[] next = calculateK(keyword);

        int i = 0, j = 0;
        while(i < doc.length() && j < keyword.length()){
            // 如果j = -1，或者当前字符匹配成功，都令i++，j++
            if(j==-1 || doc.charAt(i)==keyword.charAt(j)){
                i++;
                j++;

            }else{
                //如果j != -1，且当前字符匹配失败，则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }

        if(j == keyword.length()){
            return true;
        }
        return false;
    }
//ababa  -1 0 0 1 2
    public int[] calculateK(String keyword){
        int[] next = new int[keyword.length()];
        next[0] = -1;
        int k = -1;
        int j = 0;

        while (j < keyword.length() - 1)
        {
            //k表示前缀，j表示后缀
            if (k == -1 || keyword.charAt(j) == keyword.charAt(k))
            {
                ++k;
                ++j;
                next[j] = k;
            }
            else
            {
                k = next[k];
            }
        }
        return next;
    }

    // Boyer- Moore 字符串匹配算法
    private boolean boyerMoore(String doc, String keyword){
        Map<Character, Integer> right = new HashMap<>();
        int keyLength = keyword.length();
        int docLength = doc.length();
        int skip ;

        //初始化right

        for(int i = 0; i < keyLength; i++){
            right.put(keyword.charAt(i), i);
        }

        //匹配
        for(int i = 0; i <= docLength - keyLength; i++){
            skip = 0;
            for(int j = keyLength-1; j >= 0 ; j++){
                if(doc.charAt(i+j) != keyword.charAt(j)){
                    if(right.containsKey(doc.charAt(i+j)))
                        skip = j - right.get(doc.charAt(i+j));
                    if(skip < 1)
                        skip = 1;
                    break;
                }
                if(skip == 0){
                    return true;
                }
            }
        }
        return  false;
    }

}
