package com.ecommerce.service;

import org.springframework.util.StringUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.model.Product;
import com.ecommerce.model.ProductImage;
import com.ecommerce.repository.ProductImageRepository;

import configuration.FileStorageProperties;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	public static final String PNG_FILE_FORMAT = ".png";
	public static final String JPEG_FILE_FORMAT = ".jpeg";
	public static final String JPG_FILE_FORMAT = ".jpg";
	public static final String INVALID_FILE_FORMAT = "Only PNG, JPEG and JPG images are allowed";
	public static final String FILE_SEPERATOR = "_";
	public static final CharSequence INVALID_FILE_DELIMITER = "..";
	public static final String RESOURCE_NOT_FOUND = "Resource not found";
	
	private final Path fileStorageLocation;

	@Autowired
	private ProductImageRepository productImageRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	public ProductImageServiceImpl(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {

		}
	}

	@Override
	public String storeFile(MultipartFile file) throws IOException {

		File f = new File(file.getOriginalFilename());
		f.createNewFile();
		FileOutputStream fout = new FileOutputStream(f);
		fout.write(file.getBytes());
		fout.close();
		BufferedImage image = ImageIO.read(f);

		if (!(file.getOriginalFilename().endsWith(PNG_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(JPEG_FILE_FORMAT)
				|| file.getOriginalFilename().endsWith(JPG_FILE_FORMAT)))
			throw new IOException(INVALID_FILE_FORMAT);

		if (f.exists()) {
			f.delete();
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains(INVALID_FILE_DELIMITER)) {
				throw new RuntimeException("Sorry! Filename contains invalid path sequence" + fileName);
			}
			String newFileName = System.currentTimeMillis() + FILE_SEPERATOR + fileName;
			Path targetLocation = this.fileStorageLocation.resolve(newFileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return newFileName;

		} catch (IOException e) {
			throw new IOException("Could not store file %s !! Please try again!");
		}
	}

	@Override
	public void saveProductImages(MultipartFile[] files, Long productId) throws IOException {
		List<ProductImage> prodImageList = new ArrayList<ProductImage>();
		Product product = productService.getProductById(productId);
		for (MultipartFile file : files) {
			String fileName = this.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/getAllImage/")
					.path(fileName).toUriString();
			ProductImage prod = new ProductImage();
			prod.setProduct(product);
			prod.setProductImageName(fileName);
			prod.setProductImageurl(fileDownloadUri);
			prodImageList.add(prod);
		}
		product.setProductImage(prodImageList);
		productService.addProduct(product);

	}

	@Override
	public List<Resource> loadFilesAsResource(Long productId) throws IOException {
		List<ProductImage> imageLists = productImageRepository.findByProductId(productId);
		List<Resource> resources = new ArrayList<Resource>();
		
		for(ProductImage imageList : imageLists) {
			
			try {
				Path filePath = this.fileStorageLocation.resolve(imageList.getProductImageName()).normalize();
				Resource resource = new UrlResource(filePath.toUri());
				if (resource.exists()) {
					resources.add(resource);
				} else {
					throw new IOException(RESOURCE_NOT_FOUND);
				}
	
			} catch (Exception  e) {
				throw new IOException(RESOURCE_NOT_FOUND, e);
			}
		}
		return resources;
	}
}
