package main.dsa.nonlinear.graph.learning.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static main.dsa.nonlinear.graph.learning.topologicalsort.KahnsAlgorithm.topoSort;


public class AlienDictionary {

	public String findOrder(String[] dict, int N, int K) {

		List<ArrayList<Integer>> adj = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			String word = dict[i];
			String word2 = dict[i + 1];

			int length = Math.min(word.length(), word2.length());
			for (int j = 0; j < length; j++) {

				if (word.charAt(j) != word2.charAt(j)) {
					adj.get(word.charAt(j) - 'a').add(word2.charAt(j) - 'a');
					break;
				}
			}
		}

		int[] topoList = topoSort(K, adj);
		StringBuilder ans = new StringBuilder();
		for (int it : topoList) {
			ans.append((char) (it + ('a')));
		}


		return ans.toString();

	}

	public static void main(String[] args) {
		int N = 5, K = 4;
		String[] dict = { "baa", "abcd", "abca", "cab", "cad" };
		AlienDictionary obj = new AlienDictionary();
		String ans = obj.findOrder(dict, N, K);

		for (int i = 0; i < ans.length(); i++) {
			System.out.print(ans.charAt(i) + " ");
		}
		System.out.println("");
	}

}
