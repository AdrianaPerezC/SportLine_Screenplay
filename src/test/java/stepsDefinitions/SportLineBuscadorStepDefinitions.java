package stepsDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import drivers.GoogleChromeDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import tasks.BuscarProducto;
import uip.SportLinePage;

public class SportLineBuscadorStepDefinitions {
    Actor actor=new Actor("Cliente");
    //Se puede de dos maneras la primera con el actor
    //O la segunda como aparece en Before

    @Before
    public void before(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^Dado que me encuentro en la pagina de SportLine$")
    public void dadoQueMeEncuentroEnLaPaginaDeMercadoLibre() {
        //actor.can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://www.mercadolibre.com.co/")));
        OnStage.theActorCalled("Cliente").can(BrowseTheWeb.with(GoogleChromeDriver.chromeHisBrowserWeb().on("https://sportline.com.co/")));
    }

    @When("^busque el producto (.*)$")
    public void busqueElProducto(String producto) {
        //actor.attemptsTo(BuscarProducto.enMercadoLibre(producto));
        OnStage.theActorInTheSpotlight().attemptsTo(BuscarProducto.enSportLine(producto));
    }

    @Then("^puedo ver (.*) en pantalla$")
    public void puedoVerElProductoEnPantalla(String producto) {
        //actor.should(GivenWhenThen.seeThat(WebElementQuestion.the(MercadoLibrePage.TXT_ELEMENTO_BUSQUEDA.of(producto)), WebElementStateMatchers.containsText(producto.replace("  "," "))));
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(WebElementQuestion.the(SportLinePage.TXT_ELEMENTO_BUSQUEDA.of(producto)), WebElementStateMatchers.containsText(producto.replace("  "," "))));

    }

}
