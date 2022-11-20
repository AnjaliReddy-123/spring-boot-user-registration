package com.demo.userreg.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.userreg.dto.AppMessage;
import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.exception.ServiceException;
import com.demo.userreg.service.ImageService;
import com.demo.userreg.service.ImgurService;
import com.demo.userreg.service.UserService;
import com.sangupta.imgur.api.model.Image;

@Service
public class ImageServiceImpl implements ImageService{
	
	private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
	
	private UserService userService;
	private ImgurService imgurService;
	
	@Autowired
	public ImageServiceImpl(UserService userService, ImgurService imgurService) {
		super();
		this.userService = userService;
		this.imgurService = imgurService;
	}
	
	@Override
	public Image viewImages(ImageRequest imageRequest) throws ServiceException {
		log.debug("Validate User");
		userService.validateUser(imageRequest);
		log.debug("User validated");
		return imgurService.getAlbum(imageRequest.getAlbumId());
		
	}

	@Override
	public AppMessage uploadImage(ImageRequest imageRequest) throws ServiceException {
		log.debug("Validate User");
		userService.validateUser(imageRequest);
		log.debug("User validated");
		return imgurService.uploadImage(imageRequest);
	}
	
	@Override
	public AppMessage deleteImage(ImageRequest imageRequest) throws ServiceException {
		log.debug("Validate User");
		userService.validateUser(imageRequest);
		log.debug("User validated");
		return imgurService.deleteImage(imageRequest);
	}
	
}
