import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Atoi {
	public static int atoi(String s) { // O(n) time, O(1) space
		int n = s.length();
		int result = 0;
		for (int i = 0; i < n; ++i) {
			int digit = s.charAt(i) - '0';
			if (digit < 0 || digit > 9) {
				return -1;
			}
			result = result*10 + digit;
		}
		return result;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		System.out.println(atoi(s));
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
