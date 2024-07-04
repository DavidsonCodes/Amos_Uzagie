package com.mstramohz.BankingApp.services;

import com.mstramohz.BankingApp.model.AccountUser;
import com.mstramohz.BankingApp.model.LoginRequest;
import com.mstramohz.BankingApp.model.LoginResponse;
import com.mstramohz.BankingApp.model.Role;
import com.mstramohz.BankingApp.repository.AccountUserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountUserService {

    @Autowired
    private final AccountUserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) throws MessagingException {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (auth != null){
            AccountUser user = userRepository.getByUsername(request.getUsername());
            String token = jwtService.createToken(user);
            /*messageService.loginNotification(user.getUsername(), "Dear "+ user.getFirstName()+ ", \n"+
                    "There has been a successful login into your Bank Account. Please if you did not log in reply this mail"+
                    " or call these numbers: 01-2341677, 07060727660\n Thank you for Banking with us.");*/
            return new ResponseEntity<>(LoginResponse.builder().user(user).token(token).build(), HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<List<AccountUser>> getAllAccUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getUserById(int id){
        return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getByUsername(String username){
        return new ResponseEntity<>(userRepository.getByUsername(username), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> createNewUser(AccountUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<AccountUser> updateAccUser(int id, AccountUser user){
        AccountUser update = userRepository.findById(id).get();
        update.setFirstName(user.getFirstName());
        update.setLastName(user.getLastName());
        update.setMiddleName(user.getMiddleName());
        update.setUsername(user.getUsername());
        update.setPassword(passwordEncoder.encode(user.getPassword()));
        update.setPhoneNumber(user.getPhoneNumber());
        return new ResponseEntity<>(userRepository.save(update), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> deleteAccUser(int id) {
        AccountUser user = userRepository.findById(id).get();
        userRepository.delete(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
