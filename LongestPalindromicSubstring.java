import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LongestPalindromicSubstring {
	public static String getLps(String s) { // O(n^3) time, O(1) space
		int n = s.length();
		int max = 1, l = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = i; j < n; ++j) {
				boolean flag = true;
				for (int k = 0; k < (j-i+1)/2; ++k) {
					if (s.charAt(i+k) != s.charAt(j-k)) {
						flag = false;
					}
				}
				if (flag && (j-i+1) > max) {
					l = i;
					max = j-i+1;
				}
			}
		}
		return s.substring(l, l+max);
	}

	public static String getLpsOptimized(String s) { // O(n^2) time, O(n^2) space
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		int max = 1, l = 0;
		for (int i = 0; i < n; ++i) {
			dp[i][i] = true;
		}
		for (int i = 0; i < n-1; ++i) {
			if (s.charAt(i) == s.charAt(i+1)) {
				dp[i][i+1] = true;
				l = i;
				max = 2;
			}
		}
		for (int k = 3; k <= n; ++k) {
			for (int i = 0; i <= n-k; ++i) {
				int j = i+k-1;
				if (dp[i+1][j-1] && s.charAt(i) == s.charAt(j)) {
					dp[i][j] = true;
					if (k > max) {
						l = i;
						max = k;
					}
				}
			}
		}
		return s.substring(l, l+max);
	}

	public static String getLpsSuperOptimized(String s) { // O(n^2) time, O(1) space
		int n = s.length();
		int max = 1, l = 0;
		for (int i = 1; i < n; ++i) {
			int low = i-1, high = i;
			while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
				--low;
				++high;
			}
			++low; --high;
			if (max < high-low+1) {
				l = low;
				max = high-low+1;
			}
			low = i-1; high = i+1;
			while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
				--low;
				++high;
			}
			++low; --high;
			if (max < high-low+1) {
				l = low;
				max = high-low+1;
			}
		}
		return s.substring(l, l+max);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(getLpsSuperOptimized(s));
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
