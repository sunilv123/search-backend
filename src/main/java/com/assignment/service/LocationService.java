package com.assignment.service;

import com.assignment.dto.location.LocationDto;
import com.assignment.exception.SearchException;
import com.assignment.response.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface LocationService {


  GenericResponse getSearch(String searchKey) throws JsonProcessingException, UnirestException;

  GenericResponse saveLocation(LocationDto location) throws SearchException;

  GenericResponse deleteAll();
}
