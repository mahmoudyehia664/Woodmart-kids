Feature: full journey in the site
  as a visitor to the site i want to be able to register and add products to cart and checkout
  and add products to the wishlist and logout then login again and search for products
  and add products to cart from my wishlist and view orders

  Scenario: registration
    Given the visitor in any page
    When the visitor open login and register page from header
    And register with valid data
    Then redirected to dashboard page


  Scenario:add product to cart from home page
    Given the user in the home page
    When the user select a product and add it to cart
    Then popup appear ask for open cart page or continue shopping
    And the product added to cart

  Scenario: logout
    Given the user in any page
    When the user clicked logout from the header
    Then user logged out
    And redirected to Login and Register page

  Scenario: login
    Given the visitor in any page
    When the visitor open login and register page from header
    And login with valid data
    Then redirected to dashboard page