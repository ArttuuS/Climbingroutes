package hh.sof03.Climbingroutes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.Climbingroutes.domain.Discipline;
import hh.sof03.Climbingroutes.domain.DisciplineRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DisciplineRepositoryTest {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Test
    public void findByNameShouldReturnDiscipline() {
        List<Discipline> disciplines = disciplineRepository.findByName("Boulder");

        assertThat(disciplines).hasSize(1);
        assertThat(disciplines.get(0).getDisciplineid()).isEqualTo(1);
    }

    @Test
    public void createNewDiscipline() {
        Discipline discipline = new Discipline("Speed");
        disciplineRepository.save(discipline);

        assertThat(discipline.getDisciplineid()).isNotNull();
    }

    @Test
    public void deleteDiscipline() {
        Discipline discipline = new Discipline("Trad");
        disciplineRepository.save(discipline);

        Long disciplineId = discipline.getDisciplineid();
        disciplineRepository.deleteById(disciplineId);

        Discipline deletedDiscipline = disciplineRepository.findById(disciplineId).orElse(null);
        assertThat(deletedDiscipline).isNull();
    }
}
