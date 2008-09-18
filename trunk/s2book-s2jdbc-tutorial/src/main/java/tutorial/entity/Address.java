package tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Address {

    @Id
    @GeneratedValue
    public Integer id;

    public String name;

    @OneToOne(mappedBy = "address")
    public Employee employee;

    @Version
    public Integer version;
}