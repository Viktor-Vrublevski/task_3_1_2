package org.mentor.project.service;

import org.mentor.project.model.Role;
import org.mentor.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private EntityManagerFactory factory;


    public Role getRole(int id) {
        return repository.getOne(id);
    }


    public List<Role> getAllRoles() {
        return repository.findAll();
    }


    public void create() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createNativeQuery("INSERT INTO roles(role) VALUES " +
                "('ROLE_ADMIN'), ('ROLE_USER')");
        query.executeUpdate();
        manager.getTransaction().commit();
    }
}
