package com.fredd.products_service.model.services.impl;

import com.fredd.products_service.model.dtos.ProductRequest;
import com.fredd.products_service.model.dtos.ProductResponse;
import com.fredd.products_service.model.entities.Product;
import com.fredd.products_service.model.mappers.IProductDTOMapper;
import com.fredd.products_service.model.repositories.repositories.ProductRepository;
import com.fredd.products_service.model.services.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final IProductDTOMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {

        var products = productRepository.findAll();

        return products.stream().map(productMapper::toProductResponseDTO).toList();
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        var product = Product.builder()
                .sku(productRequest.getSku())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .status(productRequest.getStatus())
                .build();

        productRepository.save(product);

        log.info("Product added: {}", product);

    }
}
