import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Arrays;

public class MissingNumber {
	public static int getMissingNumber(int[] arr, int n) { // O(n) time, O(1) space
		long s = n * (n+1L) / 2;
		for (int i : arr) {
			s -= i;
		}
		return (int)s;
	}

	public static int getMissingNumberXor(int[] arr, int n) { // O(n) time, O(1) space
		int a = 1, b = 0;
		for (int i = 2; i <= n; ++i) {
			a ^= i;
			b ^= arr[i-2];
		}
		return a^b;
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] arr = new int[n-1];
		for (int i = 0; i < n-1; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getMissingNumberXor(arr, n));
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
