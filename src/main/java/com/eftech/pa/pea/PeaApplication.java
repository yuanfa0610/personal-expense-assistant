package com.eftech.pa.pea;

import com.eftech.pa.pea.enums.CardType;
import com.eftech.pa.pea.impl.dao.*;
import com.eftech.pa.pea.impl.dao.hibernate.*;
import com.eftech.pa.pea.impl.persistent.*;
import com.eftech.pa.pea.utils.Generator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableSwagger2
public class PeaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeaApplication.class, args);

		/**
		Transaction transaction = new Transaction();
		transaction.setEfId(Generator.generateEfId());
		transaction.setMerchantName("KFC");
		UserDao userDao = new HibernateUserDaoImpl();
		User user = userDao.getById("d7f55b0d-1966-4c02-8cb9-773c655aa112");
		CategoryDao categoryDao = new HibernateCategoryDaoImpl();
		Category category = categoryDao.getCategoryByName("Restaurant");
		PaymentOptionDao paymentOptionDao = new HibernatePaymentOptionDaoImpl();
		PaymentOption paymentOption = paymentOptionDao.getPaymentOptionByName("Credit Card");
		CreditCardDao creditCardDao = new HibernateCreditCardDaoImpl();
		CreditCard creditCard = creditCardDao.getCreditCardByUserIdAndCardName("d7f55b0d-1966-4c02-8cb9-773c655aa112", "Discover 5%");
		transaction.setUser(user);
		transaction.setCategory(category);
		transaction.setPaymentOption(paymentOption);
		transaction.setCreditCard(creditCard);
		Instant instant = LocalDate.now().atStartOfDay(ZoneOffset.UTC).toInstant();
		Date date = Date.from(instant);
		transaction.setDate(date);
		transaction.setAmount(BigDecimal.valueOf(54.38));
		transaction.setCashbackEarned(BigDecimal.valueOf(0.00));
		TransactionDao transactionDao = new HibernateTransactionDaoImpl();
		transactionDao.save(transaction);
		 **/

		/**
		User user = new User();
		user.setEfId(Generator.generateEfId());
		user.setLastName("Chong");
		user.setFirstName("Eritza");
		user.setEmail("a@gmail.com");
		Instant instant = LocalDate.of(1992, 07, 26).atStartOfDay(ZoneOffset.UTC).toInstant();
		Date date = Date.from(instant);
		user.setDateOfBirth(date);
		UserDao userDao = new HibernateUserDaoImpl();
		userDao.save(user);
		 **/
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eftech.pa.pea"))
				.build();
	}

}
