package com.example.mainapp.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@ToString
@Builder

public class User {

	@NotBlank(message ="Name parameter is blank")
	private String Name;

	@NotBlank(message ="surName parameter is blank")
	private String Surname;

}
