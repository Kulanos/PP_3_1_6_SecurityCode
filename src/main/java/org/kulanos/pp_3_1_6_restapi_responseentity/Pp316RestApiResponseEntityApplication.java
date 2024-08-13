package org.kulanos.pp_3_1_6_restapi_responseentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pp316RestApiResponseEntityApplication implements CommandLineRunner {
    @Autowired
    private MyController myController;

    public static void main(String[] args) {
        SpringApplication.run(Pp316RestApiResponseEntityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        myController.getAllUsers();
        myController.saveUser();
        myController.updateUser();
        myController.deleteUser();




    }
}
