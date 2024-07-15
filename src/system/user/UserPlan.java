package system.user;

public class UserPlan {
    public enum PlanType { TRIAL, PREMIUM, STANDARD, VIP }
    private PlanType planType;
    private boolean isActive;

    public UserPlan(PlanType planType, boolean isActive) {
        this.planType = planType;
        this.isActive = isActive;
    }

    public static UserPlan createPlan(String planType, String isActive) throws Exception {
        PlanType pt = PlanType.valueOf(planType.toUpperCase());
        boolean active = Boolean.parseBoolean(isActive);
        return new UserPlan(pt, active);
    }

    public boolean isActive() {
        return isActive;
    }
}
