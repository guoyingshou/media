package com.tissue.media.web.spring.controllers;

import com.tissue.core.Account;
import com.tissue.commons.services.ViewerService;
import com.tissue.media.services.ImageService;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ImageController {

    private static Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ViewerService viewerService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/images/_create", method=POST)
    public String addImage(@RequestParam("CKEditorFuncNum") String num, @RequestParam("upload") MultipartFile file, Map model) {

        Account viewerAccount = viewerService.getViewerAccount();

        List<String> images = Arrays.asList("image/jpeg", "image/png", "image/gif");
        String type = file.getContentType();
        if(!images.contains(type)) {
            throw new IllegalArgumentException(type + " is not supported");
        }

        try {
            String imageUrl = imageService.saveFile(file, viewerAccount.getUsername());
            model.put("imageUrl", imageUrl);
            model.put("num", num);
        }
        catch(Exception exc) {
            throw new IllegalArgumentException(exc.getMessage());
        }

        return "uploadImageCallback"; 
    }

    /**
    @RequestMapping(value="/browseImages")
    public String browseImage(@RequestParam("CKEditorFuncNum") String num, Map model) {
        model.put("num", num);
        return "browseImageCallback"; 
    }
    */

}
