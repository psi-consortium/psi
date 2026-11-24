=begin

# REQ-03-05-04-View_All_Product_Offering_Qualifications

Requirements for the endpoint described in TOD-03-05-04-View_All_Product_Offering_Qualifications.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-03-05-04-01__ | The interface shall allow a customer to retrieve all qualifications they created in a PSS, page by page. | Parameters: *offset*, *limit*. | - | QUALIFICATION |
| __PSI-03-05-04-02__ | The interface may allow the list of qualifications to be filtered by their attributes. | - | - | QUALIFICATION |
| __PSI-03-05-04-03__ | The interface shall allow a customer to control read access to the qualifications they have created in the PSS. | PSI-ICD, Authorization and Data Visibility. | - | QUALIFICATION, SEC |

Table: Requirements for viewing all product offering qualifications. {#tbl:table-PSI-REQ-03-05-04}
