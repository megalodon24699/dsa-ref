import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SubarraySum {
	public static String subarraySum(int[] arr, int n, long s) { // O(n^2) time, O(1) space
		for (int i = 0; i < n; ++i) {
			long sum = arr[i];
			for (int j = i+1; j < n+1; ++j) {
				if (sum == s) {
					return i + " " + (j-1);
				}
				if (sum > s || j == n) {
					break;
				}
				sum += arr[j];
			}
		}
		return "-1";
	}

	public static String subarraySumOptimized(int[] arr, int n, long s) { // O(n) time, O(1) space
		long sum = arr[0];
		int l = 0;
		for (int r = 1; r <= n; ++r) {
			while (sum > s && l < r-1) {
				sum -= arr[l++];
			}
			if (sum == s) {
				return l + " " + (r-1);
			}
			if (r < n) {
				sum += arr[r];
			}
		}
		return "-1";
	}

	public static String subarraySumNegative(int[] arr, int n, long s) { // O(n) time, O(n) space
		long sum = 0;
		Map<Long, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; ++i) {
			sum += arr[i];
			if (sum == s) {
				return "0 " + i;
			}
			if (hm.containsKey(sum-s)) {
				return (hm.get(sum-s) + 1) + " " + i;
			}
			hm.put(sum, i);
		}
		return "-1";
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		long s = fs.nextLong();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(subarraySumNegative(arr, n, s));
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
