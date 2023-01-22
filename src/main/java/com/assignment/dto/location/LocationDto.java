package com.assignment.dto.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LocationDto {

  private String country;
  private String wikiDataId;
  private String regionCode;
  private String city;
  private String countryCode;
  private String latitude;
  private String id;
  private String type;
  private String region;
  private String longitude;
  private String population;

}
