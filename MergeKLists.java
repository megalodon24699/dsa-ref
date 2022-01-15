import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MergeKLists {
	static class Node implements Comparable<Node> {
		int data;
		Node next;

		public Node(int aData) {
			data = aData;
			next = null;
		}

		@Override
		public int compareTo(Node node) {
			return data - node.data;
		}

		@Override
		public String toString() {
			return Integer.toString(data);
		}
	}

	static class LinkedList {
		Node head, tail;

		public LinkedList() {
			head = null;
			tail = null;
		}

		@Override
		public String toString() {
			String repr = "{ ";
			Node ptr = head;
			while (ptr != null) {
				repr += ptr.data + " ";
				ptr = ptr.next;
			}
			repr += "}";
			return repr;
		}

		public boolean isEmpty() {
			return head == null && tail == null;
		}

		public void addFront(int data) {
			Node toAdd = new Node(data);
			if (isEmpty()) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			toAdd.next = head;
			head = toAdd;
		}

		public void addFront(Node toAdd) {
			toAdd.next = null;
			if (isEmpty()) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			toAdd.next = head;
			head = toAdd;
		}

		public void addBack(int data) {
			Node toAdd = new Node(data);
			if (isEmpty()) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			tail.next = toAdd;
			tail = toAdd;
		}

		public void addBack(Node toAdd) {
			if (isEmpty()) {
				head = toAdd;
				tail = toAdd;
				return;
			}
			tail.next = toAdd;
			tail = toAdd;
		}

		public int remove() {
			if (isEmpty()) {
				return -1;
			}
			int t = head.data;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
			}
			return t;
		}

		public Node removeNode() {
			if (isEmpty()) {
				return null;
			}
			Node node = head;
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
			}
			node.next = null;
			return node;
		}
	}

	public static LinkedList merge(LinkedList[] lists, int k) { // O(n*k*log(k)) time, O(k) space
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < k; ++i) {
			if (!lists[i].isEmpty()) {
				pq.add(lists[i].head);
			}
		}
		LinkedList merged = new LinkedList();
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (node.next != null) {
				pq.add(node.next);
			}
			node.next = null;
			merged.addBack(node);
		}
		return merged;
	}

	public static LinkedList mergeTwo(LinkedList a, LinkedList b) {
		if (a.isEmpty()) {
			return b;
		}
		if (b.isEmpty()) {
			return a;
		}
		LinkedList merged = new LinkedList();
		while (!a.isEmpty() && !b.isEmpty()) {
			Node node = (a.head.data <= b.head.data) ? a.removeNode() : b.removeNode();
			merged.addBack(node);
		}
		if (!a.isEmpty()) {
			merged.addBack(a.head);
		}
		if (!b.isEmpty()) {
			merged.addBack(b.head);
		}
		return merged;
	}

	public static LinkedList mergeOptimized(LinkedList[] lists, int k) { // O(n*k*log(k)) time, O(1) space
		int last = k-1;
		while (last > 0) {
			int l = 0, r = last;
			while (l < r) {
				lists[l] = mergeTwo(lists[l], lists[r]);
				++l; --r;
			}
			last = r;
		}
		return lists[0];
	}

	public static void main(String[] args) {
		FastScanner fs = new FastScanner();

		int k = fs.nextInt();
		LinkedList[] lists = new LinkedList[k];
		for (int i = 0; i < k; ++i) {
			lists[i] = new LinkedList();
			int n = fs.nextInt(); // prepend the number of elements in this list
			for (int j = 0; j < n; ++j) {
				lists[i].addBack(fs.nextInt());
			}
		}

		System.out.println(mergeOptimized(lists, k));
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
