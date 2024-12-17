package main.dsa.nonlinear.binary.tree;

public class Pair<A, B> {
	public final A node;
	public final B val;

	public Pair(A node, B val) {
		this.node = node;
		this.val = val;
	}

	@Override
	public String toString() {
		return "(" + val + ")";
	}

	// Optional: add equals and hashCode methods for proper comparison and usage in collections
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pair<?, ?> tuple = (Pair<?, ?>) o;
		return val.equals(tuple.val);
	}

	@Override
	public int hashCode() {
		return 31 * val.hashCode();
	}
}

