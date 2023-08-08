package fer.hr.zavrsni.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDTO {

	private Long id;
	@NotNull(message = "Username ne smije biti null")
	private String username;
	@NotNull(message = "Password ne smije biti null")
	private String password;

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
