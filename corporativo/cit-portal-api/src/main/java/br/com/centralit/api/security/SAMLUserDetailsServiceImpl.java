package br.com.centralit.api.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;


/**
 * @author twreddy The SAMLUserDetailsService interface is similar to
 *         UserDetailsService with difference that SAML data is used in order
 *         obtain information about the user. So inspect the SAMLCredential
 *         object and return such a data in a form of application specific
 *         UserDetails object
 */
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

	// SAML Response PAY load attributes.
	// Some of the attributes we get back in SAML RESPONSE
	// We mapped this in openam so it should be there in SAML response.
	public static final String GROUP_MEMBER_ATTR_NAME = "Grupos";
	public static final String USER_ID = "UserID";

	private static Logger logger = LogManager.getLogger(SAMLUserDetailsServiceImpl.class);


	/*
	* This is the method spring will invoke.
	*/
	public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
		// Want to see if this object is null ...
		logger.info("credential=" + credential);
		if (credential != null) {
			String samlCredentilAsStr = "";
			if (logger.isDebugEnabled()) {
				// I dont want read this object every time.
				// Will do only of we have enabled debugging.
				samlCredentilAsStr = ToStringBuilder.reflectionToString(credential);
				logger.debug("samlCredentilAsStr=" + samlCredentilAsStr);
			}

			String userId = getUserId(credential);
			logger.debug("userId=" + userId);

			UserDetails userDetails = getUserWithRoles(credential, userId);

			return userDetails;
		}

		throw new UsernameNotFoundException(
				"SAMLCredential is null. Cant extract  "
						+ GROUP_MEMBER_ATTR_NAME + " and or " + USER_ID
						+ " in Saml Response. " );
	}

	/**
	 * Extracts all groups and creates a UserDetail object
	 * and returns it.
	 * @param credential
	 * @param userId
	 * @return
	 */
	private UserDetails getUserWithRoles(SAMLCredential credential, String userId) {
		UserDetails userDetails = null;

		// We dont have access to password ??
		// So using name as password for now.
		List<SimpleGrantedAuthority> grantedRolesList = getGrantedAuthorities(GROUP_MEMBER_ATTR_NAME, credential);
		logger.info(" roles For :" + userId + " grantedRolesList=" + grantedRolesList);

		// This object needs userid and password. I dont have password
		// Faking it with user id. Password may really be needed when
		// you try to Authenticate this object using authManager.
		userDetails = new User(userId, userId, grantedRolesList);
		return userDetails;
	}



	/**
	 * Reads the Attributes that are in SAML Pay load response. Our Group Meber
	 * ship Is a long String:
	 * CN=FILE_EDIT_USER,OU=Devtest,DC= We need to get only Group name which FILE_EDIT_USER
	 *
	 * @param name
	 * @param credential
	 * @return
	 */
	private List<SimpleGrantedAuthority> getGrantedAuthorities(final String name, final SAMLCredential credential) {

		List<SimpleGrantedAuthority> grantedRolesList = new ArrayList<SimpleGrantedAuthority>();
		List<String> attrValueList = getAttributeValue(name,credential);

		for (String attrValue : attrValueList) {
			if (attrValue != null) {
				// All we are doing here is extracting the first part in the
				// group membership
				// eg:
				//  CN=FILE_EDIT_USER,OU=Devtest,DC= We need to get only Group name which FILE_EDIT_USER
				logger.debug(" Raw Attribute=" + attrValue);
				String roleName = attrValue.split(",")[0].split("=")[1];
				// We need just "FILE_EDIT_USER"
				logger.debug(" Cleaned up group name=" + roleName);
				logger.debug(" group name with case changed=" + roleName);
				SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
				grantedRolesList.add(grantedAuthority);
			} else {
				logger.warn("One of the Group attrValue was null.");
			}
		}
		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		grantedRolesList.add(grantedAuthority);

		return grantedRolesList;
	}


	/**
	 * Given SAMLCredential inspects the object and returns the values
	 * for give attribute name.
	 * @param name
	 * @param credential
	 * @return
	 */
	// TODO RESOLVER ISSO COLOCANDO A VERSÃO JASIG CAS 4.0 QUE UTILIZA A VERSÃO 2.0 DO OPENSAML
	public static List<String> getAttributeValue(final String name, final SAMLCredential credential) {

		return null;
	}
	/*public static List<String> getAttributeValue(final String name, final SAMLCredential credential) {
		List<String> attrValList = new ArrayList<String>();
		Attribute attribute = credential.getAttribute(name);
		logger.debug(" Parsing name=" + name + " in SAMLCredential");
		if (attribute != null) {
			List<XMLObject> attributes = attribute.getAttributeValues();
			if ((attributes != null) && (attributes.size() > 0)) {
				for (XMLObject object : attributes) {
					XSString attrb = (XSString) object;
					String attrValue = attrb.getValue();
					if (attrValue != null) {
						// clean unwanted strings here in the role
						logger.debug("name=" + name + " attrValue="	+ attrValue);
						attrValList.add(attrValue);
					}
				}
			}
		}
		return attrValList;
	}*/


	/**
	 * Reads the uid from SAML credential if present
	 * @param credential
	 * @return
	 */
	public static String getUserId(SAMLCredential credential) {
		String userId = null;
		List<String> userIdValueList = getAttributeValue(USER_ID, credential);
		logger.debug(" USER_ID="+ USER_ID +" userIdValueList=" + userIdValueList);
		if (userIdValueList.size() > 0) {
			userId = userIdValueList.get(0);
		}
		return userId;
	}
}
