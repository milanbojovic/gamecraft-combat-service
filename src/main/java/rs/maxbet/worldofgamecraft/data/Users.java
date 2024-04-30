package rs.maxbet.worldofgamecraft.data;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.maxbet.worldofgamecraft.data.transport.UserRegistrationEvent;

@Entity
@Table
@Data
@NoArgsConstructor
public class Users {
    @Id
    private int id;
    private String role;


    public Users(UserRegistrationEvent userRegistrationEvent) {
        this.id = userRegistrationEvent.getId();
        this.role = userRegistrationEvent.getRole();
    }
}