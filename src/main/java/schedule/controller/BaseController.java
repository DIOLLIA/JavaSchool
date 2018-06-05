package schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author Rudkov Andrey
 */
public class BaseController {
    private MessageSource messageSource;

    protected static final Locale DEFAULT_LOCALE = new Locale("en", "us");

    protected String getMessage(String key, Locale locale, String... args) {
        Object[] objects = new Object[args.length];
        System.arraycopy(args, 0, objects, 0, objects.length);

        return messageSource.getMessage(key, objects, locale);
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
