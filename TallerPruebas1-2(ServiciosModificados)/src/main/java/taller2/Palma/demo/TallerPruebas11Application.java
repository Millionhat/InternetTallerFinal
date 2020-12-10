package taller2.Palma.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import taller2.Palma.demo.model.Iddocumenttype;
import taller2.Palma.demo.model.Institution;
import taller2.Palma.demo.model.Userr;
import taller2.Palma.demo.service.IdDocTypeService;
import taller2.Palma.demo.service.InstitutionService;
import taller2.Palma.demo.service.UserService;

@SpringBootApplication
@ComponentScan
public class TallerPruebas11Application {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(TallerPruebas11Application.class, args);
		
		
	}
	
	@Bean
	public CommandLineRunner register(InstitutionService service, UserService userve, IdDocTypeService ids) {
		return (args)->{
			Institution ins= new Institution();
			//ins.setInstId(123123);
			ins.setInstName("hola");
			service.addInstituion(ins);
			
			Institution ins2= new Institution();
			//ins2.setInstId(321321);
			ins2.setInstName("adios");
			service.addInstituion(ins2);
			
			Iddocumenttype idt=new Iddocumenttype();
			idt.setIddoctypeName("C.C.");
			ids.addIDT(idt);
			
			Iddocumenttype idt2=new Iddocumenttype();
			idt2.setIddoctypeName("T.I.");
			ids.addIDT(idt2);

			Iddocumenttype idt3=new Iddocumenttype();
			idt3.setIddoctypeName("Pasaporte");
			ids.addIDT(idt3);
			
			Userr user=new Userr();
			user.setUserName("m");
			user.setUserPassword("{noop}123");
			userve.save(user);
		};
	}

}
