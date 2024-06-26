package rw.ac.rca.OnlineShop.services.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.models.Quantity;
import rw.ac.rca.OnlineShop.repositories.IQuantityRepository;
import rw.ac.rca.OnlineShop.services.IQuantityService;

@Service
@AllArgsConstructor
public class QuantityService implements IQuantityService {
    private final IQuantityRepository quantityRepository;
    @Override
    public Quantity addQuantity(Quantity q) {
        return quantityRepository.save(q);
    }
}
