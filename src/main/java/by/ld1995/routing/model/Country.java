package by.ld1995.routing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import java.util.TreeSet;
import lombok.Data;

@Data
public class Country {

  @JsonProperty("cca3")
  private String code;
  private Set<String> borders = new TreeSet<>();
}
