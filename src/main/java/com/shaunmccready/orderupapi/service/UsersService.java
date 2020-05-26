package com.shaunmccready.orderupapi.service;

import com.shaunmccready.orderupapi.domain.Users;
import com.shaunmccready.orderupapi.exception.NoSuchElementException;
import com.shaunmccready.orderupapi.repository.UsersDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    private UsersDao usersDao;


    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public Users getUser(String userId) {
        Optional<Users> userById = usersDao.findById(userId);
        return userById.orElseThrow(() -> new NoSuchElementException("No user found with the id:" + userId));
    }


    @Transactional(rollbackOn = Exception.class)
    public Users createUser(Users user) {
        Optional<Users> userByEmail = usersDao.findByEmailIgnoreCase(user.getEmail());
        if (userByEmail.isPresent()) {
            return userByEmail.get();
        }

        String idOfNewUser = UUID.randomUUID().toString();
        user.setId(idOfNewUser)
                .setDefaults();

        return usersDao.save(user);
    }


    @Transactional(rollbackOn = Exception.class)
    public Users updateUser(String userId, Users user) {
        Optional<Users> userById = usersDao.findById(userId);

        if (userById.isEmpty()) {
            throw new NoSuchElementException("No user found with the id:" + userId);
        }

        return replaceValuesWithNewOne(userById.get(), user);
    }

    private Users replaceValuesWithNewOne(Users userInDb, Users replacementUser) {
        if (replacementUser.getEmail() != null) {
            userInDb.setEmail(replacementUser.getEmail());
            userInDb.setEmailVerified(false);
        }
        if (replacementUser.getName() != null) {
            userInDb.setName(replacementUser.getName());
        }
        if (replacementUser.getGivenName() != null) {
            userInDb.setGivenName(replacementUser.getGivenName());
        }
        if (replacementUser.getFamilyName() != null) {
            userInDb.setFamilyName(replacementUser.getFamilyName());
        }

        return userInDb;
    }
}
