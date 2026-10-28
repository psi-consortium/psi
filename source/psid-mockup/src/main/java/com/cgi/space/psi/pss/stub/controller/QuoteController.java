package com.cgi.space.psi.pss.stub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;

/**
 * Maps the endpoints of PSID648 to their TOD operations. This mock-up does not implement them: every endpoint
 * answers 501 Not Implemented.
 */
@RestController
@RequestMapping("/psi-api/quoteManagement/v2")
public class QuoteController {

    @PsiOperation("TOD-03-06-01")
    @RequestMapping(method = RequestMethod.POST, value = "/quote")
    public ResponseEntity<Void> createQuote() {
        return notImplemented();
    }

    @PsiOperation("TOD-03-06-02")
    @RequestMapping(method = RequestMethod.PATCH, value = "/quote/{id}")
    public ResponseEntity<Void> patchQuote() {
        return notImplemented();
    }

    @PsiOperation("TOD-03-06-03")
    @RequestMapping(method = RequestMethod.GET, value = "/quote/{id}")
    public ResponseEntity<Void> retrieveQuote() {
        return notImplemented();
    }

    private static ResponseEntity<Void> notImplemented() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
