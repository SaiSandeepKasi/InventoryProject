package AiThinkers.example.Inventory.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity// Marks this class as a JPA entity (table)
@Table(name = "roles")      // Maps to table "roles"
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment PK
    private int id;

    @Column(nullable = false, unique = true)
    private String name;     // "ADMIN" or "SALESPERSON"

    // Constructors, getters, setters

    public Role(){

    }


    public Role( int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String roleName) {
        this.name=roleName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

