package harry.tan.entity;

import java.util.HashMap;
import java.util.Map;

public class Node {
    
    // 节点名称
    private String name;  
    
    //子节点，key为Node对象
    private Map<Node,Integer> child=new HashMap<Node,Integer>();  
    public Node(String name){  
        this.name=name;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public Map<Node, Integer> getChild() {  
        return child;  
    }  
    public void setChild(Map<Node, Integer> child) {  
        this.child = child;  
    }  
}
