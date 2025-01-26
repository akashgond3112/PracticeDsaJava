package main.blind75.arraysAndHashing;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class RateLimitingAlgorithm {
	public static List<String> getMessageStatus(List<Integer> timestamps, List<String> messages, int k) {
		HashMap<String, Integer> lastDelivered = new HashMap<>();
		List<String> result = new ArrayList<>();

		for (int i = 0; i < messages.size(); i++) {
			String message = messages.get(i);
			int currentTime = timestamps.get(i);

			if (!lastDelivered.containsKey(message)) {
				result.add("True");
				lastDelivered.put(message, currentTime);
			} else {
				int lastTime = lastDelivered.get(message);
				if (currentTime - lastTime >= k) {
					result.add("True");
					lastDelivered.put(message, currentTime);
				} else {
					result.add("False");
				}
			}
		}

		return result;
	}
}
