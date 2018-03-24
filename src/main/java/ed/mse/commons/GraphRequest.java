package ed.mse.commons;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author Mohamad Nachabe
 *
 */
public class GraphRequest {
	
	public static final String OUTPUT_FORMAT_JSON = "json";
	public static final String OUTPUT_FORMAT_XML = "xml";
	
	private OverPassApi api;
	private double latitude1;
	private double longitude1;
	private double latitude2;
	private double longitude2;
	private String outputFormat; 
	private boolean debugMode;
	
	private GraphRequest(String outputFormat, double latitude1, double longitude1, double latitude2, double longitude2, boolean footway, boolean debugMode) { 
		api = new OverPassApiImpl();
		this.outputFormat = outputFormat;
		this.latitude1 = latitude1;
		this.longitude1 = longitude1;
		this.latitude2 = latitude2;
		this.longitude2 = longitude2;
		this.debugMode = debugMode;
	}
	
	public Graph<String, DefaultWeightedEdge> execute() {
		
		String query = "[out:".concat(outputFormat).concat("];(way(?,?,?,?)[highway];);(._;>;);out meta;");
		
		query = query.replaceFirst("\\?", String.valueOf(Math.min(latitude1, latitude2)));
		query = query.replaceFirst("\\?", String.valueOf(Math.min(longitude1, longitude2)));
		query = query.replaceFirst("\\?", String.valueOf(Math.max(latitude1, latitude2)));
		query = query.replaceFirst("\\?", String.valueOf(Math.max(longitude1, longitude2)));
		
		String result;
		
		result = api.callQuery(query);
		
		if(debugMode) {
			Logger.getLogger().log("Debug mode");
			query = query.replace(OUTPUT_FORMAT_JSON, OUTPUT_FORMAT_XML);
			String r = api.callQuery(query);
			
		    FileWriter fileWriter;
			try {
				Logger.getLogger().log("Creating xml file from response. dir=\"".concat(System.getProperty("user.home")).concat("\\OneDrive\\Desktop\\debug\\reponse.osm\""));
				fileWriter = new FileWriter(System.getProperty("user.home").concat("\\OneDrive\\Desktop\\debug\\reponse.osm"));
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(r);
				printWriter.close();
				Logger.getLogger().log("File created successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
		JSONParser parser = new JSONParser();
		return parser.parse(result);	
	}
	
	public static class Builder {
		private boolean footway;
		private String outputFormat; 
		private double latitude1;
		private double longitude1;
		private double latitude2;
		private double longitude2;
		private boolean debugMode = false;
		
		public Builder setStartLatitude(double latitude1) {
			this.latitude1 = latitude1;
			return this;
		}
		
		public Builder setEndLatitude(double latitude2) {
			this.latitude2 = latitude2;
			return this;
		}
		
		public Builder setStartLongitude(double longitude1) {
			this.longitude1 = longitude1;
			return this;
		}
		
		public Builder setEndLongitude(double longitude2) {
			this.longitude2 = longitude2;
			return this;
		}
		
		public Builder setOutputFormat(String outputFormat) {
			this.outputFormat = outputFormat;
			return this;
		}
		
		public Builder setFootway(boolean footway) {
			this.footway = footway;
			return this;
		}
		
		public Builder debugMode(boolean debugMode) {
			this.debugMode = debugMode;
			return this;
		}
		
		public GraphRequest build() {
			return new GraphRequest(outputFormat, latitude1, longitude1, latitude2, longitude2, footway, debugMode);
		}
	}
	
}
