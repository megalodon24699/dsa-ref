import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LastIndex {
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		String s = fs.next();
		boolean flag = false;
		for (int i = s.length()-1; i >= 0; --i) {
			if (s.charAt(i) == '1') {
				flag = true;
				System.out.println(i);
			}
		}
		if (!flag) {
			System.out.println("-1");
		}
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
