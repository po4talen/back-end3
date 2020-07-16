Feature: My featuredescription

  Scenario: Verify that GET request return status code 200
    Given I have server by url "https://reqres.in"
    When I send GET request on endpoint "/api/users" and page 2
    Then I get response status code 200


    Scenario:Verify that POST request return status code 201
      Given I have server by url "https://reqres.in"
      When I send POST request on endpoint "/api/users"
      Then I get response status code2 201

#      Then I check name morpheus and job leader


#     Scenario Outline:
#     When I send POST request on endpoint {endpoint} request on endpoint2 "/api/users"
#       Then I get response status code 201
#       Then I check name morpheus and job leader
#       Examples:
#       |name|job|
#       |vacya|Qa|
#       |petro|Dev|

