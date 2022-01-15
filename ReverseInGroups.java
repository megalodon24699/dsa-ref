import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReverseInGroups {
	public static void reverseInGroups(long[] arr, int n, int k) { // O(n) time, O(1) space
		for (int i = 0; i < n; i += k) {
			int l = i, r = Math.min(i+k-1, n-1);
			while (l < r) {
				long t = arr[r];
				arr[r] = arr[l];
				arr[l] = t;
				++l; --r;
			}
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int k = fs.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextLong();
		}

		reverseInGroups(arr, n, k);

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
