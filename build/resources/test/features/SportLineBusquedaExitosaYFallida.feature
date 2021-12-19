Feature: HU-001 Buscador SportLine
  Yo como usuario de SportLine
  quiero buscar un producto
  existente y no existente

  Background:
    Given Dado que me encuentro en la pagina de SportLine

  Scenario: Buscar producto existente
    When busco el nombre de producto existente en la pagina
    Then visualizo detalles del producto existente en pantalla

  Scenario: Buscar producto no existente
    When busco el nombre del producto que no esta en la pagina
    Then visualizo un mensaje de no encontrado
