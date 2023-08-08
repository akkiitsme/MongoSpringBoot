package com.mangoservice.users;


import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/mongo")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserBean user){
        user = userDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userDao.findAll(),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@RequestBody UserBean User,@PathVariable String userId){
        UserBean saveUser = userDao.findById(userId).get();
        saveUser.setUsername(User.getUsername());
        saveUser.setFirstName(User.getFirstName());
        saveUser.setLastName(User.getLastName());
        User = userDao.save(saveUser);
        return new ResponseEntity<>(User,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable String userId){
        return new ResponseEntity<>(userDao.findById(userId).get(),HttpStatus.OK);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        userDao.deleteById(userId);
        return new ResponseEntity<>("User has Been Deleted !!",HttpStatus.OK);
    }

    @GetMapping("/custom/{Id}")
    public ResponseEntity<?> findUserByCustomQuery(@PathVariable String Id){
        Query query = new Query(Criteria.where("username").is(Id));
        List<UserBean> list = Collections.singletonList(mongoTemplate.findOne(query , UserBean.class));
        list.stream().forEach(x->{
            System.out.println(x);
        });
        return new ResponseEntity<>(list,HttpStatus.OK);
    }


}
