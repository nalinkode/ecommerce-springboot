package com.ecommerce.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.ProductImage;

public interface ProductImageService {

	String storeFile(MultipartFile file) throws IOException;

	void saveProductImages(MultipartFile[] files, Long productId) throws IOException;

	List<Resource> loadFilesAsResource(Long productId) throws IOException;

}
