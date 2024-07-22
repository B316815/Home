package com.home.Service;

import com.home.entity.AppUser;
import com.home.paylod.LoginDto;
import com.home.paylod.UserDto;
import com.home.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private JWTService jwtService;
    private AppUserRepository appUserRepository;

    public UserServiceImpl(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }


    @Override
    public UserDto addUser(UserDto userDto) {
        AppUser user = mapToEntity(userDto);
        AppUser savedUser = appUserRepository.save(user);
        UserDto dto = mapToDto(savedUser);
        return dto;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUser.isPresent()){
            AppUser user = opUser.get();
            if( BCrypt.checkpw(loginDto.getPassword(),user.getPassword()));{
                return jwtService.generateToken(user);
            }
        }

        return null;
    }

    UserDto mapToDto(AppUser user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmailId(user.getEmailId());
       dto.setPassword(user.getPassword());
       dto.setUserRole(user.getUserRole());
        return dto;

    }

    AppUser mapToEntity(UserDto userDto){

        AppUser user=new AppUser();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmailId(userDto.getEmailId());
        user.setUserRole(userDto.getUserRole());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
//        user.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(5)));
        return user;
    }
}
