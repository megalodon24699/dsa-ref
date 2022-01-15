import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class LargestNumber {
	public static void main(String[] args) { // O(n*log(n)) time, O(1) space
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		List<String> ls = new ArrayList<>(n);
		for (int i = 0; i < n; ++i) {
			ls.add(fs.next());
		}
		Collections.sort(ls, new Comparator<String>() {
			public int compare(String x, String y) {
				return (y+x).compareTo(x+y);
			}
		});

		System.out.println(String.join("", ls));
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
