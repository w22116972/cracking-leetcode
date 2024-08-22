package solution;

import java.util.LinkedList;
import java.util.List;

public class InsertInterval {



    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new LinkedList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0])
            result.add(intervals[i++]);
        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval = new int[]{  // we could mutate newInterval here also
                    Math.min(newInterval[0], intervals[i][0]),
                    Math.max(newInterval[1], intervals[i][1])};
            i++;
        }
        result.add(newInterval); // add the union of intervals we got
        // add all the rest
        while (i < intervals.length) result.add(intervals[i++]);

        return result.toArray(new int[0][]);
    }

    public static void main(String[] args) {

    }
}
