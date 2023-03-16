package lessons.week9.pratice.dfsbfs.pratice4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 207. 课程表 ----争个解法
 * @author: bingyu
 * @date: 2023/3/15
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        int[][] prerequisites = new int[][]{
                {0,10}, {3,18}, {5,5},{6,11},{11,14},{13,1},{15,1},{17,4}
        };
        boolean result = zgSolved.canFinish(20, prerequisites);
        System.out.println(result);
    }


    /*
     争哥的解法是用的Kahn算法解决的，我是用的dfs
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer> adjs[] = new List[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            adjs[i] = new ArrayList<Integer>();
        }
        int[] indegrees = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            adjs[prerequisites[i][1]].add(prerequisites[i][0]);
            indegrees[prerequisites[i][0]]++;
        }
        LinkedList<Integer> zeroInDegrees = new LinkedList<>();
        for (int i = 0; i < indegrees.length; ++i) {
            if (indegrees[i] == 0) {
                zeroInDegrees.add(i);
            }
        }
        int zeroInDegreesCount = 0;
        while (!zeroInDegrees.isEmpty()) {
            int coursei = zeroInDegrees.remove();
            zeroInDegreesCount++;
            for (Integer coursej : adjs[coursei]) {
                indegrees[coursej]--;
                if (indegrees[coursej] == 0) {
                    zeroInDegrees.add(coursej);
                }
            }
        }
        return zeroInDegreesCount == numCourses;
    }


}
