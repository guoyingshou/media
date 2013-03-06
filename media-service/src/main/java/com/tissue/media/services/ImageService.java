package com.tissue.media.services;

import org.springframework.web.multipart.MultipartFile;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class ImageService {

    private String webRoot = "http://www.tissue.com/images/";
    private String dataRoot = "/home/guoyingshou/working/github/images/";

    public void setWebRoot(String rootPath) {
       this.webRoot = webRoot;
    }

    public void setDataRoot(String dataRoot) {
       this.dataRoot = dataRoot;
    }

    public String saveFile(MultipartFile multipartFile, String username) throws Exception {

        String imageName = UUID.randomUUID().toString() + ".png";

        Path dir = Paths.get(dataRoot);

        Path userRoot = dir.resolve(username);
        Path rawDataDir = userRoot.resolve("raw");

        if(Files.notExists(rawDataDir)) {
            Files.createDirectories(rawDataDir);
        }

        Path rawFile = rawDataDir.resolve(multipartFile.getOriginalFilename());
        multipartFile.transferTo(rawFile.toFile());

        ConvertCmd cmd = new ConvertCmd();

        IMOperation op = new IMOperation();
        op.addImage(rawFile.toString());

        Info info = new Info(rawFile.toString());
        if(info.getImageWidth() > 600) {
            op.resize(600);
        }

        Path imageFile = userRoot.resolve(imageName);
        op.addImage(imageFile.toString());

        IMOperation pngTargetConverter = new IMOperation();
        pngTargetConverter.format("PNG");
        pngTargetConverter.addOperation(op);

        cmd.run(pngTargetConverter);

        return webRoot + "/" + username + "/" + imageName;
    }
}
