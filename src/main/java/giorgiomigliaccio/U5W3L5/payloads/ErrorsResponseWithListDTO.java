package giorgiomigliaccio.U5W3L5.payloads;


import java.util.Date;
import java.util.List;

public record ErrorsResponseWithListDTO(String message,
                                        Date timestamp,
                                        List<String> errorsList) {
}