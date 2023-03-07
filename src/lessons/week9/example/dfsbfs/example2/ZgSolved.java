package lessons.week9.example.dfsbfs.example2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2023/2/24
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int lessTimes = zgSolved.openLock(deadends, target);
        System.out.println(lessTimes);
    }

    /*
      争哥解法:
     */
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadset = new HashSet();
        for (String d: deadends) {
            deadset.add(d);
        }
        if (deadset.contains("0000")) return -1;
        Queue<String> queue = new LinkedList();
        Set<String> visited = new HashSet();
        queue.offer("0000");
        visited.add("0000");
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int k = 0;
            while (k < size) {
                String node = queue.poll();
                k++;
                if (node.equals(target)) {
                    return depth;
                }
                List<String> newnodes = genNewNode(node);
                for (String newnode : newnodes) {
                    if (visited.contains(newnode)
                            || deadset.contains(newnode)) {
                        continue;
                    }
                    queue.add(newnode);
                    visited.add(newnode);
                }
            }
            depth++;
        }
        return -1;
    }

    private List<String> genNewNode(String node) {
        List<String> newnodes = new ArrayList<>();
        int[] change = {-1, 1};
        for (int i = 0; i < 4; ++i) {
            for (int k = 0; k < 2; ++k) {
                char[] newNode = new char[4];
                for (int j = 0; j < i; ++j) {
                    newNode[j] = node.charAt(j);
                }
                for (int j = i+1; j < 4; ++j) {
                    newNode[j] = node.charAt(j);
                }
                String newC = (((node.charAt(i)-'0') + change[k] + 10) % 10) + "";
                newNode[i] = newC.charAt(0);
                newnodes.add(new String(newNode));
            }
        }
        return newnodes;
    }
}
