package algorithm;

/**
 * Created by xiangwuzhu on 2018/11/24.
 */
public class Main {


    public static void main(String[] args) {

        int[] source = new int[]{6, 34, 2, 7, 3, 8, 10, 9, 3};
        System.out.print("排序前:");
        printArray(source);
        System.out.println("");


        BinarySort(source, source.length);


        System.out.println("--开始排序--");

        System.out.print("排序后:");
        printArray(source);

    }

    //二分插入排序
    public static void advanceInsertSortWithBinarySearch(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int low = 0, high = i - 1;
            int mid = -1;
            while (low <= high) {
                mid = low + (high - low) / 2;
                if (arr[mid] > temp) {
                    high = mid - 1;
                } else { // 元素相同时，也插入在后面的位置
                    low = mid + 1;
                }
            }
            for (int j = i - 1; j >= low; j--) {
                arr[j + 1] = arr[j];
            }
            arr[low] = temp;
        }
    }


    public static int Binary_Search(int[] arr, int len, int key) {
        int low = 0;
        int high = len;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > key) {
                high = mid - 1;
            } else
                low = mid + 1;
        }
        return low;
    }

    //将数组分为俩个区域
    //有序区  0 -- （i-1）  --> 初始有一个元素
    //无序区  i -- (len-1)
    public static void BinarySort(int[] arr, int len) {

        for (int i = 1; i < len; i++) {

            int tmp = arr[i];
            int index = Binary_Search(arr, i - 1, tmp);
            for (int j = i; j > index; j--) {
                arr[j] = arr[j - 1];
            }

            arr[index] = tmp;
        }
    }



    //选择排序
    public static void selectionSort(int[] src) {
        for (int i = 0; i < src.length - 1; i++) {
            int key = i;
            for (int j = i + 1; j < src.length; j++) {
                if (src[j] < src[key]) key = j;
            }
            if (key != i) swap(src, i, key);
        }
    }


    //冒泡
    public static void bubbleSort(int[] src) {

        for (int i = src.length - 1; i >= 0; --i) {
            for (int j = 0; j < i; j++) {
                if (src[j] > src[j + 1]) {
                    swap(src, j, j + 1);
                }
            }
        }

    }


    //shell排序 - 插入排序的变种

    /**
     * **增量d 的范围： **1<= d < 待排序数组的长度 （d 需为 int 值）
     **增量的取值： **一般的初次取序列（数组）的一半为增量，以后每次减半，直到增量为1。
     第一个增量=数组的长度/2,
     第二个增量= 第一个增量/2,
     第三个增量=第二个增量/2,
     以此类推，最后一个增量=1。

     步长：N/2^i 另一篇

     https://www.jianshu.com/p/d730ae586cf3

     * @param arr
     */
    public static void shellInsertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //希尔排序  升序
        for (int d = arr.length / 2; d > 0; d /= 2) { //d：增量  7   3   1
            for (int i = d; i < arr.length; i++) {
                //i:代表即将插入的元素角标，作为每一组比较数据的最后一个元素角标
                //j:代表与i同一组的数组元素角标
                for (int j = i - d; j >= 0; j -= d) { //在此处-d 为了避免下面数组角标越界
                    if (arr[j] > arr[j + d]) {// j+d 代表即将插入的元素所在的角标
                        //符合条件，插入元素（交换位置）
                        int temp = arr[j];
                        arr[j] = arr[j + d];
                        arr[j + d] = temp;
                    }
                }
            }
        }
    }



    //简单插入排序
    public static void insertSort(int[] src) {
        //前置判断

        int value = 0;
        int index = 1;
        for (int i = 1; i < src.length; i++) {
            value = src[i];
            index = i;

            while (index >= 1 && src[index - 1] > value) {
                src[index] = src[index - 1];
                index--;
            }
            src[index] = value;
        }

    }


    //快速排序
    public static void quickSort(int[] src, int left, int right) {

        if (left >= right) {
            return;
        }

        int low = left;
        int high = right;
        int key = src[low];

        while (low < high) {

            while (high > low && src[high] >= key) {
                high--;
            }
            src[low] = src[high];

            while (low < high && src[low] <= key) {
                low++;
            }
            src[high] = src[low];
        }

        /*当在当组内找完一遍以后就把中间数key回归*/
        src[low] = key;

        quickSort(src, left, low - 1);

        quickSort(src, low + 1, right);

    }


    public static void swap(int[] src, int left, int high) {
        int temp = src[left];
        src[left] = src[high];
        src[high] = temp;

    }


    public static void printArray(int[] src) {
        if (src == null || src.length == 0) return;

        for (int i = 0; i < src.length; i++) {
            if (i == 0) {
                System.out.print(src[i]);
            } else {
                System.out.print(" ," + src[i]);
            }

        }
    }


}
