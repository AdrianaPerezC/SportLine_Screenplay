package tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import uip.SportLinePromociones;

public class SeleccionarPromociones implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SportLinePromociones.BTN_PROMOCIONES),
                Click.on(SportLinePromociones.BTN_PROM_ARTICULOS)
        );

    }

    public static SeleccionarPromociones enSportLine(){
        return Instrumented.instanceOf(SeleccionarPromociones.class).withProperties();
    }
}
