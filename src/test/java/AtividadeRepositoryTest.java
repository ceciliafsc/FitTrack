package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AtividadeRepositoryTest {

    @Test
    void testSalvarAtividade() {
        AtividadeRepository repo = new AtividadeRepository();
        Atividade a = new Atividade("Corrida", 30);
        repo.salvar(a);
        assertEquals(1, repo.listar().size());
    }
}