=begin

# REQ-01-02-03-Dispatch_Event

Requirements for the endpoint described in TOD-01-02-03-Dispatch_Event.

=end

| Req. ID                        | Description                         | Comment / Understanding                  | Related Candidate Requirements | Category                       |
| ------------------------------ | ----------------------------------- | ---------------------------------------- | ------------------------------ | ------------------------------ |
| __PSI-01-02-03-01__ | The interface shall allow receiving events from other systems.                                   | -                       | -                              | -        |
| __PSI-01-02-03-02__ | The interface shall allow exchange of entity data within events.                                 | -                       | -                              | -        |
| __PSI-01-02-03-03__ | The sender implementation shall filter receivers by event topics.                                | -                       | -                              | -        |
| __PSI-01-02-03-04__ | The sender implementation shall filter receivers on a need-to-know basis.                        | -                       | -                              | -        |
| __PSI-01-02-03-05__ | The sender implementation shall filter receivers based on their filter query.                    | -                       | -                              | -        |
| __PSI-01-02-03-06__ | The sender implementation must apply an exponential back-off strategy for rejected events.       | -                       | -                              | -        |
| __PSI-01-02-03-07__ | The sender implementation may halt event transmission to broken callbacks for manual resumption. | -                       | -                              | -        |
| __PSI-01-02-03-08__ | The sender implementation must always send events in the original timely order.                  | -                       | -                              | -        |
| __PSI-01-02-03-09__ | The sender implementation must authenticate with the target host before sending an event.        | -                       | -                              | -        |

Table: Requirements for dispatching events. {#tbl:table-PSI-REQ-01-02-03}
