package br.com.sabedoria.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.sabedoria.model.Mentor;
import br.com.sabedoria.repository.MentorRepository;

@Controller
public class MentorController {
	
	@GetMapping("/cadastrarMentor")
	public ModelAndView cadastrarMentor() {
		ModelAndView modelAndView = new ModelAndView("cadastrarMentor");

		modelAndView.addObject("mentor", new Mentor());

		return modelAndView;
	}
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@PostMapping("/cadastrarMentor")
	public ModelAndView cadastrarMentor(Mentor mentor, @RequestParam("fileMentor") MultipartFile file) throws IOException {

		try {
			mentor.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/sucesso");

		mentorRepository.save(mentor);

		return modelAndView;
	}
	
	@GetMapping("/imagemMentor/{id}")
	@ResponseBody
	public byte[] exibirImagenMentor(@PathVariable("id") Long id) {
		Mentor mentor = this.mentorRepository.getOne(id);
		return mentor.getImagem();
	}	

	
	@GetMapping("/{id}/editarMentor")
	public ModelAndView editarMentor(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("edicaoMentor");

		Mentor mentor = mentorRepository.getOne(id);
		modelAndView.addObject("mentor", mentor);

		return modelAndView;
	}

	@PostMapping("/{id}/editarMentor")
	public ModelAndView editarMentor(Mentor mentor, @RequestParam("fileMentor") MultipartFile file) throws IOException {	
		
		try {
			mentor.setImagem(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		mentorRepository.save(mentor);
		ModelAndView modelAndView = new ModelAndView("redirect:/listarMentor");

		return modelAndView;
	}	
	
	@GetMapping("/mentor/{id}/excluir")
	public ModelAndView excluirMentor(@PathVariable Long id) {
	    ModelAndView modelAndView = new ModelAndView("redirect:/listarMentor");

	    mentorRepository.deleteById(id);

	    return modelAndView;
	}



}
