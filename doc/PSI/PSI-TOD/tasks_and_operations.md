=begin

# :book: wiki-Internal section, not compiled to documents

[[_TOC_]]

# Tasks and Operations

=end

This chapter describes the main tasks and operations that are covered as part of the Pooling and Sharing Interface Definition (PSID) project.
They are logically grouped in categories for a cleaner hierarchical structure.
Tasks and operations that belong to the same category, usually relate to a common higher level business process.

Consequently, the hierarchy consists of three levels, each using a two-digit numbering.

* Level 1 - Categories
* Level 2 - Tasks
* Level 3 - Operations

The security aspects to be taken into account when implementing these are described in the **Security Considerations** section of the [PSI-MADR] document.

## TOD-01-Miscellaneous

The Miscellaneous category contains tasks and operations that can't be grouped to a common business process.

### TOD-01-01-Party_Management

@include [TOD-01-01-Party_Management](tasks/TOD-01-01-Party_Management.md)

#### TOD-01-01-01-Create_Party_Profile

@include [TOD-01-01-01-Create_Party_Profile](operations/TOD-01-01-01-Create_Party_Profile.md)

#### TOD-01-01-02-Update_Party_Profile

@include [TOD-01-01-02-Update_Party_Profile](operations/TOD-01-01-02-Update_Party_Profile.md)

#### TOD-01-01-03-Remove_Party_Profile

@include [TOD-01-01-03-Remove_Party_Profile](operations/TOD-01-01-03-Remove_Party_Profile.md)

#### TOD-01-01-04-View_Party_Profile

@include [TOD-01-01-04-View_Party_Profile](operations/TOD-01-01-04-View_Party_Profile.md)

#### TOD-01-01-05-View_All_Party_Profiles

@include [TOD-01-01-05-View_All_Party_Profiles](operations/TOD-01-01-05-View_All_Party_Profiles.md)

### TOD-01-02-Event_Management

@include [TOD-01-02-Event_Management](tasks/TOD-01-02-Event_Management.md)

#### TOD-01-02-01-View_Event_Topics

@include [TOD-01-02-01-View_Event_Topics](operations/TOD-01-02-01-View_Event_Topics.md)

#### TOD-01-02-02-Register_Event_Callback

@include [TOD-01-02-02-Register_Event_Callback](operations/TOD-01-02-02-Register_Event_Callback.md)

#### TOD-01-02-03-Dispatch_Event

@include [TOD-01-02-03-Dispatch_Event](operations/TOD-01-02-03-Dispatch_Event.md)

#### TOD-01-02-04-Deregister_Event_Callback

@include [TOD-01-02-04-Deregister_Event_Callback](operations/TOD-01-02-04-Deregister_Event_Callback.md)

### TOD-01-03-Document_Management

@include [TOD-01-03-Document_Management](tasks/TOD-01-03-Document_Management.md)

#### TOD-01-03-01-Create_Document

@include [TOD-01-03-01-Create_Document](operations/TOD-01-03-01-Create_Document.md)

#### TOD-01-03-02-Update_Document

@include [TOD-01-03-02-Update_Document](operations/TOD-01-03-02-Update_Document.md)

#### TOD-01-03-03-Remove_Document

@include [TOD-01-03-03-Remove_Document](operations/TOD-01-03-03-Remove_Document.md)

#### TOD-01-03-04-View_Document

@include [TOD-01-03-04-View_Document](operations/TOD-01-03-04-View_Document.md)

#### TOD-01-03-05-View_All_Documents

@include [TOD-01-03-05-View_All_Documents](operations/TOD-01-03-05-View_All_Documents.md)

#### TOD-01-03-06-Create_Attachment

@include [TOD-01-03-06-Create_Attachment](operations/TOD-01-03-06-Create_Attachment.md)

#### TOD-01-03-07-Update_Attachment

@include [TOD-01-03-07-Update_Attachment](operations/TOD-01-03-07-Update_Attachment.md)

#### TOD-01-03-08-Remove_Attachment

@include [TOD-01-03-08-Remove_Attachment](operations/TOD-01-03-08-Remove_Attachment.md)

#### TOD-01-03-09-View_Attachment

@include [TOD-01-03-09-View_Attachment](operations/TOD-01-03-09-View_Attachment.md)

#### TOD-01-03-10-View_All_Attachments

@include [TOD-01-03-10-View_All_Attachments](operations/TOD-01-03-10-View_All_Attachments.md)

#### TOD-01-03-11-Fetch_Attachment_Content

@include [TOD-01-03-11-Fetch_Attachment_Content](operations/TOD-01-03-11-Fetch_Attachment_Content.md)

#### TOD-01-03-12-Update_Attachment_Content

@include [TOD-01-03-12-Update_Attachment_Content](operations/TOD-01-03-12-Update_Attachment_Content.md)

### TOD-01-04-Trouble_Ticket_Management

@include [TOD-01-04-Trouble_Ticket_Management](tasks/TOD-01-04-Trouble_Ticket_Management.md)

#### TOD-01-04-01-Create_Trouble_Ticket

@include [TOD-01-04-01-Create_Trouble_Ticket](operations/TOD-01-04-01-Create_Trouble_Ticket.md)

#### TOD-01-04-02-Update_Trouble_Ticket

@include [TOD-01-04-02-Update_Trouble_Ticket](operations/TOD-01-04-02-Update_Trouble_Ticket.md)

#### TOD-01-04-03-Remove_Trouble_Ticket

@include [TOD-01-04-03-Remove_Trouble_Ticket](operations/TOD-01-04-03-Remove_Trouble_Ticket.md)

#### TOD-01-04-04-View_Trouble_Ticket

@include [TOD-01-04-04-View_Trouble_Ticket](operations/TOD-01-04-04-View_Trouble_Ticket.md)

#### TOD-01-04-05-View_All_Trouble_Tickets

@include [TOD-01-04-05-View_All_Trouble_Tickets](operations/TOD-01-04-05-View_All_Trouble_Tickets.md)

## TOD-02-Product-Publishing

This category consists of tasks and operations related to publishing a product.
This involves the management of resources, services and products towards a final product offering with a price that could be ordered by a customer.

### TOD-02-01-Resource_Catalog_Management

@include [TOD-02-01-Resource_Catalog_Management](tasks/TOD-02-01-Resource_Catalog_Management.md)

#### TOD-02-01-01-Create_Resource_Specification

@include [TOD-02-01-01-Create_Resource_Specification](operations/TOD-02-01-01-Create_Resource_Specification.md)

#### TOD-02-01-02-Update_Resource_Specification

@include [TOD-02-01-02-Update_Resource_Specification](operations/TOD-02-01-02-Update_Resource_Specification.md)

#### TOD-02-01-03-Remove_Resource_Specification

@include [TOD-02-01-03-Remove_Resource_Specification](operations/TOD-02-01-03-Remove_Resource_Specification.md)

#### TOD-02-01-04-View_Resource_Specification

@include [TOD-02-01-04-View_Resource_Specification](operations/TOD-02-01-04-View_Resource_Specification.md)

#### TOD-02-01-05-View_All_Resource_Specifications

@include [TOD-02-01-05-View_All_Resource_Specifications](operations/TOD-02-01-05-View_All_Resource_Specifications.md)

### TOD-02-02-Service_Catalog_Management

@include [TOD-02-02-Service_Catalog_Management](tasks/TOD-02-02-Service_Catalog_Management.md)

#### TOD-02-02-01-Create_Service_Specification

@include [TOD-02-02-01-Create_Service_Specification](operations/TOD-02-02-01-Create_Service_Specification.md)

#### TOD-02-02-02-Update_Service_Specification

@include [TOD-02-02-02-Update_Service_Specification](operations/TOD-02-02-02-Update_Service_Specification.md)

#### TOD-02-02-03-Remove_Service_Specification

@include [TOD-02-02-03-Remove_Service_Specification](operations/TOD-02-02-03-Remove_Service_Specification.md)

#### TOD-02-02-04-View_Service_Specification

@include [TOD-02-02-04-View_Service_Specification](operations/TOD-02-02-04-View_Service_Specification.md)

#### TOD-02-02-05-View_All_Service_Specifications

@include [TOD-02-02-05-View_All_Service_Specifications](operations/TOD-02-02-05-View_All_Service_Specifications.md)

### TOD-02-03-Product_Catalog_Management

@include [TOD-02-03-Product_Catalog_Management](tasks/TOD-02-03-Product_Catalog_Management.md)

#### TOD-02-03-01-Create_Product_Specification

@include [TOD-02-03-01-Create_Product_Specification](operations/TOD-02-03-01-Create_Product_Specification.md)

#### TOD-02-03-02-Update_Product_Specification

@include [TOD-02-03-02-Update_Product_Specification](operations/TOD-02-03-02-Update_Product_Specification.md)

#### TOD-02-03-03-Remove_Product_Specification

@include [TOD-02-03-03-Remove_Product_Specification](operations/TOD-02-03-03-Remove_Product_Specification.md)

#### TOD-02-03-04-View_Product_Specification

@include [TOD-02-03-04-View_Product_Specification](operations/TOD-02-03-04-View_Product_Specification.md)

#### TOD-02-03-05-View_All_Product_Specifications

@include [TOD-02-03-05-View_All_Product_Specifications](operations/TOD-02-03-05-View_All_Product_Specifications.md)

### TOD-02-04-Product_Offering_Management

@include [TOD-02-04-Product_Offering_Management](tasks/TOD-02-04-Product_Offering_Management.md)

#### TOD-02-04-01-Create_Product_Offering

@include [TOD-02-04-01-Create_Product_Offering](operations/TOD-02-04-01-Create_Product_Offering.md)

#### TOD-02-04-02-Update_Product_Offering

@include [TOD-02-04-02-Update_Product_Offering](operations/TOD-02-04-02-Update_Product_Offering.md)

#### TOD-02-04-03-Remove_Product_Offering

@include [TOD-02-04-03-Remove_Product_Offering](operations/TOD-02-04-03-Remove_Product_Offering.md)

#### TOD-02-04-04-View_Product_Offering

@include [TOD-02-04-04-View_Product_Offering](operations/TOD-02-04-04-View_Product_Offering.md)

#### TOD-02-04-05-View_All_Product_Offerings

@include [TOD-02-04-05-View_All_Product_Offerings](operations/TOD-02-04-05-View_All_Product_Offerings.md)

## TOD-03-Product_Inquiry_And_Ordering

The category consists of tasks and operations related to customer's inquiries and product ordering.

### TOD-03-01-Customer_Inquiry_Management

@include [TOD-03-01-Customer_Inquiry_Management](tasks/TOD-03-01-Customer_Inquiry_Management.md)

#### TOD-03-01-01-Create_Customer_Inquiry

@include [TOD-03-01-01-Create_Customer_Inquiry](operations/TOD-03-01-01-Create_Customer_Inquiry.md)

#### TOD-03-01-02-View_Customer_Inquiry

@include [TOD-03-01-02-View_Customer_Inquiry](operations/TOD-03-01-02-View_Customer_Inquiry.md)

#### TOD-03-01-03-View_Inquiry_Results

@include [TOD-03-01-03-View_Inquiry_Results](operations/TOD-03-01-03-View_Inquiry_Results.md)

#### TOD-03-01-04-Update_Customer_Inquiry

@include [TOD-03-01-04-Update_Customer_Inquiry](operations/TOD-03-01-04-Update_Customer_Inquiry.md)

#### TOD-03-01-05-Cancel_Customer_Inquiry

@include [TOD-03-01-05-Cancel_Customer_Inquiry](operations/TOD-03-01-05-Cancel_Customer_Inquiry.md)

### TOD-03-02-Product_Order_Management

This task contains all operations to order products (and therefore services and resources).

@include [TOD-03-02-Product_Order_Management](tasks/TOD-03-02-Product_Order_Management.md)

#### TOD-03-02-01-Create_Product_Order

@include [TOD-03-02-01-Create_Product_Order](operations/TOD-03-02-01-Create_Product_Order.md)

#### TOD-03-02-02-Update_Product_Order

@include [TOD-03-02-02-Update_Product_Order](operations/TOD-03-02-02-Update_Product_Order.md)

#### TOD-03-02-03-View_Product_Order

@include [TOD-03-02-03-View_Product_Order](operations/TOD-03-02-03-View_Product_Order.md)

#### TOD-03-02-04-View_All_Product_Orders

@include [TOD-03-02-04-View_All_Product_Orders](operations/TOD-03-02-04-View_All_Product_Orders.md)

### TOD-03-03-Customer_Bill_Management

@include [TOD-03-03-Customer_Bill_Management](tasks/TOD-03-03-Customer_Bill_Management.md)

#### TOD-03-03-01-Create_Customer_Bill

@include [TOD-03-03-01-Create_Customer_Bill](operations/TOD-03-03-01-Create_Customer_Bill.md)

#### TOD-03-03-02-Update_Customer_Bill

@include [TOD-03-03-02-Update_Customer_Bill](operations/TOD-03-03-02-Update_Customer_Bill.md)

#### TOD-03-03-03-View_Customer_Bill

@include [TOD-03-03-03-View_Customer_Bill](operations/TOD-03-03-03-View_Customer_Bill.md)

#### TOD-03-03-04-View_All_Customer_Bills

@include [TOD-03-03-04-View_All_Customer_Bills](operations/TOD-03-03-04-View_All_Customer_Bills.md)

#### TOD-03-03-05-Withdraw_Customer_Bill

@include [TOD-03-03-05-Withdraw_Customer_Bill](operations/TOD-03-03-05-Withdraw_Customer_Bill.md)

## TOD-04-Template_Management

The category consists of tasks and operations related to managing templates for resources, services and products.
These are created out of the JSON Schemas described in [PSI-ICD] and allow a quick-start in defining specifications for [TOD-02-Product-Publishing](#tod-02-product-publishing).

### TOD-04-01-Resource_Template_Management

@include [TOD-04-01-Resource_Template_Management](tasks/TOD-04-01-Resource_Template_Management.md)

#### TOD-04-01-01-Create_Resource_Template

@include [TOD-04-01-01-Create_Resource_Template](operations/TOD-04-01-01-Create_Resource_Template.md)

#### TOD-04-01-02-Update_Resource_Template

@include [TOD-04-01-02-Update_Resource_Template](operations/TOD-04-01-02-Update_Resource_Template.md)

#### TOD-04-01-03-Remove_Resource_Template

@include [TOD-04-01-03-Remove_Resource_Template](operations/TOD-04-01-03-Remove_Resource_Template.md)

#### TOD-04-01-04-View_Resource_Template

@include [TOD-04-01-04-View_Resource_Template](operations/TOD-04-01-04-View_Resource_Template.md)

#### TOD-04-01-05-View_All_Resource_Templates

@include [TOD-04-01-05-View_All_Resource_Templates](operations/TOD-04-01-05-View_All_Resource_Templates.md)

### TOD-04-02-Service_Template_Management

@include [TOD-04-02-Service_Template_Management](tasks/TOD-04-02-Service_Template_Management.md)

#### TOD-04-02-01-Create_Service_Template

@include [TOD-04-02-01-Create_Service_Template](operations/TOD-04-02-01-Create_Service_Template.md)

#### TOD-04-02-02-Update_Service_Template

@include [TOD-04-02-02-Update_Service_Template](operations/TOD-04-02-02-Update_Service_Template.md)

#### TOD-04-02-03-Remove_Service_Template

@include [TOD-04-02-03-Remove_Service_Template](operations/TOD-04-02-03-Remove_Service_Template.md)

#### TOD-04-02-04-View_Service_Template

@include [TOD-04-02-04-View_Service_Template](operations/TOD-04-02-04-View_Service_Template.md)

#### TOD-04-02-05-View_All_Service_Templates

@include [TOD-04-02-05-View_All_Service_Templates](operations/TOD-04-02-05-View_All_Service_Templates.md)

### TOD-04-03-Product_Template_Management

@include [TOD-04-03-Product_Template_Management](tasks/TOD-04-03-Product_Template_Management.md)

#### TOD-04-03-01-Create_Product_Template

@include [TOD-04-03-01-Create_Product_Template](operations/TOD-04-03-01-Create_Product_Template.md)

#### TOD-04-03-02-Update_Product_Template

@include [TOD-04-03-02-Update_Product_Template](operations/TOD-04-03-02-Update_Product_Template.md)

#### TOD-04-03-03-Remove_Product_Template

@include [TOD-04-03-03-Remove_Product_Template](operations/TOD-04-03-03-Remove_Product_Template.md)

#### TOD-04-03-04-View_Product_Template

@include [TOD-04-03-04-View_Product_Template](operations/TOD-04-03-04-View_Product_Template.md)

#### TOD-04-03-05-View_All_Product_Templates

@include [TOD-04-03-05-View_All_Product_Templates](operations/TOD-04-03-05-View_All_Product_Templates.md)

## TOD-05-Inventory_Management

The category consists of tasks and operations related to managing the inventory of products, services and resources.
While services (and products containing them) are created in the order process, resources can be held in a warehouse and checked for availability via the stock management.

### TOD-05-01-Resource_Inventory_Management

@include [TOD-05-01-Resource_Inventory_Management](tasks/TOD-05-01-Resource_Inventory_Management.md)

#### TOD-05-01-01-Create_Resource

@include [TOD-05-01-01-Create_Resource](operations/TOD-05-01-01-Create_Resource.md)

#### TOD-05-01-02-Update_Resource

@include [TOD-05-01-02-Update_Resource](operations/TOD-05-01-02-Update_Resource.md)

#### TOD-05-01-03-Remove_Resource

@include [TOD-05-01-03-Remove_Resource](operations/TOD-05-01-03-Remove_Resource.md)

#### TOD-05-01-04-View_Resource

@include [TOD-05-01-04-View_Resource](operations/TOD-05-01-04-View_Resource.md)

#### TOD-05-01-05-View_All_Resources

@include [TOD-05-01-05-View_All_Resources](operations/TOD-05-01-05-View_All_Resources.md)

### TOD-05-02-Service_Inventory_Management

@include [TOD-05-02-Service_Inventory_Management](tasks/TOD-05-02-Service_Inventory_Management.md)

#### TOD-05-02-01-Create_Service

@include [TOD-05-02-01-Create_Service](operations/TOD-05-02-01-Create_Service.md)

#### TOD-05-02-02-Update_Service

@include [TOD-05-02-02-Update_Service](operations/TOD-05-02-02-Update_Service.md)

#### TOD-05-02-03-Remove_Service

@include [TOD-05-02-03-Remove_Service](operations/TOD-05-02-03-Remove_Service.md)

#### TOD-05-02-04-View_Service

@include [TOD-05-02-04-View_Service](operations/TOD-05-02-04-View_Service.md)

#### TOD-05-02-05-View_All_Services

@include [TOD-05-02-05-View_All_Services](operations/TOD-05-02-05-View_All_Services.md)

### TOD-05-03-Product_Inventory_Management

@include [TOD-05-03-Product_Inventory_Management](tasks/TOD-05-03-Product_Inventory_Management.md)

#### TOD-05-03-01-Create_Product

@include [TOD-05-03-01-Create_Product](operations/TOD-05-03-01-Create_Product.md)

#### TOD-05-03-02-Update_Product

@include [TOD-05-03-02-Update_Product](operations/TOD-05-03-02-Update_Product.md)

#### TOD-05-03-03-Remove_Product

@include [TOD-05-03-03-Remove_Product](operations/TOD-05-03-03-Remove_Product.md)

#### TOD-05-03-04-View_Product

@include [TOD-05-03-04-View_Product](operations/TOD-05-03-04-View_Product.md)

#### TOD-05-03-05-View_All_Products

@include [TOD-05-03-05-View_All_Products](operations/TOD-05-03-05-View_All_Products.md)

### TOD-05-04-Stock_Management

@include [TOD-05-04-Stock_Management](tasks/TOD-05-04-Stock_Management.md)

#### TOD-05-04-01-Check_Product_Stock

@include [TOD-05-04-01-Check_Product_Stock](operations/TOD-05-04-01-Check_Product_Stock.md)

## TOD-06-Quality_Management

The category consists of tasks and operations related to managing the service level objectives (SLO) and service level specifications (SLS), which in turn are used to define service level agreements (SLAs) and declare monitoring of services and resources on provider side.

### TOD-06-01-Service_Level_Objective_Management

@include [TOD-06-01-Service_Level_Objective_Management](tasks/TOD-06-01-Service_Level_Objective_Management.md)

#### TOD-06-01-01-Create_Service_Level_Objective

@include [TOD-06-01-01-Create_Service_Level_Objective](operations/TOD-06-01-01-Create_Service_Level_Objective.md)

#### TOD-06-01-02-Update_Service_Level_Objective

@include [TOD-06-01-02-Update_Service_Level_Objective](operations/TOD-06-01-02-Update_Service_Level_Objective.md)

#### TOD-06-01-03-Remove_Service_Level_Objective

@include [TOD-06-01-03-Remove_Service_Level_Objective](operations/TOD-06-01-03-Remove_Service_Level_Objective.md)

#### TOD-06-01-04-View_Service_Level_Objective

@include [TOD-06-01-04-View_Service_Level_Objective](operations/TOD-06-01-04-View_Service_Level_Objective.md)

#### TOD-06-01-05-View_All_Service_Level_Objective

@include [TOD-06-01-05-View_All_Service_Level_Objective](operations/TOD-06-01-05-View_All_Service_Level_Objective.md)

### TOD-06-02-Service_Level_Specification

@include [TOD-06-02-Service_Level_Specification](tasks/TOD-06-02-Service_Level_Specification.md)

#### TOD-06-02-01-Create_Service_Level_Specification

@include [TOD-06-02-01-Create_Service_Level_Specification](operations/TOD-06-02-01-Create_Service_Level_Specification.md)

#### TOD-06-02-02-Update_Service_Level_Specification

@include [TOD-06-02-02-Update_Service_Level_Specification](operations/TOD-06-02-02-Update_Service_Level_Specification.md)

#### TOD-06-02-03-Remove_Service_Level_Specification

@include [TOD-06-02-03-Remove_Service_Level_Specification](operations/TOD-06-02-03-Remove_Service_Level_Specification.md)

#### TOD-06-02-04-View_Service_Level_Specification

@include [TOD-06-02-04-View_Service_Level_Specification](operations/TOD-06-02-04-View_Service_Level_Specification.md)

#### TOD-06-02-05-View_All_Service_Level_Specification

@include [TOD-06-02-05-View_All_Service_Level_Specification](operations/TOD-06-02-05-View_All_Service_Level_Specification.md)
