=begin

# REQ-03-01-03-View_Inquiry_Results

Requirements for the endpoint described in TOD-03-01-03-View_Inquiry_Results.

=end

| Req. ID | Description | Comment / Understanding | Category |
| ------- | ----------- | ----------------------- | -------- |
| __PSI-03-01-03-01__ | The interface shall allow a customer to view the product offerings matching their inquiry. | - | INQUIRY |
| __PSI-03-01-03-02__ | The interface shall allow a customer to view the product specifications matching their inquiry. | - | INQUIRY |
| __PSI-03-01-03-03__ | The interface shall allow a customer to view the service specifications matching their inquiry. | - | INQUIRY |
| __PSI-03-01-03-04__ | The interface shall allow a customer to view the resource specifications matching their inquiry. | - | INQUIRY |
| __PSI-03-01-03-05__ | If no results are available, the interface shall indicate the reason using specified HTTP status codes. | The reason could be that the inquiry was not yet processed (204, no content) or that no matches were found (200 with empty array) | INQUIRY |
| __PSI-03-01-03-06__ | The interface may block up to 20 seconds if results are calculated by an algorithm. | Used instead of returning the 204 code to avoid busy waiting. | INQUIRY |
| __PSI-03-01-03-07__ | The interface shall return relevant security and availability information for each inquiry results. | - | INQUIRY |
| __PSI-03-01-03-08__ | The interface shall allow the provider to add notes to inquiry results. | - | INQUIRY |

Table: Requirements for viewing inquiry results. {#tbl:table-REQ-03-01-03}
