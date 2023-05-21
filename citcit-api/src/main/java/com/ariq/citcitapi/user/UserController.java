package com.ariq.citcitapi.user;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ariq.citcitapi.post.model.Post;
import com.ariq.citcitapi.post.model.dto.PostResponse;
import com.ariq.citcitapi.user.model.AppUser;
import com.ariq.citcitapi.user.model.dto.UserRequest.UserRequestLogin;
import com.ariq.citcitapi.user.model.dto.UserRequest.UserRequestRegister;
import com.ariq.citcitapi.user.model.dto.UserResponse.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<UserResponse> userRegister(@Valid @RequestBody UserRequestRegister userRequest) {
        AppUser newUser = userRequest.convertToEntity();
        AppUser saveUser = this.userService.createOne(newUser);

        if (saveUser.equals(null)) {
            return ResponseEntity.badRequest().build();
        }

        UserResponse userResponse = saveUser.convertToResponse();
        return ResponseEntity.created(null).body(userResponse);
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserResponse> userLogin(@Valid @RequestBody UserRequestLogin userRequest) {
        AppUser user = userRequest.convertToEntity();
        Optional<AppUser> existingUser = this.userService.getOne(user);

        if (existingUser.isPresent()) {
            if (existingUser.get().getPassword().equals(user.getPassword())) {
                UserResponse userResponse = existingUser.get().convertToResponse();
                return ResponseEntity.ok().body(userResponse);
            }

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<PostResponse>> getUserPosts(@PathVariable("id") int id) {
        Optional<AppUser> user = this.userService.getOneById(id);

        if (user.isPresent()) {
            UserResponse userResponse = user.get().convertToResponse();
            List<Post> posts = userResponse.getPosts();
            List<PostResponse> postResponses = posts.stream().map(post -> post.convertToResponse()).toList();

            return ResponseEntity.ok().body(postResponses);
        }

        return ResponseEntity.notFound().build();
    }
}
