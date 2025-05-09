=begin

# CGA

[[_TOC_]]

=end

## Distributed Matchmaking

### Mission Requests

| Actor | Consumed API(s) |
|-------|-------------|
| CGA | PSID001 Customer Inquiry |

Table: Parameters of all Distributed Matchmaking Views - CGA. {#tbl:parameters-distributed-matchmaking-views-cga}

![Mission Request](./img/Governance/CGA/MissionRequests.png){#fig:mission-request}

The CGA receives mission requests from the users, which are listed in the view above.
These missions must be reviewed and the CGA can accept or decline them.

![Mission Request - Details](./img/Governance/CGA/MissionRequests-Details.png){#fig:mission-request-details}

By clicking on a row, the details' panel opens underneath.
The CGA can review all data to which it has access and then decides either to decline the request or trigger the matchmaking process by clicking on the 'Calculate Options' button.
Some information might be restricted and are not shown to the CGA.
This is pre-filtered in the backend.

{#page:break}

### Offered Products

| Actor | Consumed API(s) |
|-------|-------------|
| CGA | PSID620 Product Catalog |
| CGA | PSID001 Customer Inquiry |

Table: Parameters of all Offered Products Views. {#tbl:parameters-offered-products-views}

![Offered Products](./img/Governance/CGA/OfferedProducts.png){#fig:offered-products}

After triggering the matchmaking, the results are shown in a list.
The checkbox on the left side in the row can be checked, which enables the 'Block Option' and 'Allow Option' buttons above.

![Offered Products - Details](./img/Governance/CGA/OfferedProducts-Details.png){#fig:offered-products-details}

Clicking a row opens the details' panel below.

![Offered Products - Filter Results 1](./img/Governance/CGA/OfferedProducts-Filter1.png){#fig:offered-products-filter1}

![Offered Products - Filter Results 2](./img/Governance/CGA/OfferedProducts-Filter2.png){#fig:offered-products-filter2}

The list can be filtered using the component on the left.
In this example, it can be filtered by provider and by tags, as can be seen in the two images above.
Then, the CGA can use the checkmarks in the rows to enable the buttons to block or allow product offers.
As soon as the CGA has marked at least one option as allowed, the list can be approved with the button underneath the list: 'Approve List'.
Finally, all allowed option are visible to the user after the CGA has completed this process.
