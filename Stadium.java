class Stadium implements AudienceOperations {
    private String stadiumName;
    private Audience[] audienceList = new Audience[100];
  
    public Stadium() {}
    public Stadium(String stadiumName) {
        this.stadiumName = stadiumName;
    }
    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }
    public String getStadiumName() {
        return stadiumName;
    }
	public Audience[] getAudienceList() {
    return audienceList;
	}
    @Override
    public boolean addAudience(Audience a) {
        for (int i = 0; i < audienceList.length; i++) {
            if (audienceList[i] == null) {
                audienceList[i] = a;
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean removeAudience(Audience a) {
        for (int i = 0; i < audienceList.length; i++) {
            if (audienceList[i] == a) {
                audienceList[i] = null;
                return true;
            }
        }
        return false;
    }
    @Override
    public Audience searchAudience(String seatNumber) {
        for (int i = 0; i < audienceList.length; i++) {
            if (audienceList[i] != null && audienceList[i].getSeatNumber().equals(seatNumber)) {
                return audienceList[i];
            }
        }
        return null;
    }

    @Override
    public void showAllAudience() {
        System.out.println("Stadium: " + stadiumName);
        for (int i = 0; i <audienceList.length; i++) {
			if(audienceList[i] != null){
            audienceList[i].showDetails();
            System.out.println("-------------------");
			}
        }
    }
}