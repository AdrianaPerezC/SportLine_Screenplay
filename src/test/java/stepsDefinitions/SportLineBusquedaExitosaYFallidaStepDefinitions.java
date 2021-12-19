package stepsDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import tasks.BuscarProducto;
import uip.SportLinePage;

public class SportLineBusquedaExitosaYFallidaStepDefinitions {
    String productoExitoso="Reebok Hiit Tr Dynred";
    String productoFallido="Mouse";

    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }

    @When("^busco el nombre de producto existente en la pagina$")
    public void buscoElNombreDeProductoExistenteEnLaPagina() {
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(productoExitoso));
    }

    @Then("^visualizo detalles del producto existente en pantalla$")
    public void visualizoDetallesDelProductoExistenteEnPantalla() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXT_ELEMENTO_BUSQUEDA.of(productoExitoso)), WebElementStateMatchers.containsText(productoExitoso.replace("  "," "))));
    }

    @When("^busco el nombre del producto que no esta en la pagina$")
    public void buscoElNombreDelProductoQueNoEstaEnLaPagina() {
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(productoFallido));
    }

    @Then("^visualizo un mensaje de no encontrado$")
    public void visualizoUnMensajeDeNoEncontrado() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXXT_ELEMENTO_NO_ENCONTRADO), WebElementStateMatchers.containsText("no ha devuelto".replace("  "," "))));
    }

}
