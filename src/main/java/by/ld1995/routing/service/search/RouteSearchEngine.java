package by.ld1995.routing.service.search;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface RouteSearchEngine {

  List<String> findRoute(String source, String destination,
      Function<String, Collection<String>> nodeProvider);

}
