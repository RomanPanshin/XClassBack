package com.Xjournal.Group.Service;

import com.Xjournal.Group.Exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class Storage {
    @Value("{upload.path}")
    private final String path = "/Volumes/podarochek/1/Storoge/";
    public boolean uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }

        try {
            var fileName = file.getOriginalFilename();
            var is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
            return true;
        }
        catch (IOException e) {
            var msg = String.format("Failed to store file %f", file.getName());
            throw new StorageException(msg, e);
        }
    }
}
