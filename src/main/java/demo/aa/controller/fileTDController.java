package demo.aa.controller;

import demo.aa.entity.fileTDEntity;
import demo.aa.service.fileTDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class fileTDController {
    @Autowired
    public fileTDService fileTDService;

    @Autowired
    private Environment environment;

    fileTDEntity fileTDEntity;
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String filepath) {
        // 设置实际的文件路径
        PathResource file = new PathResource(filepath);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream")) // 设置文件的 MIME 类型
                .body(file);
    }
    @GetMapping("/delete")
    public String deleteFile(String fileName) {
        fileTDService.delete(fileName);
        return "Delete file success";
    }

    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {
        System.out.println("上传文件: " + file.getOriginalFilename()+" 大小: "+file.getSize());
        String filePath = environment.getProperty("savePath") + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        fileTDService.save(file.getOriginalFilename());
        return "success";
    }

    @GetMapping("/showAll")
    public List<fileTDEntity> showAll(){
        return fileTDService.showAll();
    }



}
