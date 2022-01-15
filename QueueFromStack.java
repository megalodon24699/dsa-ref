import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class QueueFromStack {
	static class Queue {
		private Deque<Integer> sA, sB;

		public Queue() {
			sA = new ArrayDeque<>();
			sB = new ArrayDeque<>();
		}

		public void enqueue(int data) { // O(1) time, O(1) space
			sA.push(data);
		}

		public int dequeue() { // O(n) time, O(1) space
			if (sA.isEmpty()) {
				return -1;
			}
			while (!sA.isEmpty()) {
				sB.push(sA.pop());
			}
			int data = sB.pop();
			while (!sB.isEmpty()) {
				sA.push(sB.pop());
			}
			return data;
		}

		@Override
		public String toString() {
			return sA.toString();
		}
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		Queue queue = new Queue();
		int n = fs.nextInt();
		for (int i = 0; i < n; ++i) {
			int cmd = fs.nextInt();
			if (cmd == 1) {
				queue.enqueue(fs.nextInt());
			} else {
				System.out.println(queue.dequeue());
			}
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

	static class Pair<F extends Comparable<F>, S extends Comparable<S>> implements Comparable<Pair<F, S>> {
		public final F first;
		public final S second;

		Pair(F aFirst, S aSecond) {
			first = aFirst;
			second = aSecond;
		}

		@Override
		public int compareTo(Pair<F, S> o) {
			int t = first.compareTo(o.first);
			if (t == 0) {
				t = second.compareTo(o.second);
			}
			return t;
		}

		@Override
		public int hashCode() {
			return (31 + first.hashCode()) * 31 + second.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			if (o == this) {
				return true;
			}
			Pair p = (Pair)o;
			return first.equals(p.first) && second.equals(p.second);
		}

		@Override
		public String toString() {
			return "{ " + first + ", " + second + " }";
		}

		public static <F extends Comparable<F>, S extends Comparable<S>> Pair<F, S> of(F aFirst, S aSecond) {
			return new Pair<>(aFirst, aSecond);
		}
	}
}
