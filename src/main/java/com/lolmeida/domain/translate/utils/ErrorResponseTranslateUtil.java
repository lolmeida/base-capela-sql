package com.lolmeida.domain.translate.utils;


import com.lolmeida.domain.entity.RequestDetail;
import com.lolmeida.dto.response.ApiErrorResponse;

/**
 * Utility class to translate common properties from {@link RequestDetail} to our own error response implementations.
 */
public final class ErrorResponseTranslateUtil {

    private ErrorResponseTranslateUtil() {

    }

    /**
     * Translate common properties from {@link RequestDetail}.
     *
     * @param <T>
     *         type of the builder to use on translation
     * @param <X>
     *         the type of the API error response
     * @param builder
     *         builder to use on translation
     * @param requestDetail
     *         object to copy properties from
     *
     * @return the provided builder itself
     */
    public static <T extends ApiErrorResponse.Builder<T, X>, X extends ApiErrorResponse> T buildResponse(final T builder, final RequestDetail requestDetail) {
        return builder
                .logTransactionId(requestDetail.transactionId())
                .requestTimestamp(requestDetail.timestamp())
                .requestUrl(requestDetail.uri());
    }
}
