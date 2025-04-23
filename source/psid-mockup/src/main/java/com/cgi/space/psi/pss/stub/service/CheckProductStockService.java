package com.cgi.space.psi.pss.stub.service;

import com.cgi.space.psi.common.model.TaskStateType;
import com.cgi.space.psi.common.model.AlternateProductStock;
import com.cgi.space.psi.common.model.AvailabilityResultType;
import com.cgi.space.psi.common.model.CheckProductStock;
import com.cgi.space.psi.common.model.CheckProductStockCreate;
import com.cgi.space.psi.common.model.CheckProductStockItem;
import com.cgi.space.psi.common.model.CustomerBill;
import com.cgi.space.psi.common.model.PlaceRef;
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductSpecificationRef;
import com.cgi.space.psi.common.model.ProductStockRef;
import com.cgi.space.psi.common.model.Quantity;
import com.cgi.space.psi.common.model.RelatedPlaceRefOrValue;
import com.cgi.space.psi.common.request.AitfTestAwareRunnable;
import com.cgi.space.psi.pss.stub.controller.CheckProductStockController;
import com.cgi.space.psi.pss.stub.mapper.CheckProductStockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Service class for managing CRUD operations for {@link CustomerBill CustomerBills}.
 */
@Slf4j
@Service
public class CheckProductStockService {

    @Autowired
    private CheckProductStockMapper checkProductStockMapper;
    @Autowired
    private MongoTemplate template;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    /**
     * The method creates a new {@link CheckProductStock}.
     *
     * @param checkProductStockCreate The {@link CheckProductStockCreate} object based on which
     *                                the {@link CheckProductStock} is created.
     * @return The newly created {@link CheckProductStock}.
     */
    public CheckProductStock createCheckProductStock(CheckProductStockCreate checkProductStockCreate) {
        OffsetDateTime now = OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        CheckProductStock checkProductStock = checkProductStockMapper.toCheckProductStock(checkProductStockCreate);
        checkProductStock.setId(UUID.randomUUID().toString());

        if (checkProductStock.getInstantSyncCheck()) {
            checkProductStock.setState(TaskStateType.DONE);
            checkProductStock.setCompletedCheckProductStockDate(now);
            checkProductStock.getCheckProductStockItem()
                    .forEach(item -> {
                        item.setState(TaskStateType.DONE);
                        item.setAvailabilityResult(AvailabilityResultType.NOTAVAILABLE);
                    });
        }
        else {
            checkProductStock.setState(TaskStateType.ACCEPTED);
            checkProductStock.getCheckProductStockItem()
                    .forEach(item -> item.setState(TaskStateType.ACCEPTED));

            executor.schedule(new AitfTestAwareRunnable(() -> startAvailabilityCheck(checkProductStock.getId())),
                    2, TimeUnit.SECONDS);

            executor.schedule(new AitfTestAwareRunnable(() -> finishAvailabilityCheck(checkProductStock.getId())),
                    4, TimeUnit.SECONDS);
        }
        checkProductStock.setCreationDate(now);

        Link self = linkTo(methodOn(CheckProductStockController.class)
                .retrieveCheckProductStock(checkProductStock.getId(), Optional.empty())).withSelfRel();
        checkProductStock.setHref(self.toUri());

        return template.save(checkProductStock);
    }

    private void startAvailabilityCheck(String id) {
        final CheckProductStock checkProductStock = template.findById(id, CheckProductStock.class);
        if (checkProductStock == null) {
            log.error("CheckProductStock {} not found - availability check cannot be started.", id);
        }
        else {
            checkProductStock.setState(TaskStateType.INPROGRESS);
            template.save(checkProductStock);
            log.info("Set status of CheckProductStock {} to {}", id, TaskStateType.INPROGRESS);
        }
    }

    private void finishAvailabilityCheck(String id) {
        CheckProductStock checkProductStock = template.findById(id, CheckProductStock.class);
        if (checkProductStock == null) {
            log.error("CheckProductStock {} not found - process to finish the availability check is aborted.", id);
        }
        else {
            boolean alternativesWanted = checkProductStock.getProvideAlternative();
            checkProductStock.getCheckProductStockItem()
                    .forEach(item -> {
                        if (item.getProvideAlternative() || alternativesWanted) {
                            addAlternative(item);
                        }
                        else {
                            addStock(item);
                        }
                    });

            checkProductStock.setState(TaskStateType.DONE);
            checkProductStock.setCompletedCheckProductStockDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS));

            template.save(checkProductStock);
            log.info("Set status of CheckProductStock {} to {}", id, TaskStateType.DONE);
        }
    }

    private void addAlternative(CheckProductStockItem item) {
        item.setState(TaskStateType.DONE);
        item.setAvailabilityResult(AvailabilityResultType.ALTERNATE);
        item.setAlternate(List.of(
                new AlternateProductStock()
                        .id("1")
                        .alternateAvailabilityDate(OffsetDateTime.now().truncatedTo(ChronoUnit.MILLIS))
                        .alternateProduct(new Product()
                                .place(List.of(new RelatedPlaceRefOrValue().place(new PlaceRef().id("mock-related-place-ref-1"))))
                                .productSpecification(new ProductSpecificationRef().id("2")))
                        .alternateQuantity(new Quantity().amount(10f).units("pce"))
                        .alternateStock(new ProductStockRef().id("mock-product-stock-ref-2"))));
    }

    private void addStock(CheckProductStockItem item) {
        item.setState(TaskStateType.DONE);
        item.setAvailabilityResult(AvailabilityResultType.AVAILABLE);

        var productStock = item.getCheckedProductStock();
        productStock.setName("ProductStock mock name");
        productStock.setPlace(new RelatedPlaceRefOrValue().place(new PlaceRef().id("42")));
        productStock.setProductStockLevel(new Quantity().amount(10f).units("pce"));
    }

    /**
     * The method retrieves an existing {@link CheckProductStock}.
     *
     * @param id The Identifier of the {@link CheckProductStock} to be retrieved.
     * @return The retrieved {@link CheckProductStock}.
     */
    public CheckProductStock getCheckProductStock(String id) {
        return template.findById(id, CheckProductStock.class);
    }
}
