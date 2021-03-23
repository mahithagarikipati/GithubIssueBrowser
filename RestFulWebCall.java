import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.*;
@WebServlet("/HomeService")
public class RestFulWebCall extends HttpServlet{
		private static final long serialVersionUID = 1L;
		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
			handleRequest(req,res);
		}
		@SuppressWarnings("unused")
		public void handleRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException{
			JSONObject obj = new JSONObject();
			String userName = null;
			String mode = null;
			String response= null;
			JSONArray data = new JSONArray();
			StringBuilder sb = new StringBuilder();
			GetIssueData gd = new GetIssueData();
			IssueDetails details = new IssueDetails();
			try {
				URL url = new URL("https://api.github.com/repos/walmartlabs/thorax/issues");
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Accept", "application/json");
		        if (conn.getResponseCode() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		                    + conn.getResponseCode());
		        }
		        BufferedReader br = new BufferedReader(new InputStreamReader(
		            (conn.getInputStream())));

		        String output;
		        System.out.println("Output from Server .... \n");
		        while ((output = br.readLine()) != null) {
		            System.out.println(output);
		            sb.append(output);
		        }
		        JsonArray jArray = new JsonParser().parse(sb.toString()).getAsJsonArray();
		        System.out.println("output is" +jArray);
		        conn.disconnect();
		        String issueid = req.getParameter("issueId");
		        mode = req.getParameter("mode");
		        if(mode.equalsIgnoreCase("GETDATA")) {
		        	obj= details.getData(issueid,jArray);
		        	res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8"); 
					PrintWriter out = res.getWriter();
					out.print(obj);
		        }
		        else {
		        data = gd.getData(jArray);
		        res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8"); 
				PrintWriter out = res.getWriter();
				out.print(data);
		        }
		        
				res.setStatus(200);
						
			}
			catch(Exception e) {
				System.out.println("Exceprion in Rest Service!!! "+e);
			}
		}

		
	}


