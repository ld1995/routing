package by.ld1995.routing.service.search;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class BreadthFirstSearch implements RouteSearchEngine {

  @Override
  public List<String> findRoute(final String source, final String destination,
      final Function<String, Collection<String>> nodeProvider) {
    Queue<String> queue = new LinkedList<>();
    Map<String, String> path = new HashMap<>();
    queue.add(source);
    while (!queue.isEmpty()) {
      String head = queue.remove();
      if (head.equals(destination)) {
        return getRoute(path, source, destination);
      }
      Collection<String> borders = nodeProvider.apply(head);
      if (Objects.nonNull(borders)) {
        for (String border : borders) {
          if (!path.containsKey(border)) {
            queue.add(border);
            path.put(border, head);
          }
          if (border.equals(destination)) {
            return getRoute(path, source, destination);
          }
        }
      }
    }
    return Collections.emptyList();
  }

  private List<String> getRoute(final Map<String, String> path, final String source,
      final String destination) {
    Deque<String> result = new LinkedList<>();
    result.add(destination);
    while (!source.equals(result.getFirst())) {
      result.addFirst(path.get(result.getFirst()));
    }
    return new ArrayList<>(result);
  }

}
