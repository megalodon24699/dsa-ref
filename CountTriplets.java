import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class CountTriplets {
	public static int countTriplets(int[] arr, int n) { // O(n^2) time, O(n) space
		Set<Integer> s = new HashSet<>();
		for (int i : arr) {
			s.add(i);
		}
		int count = 0;
		for (int i = 0; i < n-1; ++i) {
			for (int j = i+1; j < n; ++j) {
				if (s.contains(arr[i] + arr[j])) {
					++count;
				}
			}
		}
		return count;
	}

	public static int countTripletsOptimized(int[] arr, int n) { // O(n^2) time, O(1) space
		sort(arr);
		int count = 0;
		for (int k = n-1; k > 1; --k) {
			int i = 0, j = k-1;
			while (i < j) {
				if (arr[i] + arr[j] < arr[k]) {
					++i;
				} else if (arr[i] + arr[j] == arr[k]) {
					++count;
					++i; --j;
				} else {
					--j;
				}
			}
		}
		return count;
	}

	public static void sort(int[] arr) {
		List<Integer> ls = new ArrayList<>();
		for (int i : arr) {
			ls.add(i);
		}
		Collections.sort(ls);
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = ls.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(countTripletsOptimized(arr, n));
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
