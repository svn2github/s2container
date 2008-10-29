package tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    public Integer salary;

    public Integer departmentId;

    @ManyToOne
    public Department department;

    public Integer addressId;

    @OneToOne
    public Address address;

    @Version
    public Integer version;
}