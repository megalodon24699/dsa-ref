import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FormAPalindrome {
	public static int getInsertions(String s, int l, int r) {
		if (l > r) {
			return Integer.MAX_VALUE;
		} else if (l == r) {
			return 0;
		} else if (l == r-1) {
			return (s.charAt(l) == s.charAt(r) ? 0 : 1);
		} else {
			if (s.charAt(l) == s.charAt(r)) {
				return getInsertions(s, l+1, r-1);
			} else {
				return Math.min(getInsertions(s, l+1, r), getInsertions(s, l, r-1)) + 1;
			}
		}
	}

	public static int getInsertionsDp(String s) { // O(n^2) time, O(n^2) space
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int gap = 1; gap < n; ++gap) {
			for (int i = 0, j = gap; j < n; ++i, ++j) {
				dp[i][j] = (s.charAt(i) == s.charAt(j)) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) + 1;
			}
		}
		return dp[0][n-1];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(getInsertionsDp(s));
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
