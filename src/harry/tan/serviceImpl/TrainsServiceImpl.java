package harry.tan.serviceImpl;

import harry.tan.entity.Node;
import harry.tan.entity.Node1;
import harry.tan.utils.DataUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrainsServiceImpl {

    private Set<Node>            openNode  = new HashSet<Node>();
    private Set<Node>            closeNode = new HashSet<Node>();

    // 不断更新存放输入点到各个顶点的距离
    private Map<String, Integer> path      = new HashMap<String, Integer>();

    // 封装完整的路劲信息
    private Map<String, String>  pathInfo  = new HashMap<String, String>();



    public int getDistance(String str) {
        List<Node1> nodes = DataUtil.getDatas();
        String[] node = DataUtil.getRoute(str);
        int distances = 0;

        // 表示路径不通,默认表示通，为true
        boolean flag = true;
        for (int i = 0; i < node.length - 1; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Node1 n = nodes.get(j);
                if (node[i].equals(n.getStart()) && node[i + 1].equals(n.getEnd())) {
                    distances += n.getDistance();
                    break;
                }

                if (j == nodes.size() - 1) {
                    flag = false;
                }
            }
        }

        if (!flag) {
            distances = 0;
        }

        return distances;
    }



    public int getShortDistance(final String start, final String end) {

        List<Node1> nodes = DataUtil.getDatas();
        List<Integer> distances = new ArrayList<Integer>();
        int distance = 0;

        // 保存上一个节点标识
        Node1 beforeNode = null;
        for (int i = 0; i < nodes.size(); i++) {

            Node1 node = nodes.get(i);

            // 查询到第一个可用点
            if (node.getStart().equals(start)) {
                if (node.getEnd().equals(end)) {
                    // 找到第一条路径
                    distance = node.getDistance();
                    distances.add(distance);

                    // 保存上一个节点
                    beforeNode = node;

                    // 继续寻找需要几个点连接起来的情况
                    continue;
                }
            } else {
                beforeNode = node;
                for (int j = 0; j < nodes.size(); j++) {
                    Node1 n = nodes.get(j);

                    // 两个节点
                    if (beforeNode.getEnd().equals(n.getStart()) && n.getEnd().equals(end)) {
                        distance += n.getDistance();
                        break;
                    } else {

                        // 继续匹配
                        continue;
                    }
                }
            }
        }
        return 0;
    }



    // 随便输入两个城市求最短距离
    public void gitShortestDistance(final String start, final String end) {

        // 构建数据
        DataUtil.build(this.openNode);

        // 找出起始点信息
        Iterator<Node> iter = this.openNode.iterator();
        Node startNode = null;
        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.getName().equals(start)) {
                startNode = node;
                this.closeNode.add(node);
                iter.remove();
                break;

            }
        }

        // 基础路径信息
        for (Node node : startNode.getChild().keySet()) {
            path.put(node.getName(), startNode.getChild().get(node));
            pathInfo.put(node.getName(), startNode.getName() + "-" + node.getName());
        }

    }



    /**
     * 递归实现遍历每一个节点并且记录节点的最短距离，并且记录轨迹 <method description>
     * 
     */
    public void computeCore() {

    }



    /**
     * 
     * <method description> 获取一个子节点
     * 
     * @param node
     * @return
     */
    public Node getChildShortestNode(Node node) {
        Node resut = null;
        Map<Node, Integer> childs = node.getChild();
        Integer temp = Integer.MAX_VALUE;
        for (Node child : childs.keySet()) {
            if (openNode.contains(child)) {
                int distance = childs.get(child);
                if (distance < temp) {
                    temp = distance;
                    resut = child;
                }
            }
        }

        return resut;
    }
}
