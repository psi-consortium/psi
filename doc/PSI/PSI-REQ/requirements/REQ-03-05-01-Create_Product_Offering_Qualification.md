=begin

# REQ-03-05-01-Create_Product_Offering_Qualification

Requirements for the endpoint described in TOD-03-05-01-Create_Product_Offering_Qualification.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-03-05-01-01__ | The interface shall allow a customer to create a qualification of one or more product offerings in a PSS. | One item per offering or bundled offering. | - | QUALIFICATION |
| __PSI-03-05-01-02__ | The interface shall allow a PSS to create a qualification at the provider of an offering or at another PSS that offers it. | The PSS passes a bundle on component by component, each to the provider of that component. | - | QUALIFICATION |
| __PSI-03-05-01-03__ | The interface shall allow the necessary configuration of the requested product to be specified. | The configuration is checked against the product schema of the specification and against the values that the offering allows. | - | QUALIFICATION |
| __PSI-03-05-01-04__ | The interface shall allow the quantity of each item to be specified. | Property: *quantity*. | - | QUALIFICATION |
| __PSI-03-05-01-05__ | The interface shall allow the qualification of a new product, a change of an active product and the termination of an active product. | Property: *action* (`add`, `modify`, `delete`, `noChange`). | - | QUALIFICATION |
| __PSI-03-05-01-06__ | The interface shall allow a bundle to be qualified as one item with nested items for its components. | Property: *CheckProductOfferingQualificationItem*. | - | QUALIFICATION |
| __PSI-03-05-01-07__ | The interface shall allow the requested activation date of each item to be specified. | Property: *expectedActivationDate*. | - | QUALIFICATION |
| __PSI-03-05-01-08__ | The interface shall allow a customer to ask for alternatives. | Properties: *provideAlternative*, *provideResultReason*, *provideOnlyAvailable*. | - | QUALIFICATION |
| __PSI-03-05-01-09__ | The implementation shall answer a request that it does not accept with the appropriate state and the reason for each item concerned. | At once, in the response, or later, e.g. when a provider refuses an item. TOD-03-05 lists all response codes. | - | QUALIFICATION |
| __PSI-03-05-01-10__ | The interface shall allow a PSS to record the identifier that a qualification has at another party. | Property: *externalId*. | - | QUALIFICATION |
| __PSI-03-05-01-11__ | The interface shall allow the client to follow the state changes of a qualification. | By retrieving the qualification (TOD-03-05-03) or through event notifications on the topic `qualification` (TOD-01-02). | - | QUALIFICATION |

Table: Requirements for creating a product offering qualification. {#tbl:table-PSI-REQ-03-05-01}
