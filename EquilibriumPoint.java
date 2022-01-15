import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EquilibriumPoint {
	public static int getEquilibriumPoint(int[] arr, int n) { // O(n^2) time, O(1) space
		for (int i = 0; i < n; ++i) {
			int l = 0;
			for (int j = 0; j < i; ++j) {
				l += arr[j];
			}
			int r = 0;
			for (int j = i+1; j < n; ++j) {
				r += arr[j];
			}
			if (l == r) {
				return i;
			}
		}
		return -1;
	}

	public static int getEquilibriumPointOptimized(int[] arr, int n) { // O(n) time, O(1) space
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		int l = 0;
		for (int i = 0; i < n; ++i) {
			sum -= arr[i];
			if (l == sum) {
				return i;
			}
			l += arr[i];
		}
		return -1;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getEquilibriumPointOptimized(arr, n));
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
