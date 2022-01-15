import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Strstr {
	public static int strstr(String a, String b) { // O(m*n) time, O(1) space
		int n = a.length(), m = b.length();
		for (int i = 0; i <= n-m; ++i) {
			boolean flag = true;
			for (int j = 0; j < m; ++j) {
				if (a.charAt(i+j) != b.charAt(j)) {
					flag = false;
					break;
				}
			}
			if (flag) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String a = fs.next();
		String b = fs.next();
		System.out.println(strstr(a, b));
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
