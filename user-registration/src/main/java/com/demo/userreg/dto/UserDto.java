package com.demo.userreg.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
}
