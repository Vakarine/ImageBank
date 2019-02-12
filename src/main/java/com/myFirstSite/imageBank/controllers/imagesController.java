package com.myFirstSite.imageBank.controllers;

import com.myFirstSite.imageBank.origin.Image;
import com.myFirstSite.imageBank.reposit.imageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//TODO поиск по нескольким тегам, тегу
@Controller
@RequestMapping("/posts")
public class imagesController {
    @Value("${upload.path}")
    private String uploadPath;
    private final imageRepos imageRepository;

    @Autowired
    public imagesController(imageRepos imageRepository) {
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public String showPosts(
            @RequestParam(required = false, defaultValue = "") String tag,
            Model model
    ) {
        Iterable<Image> posts;

        if (tag.isEmpty()) {
            posts = imageRepository.findAll();
        } else {
            posts = imageRepository.findByTag(tag);
        }

        model.addAttribute("posts", posts);
        return "main";
    }

    @PostMapping
    public String addPost(
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (!file.getOriginalFilename().isEmpty() && file != null && !tag.isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            Image imagePost = new Image();
            imagePost.setTag(tag);
            imagePost.setTitle(title);
            imagePost.setImageName(resultFilename);

            imageRepository.save(imagePost);
        }

        return "redirect:/posts";
    }
}
