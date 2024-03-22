package demo.aa.mapper;

import demo.aa.entity.fileTDEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface fileTDMapper {
    @Select("insert into fileTD (fileName,filePath) values (#{fileName},#{filePath})")
    public void save(String fileName,String filePath);

    @Select("select * from fileTD where fileName=#{fileName}")
    public fileTDEntity findByFileName(String fileName);

    @Select("select * from fileTD")
    public List<fileTDEntity> showAll();
    @Select("delete from fileTD where fileName=#{fileName}")
    public void deleteByFileName(String fileName);


}
