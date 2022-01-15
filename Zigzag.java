import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Zigzag {
	public static void rearrange(int[] arr, int n) { // O(n) time, O(1) space
		boolean flag = true;
		for (int i = 0; i < n-1; ++i) {
			if (flag) {
				if (arr[i] > arr[i+1]) {
					int t = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = t;
				}
			} else {
				if (arr[i] < arr[i+1]) {
					int t = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = t;
				}
			}
			flag = !flag;
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		rearrange(arr, n);

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
