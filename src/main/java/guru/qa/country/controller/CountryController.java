package guru.qa.country.controller;

import guru.qa.country.model.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CountryController {

  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/")
  public List<Country> getAll() {
    return countryService.getAllCountries();
  }

  @PostMapping("/add")
  public Country createCountry(@RequestBody Country country) {
    return countryService.saveCountry(country);
  }
  @PatchMapping("/{id}")
  public Country updateCountry(@PathVariable String id,
      @RequestBody Country country) {

    return countryService.updateCountry(id,country);
  }
}
