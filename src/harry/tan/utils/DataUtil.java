package harry.tan.utils;

import harry.tan.entity.Node;
import harry.tan.entity.Node1;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DataUtil {
	static List<Node1> nodes = null;
	static
	{
		nodes = new LinkedList<Node1>();
		nodes.add(new Node1("A","B",5));
		nodes.add(new Node1("B","C",4));
		nodes.add(new Node1("C","D",8));
		nodes.add(new Node1("D","C",8));
		nodes.add(new Node1("D","E",6));
		nodes.add(new Node1("A","D",5));
		nodes.add(new Node1("C","E",2));
		nodes.add(new Node1("E","B",3));
		nodes.add(new Node1("A","E",7));
	}
	
	public static List<Node1> getDatas(){
		return nodes;
	}
	
	public static String[] getRoute(String routeStr){
		if(null != routeStr){
			return routeStr.split("-");
		}
		
		return new String[0];
	}
	
	public static void build(Set<Node> open){  
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
	    
	    A.getChild().put(B, 5);
	    A.getChild().put(D, 5);
	    A.getChild().put(E, 7);
	    
	    B.getChild().put(C, 4);
	    
	    C.getChild().put(D, 8);
	    C.getChild().put(E, 2);
	    
	    D.getChild().put(C, 8);
	    D.getChild().put(E, 6);
	    
	    E.getChild().put(B, 3);
	    
	    // 全部加载到open 里面
	    open.add(A);
	    open.add(B);
	    open.add(C);
	    open.add(D);
	    open.add(E);
    }
}
