package com.project.coronavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.coronavirustracker.Repo.CoronaDataRepository;
import com.project.coronavirustracker.models.LocationStats;



@Service
public class CoronavirusDataservice {
	
	@Autowired
	CoronaDataRepository repository;
	@Autowired
	LocationStats locationStats;
	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	@Scheduled(cron = "* * 1 * * *")
	@PostConstruct
  public void fetchVirusData() throws IOException, InterruptedException {
	
	 HttpClient client= HttpClient.newHttpClient();
	 HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
	 
	 HttpResponse<String> httpResponse= client.send(request, HttpResponse.BodyHandlers.ofString());
	
	 StringReader csvBodyReader = new StringReader(httpResponse.body());
	 
	
	 Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
	 int id=1;
	 for (CSVRecord record : records) {
	     locationStats.setState(record.get("Province/State"));
	     locationStats.setCountry(record.get("Country/Region"));
	     locationStats.setId(id++);
	     int  latestCases = Integer.parseInt(record.get(record.size()-1));
	     int prevDayCases = Integer.parseInt(record.get(record.size()-2));
	     locationStats.setLatestTotalCases(latestCases);
	     locationStats.setDiffFromPrevDay(latestCases - prevDayCases);
	     repository.save(locationStats);
	 }
	
  }
}

