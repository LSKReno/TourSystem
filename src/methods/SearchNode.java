package methods;

import algorithm.Search;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by LSK.Reno on 2019/1/14 22:25.
 */
public class SearchNode {
    public String searchNode(String keyWord, String searchType){
        //搜索关键字
//        String keyWord ;
//        String searchType;
        Search find = new Search();
        //根据关键字搜索
        try {
            List<Integer> searchNodes = find.searchArc(keyWord, searchType);
            return JSON.toJSONString(searchNodes);
        }
        catch (ArrayIndexOutOfBoundsException e){
            return "[]";
        }

//        return JSON.toJSONString(searchNodes);
    }

}
