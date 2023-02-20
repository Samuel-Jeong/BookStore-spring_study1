package com.springmvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@PropertySource("classpath:/config/application.properties")
public class WebConfig implements WebMvcConfigurer {

	private final Logger log = LoggerFactory.getLogger(getClass());

//	Not working ...
//	@Value("${file.upload.path}")
//	@Value("#{file['file.upload.path']}")
	private String imageUploadPath;

//	Not working ...
//	@Value("${file.save.path}")
//	@Value("#{file['file.save.path']}")
	private String imageSavePath;

	public WebConfig (@Value("#{file['file.upload.path']}") String uploadPath,
						@Value("#{file['file.save.path']}") String savePath) {
		this.imageUploadPath = uploadPath;
		this.imageSavePath = savePath;
	}

//	Not working ...
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.warn("> imageUploadPath: {}", imageUploadPath);
		log.warn("> imageSavePath: {}", imageSavePath);
		registry.addResourceHandler(imageUploadPath)
				.addResourceLocations(imageSavePath);
	}

}
