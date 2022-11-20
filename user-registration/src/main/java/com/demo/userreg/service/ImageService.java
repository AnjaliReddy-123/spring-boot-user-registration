package com.demo.userreg.service;

import com.demo.userreg.dto.AppMessage;
import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.exception.ServiceException;
import com.sangupta.imgur.api.model.Image;

public interface ImageService {
	
	Image viewImages(ImageRequest imageRequest) throws ServiceException;

	AppMessage uploadImage(ImageRequest imageRequest) throws ServiceException;

	AppMessage deleteImage(ImageRequest imageRequest) throws ServiceException;

}
