package system.user;

public class UserPlan {
    public enum PlanType { TRIAL, STANDARD, VIP }

    private PlanType type;
    private boolean isActive;

    public UserPlan(PlanType type, boolean isActive) {
        this.type = type;
        this.isActive = isActive;
    }

    public static UserPlan createPlan(String planType, String isActive) {
        PlanType type;
        switch (planType.toLowerCase()) {
            case "trial":
                type = PlanType.TRIAL;
                break;
            case "standard":
                type = PlanType.STANDARD;
                break;
            case "vip":
                type = PlanType.VIP;
                break;
            default:
                return null;
        }

        boolean active;
        if ("true".equalsIgnoreCase(isActive) || "false".equalsIgnoreCase(isActive)) {
            active = Boolean.parseBoolean(isActive);
        } else {
            return null;
        }

        return new UserPlan(type, active);
    }

    public PlanType getType() {
        return type;
    }

    public void setType(PlanType type) {
        this.type = type;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserPlan{" +
                "type=" + getType() +
                ", isActive=" + isActive() +
                '}';
    }
}
