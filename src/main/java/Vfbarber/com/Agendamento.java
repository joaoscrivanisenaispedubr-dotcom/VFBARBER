package Vfbarber.com;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;
    private String telefone;
    private String servico;
    private LocalDateTime dataHorario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }

    public LocalDateTime getDataHorario() { return dataHorario; }
    public void setDataHorario(LocalDateTime dataHorario) { this.dataHorario = dataHorario; }
}