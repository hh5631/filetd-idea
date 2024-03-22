package demo.aa.service;

import demo.aa.entity.fileTDEntity;
import demo.aa.mapper.fileTDMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class fileTDService {
    @Autowired
    public fileTDMapper fileTDMapper;

    public Resource loadFileAsResource(String filePath) {
        try {
            Path file = Paths.get(filePath);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("文件未找到");
            }
        } catch (IOException ex) {
            throw new RuntimeException("文件未找到", ex);
        }
    }
    public void save(String fileName){
        if(fileTDMapper.findByFileName(fileName)==null){
//            String filePath = "/home/hh/FileTD/Trans/" + fileName;
            String filePath = "C:\\Users\\19878\\hh\\Trans\\" + fileName;

            fileTDMapper.save(fileName,filePath);
        }
    }

    public List<fileTDEntity> showAll() {
        return fileTDMapper.showAll();
    }

    public void delete(String fileName) {
        fileTDMapper.deleteByFileName(fileName);
    }

}
