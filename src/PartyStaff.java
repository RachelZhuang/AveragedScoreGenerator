/**
 * Created by zhuangli on 2016/9/13.
 */
public class PartyStaff extends Staff {
    private boolean partyMember=false;

    public PartyStaff(String monthNumebr, String personnelNumber, String name, String taxes) {
        super(monthNumebr, personnelNumber, name, taxes);
    }

    public PartyStaff(String monthNumebr, String personnelNumber, String name, String taxes, boolean partyMember) {
        super(monthNumebr, personnelNumber, name, taxes);
        this.partyMember = partyMember;
    }

    public boolean isPartyMember() {
        return partyMember;
    }

    public void setPartyMember(boolean partyMember) {
        this.partyMember = partyMember;
    }
}
