package rw.ac.rca.OnlineShop.services;

import org.springframework.web.multipart.MultipartFile;
import rw.ac.rca.OnlineShop.Enumerations.ProductType;

import java.io.File;
import java.io.IOException;

public interface IFileService {
    public String saveFile(MultipartFile file, ProductType productType) throws IOException;
}
