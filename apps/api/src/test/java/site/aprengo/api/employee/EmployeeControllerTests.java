package site.aprengo.api.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class EmployeeControllerTests
{
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmployeeController employeeController;

    @Test
    void controllerIsNotNull()
    {
        assertThat(employeeController).isNotNull();
    }

    //region findAll Tests

    @Test
    @DisplayName("GET /employees should return status code 200")
    void findAllStatusCodeTest() throws Exception
    {
        given(employeeRepo.findAll()).willReturn(new ArrayList<>());

        mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(employeeRepo, VerificationModeFactory.times(1)).findAll();
        reset(employeeRepo);
    }

    @Test
    @DisplayName("GET /employees should return a list of employees")
    void findAllTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 421);
        var cris = new Employee("Cris", "Purcia", "987654321", 422);
        var teo = new Employee("Teo", "Dane", "1122334455", 423);

        var allEmployees = Arrays.asList(alex, cris, teo);

        given(employeeRepo.findAll()).willReturn(allEmployees);

        mvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", is("Alex")))
                .andExpect(jsonPath("$[0].lastName", is("Maccnyan")))
                .andExpect(jsonPath("$[0].phoneNumber", is("123456789")))
                .andExpect(jsonPath("$[0].minutesWorked", is(421)))
                .andExpect(jsonPath("$[1].firstName", is("Cris")))
                .andExpect(jsonPath("$[1].lastName", is("Purcia")))
                .andExpect(jsonPath("$[1].phoneNumber", is("987654321")))
                .andExpect(jsonPath("$[1].minutesWorked", is(422)))
                .andExpect(jsonPath("$[2].firstName", is("Teo")))
                .andExpect(jsonPath("$[2].lastName", is("Dane")))
                .andExpect(jsonPath("$[2].phoneNumber", is("1122334455")))
                .andExpect(jsonPath("$[2].minutesWorked", is(423)));
        verify(employeeRepo, VerificationModeFactory.times(1)).findAll();
        reset(employeeRepo);
    }

    //endregion

    //region Fine One tests

    @Test
    @DisplayName("GET /employee/{id} with a valid ID should return status code 200")
    void findOneStatusCodeTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 421);
        alex.setId(1);

        when(employeeRepo.findById(alex.getId())).thenReturn(Optional.of(alex));

        mvc.perform(get("/employees/" + alex.getId())).andExpect(status().isOk());
        verify(employeeRepo, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("GET /employee/{id} with a valid id should return an employee")
    void findOneTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 421);
        alex.setId(1);

        when(employeeRepo.findById(1L)).thenReturn(Optional.of(alex));

        mvc.perform(get("/employees/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.lastName", is("Maccnyan")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.minutesWorked", is(421)));
        verify(employeeRepo, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("GET /employees/{id} with an invalid ID should return status code 404")
    void findOneInvalidStatusCodeTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 421);
        alex.setId(1);

        // The id doesn't match on purpose so it fails to find the resource
        when(employeeRepo.findById(0L)).thenReturn(Optional.of(alex));

        mvc.perform(get("/employees/1")).andExpect(status().isNotFound());
        verify(employeeRepo, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("GET /employee/{id} with an invalid id should return Resource not Found message")
    void findOneInvalidTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 421);
        alex.setId(1);

        // The id doesn't match on purpose so it fails to find the resource
        when(employeeRepo.findById(0L)).thenReturn(Optional.of(alex));

        mvc.perform(get("/employees/1").contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string("Failed to find an employee with an ID of 1"));
        verify(employeeRepo, VerificationModeFactory.times(1)).findById(Mockito.any());
        reset(employeeRepo);
    }

    //endregion

    //region create Tests

    @Test
    @DisplayName("POST /employees should return status code 201")
    void createStatusCodeTest() throws Exception
    {
        var newEmployee = new Employee("Alex", "Maccnyan", "123456789", 69);
        given(employeeRepo.save(Mockito.any())).willReturn(newEmployee);

        mvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(newEmployee)))
                .andExpect(status().isCreated());
        verify(employeeRepo, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("POST /employees should return created Employee")
    void createTest() throws Exception
    {
        Employee alex = new Employee("Alex", "Maccnyan", "123456789", 69);
        given(employeeRepo.save(Mockito.any())).willReturn(alex);

        mvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.lastName", is("Maccnyan")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.minutesWorked", is(69)));
        verify(employeeRepo, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(employeeRepo);
    }

    //endregion

    //region update Tests

    @Test
    @DisplayName("PUT /employees/{id} with a valid ID should return status code 202")
    void updateStatusCodeTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 111);
        long id = 1;

        when(employeeRepo.save(Mockito.any())).thenReturn(alex);

        mvc.perform(put("/employees/" + id).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(status().isAccepted());
        verify(employeeRepo, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("PUT /employees/{id} with a valid ID should return an updated employee")
    void updateTest() throws Exception
    {
        var oldAlex = new Employee("Alex", "The old", "987654321", 221);
        var alex = new Employee("Alex", "Maccnyan", "123456789", 112);
        long id = 1;
        when(employeeRepo.save(Mockito.any())).thenReturn(alex);

        when(employeeRepo.findById(Mockito.any())).thenReturn(Optional.of(oldAlex));


        mvc.perform(put("/employees/" + id).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.lastName", is("Maccnyan")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.minutesWorked", is(112)));
        verify(employeeRepo, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("PUT /employees/{id} with a not-matching ID should return a created employee")
    void updateSuccessfulTest() throws Exception
    {
        var employee = new Employee("Alex", "Maccnyan", "123456789", 112);
        var updatedEmployee = new Employee("Alex", "Maccnyan", "123456789", 112);
        updatedEmployee.setId(1);
        long id = 99;
        when(employeeRepo.save(Mockito.any())).thenReturn(updatedEmployee);

        mvc.perform(put("/employees/" + id).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(employee)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.lastName", is("Maccnyan")))
                .andExpect(jsonPath("$.phoneNumber", is("123456789")))
                .andExpect(jsonPath("$.minutesWorked", is(112)));
        verify(employeeRepo, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("PUT /employees should return status code 405")
    void updateStatusCodeInvalidTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 113);

        // We still mock the repository just in case the method goes through and tries to save
        when(employeeRepo.save(Mockito.any())).thenReturn(alex);

        mvc.perform(put("/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(status().isMethodNotAllowed());
        reset(employeeRepo);
    }

    //endregion

    //region Delete Tests

    @Test
    @DisplayName("DELETE /employees/{id} should return status code 20½")
    void deleteStatusCodeTest() throws Exception
    {
        given(employeeRepo.existsById(Mockito.any())).willReturn(true);

        mvc.perform(delete("/employees/1"))
                .andExpect(status().isAccepted());
        verify(employeeRepo, VerificationModeFactory.times(1)).existsById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("DELETE /employees/{id} with a non-existent ID should return status code 404")
    void deleteInvalidIdStatusCodeTest() throws Exception
    {
        given(employeeRepo.existsById(Mockito.any())).willReturn(false);

        mvc.perform(delete("/employees/99"))
                .andExpect(status().isNotFound());
        verify(employeeRepo, VerificationModeFactory.times(1)).existsById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("DELETE /employees/{id} should return an empty body")
    void deleteTest() throws Exception
    {
        given(employeeRepo.existsById(Mockito.any())).willReturn(true);

        mvc.perform(delete("/employees/1"))
                .andExpect(content().string(""));
        verify(employeeRepo, VerificationModeFactory.times(1)).existsById(Mockito.any());
        reset(employeeRepo);
    }

    @Test
    @DisplayName("DELETE /employees should return status code 405")
    void updateInvalidStatusCodeTest() throws Exception
    {
        var alex = new Employee("Alex", "Maccnyan", "123456789", 113);

        // We still mock the repository just in case the method goes through and tries to save
        when(employeeRepo.save(Mockito.any())).thenReturn(alex);

        mvc.perform(delete("/employees").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(alex)))
                .andExpect(status().isMethodNotAllowed());
        reset(employeeRepo);
    }

    //endregion
}
