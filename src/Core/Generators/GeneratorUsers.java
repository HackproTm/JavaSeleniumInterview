package Core.Generators;

import Core.Dto.UserDto;

public class GeneratorUsers {
	public static UserDto GenerateAdministrator() {
		UserDto user = new UserDto();
		user.setUserName("hackpro.ems@gmail.com");
		user.setPassword("abcd.1234");
		return user;	
	}
}
