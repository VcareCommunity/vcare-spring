package top.vcare.config.i18n

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

@Configuration
open class LocaleConfig {

    @Bean
    open fun localeResolver(): AcceptHeaderLocaleResolver {
        val localeResolver = AcceptHeaderLocaleResolver()
        localeResolver.setDefaultLocale(Locale.ENGLISH)
        return localeResolver
    }
}