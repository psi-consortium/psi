=begin

# REQ-02-01-01-Create_Resource_Specification

Requirements for the endpoint described in TOD-02-01-01-Create_Resource_Specification

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-02-01-01-01__ | The interface shall allow a party to register a resource specification to a PSS.                                               | The provider is enabled to upload services and resources, covering their specifications, to a PSS. As the same endpoint is provided to user parties, declaration of user owned assets to the PSS is enabled, too. | P&S_005, P&S_050, P&S_051, P&S_054, P&S_057, P&S_059, P&S_191 | POOLING  |
| __PSI-02-01-01-02__ | The interface shall ensure that the resource specifications are organised into a resource catalog according to resource types. | -                                                                                                                                                                                                                 | -                                                             | POOLING  |
| __PSI-02-01-01-03__ | The interface shall allow the specification of footprints in GeoJSON format.                                                   | -                                                                                                                                                                                                                 | P&S_061, P&S_084                                              | POOLING  |
| __PSI-02-01-01-04__ | The implementation shall reject the specification if it does not match the schema.                                             | -                                                                                                                                                                                                                 | P&S_056                                                       | POOLING  |

Table: Requirements for creating a resource specification. {#tbl:table-PSI-REQ-02-01-01}
