package andy.web.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class WoGServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatastoreService  srvc=DatastoreServiceFactory.getDatastoreService();
		//byte[] data=Files.readAllBytes(Paths.get("genesis.txt"));
		//List<String> lines=Files.readAllLines(Paths.get("genesis.txt"), StandardCharsets.UTF_8);
		loadData(srvc,"genesis.txt");
		resp.setContentType("text/plain");
		resp.getWriter().println("Saved data");
	}

	private void loadData(DatastoreService srvc, String fileName)
			throws IOException, JsonParseException, JsonMappingException {
		BufferedInputStream bin= null;
		try{
		InputStream in=WoGServlet.class.getClassLoader().getResourceAsStream(fileName);
		bin=new BufferedInputStream(in);
		int num=bin.available();
		byte[] data=new byte[num];
		bin.read(data, 0, num);
		ObjectMapper mapper=new ObjectMapper();
		List<Map<String,String>> list=mapper.readValue(data, new TypeReference<ArrayList<LinkedHashMap<String, String>>>() {});
		//System.out.println("Result: "+list);
		for(Map aMap: list){
			String book = String.valueOf(aMap.get("book"));
			String chap = String.valueOf(aMap.get("chapter"));
			String verse = String.valueOf(aMap.get("verse"));
			String word  = String.valueOf(aMap.get("text"));
			//System.out.println(aMap);
			Entity obj=new Entity("Bible");
			obj.setProperty("book", book);
			obj.setProperty("chapter", Integer.parseInt(chap));
			obj.setProperty("verse", Integer.parseInt(verse));
			obj.setProperty("content", word);
			//System.out.println(obj);
			//srvc.put(obj);
		}
		}finally{
			if(bin != null){
				bin.close();
			}
			
		}
	}
	

}
