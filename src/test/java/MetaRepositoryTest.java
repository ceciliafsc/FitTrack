package test.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MetaRepositoryTest {

    @Test
    void testAdicionarMeta() {
        MetaRepository repo = new MetaRepository();
        Meta meta = new Meta("Correr 5km", "Alta");
        repo.adicionar(meta);
        assertEquals(1, repo.listar().size());
    }
}