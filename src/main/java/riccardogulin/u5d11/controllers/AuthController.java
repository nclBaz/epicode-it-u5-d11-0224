package riccardogulin.u5d11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import riccardogulin.u5d11.payloads.UserLoginDTO;
import riccardogulin.u5d11.payloads.UserLoginResponseDTO;
import riccardogulin.u5d11.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
		return new UserLoginResponseDTO(authService.authenticateUserAndGenerateToken(payload));
	}
}
