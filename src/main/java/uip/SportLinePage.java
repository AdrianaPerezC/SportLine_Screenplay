package uip;

import net.serenitybdd.screenplay.targets.Target;

public class SportLinePage {
    public static final Target TXT_BUSCADOR= Target.the("Campo de busqueda").locatedBy("//input[@id='search' and @class='input-text']");
    public static final Target BTN_ELEMENTO_BUSQUEDA= Target.the("").locatedBy("//strong[@class='product name product-item-name']//a[contains(text(),'{0}')]");
    public static final Target TXT_ELEMENTO_BUSQUEDA= Target.the("").locatedBy("//div[@class='product-info-main']/div[@class='page-title-wrapper']/h1[@class='page-title']//span[contains(text(),'{0}')]");
    public static final Target TXXT_ELEMENTO_NO_ENCONTRADO=Target.the("").locatedBy("//div[@class=\"no-results\"]/p");
}
