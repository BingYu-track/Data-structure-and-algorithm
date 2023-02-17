package lessons.week8.pratice.backtrack;

/**
 * @version 1.0
 * @Description: 回溯总结
 * @author: bingyu
 * @date: 2023/1/16
 */
public class Summary {


    /*
    回溯是重点，经常考到，也是DFS和动态规划的基础。因为回溯用到递归，入门很难。入门后就简单了，因为有模板。

    回溯的核心思想:
      1).回溯的处理过程是一个穷举的过程，枚举所有的解，找出其中满足期望的可行解。为了有规律地枚举所有可能的解，避免
         遗漏和重复，我们把问题求解的过程归纳为"多阶段决策模型"。每个阶段的决策会对应多个选择，从可选的选择列表中，
         任意选择一个，然后继续进行下一个阶段的决策。
      2).整个决策的过程，如果用图来形容表示，就是一棵"决策树"。回溯穷举所有解来查找可行解的过程，就是在"决策树中进行遍历的过程"。
         遍历过程中的"路径"就是解。
      3).回溯一般使用"递归来实现"，递归树就跟决策树完全一样。递的过程进行函数调用，对应到递归树上为从一个节点进入它的
         子节点，归的过程进行函数返回，对应到递归树上是从子节点返回上一层节点。

       TODO 路径: 也就是已经做出的选择
            选择列表：也就是你当前可以做的选择。
            其核心思想就是--for 循环里面的递归，在递归函数调用之前「做选择」，在递归函数调用之后「撤销选择」--这里撤销选择是指在路径中撤销

        #############回溯代码模板##############
        result = [] --这个是一组可行解
        def backtrack(可选列表，决策阶段，路径)
            if 满足结束条件(所有决策都已经完成或得到可行解)----------|
                if 路径为可行解: result.add(路径)    --------- TODO 这三行代码范围是回溯的终止条件
                return                            -----------|

            for 选择 in [可选列表]:-----------------------|
                # 做选择                                 |
                路径.add(选择)                            |
                backtrack(可选列表,决策阶段+1，路径)------ 这几行代码范围是做决策的
                # 撤销选择                                |
                路径.remove(选择)-------------------------|

        TODO: 回溯做题总结，
            1.决策树的深度由阶段k决定，也与终止条件息息相关，决定纵向遍历的深度；
            2.可选列表决定每层的选择次数，即决策树的横向遍历，以for循环的形式体现!
            3.注意：当回溯算法找到一个可行解时一定要用成员变量进行保存，然后return后会撤销覆盖原来的解的值，从而继续寻找其它的可行解，
              如果可行解提示了只有一个，那么我们就在撤销前用变量设法控制即可。
            4.回溯一般是用来解决类似穷举，等问题。
            5.在回溯题中常常会遇到去重的问题，例如组合不能重复，或者组合不能重复但是元素可以重复，组合和元素都不能重复
              (1).组合不能重复的解决方法是(集合里没有重复元素)----集合里没有重复元素，那么只需要每次横向遍历时控制开始遍历的位置，前提是集合里没有重复的元素
                  从而去除重复组合，见pratice9里的题目代码及其详解！
              (2).组合里的元素可以重复使用，但是组合不能重复(集合里没有重复元素)----使组合里的元素可以重复，这种就需要在每次递归时做文章，具体见pratice9里的题目代码及其详解！
              (3).组合里的元素不能重复，且组合也不能重复，而且元素只能使用一次(集合里有重复元素)----这个条件就比较多且麻烦了！这里就不是单纯去除重复组合了
                  而是要去除同一树层上的元素了!，具体看下面
            所谓去重，其实就是使用过的元素不能重复选取。 这么一说好像很简单！都知道组合问题可以抽象为树形结构，那么“使用过”在这个树形结构上是有两个维度的，
            a.一个维度是同一树枝上使用过，
            b.一个维度是同一树层上使用过。没有理解这两个层面上的“使用过” 是造成大家没有彻底理解去重的根本原因。
            那么问题来了，我们是要同一树层上使用过，还是同一树枝上使用过呢？
            回看一下"组合总和II"的题目，元素在同一个组合内是可以重复的，怎么重复都没事，但两个组合不能相同。所以我们要去重的是同一树层上的“使用过”，同一树枝上的都是一个
            组合里的元素，不用去重。为了理解去重我们来举一个例子，candidates = [1, 1, 2], target = 3，（方便起见candidates已经排序了）
                                              [1,1,2]
                                            /    |      \
                                           1     1       2
                                         /  \   /  \    /  \
                                       1,1 1,2 1,1 1,2 2,1 2,1    ----------同一树层上的重复就是组合间的重复，组合内是可以重复的
            强调一下，要对树层去重的话，前提是先需要对数组进行排序！
              1.同一树层上元素的去重，需要先进行排序；如何判断同一树层上元素（相同的元素）是否使用过了呢?
                额外使用bool型数组used，用来记录同一树枝上的元素是否使用过
                如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，就说明：前一个树枝，使用了candidates[i - 1]，
                也就是说同一树层使用过candidates[i - 1]，此时for循环里就应该做continue的操作。
              2.同一树枝上的去重，同一树枝上的元素都是一个组合里的元素
                used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
                used[i - 1] == false，说明同一树层candidates[i - 1]使用过
                可能有的录友想，为什么 used[i - 1] == false 就是同一树层呢，因为同一树层，used[i - 1] == false 才能表示，
                当前取的 candidates[i] 是从 candidates[i - 1] 回溯而来的。而used[i - 1] == true，说明是进入下一层递归，去下一个数，所以是树枝上
    */


}
