package br.com.sabedoria.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.sabedoria.model.Cliente;
import br.com.sabedoria.model.Mentor;
import br.com.sabedoria.model.Monitoria;
import br.com.sabedoria.repository.ClienteRepository;
import br.com.sabedoria.repository.MentorRepository;
import br.com.sabedoria.repository.MonitoriaRepository;

@Controller
public class MonitoriaController {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MonitoriaRepository monitoriaRepository;

    @GetMapping("/cadastrarMonitoria")
    public ModelAndView cadastrarMonitoria() {
        ModelAndView modelAndView = new ModelAndView("cadastrarMonitoria");

        List<Mentor> mentores = mentorRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();

        modelAndView.addObject("mentores", mentores);
        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("monitoria", new Monitoria());

        return modelAndView;
    }

    @PostMapping("/cadastrarMonitoria")
    public ModelAndView cadastrarMonitoria(@ModelAttribute Monitoria monitoria,
                                           @RequestParam("mentorId") Long mentorId,
                                           @RequestParam("clienteId") Long clienteId,
                                           @RequestParam("dataHora") String dataHora) {
        Mentor mentor = mentorRepository.getOne(mentorId);
        Cliente cliente = clienteRepository.getOne(clienteId);

        // Converta a String dataHora para um objeto LocalDateTime
        LocalDateTime horario = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        monitoria.setMentor(mentor);
        monitoria.setCliente(cliente);
        monitoria.setHorario(horario); // Defina a data e hora aqui

        ModelAndView modelAndView = new ModelAndView("redirect:/listarMonitorias");

        monitoriaRepository.save(monitoria);

        return modelAndView;
    }
    
    @GetMapping("/listarMonitorias")
    public ModelAndView listarMonitorias() {
        ModelAndView modelAndView = new ModelAndView("listarMonitorias");

        List<Monitoria> monitorias = monitoriaRepository.findAll();
        modelAndView.addObject("monitorias", monitorias);

        return modelAndView;
    }
}
