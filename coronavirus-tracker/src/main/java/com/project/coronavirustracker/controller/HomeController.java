package com.project.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.coronavirustracker.Repo.CoronaDataRepository;
import com.project.coronavirustracker.models.LocationStats;


@Controller
@EnableAutoConfiguration
public class HomeController {
	
	
	@Autowired
    CoronaDataRepository repository; 
    
	
	@GetMapping("/")
	 public String mapper() {
		 
		 return "EntryPage";
	 }
	 
	 
	 @PostMapping("corona status")
	public String home(Model m) {
	

		Iterable<LocationStats>stats=repository.findAll();
		int totalReportedCases=0;
		for (LocationStats locationStats : stats) {
			totalReportedCases=totalReportedCases+locationStats.getLatestTotalCases();
		}
		int totalNewCases =0;
		for (LocationStats locationStats : stats) {
			totalNewCases=totalNewCases+locationStats.getDiffFromPrevDay();
		}
		m.addAttribute("totalReportedCases", totalReportedCases);
		m.addAttribute("locationstats",stats );
 		m.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
	 
	 
	 @PostMapping("corona cases")
	 public String casesByCountry(@RequestParam String Country,Model m) {
		 
		 List<LocationStats>stats=repository.findAll();
		
		 m.addAttribute("casesByCountry",repository.findcasesByCountry(Country) );
		 m.addAttribute("Country", Country);
		 m.addAttribute("LocationStat",stats);
		 
		 return "result";
	 }
	 
	 
	 @PostMapping("new cases")
	 public String diffFromPreviousCases(Model m) {
			Iterable<LocationStats>stats=repository.findAll();
			int totalNewCases =0;
			for (LocationStats locationStats : stats) {
				totalNewCases=totalNewCases+locationStats.getDiffFromPrevDay();
			}
			m.addAttribute("totalNewCases", totalNewCases);
			
		 return "newCases";
	 }
}
