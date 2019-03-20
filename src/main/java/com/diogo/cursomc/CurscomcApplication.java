package com.diogo.cursomc;

import java.text.SimpleDateFormat;
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
import com.diogo.cursomc.domain.ItemPedido;
import com.diogo.cursomc.domain.ItemPedidoPk;
import com.diogo.cursomc.domain.Pagamento;
import com.diogo.cursomc.domain.PagamentoComBoleto;
import com.diogo.cursomc.domain.PagamentoComCartao;
import com.diogo.cursomc.domain.Pedido;
import com.diogo.cursomc.domain.Produto;
import com.diogo.cursomc.domain.enums.EstadoPagamento;
import com.diogo.cursomc.domain.enums.TipoCliente;
import com.diogo.cursomc.repositories.CategoriaRepository;
import com.diogo.cursomc.repositories.CidadeRepository;
import com.diogo.cursomc.repositories.ClienteRepository;
import com.diogo.cursomc.repositories.EnderecoRepository;
import com.diogo.cursomc.repositories.EstadoRepository;
import com.diogo.cursomc.repositories.ItemPedidoRepository;
import com.diogo.cursomc.repositories.PagamentoRepository;
import com.diogo.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	PedidoRepository pedidorepository;
	
	@Autowired
	PagamentoRepository pagamentorepository;
	
	@Autowired
	ItemPedidoRepository itempedidorepository;	
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 09:55"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidorepository.saveAll(Arrays.asList(ped1,ped2));
		
		pagamentorepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.0,2,80.);
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.,1,800.);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidorepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
