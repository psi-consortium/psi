=begin

# :book: wiki-Internal section, not compiled to documents

[[_TOC_]]

# A User Story: Demonstration of capabilities

> The heading has to be included in the document that includes this document.

=end
The outlined case study shows how the interplay between different PSSs and different entities - a customer aiming at booking specific services to meet their communication needs, a provider offering resources or services via a PSS to broaden their reach to different user communities - can work adapting the PSI APIs.
The User Story accompanying the Case Study showcases how the interaction between different PSSs can be implemented.
The different roles of providers and PSS can be set as follows: 

| Name | Role | Description | 
| --------- | --------- | --------- |
| A-Sat | Provider | Provisioning of services |
| B-Sat | Provider | Provisioning of services |
| Provider PSS | Provider, PSS | Provisioning of services and PSS |
| Broker PSS | Customer, PSS | Demonstration user, Broker platform PSS |

Table: User Story players. {#tbl:us_players}

## Step 1: Accreditation and service introduction

The broker PSS is importing data from both A-Sat and B-Sat.
This can be done via JSON import if the services from A-Sat and B-Sat are being presented in a PSI API compatible JSON scheme format.
If the optional demonstration step should be included, the services should include raw bandwidth services.
After import, the required accreditation will be conducted for both providers.

The provider PSS sends both *resource specifications* and *service specifications* via the API, showcasing the API connection between the broker PSS and the provider PSS.
Subsequently, the broker PSS displays the received data and conducts the accreditation for this provider as well.
Finally, the provider PSS sends *product offerings* to the broker PSS via the aforementioned APIs as well.
One product offering includes *internet access*, to enable step 2.

## Step 2: Mission creation and product order

The demonstration user logs into the broker PSS.
The user creates a mission specifying services that are matching the ones send by the provider PSS.
Subsequently, the user performs the matchmaking.
The broker PSS presents the user the product offering as introduced by the provider PSS in step 1.
The user selects this offer and sends the product order.

## Step 3: Order processing in provider PSS

The product order is transferred from the broker PSS, via the APIs, to the provider PSS.
The new order is displayed in the provider PSS and further processed within the provider PSS.
This might include showcasing how party management is handled within the provider PSS.
Once the order is checked, a confirmation is send back to the broker PSS.

## Optional: Showing additional matchmaking

Optionally another matchmaking action, using imported data in step 1 from A-Sat, can be shown on the example of raw bandwidth services.
Potentially, the same services as those from the provider PSS can be shown with B-Sat derived services.
The user refines the mission to include raw bandwidth services.
The matchmaking in the broker PSS is being conducted once more, showing the raw bandwidth services from A-Sat as options.
Potentially, if present, the services from B-Sat can be shown here as well, but it is demonstrated that those from A-Sat are chosen to show step 4.

## Step 4: Order confirmation and start of Service

The provider PSS has confirmed the order.
The broker PSS shows the order's confirmation status and also the service status.

## Step 5: Integration tests

The user story has been completed.
Within step 5, the capabilities for other endpoints are shown.
This is done conducting the integration tests with a mock-up, i.e. mocking requests from customers or mocking further actions being conducted by potential providers or PSS <-> PSS interactions.
