package com.tissue.media.web.spring.controllers;

import com.tissue.media.services.ImageService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.Map;
import java.util.Date;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/images", method=POST)
    public String addImage(@RequestParam("CKEditorFuncNum") String num, @RequestParam("upload") MultipartFile file, Map model) throws IOException {

        /**
        System.out.println("uploaded: " + file);
        String imgName = UUID.randomUUID().toString() + ".png";
        
        File dest = new File("../images/" + imgName);
        file.transferTo(dest);
        */

        try {
            String imageUrl = imageService.saveFile(file);
            model.put("imageUrl", imageUrl);
        }
        catch(Exception exc) {
            exc.printStackTrace();
            throw new InvalidParameterException("invalid op");
        }

        model.put("num", num);
        //model.put("imageUrl", "http://www.tissue.com/images/" + imgName);
        
        return "uploadImageCallback"; 
    }

    @RequestMapping(value="/browseImages")
    public String browseImage(@RequestParam("CKEditorFuncNum") String num, Map model) {
        model.put("num", num);
        return "browseImageCallback"; 
    }

    @RequestMapping("/ttt") 
    public String test() {
        return "ttt";
    }

}
