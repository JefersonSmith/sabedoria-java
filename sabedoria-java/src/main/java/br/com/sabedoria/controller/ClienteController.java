package br.com.sabedoria.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.sabedoria.model.Cliente;
import br.com.sabedoria.repository.ClienteRepository;


@Controller
public class ClienteController {
	
	@GetMapping("/cadastrarMentorado")
	public ModelAndView cadastrarMentorado() {
		ModelAndView modelAndView = new ModelAndView("cadastrarMentorado");

		modelAndView.addObject("cliente", new Cliente());

		return modelAndView;
	}
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping("/cadastrarMentorado")
	public ModelAndView cadastrarMentorado(Cliente cliente, @RequestParam("fileCliente") MultipartFile file) throws IOException {

		try {
			cliente.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/sucesso");

		clienteRepository.save(cliente);

		return modelAndView;
	}
	
	@GetMapping("/imagem/{id}")
	@ResponseBody
	public byte[] exibirImagen(@PathVariable("id") Long id) {
		Cliente cliente = this.clienteRepository.getOne(id);
		return cliente.getImagem();
	}	
	
//	@GetMapping("/{id}")
//	public ModelAndView detalhar(@PathVariable Long id) {
//		ModelAndView modelAndView = new ModelAndView("detalhar.html");
//
//		Cliente cliente = clienteRepository.getOne(id);
//		modelAndView.addObject("cliente", cliente);
//
//		return modelAndView;
//	}
	
	@GetMapping("/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("edicao");

		Cliente cliente = clienteRepository.getOne(id);
		modelAndView.addObject("cliente", cliente);

		return modelAndView;
	}

	@PostMapping("/{id}/editar")
	public ModelAndView editar(Cliente cliente, @RequestParam("fileCliente") MultipartFile file) throws IOException {	
		
		try {
			cliente.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		clienteRepository.save(cliente);
		ModelAndView modelAndView = new ModelAndView("redirect:/listar");

		return modelAndView;
	}	
	
	@GetMapping("/{id}/excluir")
	public ModelAndView excluir(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/listar");

		clienteRepository.deleteById(id);

		return modelAndView;
	}


}
