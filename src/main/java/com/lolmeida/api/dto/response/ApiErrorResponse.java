package com.lolmeida.api.dto.response;

import java.util.Objects;

/**
 * Abstract error response class with common attributes.
 */
public class ApiErrorResponse {

    /**
     * Status Code.
     */
    protected Integer statusCode;

    /**
     * Transaction unique id.
     */
    protected String logTransactionId;

    /**
     * Transaction request URL.
     */
    protected String requestUrl;

    /**
     * The timestamp in which the request was made
     */
    protected Long requestTimestamp;

    /**
     * Constructor for common Error Response properties.
     *
     * @param builder builder for common properties
     * @param <T>     Type of builder
     * @param <X>     Type of object to build
     */
    protected <T extends Builder<T, X>, X extends ApiErrorResponse> ApiErrorResponse(
            final Builder<T, X> builder) {
        this.statusCode = builder.statusCode;
        this.logTransactionId = builder.logTransactionId;
        this.requestUrl = builder.requestUrl;
        this.requestTimestamp = builder.requestTimestamp;
    }

    ApiErrorResponse() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getLogTransactionId() {
        return logTransactionId;
    }

    public Long getRequestTimestamp() {
        return requestTimestamp;
    }

    protected void setStatusCode(final Integer statusCode) {
        this.statusCode = statusCode;
    }

    protected void setRequestUrl(final String requestUrl) {
        this.requestUrl = requestUrl;
    }

    protected void setLogTransactionId(final String logTransactionId) {
        this.logTransactionId = logTransactionId;
    }

    protected void setRequestTimestamp(final Long requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        final ApiErrorResponse castOther = (ApiErrorResponse) other;
        return Objects.equals(statusCode, castOther.statusCode) && Objects.equals(logTransactionId, castOther.logTransactionId)
                && Objects.equals(requestUrl, castOther.requestUrl)
                && Objects.equals(requestTimestamp, castOther.requestTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, logTransactionId, requestUrl, requestTimestamp);
    }

    /**
     * Builder to build {@link ApiErrorResponse}.
     *
     * @param <T> Type of builder
     * @param <X> Type of object to build
     */
    public abstract static class Builder<T extends Builder<T, X>, X extends ApiErrorResponse> {
        private Integer statusCode;
        private String logTransactionId;
        private String requestUrl;
        private Long requestTimestamp;

        /**
         * Builder constructor with a provided type.
         */
        protected Builder() {
            // do nothing
        }

        /**
         * Builder method for statusCode parameter.
         *
         * @param statusCode field to set
         * @return builder
         */
        public T statusCode(final Integer statusCode) {
            this.statusCode = statusCode;
            return getConcreteBuilderInstance();
        }

        /**
         * Builder method for logTransactionId parameter.
         *
         * @param logTransactionId field to set
         * @return builder
         */
        public T logTransactionId(final String logTransactionId) {
            this.logTransactionId = logTransactionId;
            return getConcreteBuilderInstance();
        }

        /**
         * Builder method for requestUrl parameter.
         *
         * @param requestUrl field to set
         * @return builder
         */
        public T requestUrl(final String requestUrl) {
            this.requestUrl = requestUrl;
            return getConcreteBuilderInstance();
        }

        /**
         * Builder method for requestTimestamp parameter.
         *
         * @param requestTimestamp field to set
         * @return builder
         */
        public T requestTimestamp(final Long requestTimestamp) {
            this.requestTimestamp = requestTimestamp;
            return getConcreteBuilderInstance();
        }

        /**
         * Get instance of the non-abstract builder.
         *
         * @return builder instance
         */
        protected abstract T getConcreteBuilderInstance();

        /**
         * Builder method for the non-abstract class.
         *
         * @return built class
         */
        public abstract X build();
    }
}
