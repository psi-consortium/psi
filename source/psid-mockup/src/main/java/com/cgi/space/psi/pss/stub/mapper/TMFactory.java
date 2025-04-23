package com.cgi.space.psi.pss.stub.mapper;

import com.cgi.space.psi.common.model.BusinessPartner;
import com.cgi.space.psi.common.model.BusinessPartnerFVO;
import com.cgi.space.psi.common.model.BusinessPartnerMVO;
import com.cgi.space.psi.common.model.Consumer;
import com.cgi.space.psi.common.model.ConsumerFVO;
import com.cgi.space.psi.common.model.ConsumerMVO;
import com.cgi.space.psi.common.model.Document;
import com.cgi.space.psi.common.model.DocumentFVO;
import com.cgi.space.psi.common.model.DocumentMVO;
import com.cgi.space.psi.common.model.DocumentRef;
import com.cgi.space.psi.common.model.DocumentRefFVO;
import com.cgi.space.psi.common.model.DocumentRefMVO;
import com.cgi.space.psi.common.model.DocumentRefOrValue;
import com.cgi.space.psi.common.model.DocumentRefOrValueFVO;
import com.cgi.space.psi.common.model.DocumentRefOrValueMVO;
import com.cgi.space.psi.common.model.GeographicAddress;
import com.cgi.space.psi.common.model.GeographicAddressFVO;
import com.cgi.space.psi.common.model.GeographicAddressMVO;
import com.cgi.space.psi.common.model.GeographicLocation;
import com.cgi.space.psi.common.model.GeographicLocationFVO;
import com.cgi.space.psi.common.model.GeographicLocationMVO;
import com.cgi.space.psi.common.model.GeographicLocationRef;
import com.cgi.space.psi.common.model.GeographicLocationRefFVO;
import com.cgi.space.psi.common.model.GeographicLocationRefMVO;
import com.cgi.space.psi.common.model.GeographicLocationRefOrValue;
import com.cgi.space.psi.common.model.GeographicLocationRefOrValueFVO;
import com.cgi.space.psi.common.model.GeographicLocationRefOrValueMVO;
import com.cgi.space.psi.common.model.GeographicSite;
import com.cgi.space.psi.common.model.GeographicSiteFVO;
import com.cgi.space.psi.common.model.GeographicSiteMVO;
import com.cgi.space.psi.common.model.Geometry;
import com.cgi.space.psi.common.model.Individual;
import com.cgi.space.psi.common.model.IndividualFVO;
import com.cgi.space.psi.common.model.IndividualMVO;
import com.cgi.space.psi.common.model.Intent;
import com.cgi.space.psi.common.model.IntentFVO;
import com.cgi.space.psi.common.model.IntentMVO;
import com.cgi.space.psi.common.model.IntentRef;
import com.cgi.space.psi.common.model.IntentRefFVO;
import com.cgi.space.psi.common.model.IntentRefMVO;
import com.cgi.space.psi.common.model.IntentRefOrValue;
import com.cgi.space.psi.common.model.IntentRefOrValueFVO;
import com.cgi.space.psi.common.model.IntentRefOrValueMVO;
import com.cgi.space.psi.common.model.LineString;
import com.cgi.space.psi.common.model.LogicalResource;
import com.cgi.space.psi.common.model.LogicalResourceFVO;
import com.cgi.space.psi.common.model.LogicalResourceMVO;
import com.cgi.space.psi.common.model.MultiLineString;
import com.cgi.space.psi.common.model.MultiPoint;
import com.cgi.space.psi.common.model.MultiPolygon;
import com.cgi.space.psi.common.model.Organization;
import com.cgi.space.psi.common.model.OrganizationFVO;
import com.cgi.space.psi.common.model.OrganizationMVO;
import com.cgi.space.psi.common.model.PartyOrPartyRole;
import com.cgi.space.psi.common.model.PartyOrPartyRoleFVO;
import com.cgi.space.psi.common.model.PartyOrPartyRoleMVO;
import com.cgi.space.psi.common.model.PartyRef;
import com.cgi.space.psi.common.model.PartyRefFVO;
import com.cgi.space.psi.common.model.PartyRefMVO;
import com.cgi.space.psi.common.model.PartyRole;
import com.cgi.space.psi.common.model.PartyRoleFVO;
import com.cgi.space.psi.common.model.PartyRoleMVO;
import com.cgi.space.psi.common.model.PartyRoleRef;
import com.cgi.space.psi.common.model.PartyRoleRefFVO;
import com.cgi.space.psi.common.model.PartyRoleRefMVO;
import com.cgi.space.psi.common.model.PhysicalResource;
import com.cgi.space.psi.common.model.PhysicalResourceFVO;
import com.cgi.space.psi.common.model.PhysicalResourceMVO;
import com.cgi.space.psi.common.model.PlaceRef;
import com.cgi.space.psi.common.model.PlaceRefFVO;
import com.cgi.space.psi.common.model.PlaceRefMVO;
import com.cgi.space.psi.common.model.PlaceRefOrValue;
import com.cgi.space.psi.common.model.PlaceRefOrValueFVO;
import com.cgi.space.psi.common.model.PlaceRefOrValueMVO;
import com.cgi.space.psi.common.model.Point;
import com.cgi.space.psi.common.model.Polygon;
import com.cgi.space.psi.common.model.Producer;
import com.cgi.space.psi.common.model.ProducerFVO;
import com.cgi.space.psi.common.model.ProducerMVO;
import com.cgi.space.psi.common.model.Product;
import com.cgi.space.psi.common.model.ProductFVO;
import com.cgi.space.psi.common.model.ProductMVO;
import com.cgi.space.psi.common.model.ProductRef;
import com.cgi.space.psi.common.model.ProductRefFVO;
import com.cgi.space.psi.common.model.ProductRefMVO;
import com.cgi.space.psi.common.model.ProductRefOrValue;
import com.cgi.space.psi.common.model.ProductRefOrValueFVO;
import com.cgi.space.psi.common.model.ProductRefOrValueMVO;
import com.cgi.space.psi.common.model.Resource;
import com.cgi.space.psi.common.model.ResourceCollection;
import com.cgi.space.psi.common.model.ResourceCollectionFVO;
import com.cgi.space.psi.common.model.ResourceCollectionMVO;
import com.cgi.space.psi.common.model.ResourceFVO;
import com.cgi.space.psi.common.model.ResourceMVO;
import com.cgi.space.psi.common.model.ResourceRef;
import com.cgi.space.psi.common.model.ResourceRefFVO;
import com.cgi.space.psi.common.model.ResourceRefMVO;
import com.cgi.space.psi.common.model.ResourceRefOrValue;
import com.cgi.space.psi.common.model.ResourceRefOrValueFVO;
import com.cgi.space.psi.common.model.ResourceRefOrValueMVO;
import com.cgi.space.psi.common.model.Service;
import com.cgi.space.psi.common.model.ServiceFVO;
import com.cgi.space.psi.common.model.ServiceMVO;
import com.cgi.space.psi.common.model.ServiceRef;
import com.cgi.space.psi.common.model.ServiceRefFVO;
import com.cgi.space.psi.common.model.ServiceRefMVO;
import com.cgi.space.psi.common.model.ServiceRefOrValue;
import com.cgi.space.psi.common.model.ServiceRefOrValueFVO;
import com.cgi.space.psi.common.model.ServiceRefOrValueMVO;
import com.cgi.space.psi.common.model.Supplier;
import com.cgi.space.psi.common.model.SupplierFVO;
import com.cgi.space.psi.common.model.SupplierMVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Component
@Generated("com.cgi.space.psi.tasks.GenerateMapStructFactoryTask")
public class TMFactory {

    public DocumentRefOrValue createDocumentRefOrValue(DocumentRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof Document) {
            return new Document();
        }
        else if (archetype instanceof DocumentRef) {
            return new DocumentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public DocumentRefOrValue createDocumentRefOrValue(DocumentRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof DocumentFVO) {
            return new Document();
        }
        else if (archetype instanceof DocumentRefFVO) {
            return new DocumentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public DocumentRefOrValue createDocumentRefOrValue(DocumentRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof DocumentMVO) {
            return new Document();
        }
        else if (archetype instanceof DocumentRefMVO) {
            return new DocumentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public DocumentRefOrValueFVO createDocumentRefOrValueFVO(DocumentRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof DocumentFVO) {
            return new DocumentFVO();
        }
        else if (archetype instanceof DocumentRefFVO) {
            return new DocumentRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public DocumentRefOrValueMVO createDocumentRefOrValueMVO(DocumentRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof DocumentMVO) {
            return new DocumentMVO();
        }
        else if (archetype instanceof DocumentRefMVO) {
            return new DocumentRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public GeographicLocationRefOrValue createGeographicLocationRefOrValue(GeographicLocationRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicLocation) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicLocationRef) {
            return new GeographicLocationRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public GeographicLocationRefOrValue createGeographicLocationRefOrValue(GeographicLocationRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicLocationFVO) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicLocationRefFVO) {
            return new GeographicLocationRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public GeographicLocationRefOrValue createGeographicLocationRefOrValue(GeographicLocationRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicLocationMVO) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicLocationRefMVO) {
            return new GeographicLocationRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public GeographicLocationRefOrValueFVO createGeographicLocationRefOrValueFVO(GeographicLocationRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicLocationFVO) {
            return new GeographicLocationFVO();
        }
        else if (archetype instanceof GeographicLocationRefFVO) {
            return new GeographicLocationRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public GeographicLocationRefOrValueMVO createGeographicLocationRefOrValueMVO(GeographicLocationRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicLocationMVO) {
            return new GeographicLocationMVO();
        }
        else if (archetype instanceof GeographicLocationRefMVO) {
            return new GeographicLocationRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public Geometry createGeometry(Geometry archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LineString) {
            return new LineString();
        }
        else if (archetype instanceof MultiLineString) {
            return new MultiLineString();
        }
        else if (archetype instanceof MultiPoint) {
            return new MultiPoint();
        }
        else if (archetype instanceof MultiPolygon) {
            return new MultiPolygon();
        }
        else if (archetype instanceof Point) {
            return new Point();
        }
        else if (archetype instanceof Polygon) {
            return new Polygon();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public IntentRefOrValue createIntentRefOrValue(IntentRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof Intent) {
            return new Intent();
        }
        else if (archetype instanceof IntentRef) {
            return new IntentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public IntentRefOrValue createIntentRefOrValue(IntentRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof IntentFVO) {
            return new Intent();
        }
        else if (archetype instanceof IntentRefFVO) {
            return new IntentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public IntentRefOrValue createIntentRefOrValue(IntentRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof IntentMVO) {
            return new Intent();
        }
        else if (archetype instanceof IntentRefMVO) {
            return new IntentRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public IntentRefOrValueFVO createIntentRefOrValueFVO(IntentRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof IntentFVO) {
            return new IntentFVO();
        }
        else if (archetype instanceof IntentRefFVO) {
            return new IntentRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public IntentRefOrValueMVO createIntentRefOrValueMVO(IntentRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof IntentMVO) {
            return new IntentMVO();
        }
        else if (archetype instanceof IntentRefMVO) {
            return new IntentRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PartyOrPartyRole createPartyOrPartyRole(PartyOrPartyRole archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof BusinessPartner) {
            return new BusinessPartner();
        }
        else if (archetype instanceof Consumer) {
            return new Consumer();
        }
        else if (archetype instanceof Individual) {
            return new Individual();
        }
        else if (archetype instanceof Organization) {
            return new Organization();
        }
        else if (archetype instanceof PartyRef) {
            return new PartyRef();
        }
        else if (archetype instanceof PartyRole) {
            return new PartyRole();
        }
        else if (archetype instanceof PartyRoleRef) {
            return new PartyRoleRef();
        }
        else if (archetype instanceof Producer) {
            return new Producer();
        }
        else if (archetype instanceof Supplier) {
            return new Supplier();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PartyOrPartyRole createPartyOrPartyRole(PartyOrPartyRoleFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof BusinessPartnerFVO) {
            return new BusinessPartner();
        }
        else if (archetype instanceof ConsumerFVO) {
            return new Consumer();
        }
        else if (archetype instanceof IndividualFVO) {
            return new Individual();
        }
        else if (archetype instanceof OrganizationFVO) {
            return new Organization();
        }
        else if (archetype instanceof PartyRefFVO) {
            return new PartyRef();
        }
        else if (archetype instanceof PartyRoleFVO) {
            return new PartyRole();
        }
        else if (archetype instanceof PartyRoleRefFVO) {
            return new PartyRoleRef();
        }
        else if (archetype instanceof ProducerFVO) {
            return new Producer();
        }
        else if (archetype instanceof SupplierFVO) {
            return new Supplier();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PartyOrPartyRole createPartyOrPartyRole(PartyOrPartyRoleMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof BusinessPartnerMVO) {
            return new BusinessPartner();
        }
        else if (archetype instanceof ConsumerMVO) {
            return new Consumer();
        }
        else if (archetype instanceof IndividualMVO) {
            return new Individual();
        }
        else if (archetype instanceof OrganizationMVO) {
            return new Organization();
        }
        else if (archetype instanceof PartyRefMVO) {
            return new PartyRef();
        }
        else if (archetype instanceof PartyRoleMVO) {
            return new PartyRole();
        }
        else if (archetype instanceof PartyRoleRefMVO) {
            return new PartyRoleRef();
        }
        else if (archetype instanceof ProducerMVO) {
            return new Producer();
        }
        else if (archetype instanceof SupplierMVO) {
            return new Supplier();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PartyOrPartyRoleFVO createPartyOrPartyRoleFVO(PartyOrPartyRoleFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof BusinessPartnerFVO) {
            return new BusinessPartnerFVO();
        }
        else if (archetype instanceof ConsumerFVO) {
            return new ConsumerFVO();
        }
        else if (archetype instanceof IndividualFVO) {
            return new IndividualFVO();
        }
        else if (archetype instanceof OrganizationFVO) {
            return new OrganizationFVO();
        }
        else if (archetype instanceof PartyRefFVO) {
            return new PartyRefFVO();
        }
        else if (archetype instanceof PartyRoleFVO) {
            return new PartyRoleFVO();
        }
        else if (archetype instanceof PartyRoleRefFVO) {
            return new PartyRoleRefFVO();
        }
        else if (archetype instanceof ProducerFVO) {
            return new ProducerFVO();
        }
        else if (archetype instanceof SupplierFVO) {
            return new SupplierFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PartyOrPartyRoleMVO createPartyOrPartyRoleMVO(PartyOrPartyRoleMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof BusinessPartnerMVO) {
            return new BusinessPartnerMVO();
        }
        else if (archetype instanceof ConsumerMVO) {
            return new ConsumerMVO();
        }
        else if (archetype instanceof IndividualMVO) {
            return new IndividualMVO();
        }
        else if (archetype instanceof OrganizationMVO) {
            return new OrganizationMVO();
        }
        else if (archetype instanceof PartyRefMVO) {
            return new PartyRefMVO();
        }
        else if (archetype instanceof PartyRoleMVO) {
            return new PartyRoleMVO();
        }
        else if (archetype instanceof PartyRoleRefMVO) {
            return new PartyRoleRefMVO();
        }
        else if (archetype instanceof ProducerMVO) {
            return new ProducerMVO();
        }
        else if (archetype instanceof SupplierMVO) {
            return new SupplierMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PlaceRefOrValue createPlaceRefOrValue(PlaceRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicAddress) {
            return new GeographicAddress();
        }
        else if (archetype instanceof GeographicLocation) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicSite) {
            return new GeographicSite();
        }
        else if (archetype instanceof PlaceRef) {
            return new PlaceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PlaceRefOrValue createPlaceRefOrValue(PlaceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicAddressFVO) {
            return new GeographicAddress();
        }
        else if (archetype instanceof GeographicLocationFVO) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicSiteFVO) {
            return new GeographicSite();
        }
        else if (archetype instanceof PlaceRefFVO) {
            return new PlaceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PlaceRefOrValue createPlaceRefOrValue(PlaceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicAddressMVO) {
            return new GeographicAddress();
        }
        else if (archetype instanceof GeographicLocationMVO) {
            return new GeographicLocation();
        }
        else if (archetype instanceof GeographicSiteMVO) {
            return new GeographicSite();
        }
        else if (archetype instanceof PlaceRefMVO) {
            return new PlaceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PlaceRefOrValueFVO createPlaceRefOrValueFVO(PlaceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicAddressFVO) {
            return new GeographicAddressFVO();
        }
        else if (archetype instanceof GeographicLocationFVO) {
            return new GeographicLocationFVO();
        }
        else if (archetype instanceof GeographicSiteFVO) {
            return new GeographicSiteFVO();
        }
        else if (archetype instanceof PlaceRefFVO) {
            return new PlaceRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public PlaceRefOrValueMVO createPlaceRefOrValueMVO(PlaceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof GeographicAddressMVO) {
            return new GeographicAddressMVO();
        }
        else if (archetype instanceof GeographicLocationMVO) {
            return new GeographicLocationMVO();
        }
        else if (archetype instanceof GeographicSiteMVO) {
            return new GeographicSiteMVO();
        }
        else if (archetype instanceof PlaceRefMVO) {
            return new PlaceRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ProductRefOrValue createProductRefOrValue(ProductRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof Product) {
            return new Product();
        }
        else if (archetype instanceof ProductRef) {
            return new ProductRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ProductRefOrValue createProductRefOrValue(ProductRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ProductFVO) {
            return new Product();
        }
        else if (archetype instanceof ProductRefFVO) {
            return new ProductRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ProductRefOrValue createProductRefOrValue(ProductRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ProductMVO) {
            return new Product();
        }
        else if (archetype instanceof ProductRefMVO) {
            return new ProductRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ProductRefOrValueFVO createProductRefOrValueFVO(ProductRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ProductFVO) {
            return new ProductFVO();
        }
        else if (archetype instanceof ProductRefFVO) {
            return new ProductRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ProductRefOrValueMVO createProductRefOrValueMVO(ProductRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ProductMVO) {
            return new ProductMVO();
        }
        else if (archetype instanceof ProductRefMVO) {
            return new ProductRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ResourceRefOrValue createResourceRefOrValue(ResourceRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LogicalResource) {
            return new LogicalResource();
        }
        else if (archetype instanceof PhysicalResource) {
            return new PhysicalResource();
        }
        else if (archetype instanceof Resource) {
            return new Resource();
        }
        else if (archetype instanceof ResourceCollection) {
            return new ResourceCollection();
        }
        else if (archetype instanceof ResourceRef) {
            return new ResourceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ResourceRefOrValue createResourceRefOrValue(ResourceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LogicalResourceFVO) {
            return new LogicalResource();
        }
        else if (archetype instanceof PhysicalResourceFVO) {
            return new PhysicalResource();
        }
        else if (archetype instanceof ResourceFVO) {
            return new Resource();
        }
        else if (archetype instanceof ResourceCollectionFVO) {
            return new ResourceCollection();
        }
        else if (archetype instanceof ResourceRefFVO) {
            return new ResourceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ResourceRefOrValue createResourceRefOrValue(ResourceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LogicalResourceMVO) {
            return new LogicalResource();
        }
        else if (archetype instanceof PhysicalResourceMVO) {
            return new PhysicalResource();
        }
        else if (archetype instanceof ResourceMVO) {
            return new Resource();
        }
        else if (archetype instanceof ResourceCollectionMVO) {
            return new ResourceCollection();
        }
        else if (archetype instanceof ResourceRefMVO) {
            return new ResourceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ResourceRefOrValueFVO createResourceRefOrValueFVO(ResourceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LogicalResourceFVO) {
            return new LogicalResourceFVO();
        }
        else if (archetype instanceof PhysicalResourceFVO) {
            return new PhysicalResourceFVO();
        }
        else if (archetype instanceof ResourceCollectionFVO) {
            return new ResourceCollectionFVO();
        }
        else if (archetype instanceof ResourceFVO) {
            return new ResourceFVO();
        }
        else if (archetype instanceof ResourceRefFVO) {
            return new ResourceRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ResourceRefOrValueMVO createResourceRefOrValueMVO(ResourceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof LogicalResourceMVO) {
            return new LogicalResourceMVO();
        }
        else if (archetype instanceof PhysicalResourceMVO) {
            return new PhysicalResourceMVO();
        }
        else if (archetype instanceof ResourceCollectionMVO) {
            return new ResourceCollectionMVO();
        }
        else if (archetype instanceof ResourceMVO) {
            return new ResourceMVO();
        }
        else if (archetype instanceof ResourceRefMVO) {
            return new ResourceRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ServiceRefOrValue createServiceRefOrValue(ServiceRefOrValue archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof Service) {
            return new Service();
        }
        else if (archetype instanceof ServiceRef) {
            return new ServiceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ServiceRefOrValue createServiceRefOrValue(ServiceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ServiceFVO) {
            return new Service();
        }
        else if (archetype instanceof ServiceRefFVO) {
            return new ServiceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ServiceRefOrValue createServiceRefOrValue(ServiceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ServiceMVO) {
            return new Service();
        }
        else if (archetype instanceof ServiceRefMVO) {
            return new ServiceRef();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ServiceRefOrValueFVO createServiceRefOrValueFVO(ServiceRefOrValueFVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ServiceFVO) {
            return new ServiceFVO();
        }
        else if (archetype instanceof ServiceRefFVO) {
            return new ServiceRefFVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

    public ServiceRefOrValueMVO createServiceRefOrValueMVO(ServiceRefOrValueMVO archetype) {
        if (archetype == null) {
            return null;
        }
        else if (archetype instanceof ServiceMVO) {
            return new ServiceMVO();
        }
        else if (archetype instanceof ServiceRefMVO) {
            return new ServiceRefMVO();
        }
        else {
            throw new AssertionError("Illegal type " + archetype.getClass());
        }
    }

}
