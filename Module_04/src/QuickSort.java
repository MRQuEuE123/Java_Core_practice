import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 5, 6, 7, 8, 9,3,22,3,222,11,22332,11, 10, 11, 33, 22, 11, 223, 33, 22, 11, 22, 3, 33, 22, 11, 223, 33};
        System.out.println(Arrays.toString(arr));
        quicksort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quicksort(int[] arr,int leftIndex,int rightIndex) {
        if(arr.length==0|| leftIndex>=rightIndex )return;

        int pivot = arr[(leftIndex+rightIndex)/2];
        int leftMarkerIndex=leftIndex;
        int rightMarkerIndex=rightIndex;

        while(leftMarkerIndex<=rightMarkerIndex){

            while(arr[leftMarkerIndex]< pivot)leftMarkerIndex++;
            while(arr[rightMarkerIndex]>pivot)rightMarkerIndex--;
            if(leftMarkerIndex<=rightMarkerIndex){
                int swap = arr[leftMarkerIndex];
                arr[leftMarkerIndex] = arr[rightMarkerIndex];
                arr[rightMarkerIndex] = swap;

                leftMarkerIndex++;
                rightMarkerIndex--;
            }

        }

        if(leftIndex<rightMarkerIndex) quicksort(arr,leftIndex,rightMarkerIndex);
        if(rightIndex>leftMarkerIndex) quicksort(arr,leftMarkerIndex,rightIndex);



    }
}
