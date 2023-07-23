package by.ld1995.routing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import by.ld1995.routing.service.search.BreadthFirstSearch;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BreadthFirstSearchTest {

  CountryService service = Mockito.mock(CountryService.class);

  @Test
  void searchFromCZEtoITA() {
    BreadthFirstSearch search = new BreadthFirstSearch();

    String source = "CZE";
    String destination = "ITA";

    when(service.getBorders(eq(source))).thenReturn(Set.of("AUT",
        "DEU",
        "POL",
        "SVK"));
    when(service.getBorders(eq("AUT"))).thenReturn(Set.of("CZE",
        "DEU",
        "HUN",
        "ITA",
        "LIE",
        "SVK",
        "SVN",
        "CHE"));
    when(service.getBorders(eq("SVK"))).thenReturn(Set.of("POL",
        "BLR",
        "CZE",
        "DEU",
        "LTU",
        "RUS",
        "SVK",
        "UKR"));
    when(service.getBorders(eq("POL"))).thenReturn(Set.of("SVK",
        "AUT",
        "CZE",
        "HUN",
        "POL",
        "UKR"));
    when(service.getBorders(eq("DEU"))).thenReturn(Set.of("AUT",
        "BEL",
        "CZE",
        "DNK",
        "FRA",
        "LUX",
        "NLD",
        "POL",
        "CHE"));
    when(service.getBorders(eq(destination))).thenReturn(Set.of("AUT",
        "FRA",
        "SMR",
        "SVN",
        "CHE",
        "VAT"));

    List<String> result = search.findRoute(source, destination, service::getBorders);
    assertEquals(List.of("CZE", "AUT", "ITA"), result);
  }

  @Test
  void searchFromPOLtoUKR() {
    BreadthFirstSearch search = new BreadthFirstSearch();

    String source = "POL";
    String destination = "UKR";

    when(service.getBorders(eq(source))).thenReturn(Set.of("SVK",
        "AUT",
        "CZE",
        "HUN",
        "POL",
        "UKR"));
    when(service.getBorders(eq(destination))).thenReturn(Set.of("BLR",
        "HUN",
        "MDA",
        "POL",
        "ROU",
        "RUS",
        "SVK"));

    List<String> result = search.findRoute(source, destination, service::getBorders);
    assertEquals(List.of("POL", "UKR"), result);
  }

  @Test
  void searchFromFRAtoGBR() {
    BreadthFirstSearch search = new BreadthFirstSearch();

    String source = "FRA";
    String destination = "GBR";

    when(service.getBorders(eq(source))).thenReturn(Set.of("AND",
        "BEL",
        "DEU",
        "ITA",
        "LUX",
        "MCO",
        "ESP",
        "CHE"));
    when(service.getBorders(eq(destination))).thenReturn(Set.of("IRL"));

    List<String> result = search.findRoute(source, destination, service::getBorders);
    assertEquals(Collections.emptyList(), result);
  }
}