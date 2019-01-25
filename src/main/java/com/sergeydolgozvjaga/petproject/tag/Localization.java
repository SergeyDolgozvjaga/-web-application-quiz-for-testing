package com.sergeydolgozvjaga.petproject.tag;

import com.sergeydolgozvjaga.petproject.util.UTF8Support;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Localization supports English locale as a default
 *
 * @author Sergey Dolgozvjaga
 * @version 1.0
 */
public class Localization extends TagSupport {
    private UTF8Support utf8 = new UTF8Support();
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int doStartTag() throws JspException {

        HttpSession session = pageContext.getSession();

        if (session.getAttribute("LOCALE") == null) {
            if (pageContext.getRequest().getLocale() == null) {
                session.setAttribute("LOCALE", new Locale("en", "EN"));
            } else {
                session.setAttribute("LOCALE", pageContext.getRequest().getLocale());
            }
        }

        Locale locale = (Locale) session.getAttribute("LOCALE");

        if (message != null && !message.isEmpty()) {
            ResourceBundle messages = null;
            messages = ResourceBundle.getBundle("i18n.messages", locale, utf8);
            String locMessage = messages.getString(message);
            try {
                pageContext.getOut().print(locMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }
}
