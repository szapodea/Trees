import javax.naming.PartialResultException;
import java.util.Arrays;

public class SortMerge {

    public static void sort(int[] arr) {
        Queue queue = new Queue();
        if (arr.length == 0) {
            return;
        }

        int index = 1;
        while (index < arr.length) {
            int count  = findSubArrays(queue, arr, index);
            index = count + 1;
        }
        int front = (int) queue.dequeue();
        int middle = (int) queue.dequeue();
        if (front == 0 && middle == arr.length - 1) {
            return;
        }
        boolean first = true;
        while (!queue.isEmpty()) {
            if (first) {
                first = false;
            } else {
                front = (int) queue.dequeue();
                middle = (int) queue.dequeue();
            }
            int middle2 = (int) queue.dequeue();
            int back = (int) queue.dequeue();
            int[] newArr = sort(arr, front, middle, middle2,  back);
            for (int i = 0; i < newArr.length; i++) {
                arr[i + front] = newArr[i];
            }
            if (newArr.length == arr.length) {
                return;
            } else {
                while (!queue.isEmpty()) {
                    queue.dequeue();
                }
                sort(arr);
            }
        }
        sort(arr);
    }

    public static int findSubArrays(Queue queue, int[] arr, int start) {
        if (start == 1) {
            queue.enqueue(0);
        } else {
            queue.enqueue(start - 1);
        }
        int index = start - 1;
        for (int i = start; i < arr.length ; i++) {
            if (i + 1 == arr.length) {
                queue.enqueue(arr.length - 1);
                return arr.length;
            }
            if (arr[index] > arr[i]) {
                queue.enqueue(index);
                return i;
            } else {
                index++;
            }
        }
        queue.enqueue(arr.length - 1);
        return arr.length;
    }

    public static int[] sort(int[] arr, int front, int mid1, int mid2, int back) {
        int[] arr1 = Arrays.copyOfRange(arr, front, mid1 + 1);
        int[] arr2 = Arrays.copyOfRange(arr, mid2, back + 1);
        int[] newArr = new int[back - front + 1];
        int index = 0;
        int indexArr1 = 0;
        int indexArr2 = 0;
        while (indexArr1 < arr1.length || indexArr2 < arr2.length) {
            if (indexArr1 == arr1.length && arr2.length == indexArr2) {
                return newArr;
            }
            if (indexArr1 != arr1.length && arr2.length == indexArr2) {
                newArr[index] = arr1[indexArr1];
                index ++;
                indexArr1++;
            } else if (indexArr1 == arr1.length && arr2.length != indexArr2) {
                newArr[index] = arr2[indexArr2];
                index++;
                indexArr2++;
            } else {
                if (arr1[indexArr1] == arr2[indexArr2]) {
                    newArr[index] = arr1[indexArr1];
                    index++;
                    indexArr1++;
                } else if (arr1[indexArr1] < arr2[indexArr2]) {
                    newArr[index] = arr1[indexArr1];
                    index++;
                    indexArr1++;
                } else {
                    newArr[index] = arr2[indexArr2];
                    index++;
                    indexArr2++;
                }
            }
        }
        return newArr;
    }
    
}
	