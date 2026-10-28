package com.cgi.space.psi.pss.stub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.space.psi.common.api.PsiOperation;

/**
 * Maps the endpoints of PSID679 to their TOD operations. This mock-up does not implement them: every endpoint
 * answers 501 Not Implemented.
 */
@RestController
@RequestMapping("/psi-api/productOfferingQualification/v2")
public class CheckProductOfferingQualificationController {

    @PsiOperation("TOD-03-05-01")
    @RequestMapping(method = RequestMethod.POST, value = "/checkProductOfferingQualification")
    public ResponseEntity<Void> createCheckProductOfferingQualification() {
        return notImplemented();
    }

    @PsiOperation("TOD-03-05-02")
    @RequestMapping(method = RequestMethod.PATCH, value = "/checkProductOfferingQualification/{id}")
    public ResponseEntity<Void> patchCheckProductOfferingQualification() {
        return notImplemented();
    }

    @PsiOperation("TOD-03-05-03")
    @RequestMapping(method = RequestMethod.DELETE, value = "/checkProductOfferingQualification/{id}")
    public ResponseEntity<Void> deleteCheckProductOfferingQualification() {
        return notImplemented();
    }

    @PsiOperation("TOD-03-05-04")
    @RequestMapping(method = RequestMethod.GET, value = "/checkProductOfferingQualification/{id}")
    public ResponseEntity<Void> retrieveCheckProductOfferingQualification() {
        return notImplemented();
    }

    private static ResponseEntity<Void> notImplemented() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
