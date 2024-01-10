package com.fredd.products_service.model.services;

import com.fredd.products_service.model.dtos.ProductRequest;
import com.fredd.products_service.model.dtos.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> getAllProducts();

    void addProduct(ProductRequest productRequest);


}
