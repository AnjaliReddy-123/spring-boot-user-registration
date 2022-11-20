package com.demo.userreg.service;

import com.demo.userreg.dto.AppMessage;
import com.demo.userreg.dto.ImageRequest;
import com.sangupta.imgur.api.model.Image;

public interface ImgurService {

	Image getAlbum(String albumId);

	AppMessage uploadImage(ImageRequest imageRequest);

	AppMessage deleteImage(ImageRequest imageRequest);

}
