package org.phoenixctms.ctsms.web.jersey.resource.trial;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.phoenixctms.ctsms.exception.AuthenticationException;
import org.phoenixctms.ctsms.exception.AuthorisationException;
import org.phoenixctms.ctsms.exception.ServiceException;
import org.phoenixctms.ctsms.vo.AuthenticationVO;
import org.phoenixctms.ctsms.vo.InquiryInVO;
import org.phoenixctms.ctsms.vo.InquiryOutVO;
import org.phoenixctms.ctsms.web.util.DefaultSettings;
import org.phoenixctms.ctsms.web.util.SettingCodes;
import org.phoenixctms.ctsms.web.util.Settings;
import org.phoenixctms.ctsms.web.util.Settings.Bundle;
import org.phoenixctms.ctsms.web.util.WebUtil;

import io.swagger.annotations.Api;

@Api(value = "trial")
@Path("/inquiry")
public class InquiryResource {

	@Context
	AuthenticationVO auth;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InquiryOutVO addInquiry(InquiryInVO in) throws AuthenticationException, AuthorisationException, ServiceException {
		return WebUtil.getServiceLocator().getTrialService().addInquiry(auth, in);
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public InquiryOutVO deleteInquiry(@PathParam("id") Long id, @QueryParam("force") Boolean force, @QueryParam("reason") String reason)
			throws AuthenticationException, AuthorisationException, ServiceException {
		return WebUtil.getServiceLocator().getTrialService()
				.deleteInquiry(auth, id, Settings.getBoolean(SettingCodes.INQUIRY_DEFERRED_DELETE, Bundle.SETTINGS, DefaultSettings.INQUIRY_DEFERRED_DELETE),
						force != null ? force : false, reason);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{id}")
	public InquiryOutVO getInquiry(@PathParam("id") Long id) throws AuthenticationException, AuthorisationException, ServiceException {
		return WebUtil.getServiceLocator().getTrialService().getInquiry(auth, id);
	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public InquiryOutVO updateInquiry(InquiryInVO in) throws AuthenticationException, AuthorisationException, ServiceException {
		return WebUtil.getServiceLocator().getTrialService().updateInquiry(auth, in);
	}
}
