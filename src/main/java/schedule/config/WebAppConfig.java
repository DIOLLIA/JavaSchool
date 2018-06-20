package schedule.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.Properties;


@Configuration
@ComponentScan("schedule")
@EnableWebMvc
//@Import({SecurityConfig.class})// todo чекнуть полезность этого импорта
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class WebAppConfig extends WebMvcConfigurerAdapter {
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_USERNAME = "db.username";

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.show.sql";
    private static final String HIBERNATE_DDL_SCHEMA = "hibernate.hbm2ddl.auto";
    private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    private static final String WEB_RESOURCES_HANDLER = "/resources/**";
    private static final String WEB_RESOURCES_LOCATION = "/resources/";

    private static final String VIEWS_PATH = "/WEB-INF/pages/";
    private static final String JSP_SUFFIX = ".jsp";
    private static final String MESSAGES_PATH = "/content/language";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String DEFAULT_LANGUAGE = "en";

    @Resource
    private Environment environment;

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(HIBERNATE_DIALECT, environment.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put(HIBERNATE_DDL_SCHEMA, environment.getRequiredProperty(HIBERNATE_DDL_SCHEMA));
        properties.put(HIBERNATE_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_SHOW_SQL));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty(DB_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(DB_URL));
        dataSource.setUsername(environment.getRequiredProperty(DB_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(DB_PASSWORD));

        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(environment.getRequiredProperty(ENTITYMANAGER_PACKAGES_TO_SCAN));
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        return localSessionFactoryBean;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix(VIEWS_PATH);
        resolver.setSuffix(JSP_SUFFIX);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(WEB_RESOURCES_HANDLER)
                .addResourceLocations(WEB_RESOURCES_LOCATION);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        //todo see lesha)
/*        modelMapper.addMappings(new PropertyMap<Ticket, TicketEntity>() {
            @Override
            protected void configure() {
                map().setUserEntity(modelMapper.map(source.getUser(), UserEntity.class));
                map().setTrainEntity(modelMapper.map(source.getTrain(), TrainEntity.class));
            }
        });*/
        return modelMapper;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(MESSAGES_PATH);
        messageSource.setDefaultEncoding(DEFAULT_ENCODING);

        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale(DEFAULT_LANGUAGE));

        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("mylocale");
        registry.addInterceptor(interceptor);
    }
    @Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver= new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        resolver.setExceptionMappings(mappings); // None by default
        resolver.setExceptionAttribute("ErrorOccurred"); // Default is "exception"
        resolver.setDefaultErrorView("warning");
        return resolver;
    }
}