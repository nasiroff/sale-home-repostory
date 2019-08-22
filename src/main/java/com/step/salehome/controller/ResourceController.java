package com.step.salehome.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ResourceController {
    @Value("#{getImagePath}")
    private String imagePath;

    @RequestMapping("/uploads/{image-path}")
    @ResponseBody
    public byte[] getImageFromFileSystem(@PathVariable("image-path") String postImage) throws IOException {
        postImage = new String(DatatypeConverter.parseBase64Binary(postImage));
        Path img = Paths.get(imagePath, postImage);
        return Files.readAllBytes(img);
    }
}
