package com.mstramohz.BankingApp;

import com.mstramohz.BankingApp.model.AccountUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		AccountUser adminUser = new AccountUser();
		adminUser.setFirstName("Admin");
		adminUser.getLastName()
	}*/
}
