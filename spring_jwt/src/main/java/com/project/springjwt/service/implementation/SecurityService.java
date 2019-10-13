package com.project.springjwt.service.implementation;

import com.project.springjwt.configuration.jwt.JwtUtil;
import com.project.springjwt.model.*;
import com.project.springjwt.repository.IPersonRepository;
import com.project.springjwt.repository.ISessionRepository;
import com.project.springjwt.repository.IUserRepository;
import com.project.springjwt.service.ISecurityService;
import com.project.springjwt.transversal.Constant;
import com.project.springjwt.transversal.Message;
import com.project.springjwt.transversal.Response;
import com.project.springjwt.transversal.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

@Service("SecurityService")
public class SecurityService implements ISecurityService {
    private IPersonRepository personRepository;
    private IUserRepository userRepository;
    private ISessionRepository sessionRepository;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @Autowired
    public SecurityService(IPersonRepository personRepository,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           ISessionRepository sessionRepository,
                           IUserRepository userRepository){
        this.personRepository = personRepository;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public Response<JwtResponse> authenticate(JwtRequest request) {
        Response<JwtResponse> response = new Response<>();

        if(request.getUsername().equals(Constant.Empty) || request.getPassword().equals(Constant.Empty)) {
            response.setMessage(Message.IndicarUsuarioContrasena);

            return response;
        }

        User userInformation = this.userRepository.getUserInformation(request.getUsername(), request.getPassword());

        if(userInformation == null) {
            response.setMessage(Message.UsuarioNoEncontrado);

            return response;
        }

        if(!userInformation.isEnabled()) {
            response.setMessage(Message.UsuarioDeshabilitado);

            return response;
        }

        final String token = this.jwtUtil.generateToken(userInformation);

        this.sessionRepository.deleteSession(userInformation.getUsername());

        Integer sessionCreated = this.sessionRepository.createSession(this.getSessionInformation(token, userInformation));

        if(sessionCreated == null || sessionCreated.equals(0)) {
            response.setMessage(Message.NoSePuedeCrearSesion);

            return response;
        }

        response.setData(new JwtResponse(token));
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<JwtResponse> refresh(String token) {
        Response<JwtResponse> response = new Response<>();
        String refreshedToken = "";

        if (this.jwtUtil.canTokenBeRefreshed(token)) {
            refreshedToken = this.jwtUtil.refreshToken(token);

            if(refreshedToken.equals(Constant.Empty)) {
                response.setMessage(Message.ErrorRefrescarToken);

                return response;
            }

            this.updateSession(refreshedToken);
        } else {
            response.setMessage(Message.NoSePuedeRefrescarToken);

            return response;
        }

        response.setData(new JwtResponse(refreshedToken));
        response.setIsWarning(false);

        return response;
    }

    @Override
    @Transactional
    public Response<User> create(Person person, String username, String password) {
        Response<User> response = new Response<>();

        Integer validateUserExists = this.userRepository.validateUserExists(username);

        if(validateUserExists > 0) {
            response.setMessage(Message.UsuarioYaExisteSistema);

            return response;
        }

        Integer personCreated = this.personRepository.createPerson(person);

        if(personCreated == null || personCreated.equals(0)) {
            response.setMessage(Message.NoSePudoCrearPersona);

            return response;
        }

        User userCreated = this.userRepository.createUser(getUserInformation(username,
                                                                             this.encondePassword(password),
                                                                             personCreated,
                                                                             person.getUserRegister()));

        if(userCreated == null) {
            response.setMessage(Message.NoSePudoCreUsuario);

            return response;
        }

        return response;
    }

    private Session getSessionInformation(String token, User userInformation){
        Date created = this.jwtUtil.getIssuedAtDateFromToken(token);
        Date expired = this.jwtUtil.getExpirationDateFromToken(token);

        Session session = new Session();
        session.setIdUser(userInformation.getId());
        session.setToken(token);
        session.setTimeToRelease((expired.getTime() - created.getTime())/1000);
        session.setExpirationTime(TimeHelper.convertToLocalDateTimeViaInstant(expired));

        return session;
    }

    private void updateSession(String refreshedToken) {
        Date expired = this.jwtUtil.getExpirationDateFromToken(refreshedToken);
        this.sessionRepository.updateSession(this.jwtUtil.getUsernameFromToken(refreshedToken),
                                             refreshedToken,
                                             TimeHelper.convertToLocalDateTimeViaInstant(expired));
    }

    private User getUserInformation(String username, String password, int personCreated, String userRegister) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIdPerson(personCreated);
        user.setUserRegister(userRegister);

        return user;
    }

    private String encondePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
