package schedule.model;

import org.hibernate.validator.constraints.Email;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

    private int id;

    @Pattern(regexp = "^([A-Za-z]{2,200})$", message = "Only latin and more than two symbols accept in name")
    private String name;

    @Pattern(regexp = "^([A-Za-z]{2,200})$", message = "Only latin and more than two symbols accept in surname")
    private String surname;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private Role role;

    @Size(min = 6, max = 250, message = "Password should not be less than 6 symbols")
    private String password;

    @Email(message = "Email should be valid")
    private String email;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDay=" + birthDay +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
