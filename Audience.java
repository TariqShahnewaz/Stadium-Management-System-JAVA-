public class Audience extends Person {
    private String seatNumber;
    private String ticketType;
 
    public Audience() {}
    public Audience(String name, int age, String seatNumber, String ticketType) {
        super(name, age);
        this.seatNumber = seatNumber;
        this.ticketType = ticketType;
    }
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    public String getSeatNumber() {
        return seatNumber;
    }
    public String getTicketType() {
        return ticketType;
    }
    @Override
    public void showDetails() {
        System.out.println("Audience Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Ticket Type: " + ticketType);
    }
}