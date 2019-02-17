package com.myFirstSite.imageBank.reposit;

import com.myFirstSite.imageBank.origin.Tag;
import org.springframework.data.repository.CrudRepository;

public interface tagRepos extends CrudRepository<Tag, Integer> {
    Tag findOneByText(String text);
    //    можно _
}
