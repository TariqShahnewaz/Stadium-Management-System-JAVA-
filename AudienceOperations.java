interface AudienceOperations {
    boolean addAudience(Audience a);
    boolean removeAudience(Audience a);
    Audience searchAudience(String seatNumber);
    void showAllAudience();
}