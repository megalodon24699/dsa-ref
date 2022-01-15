import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SearchRotatedArray {
	public static int getPivot(int[] arr, int n) {
		int l = 0, r = n-1;
		while (l < r) {
			int mid = l + (r-l)/2;
			if (mid < r && arr[mid] > arr[mid+1]) {
				return mid;
			} else if (l < mid && arr[mid-1] > arr[mid]) {
				return mid-1;
			} else {
				if (arr[l] >= arr[mid]) {
					r = mid-1;
				} else {
					l = mid+1;
				}
			}
		}
		return -1;
	}

	public static int binarySearchPivoted(int[] arr, int n, int key) { // O(log(n)) time, O(1) space
		int pivot = getPivot(arr, n);
		if (pivot == -1) {
			return Arrays.binarySearch(arr, key);
		}
		if (arr[pivot] == key) {
			return pivot;
		} else if (arr[0] <= key) {
			int idx = Arrays.binarySearch(arr, 0, pivot, key);
			return (idx < 0) ? -1 : idx;
		} else {
			int idx = Arrays.binarySearch(arr, pivot, n, key);
			return (idx < 0) ? -1 : idx;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int key = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(binarySearchPivoted(arr, n, key));
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
