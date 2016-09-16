/**
 * Created by zhuangli on 2016/9/7.
 */
public class Student {
    private String studentNumber="";
    private String studentName="";
    private float studentAveragedScore=0.0f;
    private float totalScore=0.0f;
    private float creditSum=0.0f;


    public Student(String studentNumber, String studentName, float studentAveragedScore, float totalScore, float creditSum) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentAveragedScore = studentAveragedScore;
        this.totalScore = totalScore;
        this.creditSum = creditSum;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public float getStudentAveragedScore() {
        return studentAveragedScore;
    }

    public void setStudentAveragedScore(float studentAveragedScore) {
        this.studentAveragedScore = studentAveragedScore;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public float getCreditSum() {
        return creditSum;
    }

    public void setCreditSum(float creditSum) {
        this.creditSum = creditSum;
    }
}
