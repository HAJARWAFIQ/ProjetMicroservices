package dcc.tp2.enseignantservice.repository;

import dcc.tp2.enseignantservice.entities.Enseignant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnseignantRepositoryTest {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @BeforeEach
    void setUp() {
        enseignantRepository.save(new Enseignant(null, "ali", "ahmadi", "LA11233", "ali@mail.com", "123", "info", "Enseignant"));
        enseignantRepository.save(new Enseignant(null, "mohamed", "mrini", "KB11233", "mohamed@mail.com", "123", "info", "Enseignant"));
    }

    @Test
    void findEnseignantByEmail_success() {
        // Given
        String email = "ali@mail.com";

        // When
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(new Enseignant(null, "ali", "ahmadi", "LA11233", email, "123", "info", "Enseignant"));
    }

    @Test
    void findEnseignantByEmail_notFound() {
        // Given
        String email = "abc@mail.com";

        // When
        Enseignant result = enseignantRepository.findEnseignantByEmail(email);

        // Then
        Assertions.assertThat(result).isNull();
    }
}
