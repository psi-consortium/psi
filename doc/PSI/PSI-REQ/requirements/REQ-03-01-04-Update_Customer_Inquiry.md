=begin

# REQ-03-01-04-Update_Customer_Inquiry

Requirements for the endpoint described in TOD-03-01-04-Update_Customer_Inquiry.

=end

| Req. ID | Description | Comment / Understanding | Category |
| ------- | ----------- | ----------------------- | -------- |
| __PSI-03-01-04-01__ | The interface shall allow a customer to update the list of inquired providers of an existing customer inquiry in a PSS. | - | INQUIRY |
| __PSI-03-01-04-02__ | The implementation shall publish the inquiries to all new participating providers using the Event API. | - | POOLING |
| __PSI-03-01-04-03__ | The implementation shall publish cancellations of inquiries for the case that previously participating providers are removed from the inquiry using the Event API. | - | POOLING |
| __PSI-03-01-04-04__ | The implementation should filter the list of participating providers sent to them. | While the list in the PSS contains all providers, the message sent to provider A does only contain their entry, not the one of provider B and C. This has to be merged correctly when updates are received. | POOLING |
| __PSI-03-01-04-05__ | The interface shall reject updates of already completed or cancelled customer inquiries in a PSS. | - | INQUIRY, SEC |

Table: Requirements for updating a customer inquiry. {#tbl:table-PSI-REQ-03-01-04}
