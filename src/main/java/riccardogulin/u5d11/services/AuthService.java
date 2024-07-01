package riccardogulin.u5d11.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d11.entities.User;
import riccardogulin.u5d11.exceptions.UnauthorizedException;
import riccardogulin.u5d11.payloads.UserLoginDTO;
import riccardogulin.u5d11.security.JWTTools;

@Service
public class AuthService {
	@Autowired
	private UsersService usersService;

	@Autowired
	private JWTTools jwtTools;

	public String authenticateUserAndGenerateToken(UserLoginDTO payload){

		// 1. Controllo le credenziali
		// 1.1 Cerco nel db tramite email se esiste tale utente
		User user = this.usersService.findByEmail(payload.email());
		// 1.2 Verifico se la password combacia con quella ricevuta nel payload
		if(user.getPassword().equals(payload.password())){
			// 2. Se tutto è OK --> Genero un token per tale utente e lo torno
			return jwtTools.createToken(user);
		} else {
			// 3. Se le credenziali sono errate --> 401 (Unauthorized)
			throw new UnauthorizedException("Credenziali non corrette!");
		}
	}
}
