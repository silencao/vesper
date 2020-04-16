package im.silen.vueboot.mapper;

import im.silen.vueboot.pojo.City;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CityMapper {
    @Select("SELECT * FROM city where state = #{state}")
    City findByState(@Param("state") String state);
}
