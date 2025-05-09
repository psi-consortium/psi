/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.cgi.space.psi.pss.stub.api;

import com.cgi.space.psi.common.model.Error;
import com.cgi.space.psi.common.model.ProductOrder;
import com.cgi.space.psi.common.model.ProductOrderFVO;
import com.cgi.space.psi.common.model.ProductOrderMVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
@Validated
@Tag(name = "ProductOrder", description = "Operations for ProductOrder Resource")
public interface ProductOrderApi {

    /**
     * POST /productOrder : Creates a ProductOrder
     * This operation creates a ProductOrder entity.
     *
     * @param productOrderFVO The ProductOrder to be created (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return OK/Created (status code 201)
     *         or Accepted (status code 202)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Conflict (status code 409)
     *         or Internal Server Error (status code 500)
     *         or Not Implemented (status code 501)
     *         or Service Unavailable (status code 503)
     */
    @Operation(
        operationId = "createProductOrder",
        summary = "Creates a ProductOrder",
        tags = { "productOrder" },
        responses = {
            @ApiResponse(responseCode = "201", description = "OK/Created", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOrder.class))
            }),
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "405", description = "Method Not allowed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "501", description = "Not Implemented", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/productOrder",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<ProductOrder> createProductOrder(
        @Parameter(name = "ProductOrderFVO", description = "The ProductOrder to be created", required = true) @Valid @RequestBody ProductOrderFVO productOrderFVO,
        @Parameter(name = "fields", description = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) Optional<String> fields
    );


    /**
     * DELETE /productOrder/{id} : Deletes a ProductOrder
     * This operation deletes a ProductOrder entity.
     *
     * @param id Identifier of the Resource (required)
     * @return Accepted (status code 202)
     *         or Deleted (status code 204)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Conflict (status code 409)
     *         or Internal Server Error (status code 500)
     *         or Not Implemented (status code 501)
     *         or Service Unavailable (status code 503)
     */
    @Operation(
        operationId = "deleteProductOrder",
        summary = "Deletes a ProductOrder",
        tags = { "productOrder" },
        responses = {
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "405", description = "Method Not allowed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "501", description = "Not Implemented", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/productOrder/{id}",
        produces = "application/json"
    )
    ResponseEntity<Void> deleteProductOrder(
        @Parameter(name = "id", description = "Identifier of the Resource", required = true) @PathVariable("id") String id
    );


    /**
     * GET /productOrder : List or find ProductOrder objects
     * List or find ProductOrder objects
     *
     * @param fields Comma-separated properties to be provided in response (optional)
     * @param offset Requested index for start of resources to be provided in response (optional)
     * @param limit Requested number of resources to be provided in response (optional)
     * @return Success (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Internal Server Error (status code 500)
     *         or Not Implemented (status code 501)
     *         or Service Unavailable (status code 503)
     */
    @Operation(
        operationId = "listProductOrder",
        summary = "List or find ProductOrder objects",
        tags = { "productOrder" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOrder.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "405", description = "Method Not allowed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "501", description = "Not Implemented", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/productOrder",
        produces = "application/json"
    )
    ResponseEntity<List<ProductOrder>> listProductOrder(
        @Parameter(name = "fields", description = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) Optional<String> fields,
        @Parameter(name = "offset", description = "Requested index for start of resources to be provided in response") @Valid @RequestParam(value = "offset", required = false) Optional<Integer> offset,
        @Parameter(name = "limit", description = "Requested number of resources to be provided in response") @Valid @RequestParam(value = "limit", required = false) Optional<Integer> limit
    );


    /**
     * PATCH /productOrder/{id} : Updates partially a ProductOrder
     * This operation updates partially a ProductOrder entity.
     *
     * @param id Identifier of the Resource (required)
     * @param productOrderMVO The ProductOrder to be patched (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return Success (status code 200)
     *         or Accepted (status code 202)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Conflict (status code 409)
     *         or Internal Server Error (status code 500)
     *         or Not Implemented (status code 501)
     *         or Service Unavailable (status code 503)
     */
    @Operation(
        operationId = "patchProductOrder",
        summary = "Updates partially a ProductOrder",
        tags = { "productOrder" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOrder.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = ProductOrder.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = ProductOrder.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = ProductOrder.class))
            }),
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "405", description = "Method Not allowed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "409", description = "Conflict", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "501", description = "Not Implemented", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch+json", schema = @Schema(implementation = Error.class)),
                @Content(mediaType = "application/json-patch-query+json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/productOrder/{id}",
        produces = "application/json",
        consumes = "application/json"
    )
    ResponseEntity<ProductOrder> patchProductOrder(
        @Parameter(name = "id", description = "Identifier of the Resource", required = true) @PathVariable("id") String id,
        @Parameter(name = "ProductOrderMVO", description = "The ProductOrder to be patched", required = true) @Valid @RequestBody ProductOrderMVO productOrderMVO,
        @Parameter(name = "fields", description = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) Optional<String> fields
    );


    /**
     * GET /productOrder/{id} : Retrieves a ProductOrder by ID
     * This operation retrieves a ProductOrder entity. Attribute selection enabled for all first level attributes.
     *
     * @param id Identifier of the Resource (required)
     * @param fields Comma-separated properties to be provided in response (optional)
     * @return Success (status code 200)
     *         or Bad Request (status code 400)
     *         or Unauthorized (status code 401)
     *         or Forbidden (status code 403)
     *         or Not Found (status code 404)
     *         or Method Not allowed (status code 405)
     *         or Internal Server Error (status code 500)
     *         or Not Implemented (status code 501)
     *         or Service Unavailable (status code 503)
     */
    @Operation(
        operationId = "retrieveProductOrder",
        summary = "Retrieves a ProductOrder by ID",
        tags = { "productOrder" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductOrder.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "405", description = "Method Not allowed", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "501", description = "Not Implemented", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "503", description = "Service Unavailable", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/productOrder/{id}",
        produces = "application/json"
    )
    ResponseEntity<ProductOrder> retrieveProductOrder(
        @Parameter(name = "id", description = "Identifier of the Resource", required = true) @PathVariable("id") String id,
        @Parameter(name = "fields", description = "Comma-separated properties to be provided in response") @Valid @RequestParam(value = "fields", required = false) Optional<String> fields
    );

}
