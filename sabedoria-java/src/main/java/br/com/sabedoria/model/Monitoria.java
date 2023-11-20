//package br.com.sabedoria.model;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Entity
//@Table
//public class Monitoria {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "mentor_id", nullable = false)
//    private Mentor mentor;
//
//    @ManyToOne
//    @JoinColumn(name = "cliente_id", nullable = false)
//    private Cliente cliente;
//
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime horario;
//
//    public Monitoria() {
//    }
//
//    public Monitoria(Long id, Mentor mentor, Cliente cliente, LocalDateTime horario) {
//        this.id = id;
//        this.mentor = mentor;
//        this.cliente = cliente;
//        this.horario = horario;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Mentor getMentor() {
//        return mentor;
//    }
//
//    public void setMentor(Mentor mentor) {
//        this.mentor = mentor;
//    }
//
//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public LocalDateTime getHorario() {
//        return horario;
//    }
//
//    public void setHorario(LocalDateTime horario2) {
//        this.horario = horario2;
//    }
//}
