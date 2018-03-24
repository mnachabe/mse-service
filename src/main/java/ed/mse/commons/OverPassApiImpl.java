package ed.mse.commons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Mohamad Nachabe
 *
 */
public class OverPassApiImpl implements OverPassApi {
	
	private static final String URL = "http://overpass-api.de/api/";
	private HttpClient client;

	public OverPassApiImpl() {
		client = HttpClientBuilder.create().build();
	}
	
	public String callQuery(String query) {
		try { 
			query = URLEncoder.encode(query, "UTF-8");
			HttpGet post = new HttpGet(URL.concat("interpreter?data=").concat(query));		
			Logger.getLogger().log("Sending request...");
			HttpResponse response = client.execute(post);
	
			Logger.getLogger().log("Response Code : " 
	                + response.getStatusLine().getStatusCode());
			
			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
	
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
