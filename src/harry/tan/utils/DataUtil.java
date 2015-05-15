package harry.tan.utils;

import harry.tan.entity.Node;

import java.util.LinkedList;
import java.util.List;

public class DataUtil {
	static List<Node> nodes = null;
	static
	{
		nodes = new LinkedList<Node>();
		nodes.add(new Node("A","B",5));
		nodes.add(new Node("B","C",4));
		nodes.add(new Node("C","D",8));
		nodes.add(new Node("D","C",8));
		nodes.add(new Node("D","E",6));
		nodes.add(new Node("A","D",5));
		nodes.add(new Node("C","E",2));
		nodes.add(new Node("E","B",3));
		nodes.add(new Node("A","E",7));
	}
	
	public static List<Node> getDatas(){
		return nodes;
	}
	
	public static String[] getRoute(String routeStr){
		if(null != routeStr){
			return routeStr.split("-");
		}
		
		return new String[0];
	}
}
