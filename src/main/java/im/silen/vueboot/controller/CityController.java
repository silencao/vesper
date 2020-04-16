package im.silen.vueboot.controller;

import im.silen.vueboot.mapper.CityMapper;
import im.silen.vueboot.pojo.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    private final CityMapper cityMapper;

    public CityController(CityMapper cityMapper) {
        this.cityMapper = cityMapper;
    }

    @GetMapping("/cities/{state}")
    City getCity(@PathVariable String state) {
        return cityMapper.findByState(state);
    }
}
