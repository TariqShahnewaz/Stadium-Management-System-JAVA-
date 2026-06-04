public class Start {
    public static void main(String[] args) {
        Audience a1 = new Audience("Tariq Shahnewaz Al-Riyadh", 21, "A1", "VIP");
        Audience a2 = new Audience("Tahsin Hassan Shovon", 21, "B2", "Regular");
        Audience a3 = new Audience("Minhajul Islam", 21, "C3", "Economy");
 
        MembershipAudience m1 = new MembershipAudience("Nabin Hassan", 22, "D4", "VIP", 15, "Gold");
        MembershipAudience m2 = new MembershipAudience("Shamim Shajib", 20, "E5", "Regular", 10, "Silver");
 
        Stadium stadium = new Stadium("Spotify Camp Nou");
 
        stadium.addAudience(a1);
        stadium.addAudience(a2);
        stadium.addAudience(a3);
        stadium.addAudience(m1);
        stadium.addAudience(m2);
 
        System.out.println("Showing all audience information:");
        stadium.showAllAudience();
 
        System.out.println("Searching for seat number D4:");
        Audience found = stadium.searchAudience("D4");
        if (found != null) {
            found.showDetails();
        } else {
            System.out.println("Empty");
        }
    }
}
