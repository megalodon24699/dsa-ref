import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SortByFrequency {
	public static void sortByFrequency(int[] arr, int n) { // O(n*log(n)) time, O(n) space
		Map<Integer, Integer> histogram = new HashMap<>();
		List<Integer> nums = new ArrayList<>();
		for (int i : arr) {
			if (histogram.containsKey(i)) {
				histogram.put(i, histogram.get(i) + 1);
			} else {
				histogram.put(i, 1);
			}
			nums.add(i);
		}
		Collections.sort(nums, new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				int fx = histogram.get(x), fy = histogram.get(y);
				return (fx == fy) ? x-y : fy-fx;
			}
		});
		for (int i = 0; i < n; ++i) {
			arr[i] = nums.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int t = fs.nextInt();
		while (t-- > 0) {
			int n = fs.nextInt();
			int[] arr = new int[n];
			for (int i = 0; i < n; ++i) {
				arr[i] = fs.nextInt();
			}

			sortByFrequency(arr, n);

			System.out.println(Arrays.toString(arr));
		}
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
