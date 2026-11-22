=begin

# REQ-03-05-03-View_Product_Offering_Qualification

Requirements for the endpoint described in TOD-03-05-03-View_Product_Offering_Qualification.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-03-05-03-01__ | The interface shall allow a customer to retrieve a qualification it created from a PSS. | - | - | QUALIFICATION |
| __PSI-03-05-03-02__ | The interface shall allow a PSS to retrieve a qualification it created from a provider or from another PSS. | - | - | QUALIFICATION |
| __PSI-03-05-03-03__ | The interface shall return a qualification in its current form, with the attributes set by the client and those set by the server. | - | - | QUALIFICATION |
| __PSI-03-05-03-04__ | The interface shall indicate the processing state of a qualification. | Property: *state*. | - | QUALIFICATION |
| __PSI-03-05-03-05__ | The interface shall indicate the result of each item and of the whole qualification once it is known. | Properties: *qualificationResult*, *qualificationItemResult*. | - | QUALIFICATION |
| __PSI-03-05-03-06__ | The interface shall indicate the reasons of a result and alternative offerings when the customer asked for them. | Properties: *eligibilityResultReason*, *alternateProductOfferingProposal*. | - | QUALIFICATION |
| __PSI-03-05-03-07__ | The interface shall indicate, for each item concerned, the reason why a qualification failed. | Property: *terminationError*. | - | QUALIFICATION |
| __PSI-03-05-03-08__ | The interface shall allow the server to state until when the result of a qualification is valid. | Property: *expirationDate*. | - | QUALIFICATION |
| __PSI-03-05-03-09__ | The interface shall allow a customer to control read access to the qualifications it has created in the PSS. | PSI-ICD, Authorization and Data Visibility. | - | QUALIFICATION, SEC |

Table: Requirements for viewing a product offering qualification. {#tbl:table-PSI-REQ-03-05-03}
