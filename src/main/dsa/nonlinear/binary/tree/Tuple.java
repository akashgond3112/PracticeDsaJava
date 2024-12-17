package main.dsa.nonlinear.binary.tree;

public class Tuple<A, B, C> {
	public final A node;
	public final B row;
	public final C col;

	public Tuple(A node, B row, C col) {
		this.node = node;
		this.row = row;
		this.col = col;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}

	// Optional: add equals and hashCode methods for proper comparison and usage in collections
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
		return row.equals(tuple.row) && col.equals(tuple.col);
	}

	@Override
	public int hashCode() {
		return 31 * row.hashCode() + col.hashCode();
	}
}

