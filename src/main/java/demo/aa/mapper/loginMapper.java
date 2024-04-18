package demo.aa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface loginMapper {
    @Select("insert into login (username,password) values (#{username},#{password})")
    public void save(String username, String password);

    @Select("select password from login where username=#{username}")
    public String findPasswdByUname(String username);
}
