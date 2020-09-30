package im.silen.vueboot.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from users where username = #{username}")
    User search(String username);

    @Insert("insert into users (username, password, enabled) values (#{username},#{password},1)")
    int insert(String username, String password);
}
