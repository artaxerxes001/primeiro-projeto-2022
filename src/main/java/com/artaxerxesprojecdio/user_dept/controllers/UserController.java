package com.artaxerxesprojecdio.user_dept.controllers;

import com.artaxerxesprojecdio.user_dept.entities.User;
import com.artaxerxesprojecdio.user_dept.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> findAll() {
        //  metodo findAll faz uma consulta no banco de dados e retona todos os dados
        List<User> result = userRepository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}") // configurando a captura o id  da requisição para ser usado no metodo
    public User findById(@PathVariable Long id) {

        User result = userRepository.findById(id).get();
        return result;
    }

    @PostMapping // configurando a captura o id  da requisição para ser usado no metodo
    public User insert(@RequestBody User user) { //  @RequestBody serve para passar  o uesr no corpo da requisição

        User result = userRepository.save(user);
        return result;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id).map(userUpdete -> {
            userUpdete.setName(user.getName());
            userUpdete.setEmail(user.getEmail());
            userUpdete.setDepartment(user.getDepartment());
            User updated = userRepository.save(userUpdete);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity updateParcial(@PathVariable Long id, @RequestBody User user) {
        return userRepository.findById(id).map(userParcialUpdate -> {
            userParcialUpdate.setName(user.getName());
            User updated = userRepository.save(userParcialUpdate);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
}
