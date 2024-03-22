package demo.aa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface loginMapper {
    @Select("insert into login (username,password,photo) values (#{username},#{password},'1')")
    public void save(String username, String password);

    @Select("select password from login where username=#{username}")
    public String findPasswdByUname(String username);
}
