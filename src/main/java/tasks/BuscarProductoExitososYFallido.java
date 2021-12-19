package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import org.openqa.selenium.Keys;
import uip.SportLinePage;

public class BuscarProductoExitososYFallido implements Task {
    private String producto;

    public BuscarProductoExitososYFallido(String producto) {
        this.producto = producto;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(producto).into(SportLinePage.TXT_BUSCADOR),
                Hit.the(Keys.ENTER).into(SportLinePage.TXT_BUSCADOR),
                Click.on(SportLinePage.BTN_ELEMENTO_BUSQUEDA.of(producto))
        );
    }
    public static BuscarProducto enSportLine(String producto){
        return Instrumented.instanceOf(BuscarProducto.class).withProperties(producto);
    }
}
