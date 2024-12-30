package ipify.models;

/**
 * Represents the formatting for the IP address from requests.
 */
public class IPFormat {

    private IPFormatType ipFormatType;
    private String callback;

    /**
     * Gets the IP format enum.
     * @return IPFormatType
     */
    public IPFormatType getIpFormatType() {
        return ipFormatType;
    }

    /**
     * Gets the callback method name.
     * @return String
     */
    public String getCallback() {
        return callback;
    }

    private IPFormat(IPFormatBuilder ipFormatBuilder) {
        ipFormatType = ipFormatBuilder.ipFormatType;
        callback = ipFormatBuilder.callback;
    }

    /**
     * Generates a IPFormat object to use with requests.
     */
    public static class IPFormatBuilder {
        private IPFormatType ipFormatType;
        private String callback;

        /**
         * Constructor for IPFormatBuilder.
         * @param ipFormatType Enum
         */
        public IPFormatBuilder(IPFormatType ipFormatType) {
            this.ipFormatType = ipFormatType;
        }

        /**
         * Sets the callback method name for IPFormatBuilder.
         * @param callback String
         * @return IPFormatBuilder
         */
        public IPFormatBuilder setCallback(String callback) {
            this.callback = callback;
            return this;
        }

        /**
         * Constructs an IPFormat object to use in requests.
         * @return IPFormat
         */
        public IPFormat build() {
            return new IPFormat(this);
        }
    }

    /**
     * Represents the format the IP address should display as.
     */
    public enum IPFormatType {
        /**
         * Plaintext.
         */
        Text,
        /**
         * JSON Object in text.
         */
        Json,
        /**
         * Function with JSON Object in text.
         */
        JsonP
    }
}
