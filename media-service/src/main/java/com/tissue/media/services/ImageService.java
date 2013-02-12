package com.tissue.media.services;

import org.springframework.web.multipart.MultipartFile;
/**
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
*/
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;

import java.util.UUID;
import java.io.File;
import java.security.InvalidParameterException;

//@Component
public class ImageService {

    private String webRoot = "http://www.tissue.com/images/";
    private String rootPath = "/home/guoyingshou/working/github/images/";

    public void setRootPath(String rootPath) {
       this.rootPath = rootPath;
    }

    public String saveFile(MultipartFile file) {
        
        try {
        File tmpDir = new File(rootPath + "tmp");
        File dest = File.createTempFile("image-", "", tmpDir);
        String tmpName = dest.getAbsolutePath();
        System.out.println(tmpName);

        file.transferTo(dest);

        Info info = new Info(tmpName);

        ConvertCmd cmd = new ConvertCmd();
        IMOperation op = new IMOperation();
        op.addImage(tmpName);

        int w = info.getImageWidth();
        if(w > 650) {
            op.resize(640);
            //op.format("png");
        }
        
        String imgName = UUID.randomUUID().toString() + ".png";
        String name = rootPath + imgName;
        op.addImage(name);

        cmd.run(op);
        return webRoot + imgName;

        }
        catch(Exception exc) {
            throw new InvalidParameterException(exc.getMessage());
        }
        finally {

        }
    }
}
