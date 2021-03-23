import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class IssueDetails {
	public JSONObject getData(String id,JsonArray arr) {
		JSONObject obj = new JSONObject();	
		
		for(int i=0; i< arr.size();i++) {
			JsonObject jsonObject1 = arr.get(i).getAsJsonObject();
			if(jsonObject1.get("number").getAsString().equalsIgnoreCase(id)) {
			obj.put("issue_id",jsonObject1.get("number").getAsString());
			obj.put("id",jsonObject1.get("id").getAsString());
			obj.put("created",jsonObject1.get("created_at").getAsString());
			obj.put("updated",jsonObject1.get("updated_at").getAsString());
			obj.put("author",jsonObject1.get("author_association").getAsString());
			obj.put("body",jsonObject1.get("body").getAsString());
			obj.put("url",jsonObject1.get("html_url").getAsString());
			System.out.println(obj);
			return obj;
			}
		}
		return obj;
	}
	
  
}
