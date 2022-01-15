import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SubarrayMaximum {
	public static void getSubarrayMaxs(int[] arr, int n, int k) { // O(n*k) time, O(1) space
		System.out.print("{ ");
		for (int i = 0; i <= n-k; ++i) {
			int max = arr[i];
			for (int j = 1; j < k; ++j) {
				max = Math.max(max, arr[i+j]);
			}
			System.out.print(max + " ");
		}
		System.out.println("}");
	}

	public static void getSubarrayMaxsTree(int[] arr, int n, int k) { // O(n*log(k)) time, O(k) space
		TreeSet<Integer> s = new TreeSet<>();
		for (int i = 0; i < k-1; ++i) {
			s.add(arr[i]);
		}
		System.out.print("{ ");
		for (int i = k-1; i < n; ++i) {
			s.add(arr[i]);
			System.out.print(s.last() + " ");
			s.remove(arr[i-k+1]);
		}
		System.out.println("}");
	}

	public static void getSubarrayMaxsDeque(int[] arr, int n, int k) { // O(n) time, O(k) space
		Deque<Integer> deque = new ArrayDeque<>();
		for (int i = 0; i < k-1; ++i) {
			while (!deque.isEmpty() && deque.getLast() <= arr[i]) {
				deque.removeLast();
			}
			deque.add(arr[i]);
		}
		System.out.print("{ ");
		for (int i = k-1; i < n; ++i) {
			while (!deque.isEmpty() && deque.getLast() <= arr[i]) {
				deque.removeLast();
			}
			deque.add(arr[i]);
			System.out.print(deque.getFirst() + " ");
			if (arr[i-k+1] == deque.getFirst()) {
				deque.remove();
			}
		}
		System.out.println("}");
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int k = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		getSubarrayMaxsDeque(arr, n, k);
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
