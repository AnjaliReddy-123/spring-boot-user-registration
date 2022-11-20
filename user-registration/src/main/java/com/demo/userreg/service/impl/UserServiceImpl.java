package com.demo.userreg.service.impl;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.dto.UserDto;
import com.demo.userreg.entity.User;
import com.demo.userreg.exception.InvalidPasswordException;
import com.demo.userreg.exception.ServiceException;
import com.demo.userreg.exception.UserNameAlreadyExists;
import com.demo.userreg.exception.UserNotFoundException;
import com.demo.userreg.repository.UserRepository;
import com.demo.userreg.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserRepository userRepository;
	private BCryptPasswordEncoder passwordEncoder;
	private MessageSource messageSource;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
			MessageSource messageSource) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.messageSource = messageSource;
	}

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDto registerUser(UserDto userDto) throws ServiceException {
		try {
			if (!userRepository.existsByUsername(userDto.getUsername())) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodedPassword = passwordEncoder.encode(userDto.getPassword());
				userDto.setPassword(encodedPassword);
				
				log.debug("User being saved to database");
				return convertEntityToDto(userRepository.save(convertDtoToEntity(userDto)));
			}
			throw new UserNameAlreadyExists(messageSource.getMessage("error.UserNameAlreadyExists",
					new String[] { userDto.getUsername() }, Locale.US));
		} catch (UserNameAlreadyExists e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws ServiceException {
		try {
			if (userRepository.existsById(userDto.getId())) {
				log.debug("User being updated - userId {}", userDto.getId());
				return convertEntityToDto(userRepository.saveAndFlush(convertDtoToEntity(userDto)));
			} else {
				throw new UserNotFoundException(messageSource.getMessage("error.UserDoesNotExists",
						new Object[] { userDto.getId() }, Locale.US));
			}
		} catch (UserNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteUser(int userId) throws ServiceException {
		try {
			if (userRepository.existsById(userId)) {
				log.debug("User being deleted - userId {}", userId);
				userRepository.deleteById(userId);
				return messageSource.getMessage("success.UserDeletedSuccessfully", new Object[] { userId }, Locale.US);
			} else {
				throw new UserNotFoundException(
						messageSource.getMessage("error.UserDoesNotExists", new Object[] { userId }, Locale.US));
			}
		} catch (UserNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void validateUser(ImageRequest imageRequest) throws ServiceException {
		try {
			if (userRepository.existsByUsername(imageRequest.getUserName())) {
				User user = userRepository.getByUsername(imageRequest.getUserName());
				log.debug("User retrieved from database {}", user.getId());
				if (!passwordEncoder.matches(imageRequest.getPassword(), user.getPassword())) {
					throw new InvalidPasswordException(
							messageSource.getMessage("error.UserNameAndPasswordMismatch", new Object[] {}, Locale.US));
				}
			} else {
				throw new UserNotFoundException(messageSource.getMessage("error.UserNameDoesNotExists",
						new String[] { imageRequest.getUserName() }, Locale.US));
			}
		} catch (UserNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private User convertDtoToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

	private UserDto convertEntityToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

}
