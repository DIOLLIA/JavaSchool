package schedule.entity;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class RoleEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roleAdmin) {
        this.role = roleAdmin;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}


