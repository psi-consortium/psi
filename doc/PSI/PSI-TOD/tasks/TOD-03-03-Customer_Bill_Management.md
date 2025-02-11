=begin

# TOD-03-03-Customer_Bill_Management

> The heading has to be included in the document including this document.

=end

The Customer Bill Management task takes care of bills (invoices) produced for a customer for placed orders in the PSS.
A customer bill or invoice is a document produced at the end of a regular back office process at the provider side which runs according to a bill cycle definition.
The customer bill contains information about the total amount due to be paid by a customer for the ordered product(s) during the billing period, the due date for the payment, and other information like the order and attachment references.

A *provider* wants to utilise the PSS to publish the bill and make it available to the customer.
Additionally, a provider might need to change the state of the bill in the PSS, for example when a customer has paid it.

The *customer* wants to utilise the PSS to find and retrieve one or several customer bills produced for them.
Also, the provider can use the PSS to find and retrieve the bills that have been created by them to the PSS.

```plantuml
@startuml
skinparam actor {
  BackgroundColor<< 0 >> 	SteelBlue
  BorderColor<< 0 >> 		SteelBlue
  BackgroundColor<< 1 >> 	Green
  BorderColor<< 1 >> 		Green
  BackgroundColor<< 3 >> 	Red
  BorderColor<< 3 >> 		Red
}

skinparam entity{
  BackgroundColor<< 2 >> 	Orange
  BorderColor<< 2 >> 		Orange
}

usecase "Customer Bill Management"          as Task
rectangle "Create Customer Bill"            as O1
rectangle "Update Customer Bill"            as O2
rectangle "Withdraw Customer Bill"          as O3
rectangle "View Customer Bill"              as O4
rectangle "View All Customer Bills"         as O5

Task  ..> O1    : <<includes>>
Task  ..> O2    : <<includes>>
Task  ..> O3    : <<includes>>
Task  ..> O4    : <<includes>>
Task  ..> O5    : <<includes>>
@enduml

```

![TOD-03-03: Customer Bill Management](../../common/pixel.png){#fig:TOD-03-03-Customer_Bill_Management}

|                             |  Customer  |  Provider  |  Other PSS   | Governance |
|-----------------------------|:----------:|:----------:|:------------:|:----------:|
| **Create Customer Bill**    |            | \checkmark | (\checkmark) |            |
| **Update Customer Bill**    |            | \checkmark | (\checkmark) |            |
| **Withdraw Customer Bill**  |            | \checkmark | (\checkmark) |            |
| **View Customer Bill**      | \checkmark | \checkmark |              |            |
| **View All Customer Bills** | \checkmark | \checkmark |              |            |

Table: Customer Bill Management Matrix. {#tbl:customer-bill-management-matrix}

**eTOM Reference**

The task is based on the 1.3.9 process identifier from the eTOM.

