package com.assignment.service.impl;

import com.assignment.dto.location.LocationDto;
import com.assignment.dto.location.LocationResDto;
import com.assignment.es.Locations;
import com.assignment.exception.SearchException;
import com.assignment.repo.LocationsRepo;
import com.assignment.response.GenericResponse;
import com.assignment.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(LocationServiceImpl.class);

  private ObjectMapper objectMapper;

  private LocationsRepo locationsRepo;
    LocationServiceImpl(ObjectMapper objectMapper, LocationsRepo locationsRepo){
    this.objectMapper = objectMapper;
    this.locationsRepo = locationsRepo;
  }

  @Override
  public GenericResponse getSearch(String searchKey) throws JsonProcessingException, UnirestException {

    LOGGER.info("getSearch searchKey : {}", searchKey);

    HttpResponse<String> response = Unirest.get("https://wft-geo-db.p.rapidapi.com/v1/geo/cities")
      .queryString("namePrefix", searchKey)
      .queryString("countryIds", "IN")

      .header("X-RapidAPI-Key", "d65b35dd6dmsh9070d9b57df7e13p15ac42jsnc2095aac1e44")
      .header("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
      .asString();

    LOGGER.info("location body: {}",response.getBody());
    LocationResDto locationRes = objectMapper.readValue(response.getBody(), LocationResDto.class);
    LOGGER.info("getSearch location: {}",locationRes.getData());

    return new GenericResponse(HttpStatus.OK, locationRes.getData());
  }

  @Override
  public GenericResponse saveLocation(LocationDto location)  {

    LOGGER.info("getSavedLocations location: {}", location);

    if(! locationsRepo.existsByCityAndRegionCodeAndCountryCode(location.getCity(),
      location.getRegionCode(), location.getCountryCode())){

      Locations locations = new Locations(location);

      locationsRepo.save(locations);

    }

    return new GenericResponse(HttpStatus.OK, locationsRepo.findAllByOrderByCreatedDateAsc());
  }

  @Override
  public GenericResponse deleteAll() {
    locationsRepo.deleteAll();
    return new GenericResponse(HttpStatus.OK, "Cleared all the locations");
  }
}
