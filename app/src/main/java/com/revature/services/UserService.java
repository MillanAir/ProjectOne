package com.revature.services;

import com.revature.App;
import com.revature.dao.IUserDao;
import com.revature.dao.UserDaoJDBC;
import com.revature.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    IUserDao ud;
    public UserService(IUserDao ud) {
        this.ud = ud;
    }

    public User loginUser(String username, String password){
        User u = ud.readUserByUsername(username);

        if(u != null){
            if(password.equals(u.getPassword())){
                return u;
            } else{
                //Incorrect password
                return null;
            }
        }
        // password mismatch.
        return null;
    }
}
