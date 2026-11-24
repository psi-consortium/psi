=begin

# REQ-03-05-02-Update_Product_Offering_Qualification

Requirements for the endpoint described in TOD-03-05-02-Update_Product_Offering_Qualification.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-03-05-02-01__ | The interface shall allow a customer to change the attributes they set in a qualification, including its items. | - | - | QUALIFICATION |
| __PSI-03-05-02-02__ | The interface shall allow a customer to cancel a qualification. | Property: *state* (`cancelled`). | - | QUALIFICATION |
| __PSI-03-05-02-03__ | The implementation shall decide which changes it accepts in which state of a qualification, and reject any other change with an error. | TOD-03-05 lists all response codes. | - | QUALIFICATION |
| __PSI-03-05-02-04__ | The interface shall allow a PSS to pass a change or a cancellation on to the provider or to the other PSS that holds its qualification. | - | - | QUALIFICATION |
| __PSI-03-05-02-05__ | The interface shall allow a customer to control write access to the qualifications they have created in the PSS. | PSI-ICD, Authorization and Data Visibility. | - | QUALIFICATION, SEC |

Table: Requirements for updating a product offering qualification. {#tbl:table-PSI-REQ-03-05-02}
