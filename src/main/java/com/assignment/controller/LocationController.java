package com.assignment.controller;

import com.assignment.AppConstant;
import com.assignment.dto.location.LocationDto;
import com.assignment.exception.SearchException;
import com.assignment.response.GenericResponse;
import com.assignment.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstant.LOCATION_URL)
@CrossOrigin(allowedHeaders = "*", originPatterns = "*")
public class LocationController {

  private LocationService locationService;

   public LocationController(LocationService locationService){
    this.locationService = locationService;
  }

  @GetMapping("search")
  public GenericResponse getSearch(@RequestParam String searchKey) throws JsonProcessingException, UnirestException {
    return locationService.getSearch(searchKey);
  }

  @PostMapping("/save")
  public GenericResponse saveLocation(@RequestBody LocationDto location) throws SearchException {
    return locationService.saveLocation(location);
  }

  @DeleteMapping("/delete-all")
  public GenericResponse deleteAll() throws SearchException {
    return locationService.deleteAll();
  }

}
