package com.myFirstSite.imageBank.origin;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> imgs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Image> getImgs() {
        return imgs;
    }

    public void setImgs(Set<Image> imgs) {
        this.imgs = imgs;
    }
}
