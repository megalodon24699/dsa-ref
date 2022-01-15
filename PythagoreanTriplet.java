import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PythagoreanTriplet {
	public static boolean pythagoreanTriplet(int[] arr, int n) { // O(n^2) time, O(1) space
		for (int i = 0; i < n; ++i) {
			arr[i] *= arr[i];
		}
		sort(arr);
		for (int k = n-1; k > 1; --k) {
			int i = 0, j = k-1;
			while (i < j) {
				if (arr[i]+arr[j] == arr[k]) {
					return true;
				} else if (arr[i]+arr[j] < arr[k]) {
					++i;
				} else {
					--j;
				}
			}
		}
		return false;
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

		System.out.println(pythagoreanTriplet(arr, n));
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
