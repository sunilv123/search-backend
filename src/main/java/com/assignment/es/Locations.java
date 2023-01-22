package com.assignment.es;

import com.assignment.dto.location.LocationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "location_info", createIndex = true)
@NoArgsConstructor
public class Locations {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "city")
    private String city;

  @Field(name = "created_date", type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern = "uuuu-MM-dd'T'HH:mm:ss")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="uuuu-MM-dd HH:mm:ss")
  private LocalDateTime createdDate = LocalDateTime.now();

  @Field(type = FieldType.Text, name = "country")
  private String country;

  @Field(type = FieldType.Text, name = "region_code")
  private String regionCode;

  @Field(type = FieldType.Text, name = "country_code")
  private String countryCode;

  @Field(type = FieldType.Text, name = "type")
  private String type;

  @Field(type = FieldType.Text, name = "region")
  private String region;

  @Field(type = FieldType.Float, name = "longitude")
  private String longitude;

  @Field(type = FieldType.Float, name = "latitude")
  private String latitude;

  public Locations(LocationDto locations){

    this.country = locations.getCountry();
    this.regionCode = locations.getRegionCode();
    this.city = locations.getCity();
    this.countryCode = locations.getCountryCode();
    this.type = locations.getType();
    this.region = locations.getRegion();
    this.latitude = locations.getLatitude();
    this.longitude = locations.getLongitude();

  }

}
