package com.assignment.dto.location;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LocationResDto {

  private List<LocationDto> data = new ArrayList<>();

}
