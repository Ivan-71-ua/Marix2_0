package com.aplication.maxtrix2_0.service;

import com.aplication.maxtrix2_0.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<String> getAllCategories() {
        return categoryRepository.findAllCategories();
    }
}
