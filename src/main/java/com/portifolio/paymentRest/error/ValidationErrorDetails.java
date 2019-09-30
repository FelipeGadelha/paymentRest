package com.portifolio.paymentRest.error;

import java.util.List;
import java.util.Map;

public class ValidationErrorDetails extends ErrorDetails {

	private Map<List<String>, List<String>> fieldErrors;
	
	public Map<List<String>, List<String>> getFieldErrors() {
		return fieldErrors;
	}

	public static final class Builder {

		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		private Map<List<String>, List<String>> fieldErrors; 

		private Builder() {
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}
		
		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}
		public Builder fieldErrors(Map<List<String>, List<String>> fieldErrors) {
			this.fieldErrors = fieldErrors;
			return this;
		}

		public ValidationErrorDetails build() {
			ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
			validationErrorDetails.setDeveloperMessage(developerMessage);
			validationErrorDetails.setTitle(title);
			validationErrorDetails.setDetail(detail);
			validationErrorDetails.setTimestamp(timestamp);
			validationErrorDetails.setStatus(status);
			validationErrorDetails.fieldErrors = fieldErrors;
			return validationErrorDetails;
		}
	}

}
