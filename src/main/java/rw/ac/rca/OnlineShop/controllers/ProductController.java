package rw.ac.rca.OnlineShop.controllers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.OnlineShop.DTOs.AddQuantityDTO;
import rw.ac.rca.OnlineShop.DTOs.CreateProductDTO;
import rw.ac.rca.OnlineShop.Enumerations.EOperationType;
import rw.ac.rca.OnlineShop.Enumerations.ProductType;
import rw.ac.rca.OnlineShop.Utils.ApiResponse;
import rw.ac.rca.OnlineShop.models.Product;
import rw.ac.rca.OnlineShop.models.Quantity;
import rw.ac.rca.OnlineShop.repositories.IProductRepository;
import rw.ac.rca.OnlineShop.services.IFileService;
import rw.ac.rca.OnlineShop.services.IProductService;
import rw.ac.rca.OnlineShop.services.IQuantityService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;


@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final IProductService productService;
    private final IProductRepository productRepository;
    private final IQuantityService quantityService;
    private final IFileService fileService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@ModelAttribute CreateProductDTO req) throws IOException {
        Product newProduct = new Product(req.getName(),ProductType.valueOf(req.getProductType()),req.getPrice(),req.getInDate());
        String filePath = fileService.saveFile(req.getImage(),ProductType.valueOf(req.getProductType()));
        newProduct.setFilePath(filePath);
        productService.createProduct(newProduct);
        return ResponseEntity.ok(new ApiResponse("Product created Successfully!", HttpStatus.CREATED,newProduct));
    }

    @PutMapping("/addQuantity/{id}")
    public ResponseEntity<ApiResponse> addQuantity(@RequestBody AddQuantityDTO req, @PathVariable("id") UUID id){
        Product p = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Product doesn't exist"));
        Quantity quantity = new Quantity();
        quantity.setQuantity(req.getCount());
        quantity.setOperation(EOperationType.valueOf(req.getOperationType()));
        quantity.setProductCode(p.getCode());
        quantity.setDate(LocalDateTime.now());
        quantityService.addQuantity(quantity);
        return ResponseEntity.ok(new ApiResponse("Added quantity successfully1",HttpStatus.CREATED,quantity));
    }
}
