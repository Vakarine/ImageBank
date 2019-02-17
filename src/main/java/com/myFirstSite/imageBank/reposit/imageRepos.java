package com.myFirstSite.imageBank.reposit;

import com.myFirstSite.imageBank.origin.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface imageRepos extends CrudRepository<Image, Integer> {
}
