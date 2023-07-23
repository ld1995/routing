package by.ld1995.routing.repository;

import by.ld1995.routing.model.Country;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class CountryRepositoryImpl implements CountryRepository {

  private final Map<String, Country> countries = new HashMap<>();

  public void init(final Map<String, Country> countries) {
    this.countries.putAll(countries);
  }

  public Set<String> getBorders(final String countryCode) {
    if (countries.containsKey(countryCode)) {
      return Collections.unmodifiableSet(countries.get(countryCode).getBorders());
    } else {
      return Collections.emptySet();
    }
  }

}
