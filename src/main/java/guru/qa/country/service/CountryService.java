package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.model.Country;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

  private final CountryRepository countryRepository;

  @Autowired
  public CountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  public List<Country> getAllCountries() {
    return countryRepository.findAll()
        .stream()
        .map(Country::fromEntity)
        .toList();
  }

  public Country saveCountry(Country country) {
    return Country.fromEntity(
        countryRepository.save(
            Country.fromJson(country)));
  }

  public Country updateCountry(String id, Country countryPatch) {
    CountryEntity modifiedCountry =  countryRepository
        .findById(UUID.fromString(id))
        .orElseThrow(RuntimeException::new); //not implemented Exception
    modifiedCountry.setCountryName(countryPatch.countryName());
    modifiedCountry.setCountryCode(countryPatch.countryCode());

    return Country.fromEntity(countryRepository.save(modifiedCountry));
  }
}
