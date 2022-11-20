package com.demo.userreg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppMessage {

	private Object data;
	private int status;
	private boolean success; 
}

