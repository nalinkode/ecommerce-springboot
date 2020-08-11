package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.service.ProductImageService;

@RestController
@RequestMapping("/image")
public class ProductImageController {
	
	public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
	
	@Autowired
	private ProductImageService productImageService;

	@PostMapping("/upload/{productId}")
	private ResponseEntity<Void> uploadImage(@RequestParam("files") MultipartFile[] files,
			@PathVariable Long productId) throws IOException {
		HttpHeaders header = new HttpHeaders();		
		try {
			productImageService.saveProductImages(files,productId);
		}catch (Exception e) {
			throw new RuntimeException("Failed to add product images");
		}
		header.add("desc", "Product Images Added");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}
	
	@GetMapping("/getAllImage/{productId}")
	private ResponseEntity<Resource>getImage(@PathVariable Long productId, HttpServletRequest request) throws IOException {
		HttpHeaders header = new HttpHeaders();
		List<Resource> resourceList = productImageService.loadFilesAsResource(productId);
		Resource resource = resourceList.get(1);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		if (contentType == null) {
				contentType = DEFAULT_CONTENT_TYPE;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		header.add("desc", "List of product images");
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).headers(header).body(resource);	
	}

}
