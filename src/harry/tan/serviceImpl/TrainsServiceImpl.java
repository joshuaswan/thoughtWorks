package harry.tan.serviceImpl;

import java.util.List;

import harry.tan.entity.Node;
import harry.tan.utils.DataUtil;

public class TrainsServiceImpl {

	public int getDistance(String str){
		List<Node> nodes = DataUtil.getDatas();
		String[] node = DataUtil.getRoute(str);
		int distances = 0;
		
		// 表示路径不通,默认表示通，为true
		boolean flag = true;
		for(int i=0;i<node.length-1;i++){
			for(int j=0;j<nodes.size();j++){
				Node n = nodes.get(j);
				if(node[i].equals(n.getStart()) && node[i+1].equals(n.getEnd())){
					distances += n.getDistance();
					break;
				}
				
				if(j == nodes.size()-1){
					flag = false;
				}
			}
		}
		
		if(!flag){
			distances = 0;
		}
		
		return distances;
	}
}
