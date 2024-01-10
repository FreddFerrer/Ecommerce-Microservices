package com.fredd.products_service.model.mappers;

import com.fredd.products_service.model.dtos.ProductRequest;
import com.fredd.products_service.model.dtos.ProductResponse;
import com.fredd.products_service.model.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductDTOMapper {

    Product toProduct(ProductRequest request);

    ProductRequest toProductRequestDTO (Product product);

    ProductResponse toProductResponseDTO (Product product);
}
