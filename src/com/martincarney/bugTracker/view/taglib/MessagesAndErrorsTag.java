package com.martincarney.bugTracker.view.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

public class MessagesAndErrorsTag extends TagSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(MessagesAndErrorsTag.class);
	
	protected static String lineEnd = System.getProperty("line.separator");
	
	@Override
	public int doStartTag() throws JspException {
		
		WebApplicationContext servletContext = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		Locale locale = RequestContextUtils.getLocale((HttpServletRequest) pageContext.getRequest());
		
		JspWriter writer = pageContext.getOut();
		try {
			writer.print(doMessagesBox(servletContext, locale, "ERRORS", "errors"));
			writer.print(doMessagesBox(servletContext, locale, "MESSAGES", "messages"));
		} catch (IOException e) {
			throw new JspException("Exception in ErrorsTag doStartTag():" + e.toString());
		}
		
		return (EVAL_BODY_INCLUDE);
		
	}
	
	/**
	 * Creates a new {@link ArrayList} and adds the contents of all the provided lists to it. Null-safe.
	 * @param lists Two or more Lists of the same type
	 * @return
	 */
	private <K extends Object> List<K> combineLists(List<K>... lists) {
		List<K> result = new ArrayList<K>();
		for (List<K> list : lists) {
			if (list != null) {
				result.addAll(list);
			}
		}
		return result;
	}
	
	/** Checks if the list is either null or empty. */
	private <K extends Object> boolean isListEmpty(List<K> list) {
		return list == null || list.isEmpty();
	}
	
	/**
	 * Builds the full html for the message or error box.
	 * @param attributeKey Key for session and request attributes to get any messages
	 * @param boxFragmentPrefix First part of the translation key, to which will be appended the four different parts.
	 */
	private <K extends Object> String doMessagesBox(WebApplicationContext servletContext, Locale locale, String attributeKey, String boxFragmentPrefix) throws JspException {
		List<K> requestMessages = (List<K>) pageContext.getRequest().getAttribute(attributeKey);
		List<K> sessionMessages = (List<K>) pageContext.getSession().getAttribute(attributeKey);
		
		// shortcut if no messages present.
		if (isListEmpty(requestMessages) && isListEmpty(sessionMessages)) {
			return "";
		}
		
		List<K> allMessages = combineLists(requestMessages, sessionMessages);
		
		BoxFragments htmlFragments = new BoxFragments(servletContext, locale, boxFragmentPrefix);
		htmlFragments.ensureUnorderedList();
		
		StringBuffer results = new StringBuffer();
		
		nullSafeAppend(results, htmlFragments.header, lineEnd);
		
		for (Object obj : allMessages) {
			if (obj == null) {
				continue;
			} // skip null errors/messages
			
			nullSafeAppend(results, htmlFragments.prefix);
			
			String messageText = null;
			if (obj instanceof FieldError) {
				FieldError e = (FieldError) obj;
				Object[] args = {e.getRejectedValue()};
				// SPRINGTODO FieldError is MessageSourceResolvable, we should add
				// generic type mismatch translations in CBT (ex typeMismatch.int)
				// so the user doesn't see the default message
				// messageText = servletContext.getMessage(e, locale);
				logger.error(e.getDefaultMessage());
			} else if (obj instanceof ObjectError) {
				ObjectError e = (ObjectError) obj;
				messageText = servletContext.getMessage(e.getCode(), e.getArguments(), locale);
//			} else if (obj instanceof ActionMessage) {
//				ActionMessage message = (ActionMessage) obj;
//				messageText = servletContext.getMessage(message.getKey(), message.getArguments(), locale);
			} else {
				messageText = obj.toString();
			}
			
			if (messageText != null) {
				// normalize the message styling, since we use bulleted lists now.
				messageText = messageText.replaceAll("^\\s*&middot;\\s*", "") // remove &middot; from the start
				.replaceAll("<br\\s*/?>$", "") // remove <br/> from the end
				.trim(); // remove leading + trailing whitespace
				
				nullSafeAppend(results, messageText);
				results.append("<br/>");
			}
			results.append(lineEnd);
			
			nullSafeAppend(results, htmlFragments.suffix);
		}
		
		nullSafeAppend(results, htmlFragments.footer, lineEnd);
		
		// Reset all messages
		pageContext.getRequest().removeAttribute(attributeKey);
		pageContext.getSession().removeAttribute(attributeKey);
		
		return results.toString();
	}
	
	/** appends 1 or more strings to the StringBuffer, skipping null and empty strings */
	private void nullSafeAppend(StringBuffer sb, String... strings) {
		for (String str : strings) {
			if (str != null && !"".equals(str)) {
				sb.append(str);
			}
		}
	}
	
	/**
	 * Struct to obtain and contain the four html fragments used to construct a message or error box.
	 */
	private class BoxFragments {
		
		public String header;
		public String footer;
		public String prefix;
		public String suffix;
		
		BoxFragments(WebApplicationContext servletContext, Locale locale, String i18nKeyPrefex) {
			try {
				header = servletContext.getMessage(i18nKeyPrefex + ".header", null, locale);
			} catch (Exception e) {
				//Do Nothing
			}
			try {
				footer = servletContext.getMessage(i18nKeyPrefex + ".footer", null, locale);
			} catch (Exception e) {
				//Do Nothing
			}
			try {
				prefix = servletContext.getMessage(i18nKeyPrefex + ".prefix", null, locale);
			} catch (Exception e) {
				//Do Nothing
			}
			try {
				suffix = servletContext.getMessage(i18nKeyPrefex + ".suffix", null, locale);
			} catch (Exception e) {
				//Do Nothing
			}
		}
		
		/**
		 * Temporary measure to ensure that messages and errors are wrapped in a {@code <ul>} and individual {@code <li>} tags
		 */
		void ensureUnorderedList() {
			if (header == null) {
				header = "<ul>";
			} else if (!header.contains("<ul")) { // leaving out '>' allows for '<ul class="foo">'
				header += "<ul>";
			}
			
			if (footer == null) {
				footer = "</ul>";
			} else if (!footer.contains("</ul>")) {
				footer += "</ul>";
			}
			
			if (prefix == null) {
				prefix = "<li>";
			} else if (!prefix.contains("<li")) { // leaving out '>' allows for '<li class="bar">'
				prefix += "<li>";
			}
			
			if (suffix == null) {
				suffix = "</li>";
			} else if (!suffix.contains("</li>")) {
				suffix += "</li>";
			}
		}
	}
}
