=begin

# REQ-03-03-03-View_Customer_Bill

Requirements for the endpoint described in TOD-03-03-03-View_Customer_Bill.

=end

| Req. ID | Description | Comment / Understanding | Category |
| ------- | ----------- | ----------------------- | -------- |
| __PSI-03-03-03-01__ | The interface shall allow a user or a provider to retrieve a customer bill from a PSS. | - | ORDER |
| __PSI-03-03-03-02__ | The interface shall allow a provider to control read access to a customer bill that is created by them in the PSS. | - | ORDER, SEC |
| __PSI-03-03-03-03__ | The interface should return a bill document as attachment(s). | - | ORDER |
| __PSI-03-03-03-04__ | The interface should return the state of a customer bill (e.g. 'sent', 'settled', 'partiallyPaid', etc.). | - | ORDER |
| __PSI-03-03-03-05__ | The interface may return the payment method of a customer bill. | - | ORDER |
| __PSI-03-03-03-06__ | The interface may return the billing account of a customer bill. | - | ORDER |
| __PSI-03-03-03-07__ | The interface may return a list of applied payments of a customer bill. | - | ORDER |

Table: Requirements for viewing a customer bill. {#tbl:table-PSI-REQ-03-03-03}
