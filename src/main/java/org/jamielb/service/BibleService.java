package org.jamielb.service;

import java.util.List;

import org.jamielb.dao.BibleDao;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class BibleService {

    @Inject
    BibleDao dao;

    public List<String> getBooks() {
        return dao.getBookNames();
    }

}
