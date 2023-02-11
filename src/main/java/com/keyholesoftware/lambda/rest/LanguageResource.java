package com.keyholesoftware.lambda.rest;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.keyholesoftware.lambda.model.Language;
//
//@RestController
//public class LanguageResource {
//
//    @RequestMapping(path = "/languages", method = RequestMethod.GET)
//    public List<Language> listLambdaLanguages() {
//    	
//    	
//    	try {
//		      URL url = new URL("https://tradestie.com/api/v1/apps/reddit");
//		      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		      connection.setRequestMethod("GET");
//		      connection.setRequestProperty("Content-Type", "application/json");
//		      connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
//		      System.out.println("Making connections...");
//		      System.out.println("Recieving the response code...");
//		      int responseCode = connection.getResponseCode();
//		      System.out.println("RESPONSE CODE: " + responseCode);
//		      if (responseCode == 200) {
//		        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//		        String inputLine;
//		        StringBuffer response = new StringBuffer();
//		        while ((inputLine = in.readLine()) != null) {
//		          response.append(inputLine);
//		        }
//		        in.close();
//		        String ans = response.toString();
//		        
//				Gson gson = new Gson();
//				String s = "";
//				TradingData[] tradingData = gson.fromJson(ans, TradingData[].class);
//				for(TradingData data:tradingData) {
////					System.out.println(data);
//					s += ("ticker: " + data.ticker + " ");
//					s += ("sentinent: " + data.sentiment);
//					s += "\n";
////					System.out.print("ticker: " + data.ticker);
////					System.out.println(" & sentinent: " + data.sentiment);
//				}
//;
//		        return Arrays.asList(new Language(s));
////		        System.out.println(response.toString());
//		      } else {
//		    	  return Arrays.asList(new Language("Failed to fetch data. Response code: " + responseCode));
////		        return ("Failed to fetch data. Response code: " + responseCode);
//		      }
//		    } catch (IOException e) {
//		    	return Arrays.asList(new Language("Failed to fetch data. Exception: " + e.getMessage()));
////		      return ("Failed to fetch data. Exception: " + e.getMessage());
//		    }
//    	
//    	
////        return Arrays.asList(new Language("node"), new Language("java"), new Language("python"), new Language("scala"));
//    }
//
//
//}

