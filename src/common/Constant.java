package common;

public final class Constant {
	public static final String DATE_FORMAT = "MM/dd/yyyy";
	public static final Integer RECOMMENDED_ROOMS_DEFAULT_PLUS_DAYS = 7;
	
	public enum OPTION {
		YES("y"),
		NO("n");
		private String option;
		OPTION(String option) {
			this.option = option;
		}
		public String getOption() {
	        return option;
	    }
	}
}
