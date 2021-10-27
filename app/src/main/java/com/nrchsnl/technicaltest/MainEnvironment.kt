package com.nrchsnl.technicaltest

import android.content.Context
import android.os.Bundle
import com.nrchsnl.technicaltest.helper.extensions.navigatorImplicit

object MainEnvironment {
    object ConstOther {
        // TODO add uncategories const in here
        const val HTTP_STRING = "http"

        const val SNACKBAR_TIMER_SHOWING_DEFAULT = 2000

        const val MESSAGE_SUCCESS = "success"
    }

    object routeNavigation{
        private const val PACKAGE_DETAIL_PAGE = "com.nrchsnl.technicaltest.ui.detail.DetailActivity"
        private const val PACKAGE_GENRE_PAGE = "com.nrchsnl.technicaltest.ui.genre.GenreActivity"
        private const val PACKAGE_SEARCH_PAGE = "com.nrchsnl.technicaltest.ui.search.SearchActivity"

        /**
         * detail
         */
        fun openDetailPage(context: Context, bundle: Bundle) {
            context.navigatorImplicit(context.packageName, PACKAGE_DETAIL_PAGE, bundle)
        }
        /**
         * genre
         */
        fun openGenreMoviePage(context: Context, bundle: Bundle) {
            context.navigatorImplicit(context.packageName, PACKAGE_GENRE_PAGE, bundle)
        }
        /**
         * search
         */
        fun openSearchMoviePage(context: Context) {
            context.navigatorImplicit(context.packageName, PACKAGE_SEARCH_PAGE)
        }

    }
}