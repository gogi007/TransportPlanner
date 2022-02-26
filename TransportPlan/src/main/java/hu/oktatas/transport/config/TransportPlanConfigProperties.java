package hu.oktatas.transport.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class TransportPlanConfigProperties {

	private PercentMins percentMins = new PercentMins();

	public PercentMins getPercentMins() {
		return percentMins;
	}

	public void setPercentMins(PercentMins percentMins) {
		this.percentMins = percentMins;
	}

	public static class PercentMins {
		private int penalty30Min;
		private int penalty60Min;
		private int penalty120Min;

		public int getPenalty30Min() {
			return penalty30Min;
		}

		public void setPenalty30Min(int penalty30Min) {
			this.penalty30Min = penalty30Min;
		}

		public int getPenalty60Min() {
			return penalty60Min;
		}

		public void setPenalty60Min(int penalty60Min) {
			this.penalty60Min = penalty60Min;
		}

		public int getPenalty120Min() {
			return penalty120Min;
		}

		public void setPenalty120Min(int penalty120Min) {
			this.penalty120Min = penalty120Min;
		}
	}
}
