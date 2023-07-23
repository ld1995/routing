package by.ld1995.routing;

import by.ld1995.routing.model.Country;
import by.ld1995.routing.repository.CountryRepositoryImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileLoader implements ApplicationRunner {

  private final ObjectMapper mapper;
  private final CountryRepositoryImpl repository;

  @Value(Constants.FILE_NAME_PARAM)
  private String filename;

  @Override
  public void run(final ApplicationArguments args) throws Exception {
    // if the file does not exist or does not match to model the application should not start
    File file = loadFile(filename);
    if (file.exists()) {
      Map<String, Country> collect = mapper.readValue(file, new TypeReference<List<Country>>() {
      }).stream().collect(Collectors.toMap(Country::getCode, Function.identity()));
      repository.init(collect);
    } else {
      throw new RuntimeException("The file can't be handled " + file.getName());
    }
  }

  public File loadFile(final String filename) throws FileNotFoundException {
    return ResourceUtils.getFile(Constants.BASE_FOLDER + filename);
  }
}
