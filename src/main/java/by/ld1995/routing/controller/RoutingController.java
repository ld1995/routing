package by.ld1995.routing.controller;

import by.ld1995.routing.model.RouteResponse;
import by.ld1995.routing.service.RoutingService;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
@Validated
public class RoutingController {

  private final RoutingService routingService;

  @GetMapping("/routing/{origin}/{destination}")
  public ResponseEntity<RouteResponse> getRoute(
      final @PathVariable("origin") @NotBlank String origin,
      final @PathVariable("destination") @NotBlank String destination) {
    List<String> route = routingService.getRoute(origin, destination);
    if (route.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(new RouteResponse(route));
  }
}
