package by.ld1995.routing.service;

import by.ld1995.routing.repository.CountryRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CountryService {

  private final CountryRepository repository;

  public Set<String> getBorders(final String countryCode) {
    return repository.getBorders(countryCode);
  }

}
