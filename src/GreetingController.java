package hello;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@CrossOrigin(maxAge=10)
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

	@GetMapping("/greeting-javaconfig")
	public Greeting greetingWithJavaconfig(@RequestParam(required= false, value="name", defaultValue="World") String name){
		return new Greeting(counter.incrementAndGet(),
				String.format(template, name));
	}

	@GetMapping("variant/{coords}/{alt}")
	public Variant variantInfo(@PathVariable String coords, @PathVariable String alt) throws IOException{
		System.out.println(coords+" "+alt);
		String server = "https://rest.ensembl.org";
		//String ext = "/vep/human/region/9:22125503-22125502:1/C?";
		String ext = "/vep/human/region/"+coords+"/"+alt+"?";

		System.setProperty("https.proxyHost", "ptproxy.persistent.co.in");
		System.setProperty("https.proxyPort", "8080");
		System.setProperty("https.proxyUser", "build_master");
		System.setProperty("https.proxyPassword", "cde3$RFV");

		URL url = new URL(server + ext);

		URLConnection connection = url.openConnection();
		HttpURLConnection httpConnection = (HttpURLConnection)connection;

		httpConnection.setRequestProperty("Content-Type", "application/json");


		InputStream response = connection.getInputStream();
		int responseCode = httpConnection.getResponseCode();

		if(responseCode != 200) {
			throw new RuntimeException("Response code was not 200. Detected response was "+responseCode);
		}

		String output;
		Reader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			output = builder.toString();
		} 
		finally {
			if (reader != null) try {
				reader.close(); 
			} catch (IOException logOrIgnore) {
				logOrIgnore.printStackTrace();
			}
		}

		System.out.println(output);

		//  ObjectMapper mapper = new ObjectMapper();
		//  JsonNode actualObj = mapper.readTree(output);

		return new Variant(output);



	}
}
