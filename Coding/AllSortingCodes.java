import java.util.Arrays;
public class MySort{
    public static void main(String args[]){
        int[] arr1 = new int[]{8,7,6,5,4,3,2,1};
        int[] arr2 = new int[]{1,2,3,4,5,6,7,8};
        int[] arr3 = new int[]{2,8,7,1,3,5,6,4};

        heapSort(arr1);
        System.out.println(Arrays.toString(arr1));
        heapSort(arr2);
        System.out.println(Arrays.toString(arr2));
        heapSort(arr3);
        System.out.println(Arrays.toString(arr3));
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    //Bubble Sort : Worst, Avg. and Best Cases O(n2)
    public static void bubbleSort(int[] arr){
        for(int k=0; k<arr.length; k++){
            for(int i=0; i<=arr.length-2; i++){
                if(arr[i]>arr[i+1])
                    swap(arr,i,i+1);
            }
        }
    }
    //Selection Sort : Worst, Avg. and Best Cases O(n2)
    public static void selectionSort(int[] arr){
        for(int i=0; i<=arr.length-2; i++){
            int minj = i;
            int minx = arr[i];
            for(int j=i+1; j<=arr.length-1; j++){
                if(arr[j] < minx){
                    minj = j;
                    minx = arr[j];
                }
            }
            //swap
            arr[minj] = arr[i];
            arr[i] = minx;
        }
    }
    //Insertion Sort : Worst and Avg. O(n2) Best O(n)
    public static void insertionSort(int[] arr){
        for(int j=1; j<=arr.length-1; j++){
            int key = arr[j];
            int i = j-1;
            while(i>=0 && arr[i]>key){
                arr[i+1] = arr[i];
                i--;
            }
            arr[i+1] = key;
        }
    }
    //Quick Sort : Worst O(n2) Best and Avg. O(nLogn)
    public static void quickSort(int arr[], int p, int r){
        if(p<r){
            int q = partition(arr, p, r);//sorted pivot position
            quickSort(arr, p, q-1);
            quickSort(arr, q+1, r);
        }
    }
    public static int partition(int arr[], int p, int r){
        int x = arr[r]; //pivot element
        int i = p-1;
        for(int j=p; j<=r-1; j++){
            if(arr[j] <= x){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, r);
        return i+1;
    }
    //Merge Sort : All Cases O(nLogn)
    public static void mergeSort(int arr[], int p, int r){
        if(p<r){
            int q = (p+r)/2;
            mergeSort(arr, p, q);
            mergeSort(arr, q+1, r);
            merge(arr,p,q,r);
        }
    }
    public static void merge(int arr[], int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(int i=0; i<=n1-1; i++)
            L[i] = arr[p+i];
        for(int j=0; j<=n2-1; j++)
            R[j] = arr[q+1+j];
        int i=0;
        int j=0;
        int k=p;
        while(i<n1 && j<n2){
            if(L[i]<=R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }
        while(i<n1 && k<=r){
            arr[k++] = L[i++];
        }
        while(j<n2 && k<=r){
            arr[k++] = R[j++];
        }
    }
    //Heap Sort : All Cases O(nLogn)
    public static void heapSort(int[] arr){
        Heap A = new Heap(arr);
        buildMaxHeap(A);
        for(int i=A.length-1; i>=1; i--){
            // swap(arr, 0, i);
            int temp = A.arr[0];
            A.arr[0] = A.arr[i];
            A.arr[i] = temp;
            A.heapSize = A.heapSize - 1;
            maxHeapify(A, 0);//index-1
        }
    }
    static class Heap{
        public int[] arr;
        public int length;
        public int heapSize;
        Heap(int[] arr){
            this.arr = arr;
            this.length = arr.length;
            this.heapSize = arr.length;
        }
    }
    public static void buildMaxHeap(Heap A){
        // A.heapSize = A.length;
        for(int i=A.length/2-1; i>=0; i--){
            maxHeapify(A, i);
        }
    }
    public static void maxHeapify(Heap A, int i){
        int l = left(i+1)-1;//managing index
        int r = right(i+1)-1;//managing index
        int largest = 0;
        if(l<=A.heapSize-1 && A.arr[l]>A.arr[i])
            largest = l;
        else largest = i;
        if(r<=A.heapSize-1 && A.arr[r]>A.arr[largest])
            largest = r;
        if(largest != i){
            // swap(arr, i, largest);
            int temp = A.arr[i];
            A.arr[i] = A.arr[largest];
            A.arr[largest] = temp;
            maxHeapify(A, largest);
        }
    }
    public static int parent(int i){
        return i/2;
    }
    public static int left(int i){
        return 2*i;
    }
    public static int right(int i){
        return 2*i+1;
    }
}
