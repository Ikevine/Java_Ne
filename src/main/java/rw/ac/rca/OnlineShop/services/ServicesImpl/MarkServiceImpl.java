package rw.ac.rca.OnlineShop.services.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rw.ac.rca.OnlineShop.models.Mark;
import rw.ac.rca.OnlineShop.repositories.IMarkRepository;
import rw.ac.rca.OnlineShop.services.IMarkService;

@Service
@AllArgsConstructor
public class MarkServiceImpl implements IMarkService {
    private IMarkRepository markRepository;
    @Override
    public Mark createMark(Mark mark) {
        return markRepository.save(mark);
    }
}
