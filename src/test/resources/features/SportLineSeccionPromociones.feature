Feature: HU-003 Visualizar promociones SportLine
  Yo como usuario de SportLine
  quiero visualizar las promociones de la pagina SportLine

  Background:
    Given Dado que me encuentro en la pagina

  Scenario: Seleccionar promociones
    When seleccione la seccion promociones de la pagina SportLine
    Then vemos los productos en promocion

  Scenario: seleccionar el primer producto que aparezca en promociones
    When selecciono el primer producto que aparace
    Then visualizo las caracteristicas del producto seleccionado