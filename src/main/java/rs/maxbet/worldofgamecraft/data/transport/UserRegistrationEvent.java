package rs.maxbet.worldofgamecraft.data.transport;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.maxbet.worldofgamecraft.data.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationEvent {
    @Id
    private int id;
    private String role;

    public UserRegistrationEvent(Users user) {
        this.id = user.getId();
        this.role = user.getRole();
    }
}
