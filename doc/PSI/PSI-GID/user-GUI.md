### Distributed Matchmaking

| Actor | Consumed API(s) |
|-------|-------------|
| User | PSID001 Customer Inquiry |

Table: Parameters of all Distributed Matchmaking Views - User. {#tbl:parameters-distributed-matchmaking-views-user}

![By User Defined Mission](img/User/Distributed-Matchmaking/MissionSummary.png){#fig:mission-summary}

![By User Defined Mission - Service Details](img/User/Distributed-Matchmaking/MissionSummary-Details.png){#fig:mission-summary-service-details}

The basis for the wireframes is the UCSM study.
For the user journey, the wireframes for PSID start just after the process of defining a mission.
This image shows an example of how a user could have defined a mission to specify the communication needs.
This includes the defined zones displayed on the map, start and end date and service grades.
Below 'Services', all user-specified services are listed.
By clicking on a row, a details' panel will show further details about the service.

In this case, the matchmaking process will be performed by a third party (CGA), which could also act in a governmental role.
When the user clicks the button 'Request Service Option for Mission', the request will be sent to a third party, which will check the request and if the check passes, will trigger the matchmaking.

![By User Defined Mission - Dialog](img/User/Distributed-Matchmaking/MissionSummary-Modal.png){#fig:mission-summary-modal}

The dialogue informs the user, that the request successfully has been sent. Subsequently, the user will be notified upon completion of the third-party process.

### Offered Products

| Actor | Consumed API(s) |
|-------|-------------|
| User | PSID001 Customer Inquiry |

Table: Parameters of all Offered Products Views. {#tbl:parameters-offered-products-views}

![Offered Products: User's Service Options](img/User/Offered-Products.png){#fig:service-options}

Once the third party described above has validated the mission specified by the user and triggered the matchmaking, the results will be sent to the user.
What is shown here, is the list of found service options.

![Offered Products: User's Service Options Details - Immediate](img/User/OfferedProducts_Place-Order.png){#fig:service-option-details-immediate}

By clicking on the row, the detail panel will open below the row.
In the image above, the offering is immediately bookable.
If the user decides to book it, they can click on the button in the lower right corner _Place Order_.

![Offered Products: User's Options Details - On-Demand](img/User/OfferedProducts_RFQ.png){#fig:service-options-details-on-demand}

The image above shows the details of an on-demand service option.
The user can trigger an RFQ to get an offering from the provider.

![Offered Products: RFQ Modal](img/User/OfferedProducts_RFQ-Modal.png){#fig:service-options-rfq}

The Request for Quote must be confirmed by clicking the submit button, and sending the request to the provider.

### Shopping Cart

| Actor | Consumed API(s) |
|-------|-------------|
| User | PSID663 Shopping Cart |

Table: Parameters of the Shopping Cart View. {#tbl:shopping-cart-view}

![Shopping Cart](img/User/ShoppingCart.png){#fig:shopping-cart}

The shopping cart stores all order items, ready to be ordered.
For example, when the user selects a service option, which is immediately bookable, the order item will be stored in the shopping cart.
They can store several order items here and checkout all at once.

### Documents & Interactions

#### Outgoing Customer Inquiries

| Actor | Consumed API(s) |
|-------|-------------|
| User | PSID001 Customer Inquiry |

Table: Parameters of all RFQ Views. {#tbl:rfq-views}

![Documents & Interactions: Request for Quote](img/User/DocumentsInteractions_RFQ.png){#fig:user-interactions-rfq}

The 'Documents & Interactions' area provides all views concerning RFQs and orders.
This can be navigated by the side navigation on the left.
The image above shows the requested quotes.
In the 'Total Costs' column, the initial value will be 'status: pending', because the provider did not answer the request, yet.
As soon as the provider makes an offering, it will show the offered price and the button 'Re-calculate Service Options' will be enabled.
This gives the opportunity to trigger the matchmaking process again, with the same parameters as the first calculation.

![Documents & Interactions: Request for Quote - Details](img/User/DocumentsInteractions_RFQ-Details.png){#fig:user-interactions-rfq-details}

By clicking on the row, the 'details' panel of the service option is shown below.

![Documents & Interactions: Results after Re-calculation](img/User/DocumentsInteractions_SavedResults2.png){#fig:user-interactions-recalculation}

The image above shows the list of service options including the requested service option.

#### Outgoing Product Orders

| Actor | Consumed API(s) |
|-------|-------------|
| User | PSID622 Product Ordering |

Table: Parameters of all Ordered Products Views. {#tbl:parameters-ordered-products-views}

![Documents & Interactions: Active Orders](img/User/DocumentsInteractions_Orders.png){#fig:user-interactions-active-orders}

The view for orders shows a component, where the user can easily switch between active and past orders.
The image above shows the active orders.
The image below shows the past orders.

![Documents & Interactions: Past Orders](img/User/DocumentsInteractions_OrdersArchive.png){#fig:user-interactions-past-orders}
