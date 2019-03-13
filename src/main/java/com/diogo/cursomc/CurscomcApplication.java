package com.diogo.cursomc;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diogo.cursomc.domain.Categoria;
import com.diogo.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CurscomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriarepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CurscomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriarepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
