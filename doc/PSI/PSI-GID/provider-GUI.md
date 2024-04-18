To interact with the system, the provider needs a graphical interface.
The GUI will provide a dashboard to get an initial overview and get a quick-start for some action.
Further views are catalog, inventory and customer interactions.
The character of these views will be described below.

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | - |

Table: Parameters of Dashboard View. {#tbl:parameters-dashboard-view}

![Provider Dashboard](img/Provider/Dashboard.png){#fig:provider-dashboard}

The dashboard is the entry-point for all possible views and actions.
It can scale alongside the application and might potentially also show metrics and statistics about certain topics.
For now, it foresees quick actions, for example to see incoming RFQs directly.

### Catalogs

#### Resource Catalog

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID634 Resource Catalog |

Table: Parameters of all Catalog Resources Views. {#tbl:parameters-catalog-resources-views}

![Catalog: Resources](img/Provider/Catalog/Catalog_Resources.png){#fig:catalog-resources}

The catalog view provides the catalogs for resources, services, products and offerings.
The different catalogs can be accessed by the side-navigation on the left.
The buttons below provide a file upload for JSON files and a direct switch to the inventory view.
The figure above shows the catalog example for resources.

![Catalog: Resources Details](img/Provider/Catalog/Catalog_Resources_Details.png){#fig:catalog-resource-details}

When clicking on the row, the panel for details will open below the row and show the resource specifications, as shown in the figure above.
The details can be closed by clicking on the row, again.

![Catalog: File Upload](img/Provider/Catalog/Catalog_Resources_Upload.png){#fig:catalog-file-upload}

Though the catalog and the inventory data are provided by a database, which is maintained by the provider, it is foreseen to provide the upload
for JSON-files via the GUI.
The image above shows the confirmation dialogue.
This process is the same for all categories within catalog and inventory and their consumed APIs.

#### Service Catalog

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID633 Service Catalog |

Table: Parameters of all Catalog Services Views. {#tbl:parameters-catalog-services-views}

![Catalog: Services](img/Provider/Catalog/Catalog_Services.png){#fig:catalog-services}

This view shows the list of all services, that are part of the catalog.

![Catalog: Services Details](img/Provider/Catalog/Catalog_Services_Details.png){#fig:catalog-service-details}

Similar to the resource details, the details of a specific service can be opened or closed by clicking on the row, as shown in the image above.

#### Product Catalog

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID620 Product Catalog |

Table: Parameters of all Catalog Products and Offerings Views. {#tbl:parameters-catalog-products-views}

![Catalog: Products](img/Provider/Catalog/Catalog_Products.png){#fig:catalog-products}

This view shows the list of all products, that are part of the catalog.

![Catalog: Products Details](img/Provider/Catalog/Catalog_Products_Details.png){#fig:catalog-product-details}

Similar to the resource and services details, the details of a specific product can be opened or closed by clicking on the row, as shown in the image above.

![Catalog: Offerings](img/Provider/Catalog/Catalog_Offer.png){#fig:catalog-offerings}

This view shows the list of all offerings, that are part of the catalog.

![Catalog: Offerings Details](img/Provider/Catalog/Catalog_Offer_Details.png){#fig:catalog-offering-details}

Also, the details of a specific offering can be opened or closed by clicking on the row, as shown in the image above.

### Inventories

#### Resource Inventory

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID639 Resource Inventory |

Table: Parameters of all Inventory Products and Offerings Views. {#tbl:parameters-inventory-resources-views}

![Inventory: Resources](img/Provider/Inventory/Inventory_Resources.png){#fig:inventory-resources}

The inventory view provides the inventory for resources, services, products and offerings.
The different categories can be accessed by the side-navigation on the left.
The buttons below provide a file upload for JSON files and a direct switch to the catalog view.
The figure above shows the inventory example for resources.

![Inventory: Resources Details](img/Provider/Inventory/Inventory_Resources_Details.png){#fig:inventory-resource-details}

When clicking on the row, the panel for details will open below the row and shows the list of resources, that are owned by the provider, as shown in the figure above.
The details can be closed by clicking on the row, again.

#### Service Inventory

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID638 Service Inventory |

Table: Parameters of all Inventory Services Views. {#tbl:parameters-inventory-services-views}

![Inventory: Services](img/Provider/Inventory/Inventory_Services.png){#fig:inventory-services}

This view shows the list of all services, that are part of the inventory.
According to the inventory, services are only part of it, when they are part of a product which is booked by a customer.

![Inventory: Service Details](img/Provider/Inventory/Inventory_Services_Details.png){#fig:inventory-service-details}

Similar to the resource details, the details of a specific service can be opened or closed by clicking on the row, as shown in the image above.
It shows a list of organisations, which are using the specific service on their mission/s.

#### Product Inventory

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID637 Product Inventory |

Table: Parameters of all Inventory Products and Offerings Views. {#tbl:parameters-inventory-product-views}

![Inventory: Products](img/Provider/Inventory/Inventory_Products.png){#fig:inventory-products}

This view shows the list of all products, that are part of the inventory.
Like services, products are only part of the inventory, when it is booked by a customer.

![Inventory: Product Details](img/Provider/Inventory/Inventory_Products_Details.png){#fig:inventory-product-details}

The opened detail panel shows the amount of booked products, the duration and the booking organisation.

### Customer Interactions

#### Incoming Customer Inquiries

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID001 Customer Inquiry |

Table: Parameters of all RFQ Views. {#tbl:parameters-provider-rfq-views}

![Customer Interactions: Pending Requests](img/Provider/CustomerInteractions/CustomerInteractions_RFQ.png){#fig:interactions-pending}

The customer interactions view provides the pending requests, the orders and the archive.
There is currently no focus on the archive, and therefore no layout for it.
This might change in the future.
The different categories can be accessed by the side-navigation on the left.
The figure above shows the example for pending requests.
This is indicated by the 'Status' column, where all entries are marked as 'Request'.

![Customer Interactions: Pending Requests Details](img/Provider/CustomerInteractions/CustomerInteractions_RFQ_Details.png){#fig:interactions-pending-details}

When clicking on the row, the panel for details will open below the row and show the details of the request, shown in the figure above.
There are two buttons to react on the request.
In case no offering can be made, for example because one or more resources are not available, the 'Decline Request' button can be clicked.
The customer should get a notification about the denial.
In case the provider wants to make an offering, they click the 'Offering Price' button. This will lead to a dialog, which will be described in the next figure.
The details can be closed by clicking on the row, again.

![Customer Interactions: Pending Request - Create Offering](img/Provider/CustomerInteractions/CustomerInteractions_RFQ_Details_Create-Offer.png){#fig:interactions-pending-create}

To create an offering, a price can be entered and a period - like monthly - can be selected.
After submitting, the offering is sent to the customer.

#### Incoming Product Orders

| Actor | Consumed API(s) |
|-------|-------------|
| Provider | PSID622 Product Ordering |

Table: Parameters of all Customer Interaction Views. {#tbl:parameters-customer-interaction-views}

![Customer Interactions: Orders](img/Provider/CustomerInteractions/CustomerInteractions_Orders.png){#fig:interactions-orders}

This view shows the list of all orders, that were placed.

![Customer Interactions: Orders Details](img/Provider/CustomerInteractions/CustomerInteractions_Orders_Details.png){#fig:interactions-order-details}

Again, the details are displayed below the row.
They list everything about the order to give a good overview.
