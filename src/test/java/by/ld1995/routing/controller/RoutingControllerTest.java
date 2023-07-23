package by.ld1995.routing.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.ld1995.routing.service.RoutingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@WebMvcTest(RoutingController.class)
class RoutingControllerTest {

  @Autowired
  protected ObjectMapper mapper;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private RoutingService service;

  @BeforeEach
  void resetMocks() {
    Mockito.reset(service);
  }

  @Nested
  @DisplayName("Testing [getRoute(...) method]")
  class getRoute {

    @Test
    void getRouteWithOK() throws Exception {
      // GIVEN request with params
      String origin = "CZE";
      String destination = "ITA";
      MockHttpServletRequestBuilder requestBuilder = get("/routing/CZE/ITA");
      when(service.getRoute(ArgumentMatchers.eq(origin),
          ArgumentMatchers.eq(destination))).thenReturn(List.of("CZE", "AUT", "ITA"));

      // WHEN trying to get response
      ResultActions result = mockMvc.perform(requestBuilder);

      // THEN get OK response and expected data
      result
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.route", hasSize(3)))
          .andExpect(jsonPath("$.route[0]", is("CZE")))
          .andExpect(jsonPath("$.route[1]", is("AUT")))
          .andExpect(jsonPath("$.route[2]", is("ITA")));
    }

    @Test
    void getRouteWithBadRequest() throws Exception {
      // GIVEN request with wrong params
      MockHttpServletRequestBuilder requestBuilder = get("/routing/CZE/GBR");
      when(service.getRoute(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(
          Collections.emptyList());

      // WHEN trying to get response
      ResultActions result = mockMvc.perform(requestBuilder);

      // THEN get OK response and expected data
      result
          .andExpect(status().isBadRequest());
    }
  }
}