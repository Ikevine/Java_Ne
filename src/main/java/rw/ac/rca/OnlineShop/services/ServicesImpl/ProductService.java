package rw.ac.rca.OnlineShop.services.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.models.Product;
import rw.ac.rca.OnlineShop.repositories.IProductRepository;
import rw.ac.rca.OnlineShop.services.IProductService;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    @Override
    public Product createProduct(Product p) {
        return productRepository.save(p);
    }
}
