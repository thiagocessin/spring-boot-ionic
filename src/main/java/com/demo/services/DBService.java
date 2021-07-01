package com.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Categoria;
import com.demo.domain.Cidade;
import com.demo.domain.Cliente;
import com.demo.domain.Endereco;
import com.demo.domain.Estado;
import com.demo.domain.ItemPedido;
import com.demo.domain.Pagamento;
import com.demo.domain.PagamentoComBoleto;
import com.demo.domain.PagamentoComCartao;
import com.demo.domain.Pedido;
import com.demo.domain.Produto;
import com.demo.domain.enums.EstadoPagamento;
import com.demo.domain.enums.TipoCliente;
import com.demo.repositories.CategoriaRepository;
import com.demo.repositories.CidadeRepository;
import com.demo.repositories.ClienteRepository;
import com.demo.repositories.EnderecoRepository;
import com.demo.repositories.EstadoRepository;
import com.demo.repositories.ItemPedidoRepository;
import com.demo.repositories.PagamentoRepository;
import com.demo.repositories.PedidoRepository;
import com.demo.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CidadeService cidadeService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void initiate() {
		
		//chamar métodos necessários
		
		//carregarClienteEnderecosTelefones();
	}
	
	
	public void carregarItemPedido() {

		Pedido ped1 = pedidoService.find(17);
		Pedido ped2 = pedidoService.find(18);

		Produto p1 = produtoService.find(1);
		Produto p2 = produtoService.find(2);
		Produto p3 = produtoService.find(3);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

	public void carregarPedido() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Cliente cli1 = clienteService.find(1);
		Endereco e1 = enderecoService.find(1);
		Endereco e2 = enderecoService.find(2);

		Pedido ped1 = new Pedido(sdf.parse("30/09/2020 10:32"), cli1, e1);

		Pedido ped2 = new Pedido(sdf.parse("10/10/2020 17:23"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2020 00:00"),
				null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));

	}

	public void carregarClienteEnderecosTelefones() {

		Cliente cli1 = new Cliente(null, "Thiago Silva", "tsilva@gmail.com", "11238837635", TipoCliente.PESSOAFISICA,pe.encode("1234"));
		cli1.getTelefones().addAll(Arrays.asList("12345678", "76767667"));

		Cidade cidade1 = cidadeService.find(1);
		Cidade cidade2 = cidadeService.find(2);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38383863", cli1, cidade1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "45443863", cli1, cidade2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}

	public void carregarCidadesEstados() {

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

	public void carregaCategoriasProdutos() {

		List<Categoria> all = categoriaRepository.findAll();
		Map<Integer, Categoria> map = new HashMap<>();

		for (Categoria c : all) {
			map.put(c.getId(), c);
		}

//		Produto p1 = new Produto("Mesa de Escritório", 300.00);
//		map.get(1).getProdutos().add(p1);
//		p1.getCategorias().addAll(Arrays.asList(map.get(1)));

		Produto p2 = new Produto("Luminária", 120.00);
		map.get(8).getProdutos().add(p2);
		p2.getCategorias().addAll(Arrays.asList(map.get(1), map.get(2), map.get(4)));

		categoriaRepository.saveAll(all);
		produtoRepository.saveAll(Arrays.asList(p2));

	}
}
