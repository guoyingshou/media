package com.tissue.media.web.spring.controllers;

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
import java.security.Principal;

@Controller
public class ImageController {

    @RequestMapping(value="/images", method=POST)
    public String addImage(@RequestParam("CKEditorFuncNum") String num, MultipartFile file, Principal principal, Map model) {
        model.put("num", num);
        model.put("imageUrl", "http://localhost/images/1.jpg");
        //model.put("msg", "OK");
        return "uploadImageCallback"; 
    }

    @RequestMapping(value="/browseImages")
    public String browseImage(@RequestParam("CKEditorFuncNum") String num, Principal principal, Map model) {
        model.put("num", num);
        return "browseImageCallback"; 
    }

    @RequestMapping("/ttt") 
    public String test() {
        return "ttt";
    }

}
