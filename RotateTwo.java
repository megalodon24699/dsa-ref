import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class RotateTwo {
	public static boolean isRotatedLeftByTwo(String a, String b) {
		int m = a.length();
		int n = b.length();
		if (m != n) {
			return false;
		}
		boolean isLeftRotated = true;
		for (int i = 2; i < m; ++i) {
			if (a.charAt(i) != b.charAt(i-2)) {
				isLeftRotated = false;
				break;
			}
		}
		isLeftRotated = isLeftRotated && a.charAt(0) == b.charAt(n-2) && a.charAt(1) == b.charAt(n-1);
		return isLeftRotated;
	}

	public static boolean isRotatedByTwo(String a, String b) { // O(n) time, O(1) space
		return (isRotatedLeftByTwo(a, b) || isRotatedLeftByTwo(b, a));
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String a = fs.next();
		String b = fs.next();

		System.out.println(isRotatedByTwo(a, b));
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
