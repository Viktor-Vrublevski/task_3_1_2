package org.mentor.project.service;

import org.mentor.project.model.User;
import org.mentor.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory factory;


    public void save(User user) {
       userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAllBy();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    public void delete(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.contains(user) ? user : manager.merge(user));
        manager.getTransaction().commit();
    }
}
