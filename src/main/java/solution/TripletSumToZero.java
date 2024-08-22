package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Problem Statement
//        Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
//        Example 1:
//        Input: [-3, 0, 1, 2, -1, 1, -2]
//        Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
//        Explanation: There are four unique triplets whose sum is equal to zero.
//        Example 2:
//        Input: [-5, 2, -1, -2, 3]
//        Output: [[-5, 2, 3], [-2, -1, 3]]
//        Explanation: There are two unique triplets whose sum is equal to zero.
public class TripletSumToZero {
    // The goal is to find 3 numbers in array which their sum is equal to zero.
    // Assume we have x + y + z = 0. When we iterate at x, then we have to find the pair of y and z which
    // is equal to -x i.e. y + z = -x
    // Since we want to find unique triplets, we could try to skip duplicate numbers by sorting them first

    public static List<List<Integer>> demo(int[] arr) {
        Arrays.sort(arr); // T(n) = O(n log n)
        List<List<Integer>> result = new ArrayList<>();
        for (int x = 0; x < arr.length - 2; x++) {
            // To skip duplicate x
            if (x > 0 && arr[x] == arr[x - 1]) {
                continue;
            }
            findPairAndPutToResult(arr, result, x + 1, -arr[x]);
        }

        return result;
    }

    public static void findPairAndPutToResult(int[] arr, List<List<Integer>> result, int y, int targetSum) {
        int z = arr.length - 1;
        while (y < z) {
            if (arr[y] + arr[z] == targetSum) {
                result.add(List.of(-targetSum, arr[y], arr[z]));
                z--;
                y++;

                // it is possible that y or z is duplicate
                while (y < z && arr[y] == arr[y - 1]) {
                    y++;
                }
                while (y < z && arr[z] == arr[z + 1]) {
                    z--;
                }
            } else if (arr[y] + arr[z] > targetSum) {
                z--;
            } else {
                y++;
            }
        }
    }


    public static List<List<Integer>> sol(int[] arr) {
        Arrays.sort(arr); // nlogn
        List<List<Integer>> triplets = new ArrayList<>();
        // Since y + z = -x, we see -x as target sum

        // Iterate x from index 0 to index arr.length - 2
        // The reason why maximum of x is length of array minus 3
        // is that since we see x as the left-most value for triplets.
        // So y and z are right next to x (arr.length - 2, arr.length - 1)
        for (int x = 0; x < arr.length - 2; x++) {
            // To skip duplicate value of x, we have to check whether current value of x is same as previous one
            if (x > 0 && arr[x] == arr[x-1]) {
                continue;
            }
            putPairToTriplets(arr, -arr[x], x + 1, triplets);

        }
        return triplets;
    }

    private static void putPairToTriplets(int[]arr, int targetSum, int y, List<List<Integer>> triplets) {
        int z = arr.length - 1;
        while (y < z) {
            final int actualSum = arr[y] + arr[z];
            if (actualSum == targetSum) {
                triplets.add(List.of(-targetSum, arr[y], arr[z]));
                z--;
                y++;
                // we would like to skip duplicate value when current value is equal to previous value
                while (y < z && arr[z] == arr[z + 1]) {
                    z--;
                }
                while (y < z && arr[y] == arr[y - 1]) {
                    y++;
                }
            } else if (actualSum > targetSum) {
                z--;
            } else if (actualSum < targetSum) {
                y++;
            }
        }
    }


    public static List<List<Integer>> findTriplet(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplet = new ArrayList<>();
        for (int x = 0; x < arr.length - 2; x++) {
            if (x >= 1 && arr[x] == arr[x-1]) { //
                continue;
            }
            // k = -x
            // y = x+1開始找
            findPairOfTargetSum(arr, -arr[x], x+1, triplet);
        }
        return triplet;
    }

    public static void findPairOfTargetSum(int[] arr, int k, int start, List<List<Integer>> triplet) {
        int end = arr.length - 1;
        while (start < end) {
            int currentSum = arr[start] + arr[end];
            if (currentSum == k) {
                triplet.add(Arrays.asList(-k, arr[start], arr[end]));
                start++;
                end--;
                // dedup
                // skip start if dup
                while (start < end && arr[start] == arr[start-1]) {
                    start++;
                }
                // skip end if dup
                while (start <end && arr[end] == arr[end+1]) {
                    end--;
                }
            } else if (k > currentSum) {
                start++;
            } else if (k <= currentSum) {
                end--;
            }
        }
    }

    public static List<List<Integer>> practice(int[] arr) {
        Arrays.sort(arr); // nlogn
        List<List<Integer>> result = new ArrayList<>();
        // Since y + z = -x, we see -x as target sum
        for (int x = 0; x < arr.length - 2; x++) {
            if (x >= 1 && arr[x] == arr[x - 1]) {
                continue;
            }
            twoSum(arr, -x, x + 1, result);
        }
        return result;

        // Iterate x from index 0 to index arr.length - 2
        // The reason why maximum of x is length of array minus 3
        // is that since we see x as the left-most value for triplets.
        // So y and z are right next to x (arr.length - 2, arr.length - 1)
//        for (int x = 0; x < arr.length - 2; x++) {
//            // To skip duplicate value of x, we have to check whether current value of x is same as previous one
//            if (x > 0 && arr[x] == arr[x-1]) {
//                continue;
//            }
//            putPairToTriplets(arr, -arr[x], x + 1, triplets);
//
//        }
//        return triplets;
    }

    // target is -x
    public static void twoSum(int[] arr, int target, int headIndex, List<List<Integer>> result) {
        int tailIndex = arr.length - 1;
        while (headIndex < tailIndex) {
            // y + z
            final int currentSum = arr[headIndex] + arr[tailIndex];
            if (currentSum == target) {
                result.add(List.of(-target, headIndex, tailIndex));
                headIndex++;
                tailIndex--;
            } else if (currentSum < target) {
                headIndex++;
            } else if (currentSum > target) {
                tailIndex--;
            }
        }
    }

    public static void main(String[] args) {
        int[] input1 = new int[]{-3, 0, 1, 2, -1, 1, -2};
        int[] input2 = new int[]{-5, 2, -1, -2, 3};


    }

}
