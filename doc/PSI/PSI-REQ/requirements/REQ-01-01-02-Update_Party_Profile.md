=begin

# REQ-01-01-02-Update_Party_Profile

Requirements for the endpoint described in TOD-01-01-02-Update_Party_Profile.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ |
| __PSI-01-01-02-01__ | The interface shall allow an individual to update the existing party in a PSS.                                                                         | -                                                                                                                        | PARTY      |
| __PSI-01-01-02-02__ | The interface shall allow an individual to update only their party in the PSS.                                                                         | -                                                                                                                        | PARTY, SEC |
| __PSI-01-01-02-03__ | The interface shall allow an organization to update the existing party in a PSS.                                                                       | -                                                                                                                        | PARTY, SEC |
| __PSI-01-01-02-04__ | The interface shall allow an organization to update only their party in the PSS.                                                                       | -                                                                                                                        | PARTY, SEC |
| __PSI-01-01-02-05__ | The interface shall allow an organization to specify its sub-organization(s) in hierarchical layers.                                                   | The interface shall allow an organization to register its sub-organization(s) in hierarchical layers.  | Role inheritance by the sub-organizations and individuals is enabled by the API. The usage of such is however within the scope of the PSS implementation.                                                                                                                        | PARTY      |
| __PSI-01-01-02-06__ | The interface shall allow an organization to specify its associated community of individuals (users).                                                  | -                                                                                                                        | PARTY      |
| __PSI-01-01-02-07__ | The interface shall allow the governance to update an existing party in a PSS.                                                                         | -                                                                                                                        | PARTY      |
| __PSI-01-01-02-08__ | The interface shall allow the governance to set the response time for orders and inquiries of an existing organization party (i.e. provider) in a PSS. | The governance uses the response time to enforce the respective provider organization to respond according to their SLA. | PARTY      |
| __PSI-01-01-02-09__ | The interface shall allow the governance to set the maximum priority for inquiries of an existing party (i.e. customer) in a PSS.                      | -                                                                                                                        | PARTY      |

Table: Requirements for updating a party profile. {#tbl:table-PSI-REQ-01-01-02}
