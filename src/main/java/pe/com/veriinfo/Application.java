package pe.com.veriinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.com.veriinfo.repository.ClienteRepository;

@RequiredArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
