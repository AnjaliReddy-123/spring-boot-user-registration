package com.demo.userreg.service.impl;

import java.time.Duration;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.userreg.config.ConfigProperties;
import com.demo.userreg.dto.AppMessage;
import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.service.ImgurService;
import com.sangupta.imgur.api.model.Image;

@Service
public class ImgurServiceImpl implements ImgurService {
	
	private static final Logger log = LoggerFactory.getLogger(ImgurServiceImpl.class);

	private ConfigProperties configProperties;
	private WebClient webClient;
	
	@Autowired
	public ImgurServiceImpl(ConfigProperties configProperties, WebClient webClient) {
		super();
		this.configProperties = configProperties;
		this.webClient = webClient;
	}

	@Override
	public Image getAlbum(String albumId) {
		var image =  webClient.get()
				.uri(uriBuilder -> uriBuilder.path(configProperties.getImgurViewUrl()).build(albumId))
				.retrieve()
				.toEntity(Image.class)
				.timeout(Duration.ofMillis(configProperties.getTimeout()))
				.block();
		log.debug("Image retrived {}", image);
		return Optional.ofNullable(image).isPresent() ? image.getBody() : new Image();
	}

	@Override
	public AppMessage uploadImage(ImageRequest imageRequest) {
		var appMessage = webClient.post().uri(uriBuilder -> uriBuilder.path(configProperties.getImgurUploadUrl())
							.queryParam("image", imageRequest.getImage())
							.queryParam("album", imageRequest.getAlbumId())
							.queryParam("type", imageRequest.getImageType())
							.queryParam("name", imageRequest.getImageName())
							.queryParam("title", imageRequest.getImageTitle())
							.queryParam("description", imageRequest.getImageDescription())
							.build())
						.bodyValue(imageRequest).retrieve()
						.toEntity(AppMessage.class)
						.timeout(Duration.ofMillis(configProperties.getTimeout()))
						.block();
		log.debug("Image uploaded {}", appMessage);
		return Optional.ofNullable(appMessage).isPresent() ? appMessage.getBody() : 
			new AppMessage("Upload Failed", HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
	}

	@Override
	public AppMessage deleteImage(ImageRequest imageRequest) {
		var appMessage =  webClient.delete()
				.uri(uriBuilder -> uriBuilder.path(configProperties.getImgurDeleteUrl()).build(imageRequest.getImageId()))
				.retrieve()
				.toEntity(AppMessage.class)
				.timeout(Duration.ofMillis(configProperties.getTimeout()))
				.block();
		log.debug("Image deleted {}", appMessage);
		return Optional.ofNullable(appMessage).isPresent() ? appMessage.getBody() : 
			new AppMessage("Delete Failed", HttpStatus.INTERNAL_SERVER_ERROR.value(), false);
	}

}
