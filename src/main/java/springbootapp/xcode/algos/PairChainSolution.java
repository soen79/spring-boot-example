package springbootapp.xcode.algos;

import java.util.Arrays;

/**
 * This is a puzzle to solve.
 */
public class PairChainSolution {

    public static void main(String[] args) {
        System.out.println("START OF PROGRAM");
        PairChainSolution app = new PairChainSolution();
        int[] [] arr = {{3,4}, {7,8}, {1,2},{2,3}};
        System.out.println("\nLength of Largest Chain is: " + app.findLongestChain(arr));
        System.out.println("END OF PROGRAM");
    }

    public int findLongestChain(int [][] pairs) {
        int longestChainSize = 1;
        // starts at element 0 and sorts with next one.
        // remember that each element is itself an array.
        // This seems to use TimSort/Merge/Binary
        Arrays.sort(pairs,  (a,b)  -> a[0] - b[0]);

        Arrays.stream(pairs).forEach( x -> System.out.print("["+x[0] + ", " + x[1]+"] "));

        /*
         * ensure that b < c
         */

        int currentEnd = 0;
       for(int i = 0; i < pairs.length-1 ; i++) {
           System.out.print("\nIteration: " + i);
            int lastOfFirst = pairs[currentEnd][1];
            int firstOfSecond = pairs[i+1][0];

           System.out.print("\ncomparing: " + lastOfFirst + " with " + firstOfSecond);
            if(lastOfFirst < firstOfSecond) {
                longestChainSize++;
                System.out.print(" -> TRUE - part of chain");
                currentEnd = i;
            }
       }

        return longestChainSize;
    }
}
