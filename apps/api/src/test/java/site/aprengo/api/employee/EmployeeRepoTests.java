package site.aprengo.api.employee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeRepoTests
{
    @Autowired

    EmployeeRepo employeeRepo;

    @Test
    void employeeIsNotNullTest()
    {
        assertThat(employeeRepo).isNotNull();
    }
}
