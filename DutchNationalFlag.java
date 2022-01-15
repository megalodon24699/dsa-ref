import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DutchNationalFlag {
	public static void sort012(int[] arr, int n) { // O(n) time, O(1) space
		int[] counts = new int[3];
		for (int i : arr) {
			++counts[i];
		}
		Arrays.fill(arr, 0, counts[0], 0);
		Arrays.fill(arr, counts[0], counts[0]+counts[1], 1);
		Arrays.fill(arr, counts[0]+counts[1], counts[0]+counts[1]+counts[2], 2);
	}

	public static void sort01Optimized(int[] arr, int n) { // O(n) time, O(1) space
		int l = 0, r = n-1;
		while (l < r) {
			if (arr[l] == 0) {
				++l;
			} else {
				int t = arr[l];
				arr[l] = arr[r];
				arr[r] = t;
				--r;
			}
		}
	}

	public static void sort012Optimized(int[] arr, int n) { // O(n) time, O(1) space
		int l = 0, m = 0, r = n-1;
		while (m <= r) {
			int t;
			switch (arr[m]) {
				case 0:
					t = arr[m];
					arr[m] = arr[l];
					arr[l] = t;
					++l; ++m;
					break;
				case 1:
					++m;
					break;
				case 2:
					t = arr[m];
					arr[m] = arr[r];
					arr[r] = t;
					--r;
					break;
			}
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		sort012Optimized(arr, n);

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
}
