package com.eftech.pa.pea.api;

import com.eftech.pa.pea.dto.CategoryDTO;
import com.eftech.pa.pea.exception.ApiException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Get all categories",
            notes = "Return a list of all existing categories",
            response = CategoryDTO.class,
            responseContainer = "List")
    List<CategoryDTO> getCategories();

    /**
     * Create a new category
     * @param categoryDTO DTO of the category to create
     * @return DTO of the created category
     * @throws ApiException exception to throw
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create category",
            notes = "Create a new category ",
            response = CategoryDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 409, message = "Duplicate category")
    })
    CategoryDTO createCategory(@ApiParam(name = "Request body", value = "Category information for a new category to be created", required = true)
                               @RequestBody CategoryDTO categoryDTO) throws ApiException;

    /**
     * Retrieve category with specified id
     * @param categoryId id of the target category
     * @return DTO of the requested category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get category",
            notes = "Get an existing category by providing category id",
            response = CategoryDTO.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Category not found")
    })
    CategoryDTO getCategory( @ApiParam(value = "ID for target category", required = true)
                             @PathVariable("categoryId") String categoryId) throws ApiException;

    /**
     * Update an existing category
     * @param categoryId id of the target category
     * @param categoryDTO DTO of the category to update
     * @return DTO of the updated category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update category",
            notes = "Update an existing category",
            response = CategoryDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request body"),
            @ApiResponse(code = 409, message = "Duplicate category")
    })
    CategoryDTO updateCategory(@ApiParam(value = "ID for target category", required = true)
                               @PathVariable("categoryId") String categoryId,
                               @ApiParam(name = "Request body", value = "Category information for an existing category to be updated to", required = true)
                               @RequestBody CategoryDTO categoryDTO) throws ApiException;

    /**
     * Delete category with specified id
     * @param categoryId id of the target category
     * @throws ApiException exception to throw
     */
    @RequestMapping(path = "/{categoryId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete category",
            notes = "Delete an existing category by providing category id")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Category not found")
    })
    void deleteCategory(@ApiParam(value = "ID for target category", required = true)
                        @PathVariable("categoryId") String categoryId) throws ApiException;

}
