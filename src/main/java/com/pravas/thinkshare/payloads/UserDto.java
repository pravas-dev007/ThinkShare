package com.pravas.thinkshare.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="User must be min of 4 characters !!!!")
	private String name;
	
	@Email(message="Email address is not valid !!!")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10, message="password must be min of 3 chars and max of 10 chars !!!!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&#]).{5,}$",
	        message = "Password must contain at least one uppercase letter, one number, and one special character")
	private String password;
	
	@NotNull
	private String about;

}
