package sec.project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
public class Signup extends AbstractPersistable<Long> {

    @Id
    private Long id;
    private String name;
    private String address;
    private String creditCard;
    private Boolean isAdmin;

    public Signup() {
        super();
    }

    public Signup(String name, String address, String creditCard) {
        this();
        this.name = name;
        this.address = address;
        this.creditCard = creditCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCreditCard(){
        return creditCard;
    }
    
    public void setCreditCard(String creditCard){
        this.creditCard = creditCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setIsAdmin(Boolean isAdmin){
        this.isAdmin = isAdmin;
    }
    
    public Boolean getIsAdmin(){
        return isAdmin;
    }

}
