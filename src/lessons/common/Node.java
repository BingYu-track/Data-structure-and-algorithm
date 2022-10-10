package lessons.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/10/10
 */
public class Node {

    public int val;
    public List<Node> children = new ArrayList<>();

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                ", children=" + children +
                '}';
    }
}
