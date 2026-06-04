class MembershipAudience extends Audience {
    private double discounts;
    private String membershipLevel;
 
    public MembershipAudience() {}
    public MembershipAudience(String name, int age, String seatNumber, String ticketType, double discounts, String membershipLevel) {
        super(name, age, seatNumber, ticketType);
        this.discounts = discounts;
        this.membershipLevel = membershipLevel;
    }
    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }
    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
    public double getDiscounts() {
        return discounts;
    }
    public String getMembershipLevel() {
        return membershipLevel;
    }
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Discounts: " + discounts +"%");
        System.out.println("Membership Level: " + membershipLevel);
    }
}