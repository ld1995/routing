package by.ld1995.routing.repository;

import java.util.Set;

public interface CountryRepository {

  Set<String> getBorders(String countryCode);

}
