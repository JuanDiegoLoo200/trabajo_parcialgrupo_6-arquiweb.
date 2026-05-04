package pe.edu.upc.clearair.dtos;

public class JwtResponseDTO {
    private final String jwttoken;

    public JwtResponseDTO(String jwttoken) { this.jwttoken = jwttoken; }

    public String getJwttoken() { return jwttoken; }
}
