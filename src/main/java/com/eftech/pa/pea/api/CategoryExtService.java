package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.CategoryDTO;
import com.eftech.pa.pea.exception.ApiException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface CategoryExtService {

    /**
     * Retrieve all categories
     * @return a list of category DTOs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<CategoryDTO> getCategories();

    /**
     * Create a new category
     * @param categoryDTO DTO of the category to create
     * @return DTO of the created category
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws ApiException;

    /**
     * Retrieve category with specified id
     * @param categoryId id of the target category
     * @return DTO of the requested category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.GET)
    CategoryDTO getCategory(@PathVariable("categoryId") String categoryId) throws ApiException;

    /**
     * Update an existing category
     * @param categoryId id of the target category
     * @param categoryDTO DTO of the category to update
     * @return DTO of the updated category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.PUT)
    CategoryDTO updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody CategoryDTO categoryDTO) throws ApiException;

    /**
     * Delete category with specified id
     * @param categoryId id of the target category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.DELETE)
    void deleteCategory(@PathVariable("categoryId") String categoryId) throws ApiException;

}
