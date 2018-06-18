package insights;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.discovery.v1.model.CreateCollectionOptions.Language;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;

public class Insights_lib {
	private PersonalityInsights service;
	private ProfileOptions options = null;

	public Insights_lib(String text){
		service = new PersonalityInsights("2016-10-19");
		service.setUsernameAndPassword("a581bbcb-3a90-4aad-b66d-637e50b1afcb", "ChGqITJ6HT7Q");

		options = new ProfileOptions.Builder()
		    	/*
		    	.contentLanguage(Language.JA)//日本語入力
		    	*/
		    	.acceptLanguage(Language.JA)//日本語出力
		        .text(text)
		        .build();
	}

	public Profile getProfile(){
		Profile profile = service.profile(options).execute();
		return profile;
	}

	public JsonNode getJsonNode(Profile profile){
		JsonNode node = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			  node = mapper.readTree(profile.toString());
		  } catch (IOException e) {
			  // TODO 自動生成された catch ブロック
			  e.printStackTrace();
		  }

		return node;
	}

}
