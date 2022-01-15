import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.StringTokenizer;

public class KthSmallest {
	static Random random = new Random();

	public static int getKthSmallest(int[] arr, int n, int k) { // O(n*log(n)) time, O(1) space
		sort(arr);
		return arr[k-1];
	}

	public static int getKthSmallestMinHeap(int[] arr, int n, int k) { // O(n+k*log(n)) time, O(n) space
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i : arr) {
			pq.add(i);
		}
		for (int i = 0; i < k-1; ++i) {
			pq.poll();
		}
		return pq.peek();
	}

	public static int getKthSmallestMaxHeap(int[] arr, int n, int k) { // O(k+(n-k)*log(k)) time, O(k) space
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < k; ++i) {
			pq.add(arr[i]);
		}
		for (int i = k; i < n; ++i) {
			if (arr[i] < pq.peek()) {
				pq.poll();
				pq.add(arr[i]);
			}
		}
		return pq.peek();
	}

	public static int partition(int[] arr, int l, int r) {
		int randomIdx = random.nextInt(r-l+1) + l;
		int tmp = arr[r];
		arr[r] = arr[randomIdx];
		arr[randomIdx] = tmp;

		int pivot = arr[r];
		int i = l;
		for (int j = l; j < r; ++j) {
			if (arr[j] <= pivot) {
				int t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
				++i;
			}
		}
		arr[r] = arr[i];
		arr[i] = pivot;
		return i;
	}

	public static int getKthSmallestQuickselectAux(int[] arr, int l, int r, int k) {
		if (0 < k && k <= r-l+1) {
			int idx = partition(arr, l, r);
			if (idx-l == k-1) {
				return arr[idx];
			} else if (idx-l > k-1) {
				return getKthSmallestQuickselectAux(arr, l, idx-1, k);
			} else {
				return getKthSmallestQuickselectAux(arr, idx+1, r, k-1 - (idx-l));
			}
		}
		return Integer.MAX_VALUE;
	}

	public static int getKthSmallestQuickselect(int[] arr, int n, int k) { // O(n) expected time, O(1) space
		return getKthSmallestQuickselectAux(arr, 0, n-1, k);
	}

	public static void sort(int[] arr) {
		List<Integer> ls = new ArrayList<>();
		for (int i : arr) {
			ls.add(i);
		}
		Collections.sort(ls);
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = ls.get(i);
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int k = fs.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; ++i) {
			arr[i] = fs.nextInt();
		}

		System.out.println(getKthSmallestQuickselect(arr, n, k));
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
