package uip;

import net.serenitybdd.screenplay.targets.Target;

public class SportLinePromociones {
    public static final Target BTN_PROMOCIONES= Target.the("").locatedBy("//div[@class='top_links']/ul/li/a[@tabindex=\"0\" and @href=\"https://sportline.com.co/promociones/\"]");
    public static final Target BTN_PROM_ARTICULOS= Target.the("").locatedBy("//a/img [@class=\"pagebuilder-mobile-hidden\" and @alt=\"\" and @title=\"\" and @data-element=\"desktop_image\" and @data-pb-style=\"QL3FS4L\"]");
    public static final Target TXT_PROMOCION=Target.the("").locatedBy("(//div[@class=\"__featured_tag\"])[1]");
    public static final Target BTN_INFO_PRODUCTO=Target.the("").locatedBy("(//div[@class=\"slick-slide slick-active\"]/div/li[@class=\"product-item\"]/div[@class=\"product-item-info\"]/a)[1]");
    public static final Target TXT_INFO_PRODUCTO=Target.the("").locatedBy("(//th[@class=\"col label\" and @scope=\"row\"])[2]");
}
