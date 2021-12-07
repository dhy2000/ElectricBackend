package com.dbproject.electricbackend.controller;

import com.dbproject.electricbackend.exception.CustomException;
import com.dbproject.electricbackend.schema.FileUploadResponse;
import com.dbproject.electricbackend.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Api(tags = "文件相关")
@RequestMapping("/file")
@Log4j2
@RestController
public class FileController {

    private final FileService fileService;

    private static final String DOWNLOAD_URI_PREFIX = "/file/image/";

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // @ApiOperation("上传文件(单个或多个均可), 请求体格式采用 multipart/form-data, KEY 名称为 \"file\"")
    // @PostMapping(value = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<FileUploadResponse> upload(@RequestParam("file") MultipartFile[] files) {
        List<FileUploadResponse> responses = new LinkedList<>();
        for (MultipartFile file : files) {
            try {
                String fileName = fileService.store(file);
                String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path(DOWNLOAD_URI_PREFIX).path(fileName).toUriString();
                FileUploadResponse response = new FileUploadResponse(fileName, uri,
                        Optional.ofNullable(file.getContentType()).orElse(""), file.getSize());
                responses.add(response);
            } catch (CustomException e) {
                responses.add(new FileUploadResponse(file.getOriginalFilename(), e.getCode(), e.getMessage()));
            }
        }
        return responses;
    }

    @ApiOperation("查看图片(目前仅支持了图片类型的文件查看, 其他文件类型暂未支持)")
    @GetMapping(value = "image/{filename:.+}")
    public void image(@PathVariable String filename, HttpServletResponse response) throws CustomException {
        Resource resource = fileService.load(filename);
        OutputStream os;
        try {
            BufferedImage image = ImageIO.read(resource.getInputStream());
            response.setContentType("image/png");
            os = response.getOutputStream();
            if (Objects.nonNull(image)) {
                ImageIO.write(image, "png", os);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            throw CustomException.defined(CustomException.Define.IMAGE_ERROR);
        }
    }
}
