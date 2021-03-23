import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GetIssueData {
	public JSONArray getData(JsonArray arr) {
		JSONArray message = new JSONArray();	
		for(int i=0; i< arr.size();i++) {
			JsonObject jsonObject1 = arr.get(i).getAsJsonObject();
			JSONObject obj = new JSONObject();
			obj.put("issue_id",jsonObject1.get("number").getAsString());
			obj.put("issue",jsonObject1.get("title").getAsString());
			obj.put("state",jsonObject1.get("state").getAsString());
			message.put(obj);
			System.out.println(message);
		}
		return message;
	}
}
