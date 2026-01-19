import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(fib(Integer.MAX_VALUE + 1, new HashMap<>()));

    }

    //0 1 1 2 3 5 8 13 21 34
    //fib(0) = 0, fib(1) =1, fib(2) =1
    //log10 10 -> 1, log10 7 <1
    //time complex = o(2^N)
    //mem complex = o(1)

    //fib(5) -> fib(4) + fib(3)
    //fib (4) -> fib(3) + fib(2)
    //fib(3) -> fib(2) = fib(1)


    public static int fib(int n, Map<Integer, Integer> prev) {
        System.out.println(n);
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        // Check cache first
        if (prev.containsKey(n)) {
            return prev.get(n);
        }

        if (n == 0) {
            prev.put(0, 0); // Cache base case
            return 0;
        }

        if (n == 1) {
            prev.put(1, 1); // Cache base case
            return 1;
        }

        // Calculate and cache the result
        int result = fib(n - 1, prev) + fib(n - 2, prev);
        prev.put(n, result);

        return result;
    }

    //base cases: fib 0,1

    //negative throw invalidinputexception

    //upperbound

    //INT MAX -> fib(x) -> find x -> limit input range from [0,x)

}