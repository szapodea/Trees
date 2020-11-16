public class SortGrid {
    private static int compares = 0;
    private static int[][] grid;

    // PUBLIC METHODS
    /*
     * This method uses the topdown mergesort method from the book as inspiration to make it 2d
     */
    public static int sort(int[][] thisGrid) {
        compares = 0;
        grid = new int[thisGrid.length][thisGrid.length];
        for (int i = 0; i < thisGrid.length; i++) {
            sort(thisGrid, 0, thisGrid.length - 1, i);
        }
        sortArr(thisGrid, 0, thisGrid.length - 1);
        return compares;
    }

    private static void sort(int[][] arr, int low, int high, int index) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(arr, low, mid, index);
        sort(arr, mid+1, high, index);
        merge(arr, low, mid, high, index);
    }

    private static void merge(int[][] arr, int low, int mid, int high, int index) {
        int index1 = low;
        int index2 = mid + 1;
        for (int i = low; i <= high; i++) {
            grid[index][i] = arr[index][i];
        }
        for (int i = low; i <= high; i++) {
            if ((index1) > mid) {
                arr[index][i] = grid[index][index2++];
            } else if  (index2 > high){
                arr[index][i] = grid[index][index1++];
            } else if (lessThan(index, index2, index, index1)) {
                arr[index][i] = grid[index][index2++];
            } else {
                arr[index][i] = grid[index][index1++];
            }
        }
    }

    private static void sortArr(int[][] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        int mid = low + (high - low) / 2;

        sortArr(arr, low, mid);
        sortArr(arr, mid + 1, high);
        merge2d(arr, low, mid, high);

    }

    private static void merge2d(int[][] arr, int low, int mid, int high) {
        int index1 = low;
        int index2 = mid + 1;
        int loc1 = 0;
        int loc2 = 0;
        for (int i = low; i <= high; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                grid[i][j] = arr[i][j];
            }
        }
        for (int i = low; i <= high; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (loc1 >= arr.length) {
                    loc1 = 0;
                    index1++;
                }
                if (loc2 >= arr.length) {
                    loc2 = 0;
                    index2++;
                }
                if ((index1) > mid) {
                    arr[i][j] = grid[index2][loc2++];
                } else if  (index2 > high){
                    arr[i][j] = grid[index1][loc1++];
                } else if (lessThan(index2, loc2, index1, loc1)) {
                    arr[i][j] = grid[index2][loc2++];
                } else {
                    arr[i][j] = grid[index1][loc1++];
                }
            }
        }
    }

    //  HELPER METHODS 
    // returns true if value at (r1, c1) is less
    // than value at (r2, c2) and false otherwise;
    // counts as 1 compare
    private static boolean lessThan(int r1, int c1, int r2, int c2) {
        compares++;

        if (grid[r1][c1] < grid[r2][c2])
            return true;

        return false;
    }

    // returns true if value at (r1, c1) is greater than
    // value at (r2, c2) and false otherwise;
    // counts as 1 compare
    private static boolean greaterThan(int r1, int c1, int r2, int c2) {
        compares++;

        if (grid[r1][c1] > grid[r2][c2])
            return true;

        return false;
    }

    // swaps values in the grid
    // at (r1, c1) and (r2, c2);
    // assumes that the parameters
    // are within the bounds
    private static void swap(int r1, int c1, int r2, int c2) {
        int temp = grid[r1][c1];
        grid[r1][c1] = grid[r2][c2];
        grid[r2][c2] = temp;
    }
}
