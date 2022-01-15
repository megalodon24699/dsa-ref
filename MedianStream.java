import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MedianStream {
	public static void getMedians(int[] arr, int n) { // O(n^2) time, O(1) space
		if (n == 0) {
			return;
		}
		int count = 1;
		System.out.println(arr[0]);
		for (int i = 1; i < n; ++i) {
			int x = arr[i];
			int idx = Arrays.binarySearch(arr, 0, i, x);
			if (idx < 0) {
				idx = -idx - 1;
			} else {
				while (idx < i && arr[idx] == arr[i]) {
					++idx;
				}
			}
			int j = i-1;
			while (j >= idx) {
				arr[j+1] = arr[j];
				--j;
			}
			arr[j+1] = x;
			++count;
			int median = (count % 2 != 0) ? arr[count/2] : (arr[count/2-1] + arr[count/2]) / 2;
			System.out.println(median);
		}
	}

	public static int getMedian(int x, PriorityQueue<Integer> left, PriorityQueue<Integer> right, int median) {
		if (left.size() < right.size()) {
			if (x < median) {
				left.add(x);
			} else {
				left.add(right.poll());
				right.add(x);
			}
			median = (left.peek() + right.peek()) / 2;
		} else if (left.size() == right.size()) {
			if (x < median) {
				left.add(x);
				median = left.peek();
			} else {
				right.add(x);
				median = right.peek();
			}
		} else {
			if (x < median) {
				right.add(left.poll());
				left.add(x);
			} else {
				right.add(x);
			}
			median = (left.peek() + right.peek()) / 2;
		}
		return median;
	}

	public static void getMediansOptimized(int[] arr, int n) { // O(n*log(n)) time, O(n) space
		int median = 0;
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for (int i : arr) {
			median = getMedian(i, left, right, median);
			System.out.println(median);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		getMediansOptimized(arr, n);
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
