import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ZeroSubarray {
	public static int getMaxZeroSubarray(int[] arr, int n) { // O(n^2) time, O(1) space
		int max = 0;
		for (int i = 0; i < n; ++i) {
			int currSum = 0;
			for (int j = i; j < n; ++j) {
				currSum += arr[j];
				if (currSum == 0) {
					max = Math.max(max, j-i+1);
				}
			}
		}
		return max;
	}

	public static int getMaxZeroSubarrayOptimized(int[] arr, int n) { // O(n) time, O(n) space
		int max = 0;
		int currSum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			currSum += arr[i];
			if (arr[i] == 0 && max == 0) {
				max = 1;
			}
			if (currSum == 0) {
				max = i+1;
			}
			if (map.containsKey(currSum)) {
				max = Math.max(max, i-map.get(currSum));
			} else {
				map.put(currSum, i);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getMaxZeroSubarrayOptimized(arr, n));
	}

	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}
	}

	static class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
		public final F first;
		public final S second;

		Pair(F aFirst, S aSecond) {
			first = aFirst;
			second = aSecond;
		}

		@Override
		public int compareTo(Pair<F, S> o) {
			int t = first.compareTo(o.first);
			if (t == 0) {
				t = second.compareTo(o.second);
			}
			return t;
		}

		@Override
		public int hashCode() {
			return (31 + first.hashCode()) * 31 + second.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			if (o == this) {
				return true;
			}
			Pair p = (Pair)o;
			return first.equals(p.first) && second.equals(p.second);
		}

		@Override
		public String toString() {
			return "{ " + first + ", " + second + " }";
		}

		public static <F extends Comparable<F>, S extends Comparable<S>> Pair<F, S> of(F aFirst, S aSecond) {
			return new Pair<>(aFirst, aSecond);
		}
	}
}
