package com.ariq.citcitapi.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ariq.citcitapi.user.model.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<AppUser> getOneById(int id) {
        return this.userRepository.findById(id);
    }

    public Optional<AppUser> getOne(AppUser user) {
        return this.userRepository.findByUsername(user.getUsername());
    }

    public AppUser createOne(AppUser user) {
        Optional<AppUser> existingUser = this.userRepository.findByUsername(user.getUsername());

        if(existingUser.isPresent()) {
            return null;
        }

        return this.userRepository.save(user);
    }
}
