package Vfbarber.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AgendamentoController {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/agendar")
    @ResponseBody
    public String agendar(@RequestBody Agendamento agendamento) {
        repository.save(agendamento);
        return "ok";
    }

    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome,
                            @RequestParam String email,
                            @RequestParam String senha) {
        usuarioService.cadastrar(nome, email, senha);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Agendamento> agendamentos = repository.findAll();
        model.addAttribute("agendamentos", agendamentos);
        return "admin";
    }

    @GetMapping("/criar-admin")
    @ResponseBody
    public String criarAdmin() {
        usuarioService.cadastrar("Admin", "admin@vfbarber.com", "admin123");
        return "Admin criado!";
    }
}