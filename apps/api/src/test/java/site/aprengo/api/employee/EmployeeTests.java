package site.aprengo.api.employee;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class EmployeeTests
{

	@Test
	void testEmployee()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Check that the Employee is not null
		Assertions.assertThat(testEmp).isNotNull();
	}

	@Test
	void testGetFirstName()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Check that the Employee's first name matches
		String testEmpFirstName = testEmp.getFirstName();
		Assertions.assertThat(testEmpFirstName).isEqualTo("firstName");
	}

	@Test
	void testSetFirstName()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Change testEmp's first name
		String newFirstName = "newFirstName";
		testEmp.setFirstName(newFirstName);
		
		// Check that the new first name is correctly applied
		String testEmpFirstNameNew = testEmp.getFirstName();
		Assertions.assertThat(testEmpFirstNameNew).isEqualTo("newFirstName");
	}

	@Test
	void testGetLastName()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Check that the Employee's last name matches
		String testEmpLastName = testEmp.getLastName();
		Assertions.assertThat(testEmpLastName).isEqualTo("lastName");
	}

	@Test
	void testSetLastName()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Change testEmp's last name
		String newLastName = "newLastName";
		testEmp.setLastName(newLastName);
				
		// Check that the new last name is correctly applied
		String testEmpLastNameNew = testEmp.getLastName();
		Assertions.assertThat(testEmpLastNameNew).isEqualTo("newLastName");
	}

	@Test
	void testGetPhoneNumber()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Check that the Employee's phone number matches
		String testEmpPhoneNumber = testEmp.getPhoneNumber();
		Assertions.assertThat(testEmpPhoneNumber).isEqualTo("phoneNumber");
	}

	@Test
	void testSetPhoneNumber()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Change testEmp's phone number
		String newPhoneNumber = "newPhoneNumber";
		testEmp.setPhoneNumber(newPhoneNumber);
				
		// Check that the new phone number is correctly applied
		String testEmpPhoneNumberNew = testEmp.getPhoneNumber();
		Assertions.assertThat(testEmpPhoneNumberNew).isEqualTo("newPhoneNumber");
	}

	@Test
	void testGetMinutesWorked()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Check that the Employee's phone number matches
		int testEmpMinutesWorked = testEmp.getMinutesWorked();
		Assertions.assertThat(testEmpMinutesWorked).isEqualTo(1);
	}

	@Test
	void testSetHoursWorked()
	{
		// create a blank Employee
		Employee testEmp;

		// Initialise the Employee
		testEmp = new Employee("firstName", "lastName", "phoneNumber", 1);

		// Change testEmp's phone number
		int newMinutesWorked = 2;
		testEmp.setMinutesWorked(newMinutesWorked);
				
		// Check that the new phone number is correctly applied
		int testEmpMinutesWorkedNew = testEmp.getMinutesWorked();
		Assertions.assertThat(testEmpMinutesWorkedNew).isEqualTo(2);
	}

}
