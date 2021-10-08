package com.dbproject.electricbackend.service.impl;

import com.dbproject.electricbackend.config.FileConfiguration;
import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileServiceImpl implements FileService {

    private final Path storage;

    @Autowired
    public FileServiceImpl(FileConfiguration config) {
        this.storage = Paths.get(config.getFileDir()).toAbsolutePath().normalize();
    }

    @Override
    public String store(MultipartFile file) throws CustomException {
        String realFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (realFileName.contains("..")) {
            throw CustomException.defined(CustomException.Define.INVALID_FILE_NAME);
        }
        Path target = storage.resolve(realFileName);
        if (new File(target.toAbsolutePath().toString()).exists()) {
            throw CustomException.defined(CustomException.Define.FILE_ALREADY_EXIST);
        }
        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return realFileName;
        } catch (IOException e) {
            throw CustomException.defined(CustomException.Define.FILE_CREATE_ERROR);
        }
    }

    @Override
    public Resource load(String name) throws CustomException {
        Path path = storage.resolve(name);
        try {
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw CustomException.defined(CustomException.Define.FILE_NOT_EXIST);
            }
        } catch (MalformedURLException e) {
            throw CustomException.defined(CustomException.Define.FILE_NOT_EXIST);
        }
    }
}
