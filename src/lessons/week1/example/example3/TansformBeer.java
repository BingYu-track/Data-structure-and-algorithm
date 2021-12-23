package lessons.week1.example.example3;

/**
 * @version 1.0
 * @Description: 例题：换啤酒(阿里钉钉22届暑期实习)
 * 现有x瓶啤酒，每3个空瓶子换一瓶啤酒，每7个瓶盖子也可以换一瓶啤酒，问最后可以喝多少瓶啤酒。
 *
 * @author: bingyu
 * @date: 2021/12/17
 */
public class TansformBeer {

    public static void main(String[] args) {
        int i = transformBeer(10);
        int drink = drink(10);
        System.out.println(i);
        System.out.println(drink);
    }

    /**
     * 10 10->
     */
    /** 错误解法：
     * 例：10瓶啤酒  10个空瓶 10个盖子->1瓶啤酒
     * @param count 初始的啤酒瓶数
     * @return
     */
    public static int transformBeer(int count) {
        int beerNum = count; //可喝的啤酒数
        int bottleNum = count; //空瓶数
        int lidNum = count; //瓶盖数
        while(bottleNum >= 3) { //空瓶数大于3,可喝的啤酒数增加1,瓶盖数增加1，空瓶数减3(换了一瓶酒)，还要再加1，因为换了一瓶酒
            beerNum ++;
            lidNum ++;
            bottleNum = bottleNum - 3 + 1;
        }
        while (lidNum >= 7) { //瓶盖数大于7，可喝的啤酒数增加1,瓶盖数增加1，瓶盖数减7，再加1
            beerNum ++;
            bottleNum ++;
            lidNum = lidNum - 7 + 1; //该方法有问题，因为这里用瓶盖换酒后，空瓶数也会增加，可能会导致空瓶数大于3
        }
        return beerNum;
    }


    //正确解法：
    public static int drink(int x) {
        int count = x; //喝了多少瓶
        int k1 = x; //有多少个空瓶子
        int k2 = x; //有多少个瓶盖
        while (k1 >=3 || k2>=7) { //空瓶换酒和瓶盖换酒，会对瓶盖和空瓶数量互相影响，因此还需要这个while循环包裹住
            while (k1 >= 3) {
                int change = k1/3; //空瓶换了多少瓶酒
                count += change;
                k1 %= 3; //换酒后剩下1空瓶数
                k1 += change; //增加换了酒的空瓶数
                k2 += change; //增加换了酒的瓶盖数
            }
            while (k2 >= 7) {
                int change = k2/7;
                count += change;
                k2 %= 7;
                k1 += change;
                k2 += change;
            }
        }
        return count;
    }


}
