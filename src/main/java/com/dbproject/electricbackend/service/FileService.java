package com.dbproject.electricbackend.service;

import com.dbproject.electricbackend.exception.CustomException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 存储上传的文件并返回文件名
     * @param file 上传的文件对象
     * @return 在服务器上存储的文件名
     * @throws CustomException 无法创建文件或重名异常
     */
    String store(MultipartFile file) throws CustomException;

    /**
     * 获取文件并加载为资源对象
     * @param name 文件名
     * @return 资源对象
     * @throws CustomException 文件不存在异常
     */
    Resource load(String name) throws CustomException;
}
