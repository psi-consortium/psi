<!--
    ATTENTION: This file was generated via gradle!
               Do NOT manually edit this file! Any such changes will be overwritten!
-->

# Error422Code

One of the following error codes:
- missingProperty: The property that was expected is not present in the
  payload
- invalidValue: The property has an incorrect value
- invalidFormat: The property value does not comply with the expected 
  value format
- referenceNotFound: The object referenced by the property cannot be 
  identified in the target system
- unexpectedProperty: Additional, not expected property has been 
  provided
- tooLargeDataset: The requested entity will produce too much data
- tooManyRecords: The number of records to be provided in the response
  exceeds the  threshold
- tooManyRequests: The number of simultaneous requests from one API 
  client exceeds the threshold
- otherIssue: Other problem was identified (detailed information
  provided in a reason).

| Field | Type | Format | Required |
| ------- | ------- | ------- | --- |
| Error422Code | string | oneOf[missingProperty, invalidValue, invalidFormat, referenceNotFound, unexpectedProperty, tooLargeDataset, tooManyRecords, tooManyRequests, otherIssue] | No |

Table: Fields of Error422Code. {#tbl:Error422Code.md:Error422Code}

{#page:break}
