package com.assignment.repo;

import com.assignment.es.Locations;
import com.assignment.response.GenericResponse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface LocationsRepo extends ElasticsearchRepository<Locations, String> {

  List<Locations> findAllByOrderByCreatedDateAsc();

  boolean existsByCityAndRegionCodeAndCountryCode(String city, String regionCode, String countryCode);
}
