package com.myFirstSite.imageBank.origin;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;
    private String imageName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tagId")
    private Tag tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
