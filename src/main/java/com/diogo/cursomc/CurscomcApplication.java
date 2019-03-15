package com.diogo.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diogo.cursomc.domain.Categoria;
import com.diogo.cursomc.domain.Cidade;
import com.diogo.cursomc.domain.Cliente;
import com.diogo.cursomc.domain.Endereco;
import com.diogo.cursomc.domain.Estado;
import com.diogo.cursomc.domain.Produto;
import com.diogo.cursomc.domain.enums.TipoCliente;
import com.diogo.cursomc.repositories.CategoriaRepository;
import com.diogo.cursomc.repositories.CidadeRepository;
import com.diogo.cursomc.repositories.ClienteRepository;
import com.diogo.cursomc.repositories.EnderecoRepository;
import com.diogo.cursomc.repositories.EstadoRepository;
import com.diogo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CurscomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriarepository;
	
	@Autowired
	ProdutoRepository produtorepository;
	
	@Autowired
	EstadoRepository estadorepository;
	
	@Autowired
	CidadeRepository cidaderepository;
	
	@Autowired
	ClienteRepository clienterepository;
	
	@Autowired
	EnderecoRepository enderecorepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CurscomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 5000.00);
		Produto p3 = new Produto(null, "Mouse", 500.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat1.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		

		
		categoriarepository.saveAll(Arrays.asList(cat1, cat2));
		
		produtorepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));	
		
		estadorepository.saveAll(Arrays.asList(est1,est2));
		
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silvia", "maria@gmail.com", "2525522", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("24445588", "98566655"));
		
		Endereco e1 = new Endereco(null, "rua das flores", "25", "B", "Perdizes", "252225", cli1, c1);
		Endereco e2 = new Endereco(null, "rua das garças", "545", "Lado B", "Jardim", "234343225", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienterepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(e1,e2));
		
	}

}
