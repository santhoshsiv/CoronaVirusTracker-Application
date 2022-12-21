package com.project.coronavirustracker.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.coronavirustracker.models.LocationStats;

public interface CoronaDataRepository extends JpaRepository<LocationStats, Integer> {
	
	
	@Query(value = "select sum(latest_total_cases)from location_stats where Country = :Country ",nativeQuery = true)
	public int findcasesByCountry(@Param("Country")String Country);

}
