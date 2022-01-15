import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class StockBuySell {
	// Can transact infinitely
	public static long getProfit(int[] arr, int n) { // O(n) time, O(1) space
		long profit = 0;
		int i = 0;
		while (i < n-1) {
			while (i < n-1 && arr[i] >= arr[i+1]) {
				++i;
			}
			int buy = arr[i++];
			while (i < n && arr[i] >= arr[i-1]) {
				++i;
			}
			int sell = arr[i-1];
			profit += sell - buy;
		}
		return profit;
	}

	// Can transact infinitely
	public static long getProfitAlt(int[] arr, int n) { // O(n) time, O(1) space
		long profit = 0;
		for (int i = 1; i < n; ++i) {
			if (arr[i] > arr[i-1]) {
				profit += arr[i] - arr[i-1];
			}
		}
		return profit;
	}

	// Can transact at most once
	public static long getProfitOnce(int[] arr, int n) { // O(n) time, O(1) space
		int maxDiff = 0;
		int min = arr[0];
		for (int i = 1; i < n; ++i) {
			maxDiff = Math.max(maxDiff, arr[i]-min);
			min = Math.min(min, arr[i]);
		}
		return maxDiff;
	}

	// Can transact at most twice
	public static long getProfitTwice(int[] arr, int n) { // O(n) time, O(n) space
		int[] profits = new int[n];
		int max = arr[n-1];
		for (int i = n-2; i >= 0; --i) {
			max = Math.max(max, arr[i]);
			profits[i] = Math.max(profits[i+1], max-arr[i]);
		}
		int min = arr[0];
		for (int i = 1; i < n; ++i) {
			min = Math.min(min, arr[i]);
			profits[i] = Math.max(profits[i-1], profits[i]+arr[i]-min);
		}
		return profits[n-1];
	}

	// Can transact at most K times
	public static long getProfitK(int[] arr, int n, int k) { // O(k*n^2) time, O(k*n) space
		int[][] dp = new int[k+1][n];
		for (int i = 1; i <= k; ++i) {
			for (int j = 1; j < n; ++j) {
				int max = 0;
				for (int m = 0; m < j; ++m) {
					max = Math.max(max, arr[j]-arr[m]+dp[i-1][m]);
				}
				dp[i][j] = Math.max(dp[i][j-1], max);
			}
		}
		return dp[k][n-1];
	}

	// Can transact at most K times
	public static long getProfitKOptimized(int[] arr, int n, int k) { // O(k*n) time, O(k*n) space
		int[][] dp = new int[k+1][n];
		for (int i = 1; i <= k; ++i) {
			int prevDiff = Integer.MIN_VALUE;
			for (int j = 1; j < n; ++j) {
				prevDiff = Math.max(prevDiff, dp[i-1][j-1] - arr[j-1]);
				dp[i][j] = Math.max(dp[i][j-1], arr[j] + prevDiff);
			}
		}
		return dp[k][n-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int k = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getProfitKOptimized(arr, n, k));
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
