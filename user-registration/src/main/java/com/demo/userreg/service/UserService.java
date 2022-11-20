package com.demo.userreg.service;

import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.dto.UserDto;
import com.demo.userreg.exception.ServiceException;

public interface UserService {

	UserDto registerUser(UserDto user) throws ServiceException;

	UserDto updateUser(UserDto user) throws ServiceException;

	String deleteUser(int userId) throws ServiceException;

	void validateUser(ImageRequest imageRequest) throws ServiceException;

}
