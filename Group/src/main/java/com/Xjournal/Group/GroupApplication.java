package com.Xjournal.Group;
import com.Xjournal.Group.Repo.UserRepository;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import static com.Xjournal.Group.Repo.UserRepository.addClaims;


@SpringBootApplication
public class GroupApplication {
	public static void main(String[] args) {
		SpringApplication.run(GroupApplication.class, args);

	}


}
