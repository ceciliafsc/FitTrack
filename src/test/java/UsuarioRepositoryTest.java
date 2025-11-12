package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class UsuarioRepositoryTest {

    @Test
    void deveCriarUsuarioValido() {
        Usuario u = new Usuario("Cecilia", "cecilia@fittrack.com", "senha1234");
        assertEquals("Cecilia", u.getNome());
        assertEquals("cecilia@fittrack.com", u.getEmail());
    }

    @Test
    void naoDeveCriarUsuarioComNomeVazio() {
        assertThrows(RuntimeException.class, () -> new Usuario("", "email@teste.com", "senha1234"));
    }

    @Test
    void deveAdicionarEListarAtividade() {
        Usuario u = new Usuario("Cecilia", "c@fit.com", "12345678");
        TipoAtividade corrida = new TipoAtividade("Corrida");
        Atividade a = new Atividade(UUID.randomUUID(), corrida, 30.0, 5.0, Intensidade.MODERADA);
        u.adicionarAtividade(a);
        assertEquals(1, u.getAtividades().size());
        assertEquals("Corrida", u.getAtividades().get(0).getTipo().getNome());
    }
}
