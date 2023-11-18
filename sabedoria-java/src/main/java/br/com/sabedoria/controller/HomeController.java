package br.com.sabedoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.sabedoria.model.Cliente;
import br.com.sabedoria.model.Mentor;
import br.com.sabedoria.repository.ClienteRepository;
import br.com.sabedoria.repository.MentorRepository;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/sucesso")
    public String sucesso() {
        return "sucesso";
    }
    
    @RequestMapping("/listar")
    @GetMapping
    public ModelAndView listar() {
        ModelAndView modelAndView = new ModelAndView("listar.html");

        List<Cliente> clientes = clienteRepository.findAll();
        modelAndView.addObject("clientes", clientes);

        return modelAndView;
    }
    
	@Autowired
	private ClienteRepository clienteRepository;
	
	@RequestMapping("/listarMentor")
	@GetMapping
	public ModelAndView listarMentor() {
	    ModelAndView modelAndView = new ModelAndView("listarMentor.html");

	    List<Mentor> mentors = mentorRepository.findAll();
	    modelAndView.addObject("mentors", mentors);

	    return modelAndView;
	}

@Autowired
private MentorRepository mentorRepository;
    
	

}