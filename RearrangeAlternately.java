import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RearrangeAlternately {
	public static void rearrange(long[] arr, int n) { // O(n) time, O(n) space
		long[] t = new long[n];
		boolean flag = true;
		int l = 0, r = n-1;
		for (int i = 0; i < n; ++i) {
			t[i] = flag ? arr[r--] : arr[l++];
			flag = !flag;
		}
		System.arraycopy(t, 0, arr, 0, n);
	}

	public static void rearrangeOptimized(long[] arr, int n) { // O(n) time, O(1) space
		int minIdx = 0, maxIdx = n-1;
		long maxElement = arr[n-1] + 1;
		boolean flag = true;
		for (int i = 0; i < n; ++i) {
			long e = flag ? arr[maxIdx--] : arr[minIdx++];
			arr[i] += e % maxElement * maxElement;
			flag = !flag;
		}
		for (int i = 0; i < n; ++i) {
			arr[i] /= maxElement;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextLong();
		}

		rearrangeOptimized(arr, n);

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
