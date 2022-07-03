package com.lucasf.springdevforum.domain.services;

import com.lucasf.springdevforum.domain.entities.Category;
import com.lucasf.springdevforum.application.exceptions.ObjectNotFoundException;
import com.lucasf.springdevforum.infraestructure.repositories.CategoryRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceUnitTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    @DisplayName("Given categories Page, When get all categories, Then categories Page is returned")
    public void test1(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        Page<Category> categories = createCategoryPage(pageable);
        Mockito.when(categoryRepository.findAll(pageable)).thenReturn(categories);

        // When
        Page<Category> result = categoryService.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(categories);
    }

    @Test
    @DisplayName("Given empty categories Page, When get all categories, Then empty list is returned")
    public void test2(){
        // Given
        Pageable pageable = PageRequest.of(1, 2);
        Page<Category> categories = createEmptyCategoryPage(pageable);
        Mockito.when(categoryRepository.findAll(pageable)).thenReturn(categories);

        // When
        Page<Category> result = categoryService.findAll(pageable);

        // Then
        assertThat(result).isEqualTo(categories);
    }

    @Test
    @DisplayName("Given existent category id, When get category by id, Then category is returned")
    public void test3(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        Category categoryTest = new Category("Category1");
        categoryTest.setId(id);
        Optional<Category> optionalCategory = Optional.ofNullable(categoryTest);
        Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);

        // When
        Category result = categoryService.findById(id);

        // Then
        assertThat(result).isEqualTo(categoryTest);
    }

    @Test
    @DisplayName("Given nonexistent category id, When get category by id, Then ObjectNotFound exception is Thrown")
    public void test4(){
        // Given
        String id = "507f1f77bcf86cd799439011";
        Optional<Category> optionalCategory = Optional.empty();
        Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);

        // When
        Throwable thrown = catchThrowable(() -> categoryService.findById(id));

        // Then
        assertThat(thrown).isInstanceOf(ObjectNotFoundException.class).hasMessage("Category not found.");
    }

    // Test data
    public Page<Category> createCategoryPage(Pageable pageable){
        Category c1 = new Category("Category1");
        Category c2 = new Category("Category2");
        List<Category> categories = new ArrayList<>(Arrays.asList(c1,c2));

        return new PageImpl<Category>(categories, pageable, categories.size());
    }

    public Page<Category> createEmptyCategoryPage(Pageable pageable){
        List<Category> categories = new ArrayList<>();

        return new PageImpl<Category>(categories, pageable, categories.size());
    }
}