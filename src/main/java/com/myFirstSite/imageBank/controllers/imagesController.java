package com.myFirstSite.imageBank.controllers;

import com.myFirstSite.imageBank.origin.Image;
import com.myFirstSite.imageBank.origin.Tag;
import com.myFirstSite.imageBank.reposit.imageRepos;
import com.myFirstSite.imageBank.reposit.tagRepos;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

//TODO поиск по нескольким тегам, тегу
@Controller
@RequestMapping("/posts")
public class imagesController {
    @Value("${upload.path}")
    private String uploadPath;

    private final imageRepos imageRepository;
    private final tagRepos tagRepository;

    @Autowired
    public imagesController(imageRepos imageRepository, tagRepos tagRep) {
        this.imageRepository = imageRepository;
        this.tagRepository = tagRep;
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
            posts = tagRepository.findOneByText(tag).getImgs(); //TODO
        }

        model.addAttribute("statusPosts", "active");
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
            File uploadDir = new File(uploadPath + "/" + tag);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String resultFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + tag + "/" + resultFilename));

            createImage(title, tag, resultFilename);
        } else {
            createImage(title, tag, null);
        }


        return "redirect:/posts";
    }

    @GetMapping("/clear")
    public String deleteImagesPosts() throws IOException {
        Iterable<Tag> tagsAll = tagRepository.findAll();

        deleteDirs(tagsAll);

        imageRepository.deleteAll();
        tagRepository.deleteAll();

        return "redirect:/posts";
    }

    @GetMapping("/tags")
    public String tagsTable(Model model) {
        //TODO
        Iterable<Tag> allTags = tagRepository.findAll();

        model.addAttribute("tags", allTags);
        return "tagsList";
    }

    private void createImage(@RequestParam String title, @RequestParam String tag, String resultFilename) {
        Image imagePost = new Image();

        Tag newTag = initTag(tag);

        imagePost.setTag(newTag);
        imagePost.setTitle(title);
        imagePost.setImageName(resultFilename);

        imageRepository.save(imagePost);
    }

    private Tag initTag(@RequestParam String tag) {
        Tag tags = tagRepository.findOneByText(tag);

        if (tags!= null) {
            return tags;
        } else {
            tags = new Tag();
            tags.setText(tag);

            tagRepository.save(tags);

            return tags;
        }
    }

    private void deleteDirs(Iterable<Tag> tagsAll) throws IOException {
        for (Tag tag : tagsAll) {
            String pathWithTag = uploadPath + "/" + tag.getText();

            for (Image image : tag.getImgs()) {
                if (image.getImageName() != null) {
                    Files.delete(Paths.get(pathWithTag + "/" + image.getImageName()));
                }
            }

            Files.delete(Paths.get(pathWithTag));
        }
    }
    /*
    Получить из тегов посты, удалить их последовательно, потом директорию
     */
}
