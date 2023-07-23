package by.ld1995.routing.service;

import by.ld1995.routing.service.search.RouteSearchEngine;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoutingService {

  private final RouteSearchEngine engine;
  private final CountryService countryService;

  public List<String> getRoute(final String origin, final String destination) {
    return engine.findRoute(origin, destination, countryService::getBorders);
  }

}
