package schedule.model;

import org.joda.time.LocalDate;

public class User {

    private int id;

   // @Size(min = 2, max = 20, message = "Name should not be less than 2 symbols")
    private String name;

    //@Size(min = 2, max = 50, message = "Surname should not be less than 2 symbols")
    private String surname;

   //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private String role;

   // @Size(min = 6, max = 30, message = "Password should not be less than 6 symbols")
    private String password;

   // @Email(message = "Email should be valid")
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
}
