import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LeftMaxRightMin {
	public static int getLeftMaxRightMin(int[] arr, int n) { // O(n) time, O(n) space
		int[] leftMaxs = new int[n];
		leftMaxs[0] = arr[0];
		for (int i = 1; i < n; ++i) {
			leftMaxs[i] = Math.max(leftMaxs[i-1], arr[i]);
		}
		int rightMin = arr[n-1];
		for (int i = n-2; i > 0; --i) {
			rightMin = Math.min(rightMin, arr[i]);
			if (leftMaxs[i] <= arr[i] && arr[i] <= rightMin) {
				return arr[i];
			}
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

		System.out.println(getLeftMaxRightMin(arr, n));
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
