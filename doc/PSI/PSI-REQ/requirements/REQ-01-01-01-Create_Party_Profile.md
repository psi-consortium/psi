=begin

# REQ-01-01-01-Create_Party_Profile

Requirements for the endpoint described in TOD-01-01-01-Create_Party_Profile.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-01-01-01-01__ | The interface shall allow an individual to register as a party to a PSS.                               | -                       | P&S_002                        | PARTY    |
| __PSI-01-01-01-02__ | The interface shall allow an organization to register as a party to a PSS.                             | -                       | P&S_002                        | PARTY    |
| __PSI-01-01-01-03__ | The interface shall allow an organization to register its sub-organization(s) in hierarchical layers.  | Role inheritance by the sub-organizations and individuals is enabled by the API. The usage of such is however within the scope of the PSS implementation.                       | P&S_138, P&S_140                        | PARTY    |
| __PSI-01-01-01-04__ | The interface shall allow an organization to register its associated community of individuals (users). | -                       | P&S_138, P&S_140, P&S_194               | PARTY    |
| __PSI-01-01-01-05__ | The interface shall allow the governance to register a party to a PSS.                                 | -                       | P&S_002                        | PARTY    |
| __PSI-01-01-01-06__ | The interface shall allow the store contract relevant information about a party.                       | This could be accessible pools, SLA-Information of the PSS itself or other capabilities available to the party. | P&S_024 | PARTY |

Table: Requirements for creating a party profile. {#tbl:table-PSI-REQ-01-01-01}
