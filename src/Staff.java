/**
 * Created by zhuangli on 2016/9/7.
 */
public class Staff {
    private String monthNumebr="";
    private String personnelNumber="";
    private String name="";
    private String taxes="";
    //private boolean partyMember=false;


    public Staff(String monthNumebr, String personnelNumber, String name, String taxes) {
        this.monthNumebr = monthNumebr;
        this.personnelNumber = personnelNumber;
        this.name = name;
        this.taxes = taxes;
    }


    public String getMonthNumebr() {
        return monthNumebr;
    }

    public void setMonthNumebr(String monthNumebr) {
        this.monthNumebr = monthNumebr;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxes() {
        return taxes;
    }

    public void setTaxes(String taxes) {
        this.taxes = taxes;
    }
}
