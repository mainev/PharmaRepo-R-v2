/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.pharma_red_v2.security.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import server.pharma_red_v2.security.entity.User;

/**
 *
 * @author maine
 */
@Stateless
public class UserFacade {

    @PersistenceContext(unitName = "RedServer-v2PU")
    private EntityManager em;

    //Get user with matching username/emailad
    public User findByUsername(String username) {
        return (User) em.createQuery("Select u from User u where u.emailAd = :uname")
                .setParameter("uname", username)
                .getSingleResult();

    }

    public User findById(int id) {
        return em.find(User.class, id);
    }

    public List<User> findAll() {
        return em.createQuery("Select u from User u").getResultList();
    }

    public User findUserByEmailAndPassword(String emailAd, String pwd) {
        try {
            User result = (User) em.createQuery("Select u from User u where u.emailAd = :emailAd and u.password = :pwd")
                    .setParameter("emailAd", emailAd)
                    .setParameter("pwd", pwd)
                    .getSingleResult();
            return result;
        } catch (Exception e) {
            //create exception handler
            return null;
        }

    }
}
