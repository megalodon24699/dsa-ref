import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class QuickSort {
	static Random random = new Random();

	public static int partition(int[] arr, int l, int r) {
		int randomIdx = random.nextInt(r-l+1) + l;
		int tmp = arr[r];
		arr[r] = arr[randomIdx];
		arr[randomIdx] = tmp;

		int pivot = arr[r];
		int i = l;
		for (int j = l; j < r; ++j) {
			if (arr[j] <= pivot) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
				++i;
			}
		}
		arr[r] = arr[i];
		arr[i] = pivot;
		return i;
	}

	public static void quickSort(int[] arr, int l, int r) { // O(n*log(n)) expected time, O(1) space (recursion stack ignored)
		if (l < r) {
			int pivot = partition(arr, l, r);
			quicksort(arr, 0, pivot-1);
			quicksort(arr, pivot, r-1);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		quickSort(arr, 0, n-1);

		System.out.println(Arrays.toString(arr));
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
