=begin

# TOD-03-06-04-View_All_Quotes

> The heading has to be included in the document including this document.

=end

**Prerequisites**

Quotes exist in the PSS datastore.

**Main operation**

Gets all quotes of the customer from the PSS via a standard interface specification, in pages (*offset*, *limit*), so that many concurrent quote requests can be monitored.
The PSS can likewise view all of its quotes on the provider's system.
With the parameter *fields*, the client can select the attributes of the quotes to be returned.

**REST Endpoints**

@include [TOD-03-06-04 View All Quotes Endpoints](endpoints/TOD-03-06-04-View_All_Quotes-endpoints.md)

**Post Conditions**

All quotes which the client can read are successfully returned to be viewed (`200`).
If no quote is found, an empty list is returned with `200`.

**Applicable Requirements**

@include [TOD-03-06-04 View All Quotes Requirements](requirements/TOD-03-06-04-View_All_Quotes-requirements.md)

**eTOM Reference**

The operation is based on 1.1.9.2 and 1.1.9.5 process identifiers from the eTOM.
