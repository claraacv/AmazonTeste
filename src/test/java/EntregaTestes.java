import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class EntregaTestes {
    @Test
    public void entregaEntregue(){
        baseURI = "https://mockfast.io";
        basePath = "/backend/apitemplate/get/QUMLUQGLQG";

        given()
                .when()
                        .get()
                .then()
                        .assertThat()
                            .statusCode(200)
                            .body("codigoVenda", equalTo("ABC123"))
                            .body("status", equalTo("Entregue"))
                            .body("cidade", equalTo("Ibirama"))
                            .body("estado", equalTo("SC"))
                            .body("estimativaEntrega", equalTo("2025-06-26"));;

    }

    @Test
    public void entregaEmAndamento(){
        baseURI = "https://mockfast.io";
        basePath = "/backend/apitemplate/get/W3TY427MBF";

        given()
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .body("codigoVenda", equalTo("ABC124"))
                .body("status", equalTo("Em andamento"))
                .body("cidade", equalTo("Blumenau"))
                .body("estado", equalTo("SC"))
                .body("estimativaEntrega", equalTo("2025-08-12"));;

    }
}
