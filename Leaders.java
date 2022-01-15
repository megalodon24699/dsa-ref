import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Leaders {
	public static void getLeaders(int[] arr, int n) { // O(n^2) time, O(1) space
		for (int i = 0; i < n; ++i) {
			int j;
			for (j = i+1; j < n; ++j) {
				if (arr[i] <= arr[j]) {
					break;
				}
			}
			if (j == n) {
				System.out.print(arr[i] + " ");
			}
		}
		System.out.println();
	}

	public static void getLeadersOptimized(int[] arr, int n) { // O(n) time, O(1) space
		int max = arr[n-1];
		System.out.print(max + " ");
		for (int i = n-2; i >= 0; --i) {
			if (max < arr[i]) {
				max = arr[i];
				System.out.print(max + " ");
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		getLeadersOptimized(arr, n);
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
