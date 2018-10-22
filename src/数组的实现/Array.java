package 数组的实现;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 数组插入删除方法实现
 * @author: hxw
 * @date: 2018/10/21 21:07
 */
public class Array {

    //定义整型数据data保存数据
    public int[] data;
    //定义数组长度
    private int length;
    //定义中实际个数
    private int count;

    //构造方法，定义数组大小
    public Array(int length){
        data = new int[length];
        this.length = length;
        count = 0;
    }

    //根据索引，找到数据中的元素并返回
    public int find(int index){
        if(index>=count || index<0){
            return -1;
        }
        return data[index];
    }

    /**
     *
     * @param index 当前要插入的位置
     * @param element 要插入的元素
     * @return
     */
    public boolean insert(int index,int element){
        //如果当前实际的元素与设定的数组长度相同
        if(count==length){
            System.out.println("空间已满,没有可插入的位置");
            return false;
        }
        // 如果count还没满，那么就可以插入数据到数组中
        // 插入的位置不合法(如果索引大于实际的数目也不合法)
        if (index < 0 || index > count ) {
            System.out.println("位置不合法");
            return false;
        }
        /*for (int i=0; i<length-index;i++) { //这是从当前插入的开始移动，明显算法有错
            data[index+1] = data[index];
        }*/
        // 执行到这里说明位置合法，先将index到后面的元素都向后移动一位,从最后面的元素开始移动
        for( int i = count; i > index; i--){
            data[i] = data[i - 1];
        }
        //再插入元素
        data[index] = element;
        count++;
        return true;
    }

    //根据索引，删除数组中元素
    public boolean delete(int index){
        // 删除的位置不合法
        if (index < 0 || index >= count ) {
            System.out.println("位置不合法");
            return false;
        }
        //将index到后面的元素都向前移动一位
        for(int i=index; i<count-1;i++){
            data[i] = data[i+1];
        }
       /* for (int i=index+1; i<count; ++i){
            data[i-1] = data[i];
        }*/
        //删除数组末尾元素，再创建一个新的数组来满足删除后
        /*int[] arr = new int[count-1];
        for (int i=0; i<count-1;i++){
            arr[i] = data[i];
        }
        this.data = arr;*/
        count--;
        return true;
    }

    public void printAll(){
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Array array = new Array(6);
        array.printAll();
        array.insert(0, 3);
        array.insert(1, 5);
        array.insert(2,4);
        array.insert(3, 9);
        array.insert(3, 10);
        array.insert(2,7);
        array.printAll();
        //array.delete(0);
        array.delete(2);
        array.printAll();
    }
}
