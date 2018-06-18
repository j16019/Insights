package insights;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;

public class Insights_main {

  public static void main(String[] args) {
	  String text = "Call me Ishmael. Some years ago-never mind how long "
		        + "precisely-having little or no money in my purse, and nothing "
		        + "particular to interest me on shore, I thought I would sail about "
		        + "a little and see the watery part of the world. It is a way "
		        + "I have of driving off the spleen and regulating the circulation. "
		        + "Whenever I find myself growing grim about the mouth; whenever it "
		        + "is a damp, drizzly November in my soul; whenever I find myself "
		        + "involuntarily pausing before coffin warehouses, and bringing up "
		        + "the rear of every funeral I meet; and especially whenever my "
		        + "hypos get such an upper hand of me, that it requires a strong "
		        + "moral principle to prevent me from deliberately stepping into "
		        + "the street, and methodically knocking people's hats off-then, "
		        + "I account it high time to get to sea as soon as I can.";

	  Insights_lib ilib = new Insights_lib(text);
	  Profile profile = ilib.getProfile();
	  System.out.println(profile);


	  JsonNode node = ilib.getJsonNode(profile);

	  List<Double> personality_percentile = new ArrayList<Double>();
	  List<List<Double>> personality_children_percentile = new ArrayList<List<Double>>();

	  for(JsonNode j : node.get("personality")){
		  personality_percentile.add(j.get("percentile").asDouble());
	  }

	  for(JsonNode j : node.get("personality")){
		  List<Double> work = new ArrayList<Double>();

		  for(JsonNode k : j.get("children")){
			  work.add(k.get("percentile").asDouble());
		  }

		  personality_children_percentile.add(work);
	  }

	  MySQL mysql = new MySQL();
	  mysql.updateImage(personality_percentile, personality_children_percentile, text);
  }

}