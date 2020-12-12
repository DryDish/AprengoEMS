package site.aprengo.api.employee;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Basic
    @Column(name = "first_name", length = 80)
    private String firstName;

    @Basic
    @Column(name = "last_name", length = 80)
    private String lastName;

    @Basic
    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Basic
    @Column(name = "minutes_worked", columnDefinition = "integer default 0")
    private int minutesWorked;

    public Employee() {}
    
    public Employee(String firstName, String lastName, String phoneNumber, int minutesWorked) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.minutesWorked = minutesWorked;
	}

	public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public int getMinutesWorked()
    {
        return minutesWorked;
    }

    public void setMinutesWorked(int minutesWorked)
    {
        this.minutesWorked = minutesWorked;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                minutesWorked == employee.minutesWorked &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(phoneNumber, employee.phoneNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, phoneNumber, minutesWorked);
    }
}
