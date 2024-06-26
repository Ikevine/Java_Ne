package rw.ac.rca.OnlineShop.services.ServicesImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.OnlineShop.Enumerations.ProductType;
import rw.ac.rca.OnlineShop.services.IFileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
@Service
public class FileService implements IFileService {
    @Value("${file.upload.dir}")
    private String rootDir;
    @Override
    public String saveFile(MultipartFile file, ProductType productType) throws IOException {
        String savePath = rootDir + "/" + productType.name().toLowerCase();
        String fileName = file.getOriginalFilename() + UUID.randomUUID().toString();
        Path filePath = Paths.get(savePath + "/" + fileName);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "" + filePath;
    }
}
