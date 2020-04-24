import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class Amazon {

	public ArrayList<String> popularNFeatures(int numFeatures, 
			int topFeatures, 
			List<String> possibleFeatures, 
			int numFeatureRequests, 
			List<String> featureRequests)
	{

		ArrayList<String> res = new ArrayList<>();
		if(possibleFeatures==null || possibleFeatures.size()<1 || featureRequests==null || featureRequests.size()<1)
			return res;

		Map<String, Integer> map = new HashMap<>();
		for(String requests : featureRequests) {
			String[] strs = requests.split(" ");
			for(String s : strs) {
				s = s.toLowerCase();
				if(possibleFeatures.contains(s)) {
					map.put(s, map.getOrDefault(s, 0) + 1);
				}
			}
		}

		ArrayList<String> sortedList = new ArrayList<>(map.keySet());
		Collections.sort(sortedList,(w1,w2) -> (map.get(w1)) != (map.get(w2)) ? map.get(w2)-map.get(w1) : w1.compareTo(w2));
		for(int i=0; i<topFeatures;i++){
			res.add(sortedList.get(i));
		}
		return res;
	}
	
	public ArrayList<String> popularNFeaturesNoRepeat(int numFeatures, 
			int topFeatures, 
			List<String> possibleFeatures, 
			int numFeatureRequests, 
			List<String> featureRequests)
	{

		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> seen;
		if(possibleFeatures==null || possibleFeatures.size()<1 || featureRequests==null || featureRequests.size()<1)
			return res;

		Map<String, Integer> map = new HashMap<>();
		for(String requests : featureRequests) {
			seen = new ArrayList<>();
			String[] strs = requests.split(" ");
			for(String s : strs) {
				s = s.toLowerCase();
				if(possibleFeatures.contains(s) && !seen.contains(s)) {
					map.put(s, map.getOrDefault(s, 0) + 1);
					seen.add(s);
				}
			}
		}

		ArrayList<String> sortedList = new ArrayList<>(map.keySet());
		Collections.sort(sortedList,(w1,w2) -> (map.get(w1)) != (map.get(w2)) ? map.get(w2)-map.get(w1) : w1.compareTo(w2));
		for(int i=0; i<topFeatures;i++){
			res.add(sortedList.get(i));
		}
		return res;
	}


	@Test
	public void testFrequentWords() {
		String[] keywords1 = { "anacell", "cetracular", "betacellular", "deltacellular","eurocell" };
		String[] reviews1 = { 
				"I love anacell Best services provided by anacell in the town",
				"betacellular has great services",
				"deltacellular provides much better services than betacellular",
				"cetracular is worse than eurocell",
				"betacellular is better than deltacellular" };
		ArrayList<String> res = new ArrayList<>();
		res.add("betacellular");
		res.add("anacell");
		Assert.assertEquals(res.toString(),popularNFeatures(5,2, Arrays.asList(keywords1), 5,Arrays.asList(reviews1)).toString());
	}
	
	@Test
	public void testFrequentWordsNoRepeat() {
		String[] keywords1 = { "anacell", "cetracular", "betacellular", "deltacellular","eurocell" };
		String[] reviews1 = { 
				"I love anacell Best services provided by anacell in the town",
				"betacellular has great services",
				"deltacellular provides much better services than betacellular",
				"cetracular is worse than eurocell",
				"betacellular is better than deltacellular" };
		ArrayList<String> res = new ArrayList<>();
		res.add("betacellular");
		res.add("deltacellular");
		Assert.assertEquals(res.toString(),popularNFeaturesNoRepeat(5,2, Arrays.asList(keywords1), 5,Arrays.asList(reviews1)).toString());

	}

}
