package hello;

public class Test {
 
//  public static void main(String[] args) throws Exception {
//    String server = "https://rest.ensembl.org";
//    String ext = "/vep/human/region/9:22125503-22125502:1/C?";
//    
//    System.setProperty("https.proxyHost", "ptproxy.persistent.co.in");
//    System.setProperty("https.proxyPort", "8080");
//    System.setProperty("https.proxyUser", "build_master");
//    System.setProperty("https.proxyPassword", "cde3$RFV");
//    
//    URL url = new URL(server + ext);
// 
//    URLConnection connection = url.openConnection();
//    
//    HttpURLConnection httpConnection = (HttpURLConnection)connection;
//    
//    httpConnection.setRequestProperty("Content-Type", "application/json");
//    
// 
//    InputStream response = connection.getInputStream();
//    int responseCode = httpConnection.getResponseCode();
// 
//    if(responseCode != 200) {
//      throw new RuntimeException("Response code was not 200. Detected response was "+responseCode);
//    }
// 
//    String output;
//    Reader reader = null;
//    try {
//      reader = new BufferedReader(new InputStreamReader(response, "UTF-8"));
//      StringBuilder builder = new StringBuilder();
//      char[] buffer = new char[8192];
//      int read;
//      while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
//        builder.append(buffer, 0, read);
//      }
//      output = builder.toString();
//    } 
//    finally {
//        if (reader != null) try {
//          reader.close(); 
//        } catch (IOException logOrIgnore) {
//          logOrIgnore.printStackTrace();
//        }
//    }
// 
//    System.out.println(output);
//  }
}
