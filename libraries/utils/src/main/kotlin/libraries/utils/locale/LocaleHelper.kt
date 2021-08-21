/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libraries.utils.locale

import android.content.Context
import android.content.res.Resources
import java.util.*

/**
 * Singleton class for changing locale (layout direction, resources) on runtime
 *
 * @author Mohammad Esteki
 * @see [Locale]
 */
object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val LANGUAGE = "language"

    const val DEFAULT_LOCALE = "en_US"

    /**
     * Attach default language context
     *
     * @param context Context of app
     *
     * @return Default language context
     */
    fun onAttach(context: Context): Context {
        val lang =
            getPersistedData(
                context,
                Locale.getDefault().language
            )
        return setLocale(context, lang)
    }

    /**
     * Attach particular language context
     *
     * @param context Context of app
     * @param defaultLanguage Language code for example fa, en
     *
     * @return Particular language context
     */
    fun onAttach(
        context: Context,
        defaultLanguage: String
    ): Context {
        val lang = getPersistedData(
            context,
            defaultLanguage
        )
        return setLocale(context, lang)
    }

    /**
     * Get current language code
     *
     * @return Current language code
     */
    fun getLanguage(context: Context): String = getPersistedData(
        context,
        Locale.getDefault().language
    )

    /**
     * Change locale of application
     *
     * @param context Context of app
     * @param language Language code
     *
     * @return Particular language context
     */
    fun setLocale(
        context: Context,
        language: String
    ): Context {
        persist(context, language)
        return updateResources(
            context,
            language
        )
    }

    /**
     * Get persisted language code
     *
     * @param context Context of app
     * @param defaultLanguage return this if no language persisted
     *
     * @return Persisted language code
     */
    private fun getPersistedData(
        context: Context,
        defaultLanguage: String
    ): String {
        val preferences = context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)!!
    }

    /**
     * save language code on shared preferences
     *
     * @param context Context of app
     * @param language return this if no language persisted
     */
    private fun persist(context: Context, language: String) {
        val preferences = context.getSharedPreferences(LANGUAGE, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    /**
     * Updated android application configuration
     *
     * @param context Context of app
     * @param language Language code
     *
     * @return Particular language context
     * */
    private fun updateResources(
        context: Context,
        language: String
    ): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }
}
