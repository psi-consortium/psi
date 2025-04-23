package com.cgi.space.psi.pss.stub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.SubclassMapping;
import org.springframework.stereotype.Component;

import com.cgi.space.psi.common.model.PartyRef;
import com.cgi.space.psi.common.model.PartyRefFVO;
import com.cgi.space.psi.common.model.PartyRefMVO;
import com.cgi.space.psi.common.model.PartyRefOrPartyRoleRef;
import com.cgi.space.psi.common.model.PartyRefOrPartyRoleRefFVO;
import com.cgi.space.psi.common.model.PartyRefOrPartyRoleRefMVO;
import com.cgi.space.psi.common.model.PartyRoleRef;
import com.cgi.space.psi.common.model.PartyRoleRefFVO;
import com.cgi.space.psi.common.model.PartyRoleRefMVO;

/**
 * Mapper interface for {@link PartyRefOrPartyRoleRef PartyRefOrPartyRoleRefs}.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = PartyRefMapper.ExceptionFactory.class)
public interface PartyRefMapper {

    /**
     * The method maps a {@link PartyRefOrPartyRoleRefFVO} instance to a
     * {@link PartyRefOrPartyRoleRef} object.
     *
     * @param partyRefOrPartyRoleRefFVO The {@link partyRefOrPartyRoleRefFVO} object to be mapped.
     * @return The mapped {@link PartyRefOrPartyRoleRef}.
     */
    @SubclassMapping(source = PartyRefFVO.class, target = PartyRef.class)
    @SubclassMapping(source = PartyRoleRefFVO.class, target = PartyRoleRef.class)
    PartyRefOrPartyRoleRef toPartyRefOrPartyRoleRef(PartyRefOrPartyRoleRefFVO partyRefOrPartyRoleRefFVO);

    /**
     * The method maps a {@link PartyRefOrPartyRoleRefMVO} instance to a
     * {@link PartyRefOrPartyRoleRef} object.
     *
     * @param partyRefOrPartyRoleRefMVO The {@link PartyRefOrPartyRoleRefMVO} object to be mapped.
     * @return The mapped {@link PartyRefOrPartyRoleRef}.
     */
    @SubclassMapping(source = PartyRefMVO.class, target = PartyRef.class)
    @SubclassMapping(source = PartyRoleRefMVO.class, target = PartyRoleRef.class)
    PartyRefOrPartyRoleRef toPartyRefOrPartyRoleRef(PartyRefOrPartyRoleRefMVO partyRefOrPartyRoleRefMVO);

    @Component
    public static class ExceptionFactory {

        public PartyRefOrPartyRoleRef createPartyRefOrPartyRoleRef() {
            throw new AssertionError("Illegal sub-type of PartyRefOrPartyRoleRef");
        }

    }
}
