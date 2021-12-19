#Author: adrianaperez.crist@gmail.com
#Keywords Summary :

Feature: HU-001 Buscador SportLine
  Yo como usuario de SportLine
  quiero buscar un producto en la plataforma
  para ver el nombre del producto

  Scenario Outline: Buscar producto.
    Given Dado que me encuentro en la pagina de SportLine
    When busque el producto <NombreProducto>
    Then puedo ver <NombreProducto> en pantalla
    Examples:
      | NombreProducto  |
      | Reebok Hiit Tr Dynred |
      |Advanced Trainette|
      |Downshifter 9     |
      |Lebron Witness 4  |
      |Court Borough Low 2|




