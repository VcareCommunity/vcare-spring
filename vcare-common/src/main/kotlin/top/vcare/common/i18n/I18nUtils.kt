package top.vcare.common.i18n

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class I18nUtils : ApplicationContextAware {

    companion object {
        private lateinit var messageSourceSingleton: MessageSource

        @JvmStatic
        fun getMessage(code: String): String {
            val locale = LocaleContextHolder.getLocale()
            return getMessage(code, null, code, locale)
        }

        @JvmStatic
        fun getMessage(code: String, args: Array<Any>? = null, defaultMessage: String, locale: Locale): String {
            return try {
                messageSourceSingleton.getMessage(code, args, defaultMessage, locale)!!
            } catch (e: Exception) {
                defaultMessage
            }
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        messageSourceSingleton = applicationContext.getBean(MessageSource::class.java)
    }

}