import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Lcp {
	public static String getCp(String a, String b) {
		int n = Math.min(a.length(), b.length());
		int i = 0;
		while (i < n) {
			if (a.charAt(i) != b.charAt(i)) {
				break;
			}
			++i;
		}
		return a.substring(0, i);
	}

	public static String getLcpWordByWord(String[] ss, int n) { // O(n*min|m|) time, O(min|m|) space
		String lcp = ss[0];
		for (int i = 1; i < n; ++i) {
			lcp = getCp(lcp, ss[i]);
		}
		return lcp;
	}

	public static int getMinLength(String[] ss) {
		int min = Integer.MAX_VALUE;
		for (String s : ss) {
			min = Math.min(min, s.length());
		}
		return min;
	}

	public static String getLcpCharByChar(String[] ss, int n) { // O(n*min|m|) time, O(min|m|) space
		int min = getMinLength(ss);
		int i = 0;
		boolean flag = true;
		while (i < min && flag) {
			char c = ss[0].charAt(i);
			for (int j = 1; j < n; ++j) {
				if (c != ss[j].charAt(i)) {
					flag = false;
					break;
				}
			}
			++i;
		}
		return ss[0].substring(0, i);
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		String[] ss = new String[n];
		for (int i = 0; i < n; ++i) {
			ss[i] = fs.next();
		}

		System.out.println(getLcpCharByChar(ss, n));
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
