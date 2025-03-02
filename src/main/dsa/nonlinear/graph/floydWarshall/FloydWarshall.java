package main.dsa.nonlinear.graph.floydWarshall;

/*
Floyd Warshall
Difficulty: MediumAccuracy: 32.89%Submissions: 135K+Points: 4
The problem is to find the shortest distances between every pair of vertices in a given edge-weighted directed graph. The graph is represented as an adjacency matrix of size n*n. Matrix[i][j] denotes the weight of the edge from i to j. If Matrix[i][j]=-1, it means there is no edge from i to j.
Note : Modify the distances for every pair in-place.

Examples :

Input: matrix = [[0, 25],[-1, 0]]

Output: [[0, 25],[-1, 0]]

Explanation: The shortest distance between every pair is already given(if it exists).
Input: matrix = [[0, 1, 43],[1, 0, 6],[-1, -1, 0]]

Output: [[0, 1, 7],[1, 0, 6],[-1, -1, 0]]

Explanation: We can reach 2 from 0 as 0->1->2 and the cost will be 1+6=7 which is less than 43.
Expected Time Complexity: O(n3)
Expected Space Complexity: O(1)

Constraints:
1 <= n <= 100
-1 <= matrix[ i ][ j ] <= 1000
*/
public class FloydWarshall {

	public void shortest_distance(int[][] matrix) {
		// Code here
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == -1) {
					matrix[i][j] = (int) 1e9;
				}
				if (i == j) {
					matrix[i][j] = 0;
				}
			}
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == (int) 1e9) {
					matrix[i][j] = -1;
				}
			}
		}
	}

	public static void main(String[] args) {
		int V = 4;
		int[][] matrix = new int[V][V];

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = -1;
			}
		}

		matrix[0][1] = 2;
		matrix[1][0] = 1;
		matrix[1][2] = 3;
		matrix[3][0] = 3;
		matrix[3][1] = 5;
		matrix[3][2] = 4;

		FloydWarshall obj = new FloydWarshall();
		obj.shortest_distance(matrix);

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
