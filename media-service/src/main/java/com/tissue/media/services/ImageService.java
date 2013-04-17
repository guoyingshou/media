package com.tissue.media.services;

import org.springframework.web.multipart.MultipartFile;

//import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

@Component
public class ImageService {

    @Value("${dataRoot}")
    private String dataRoot;

    @Value("${webRoot}")
    private String webRoot;

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
        if(info.getImageWidth() > 480) {
            op.resize(480);
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
