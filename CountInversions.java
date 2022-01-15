import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CountInversions {
	public static long countInversions(long[] arr, int n) { // O(n^2) time, O(1) space
		long count = 0;
		for (int i = 0; i < n-1; ++i) {
			for (int j = i+1; j < n; ++j) {
				if (arr[i] > arr[j]) {
					++count;
				}
			}
		}
		return count;
	}

	public static long merge(long[] arr, int l, int m, int r) {
		long[] ls = Arrays.copyOfRange(arr, l, m+1);
		long[] rs = Arrays.copyOfRange(arr, m+1, r+1);
		long count = 0;
		int i = 0, j = 0, k = l;
		while (i < ls.length && j < rs.length) {
			if (ls[i] <= rs[j]) {
				arr[k++] = ls[i++];
			} else {
				count += m+1 - (l+i);
				arr[k++] = rs[j++];
			}
		}
		while (i < ls.length) {
			arr[k++] = ls[i++];
		}
		while (j < rs.length) {
			arr[k++] = rs[j++];
		}
		return count;
	}

	public static long mergeSortAux(long[] arr, int l, int r) {
		long count = 0;
		if (l < r) {
			int m = l + (r-l)/2;
			count += mergeSortAux(arr, l, m);
			count += mergeSortAux(arr, m+1, r);
			count += merge(arr, l, m, r);
		}
		return count;
	}

	public static long mergeSort(long[] arr, int n) { // O(n*log(n)) time, O(n) space
		return mergeSortAux(arr, 0, n-1);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextLong();
		}

		System.out.println(mergeSort(arr, n));
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
}
