package br.com.passoplantao.di

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Module
    companion object {

        @Singleton
        @JvmStatic
        @Provides
        @Named("LoggingInterceptor")
        fun provideLoggingInterceptor(): Interceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        @JvmStatic
        @Provides
        @Singleton
        fun provideOkHttpCache(application: Application): Cache {
            val cacheSize: Long = 10 * 1024 * 1024
            return Cache(application.cacheDir, cacheSize)
        }

        @JvmStatic
        @Provides
        @Singleton
        fun provideMoshi(): Moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory())
                .build()


        @Singleton
        @JvmStatic
        @Provides
        fun providesOkHttp(
            @Named("LoggingInterceptor")
            loggingInterceptor: Interceptor,
        ): OkHttpClient =

            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

        @Singleton
        @JvmStatic
        @Provides
        fun provideRetrofit(
            oktHttpClient: OkHttpClient,
            moshi: Moshi
        ): Retrofit =
            Retrofit.Builder()
                .client(oktHttpClient)
                .baseUrl("https://passo-plantao-api.herokuapp.com")
                .addConverterFactory(nullOnEmptyConverterFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        private val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ) = object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
        }
    }
}
