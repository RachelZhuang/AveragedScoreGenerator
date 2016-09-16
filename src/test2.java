import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zhuangli on 2016/9/13.
 */
public class test2 {

    ArrayList<String> names=new ArrayList<>();
    ArrayList<Student> students=new ArrayList<>();

    jxl.Workbook workBook;
    Sheet sheet;
    public static void main(String[] args) {



        test2 ts = new test2();
        //float fg=ts.extractCredit(name);
        // TODO Auto-generated method stub
        Comparator<Student> comparator=new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return (Integer.parseInt(s1.getStudentNumber().substring(4,s1.getStudentNumber().length()))-Integer.parseInt(s2.getStudentNumber().substring(4,s2.getStudentNumber().length())));
            }
        };

        Comparator<Student> comparator2=new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return (int)(s2.getStudentAveragedScore()*1000000-s1.getStudentAveragedScore()*1000000);
            }
        };
        String url1 = "F:\\2013学年电科.xls";
       // String url1 = "F:\\1.xls";

        ts.readYearScore(url1);
       String url2 = "F:\\2014学年电科.xls";
        //String url2 = "F:\\2.xls";

        ts.readYearScore(url2);
        String url3 = "F:\\2015学年电科.xls";
        //String url3 = "F:\\3.xls";

        ts.readYearScore(url3);
        Collections.sort(ts.students,comparator);
        Student tmp2=new Student("2012301200001","",0,0,0);
        ts.students.add(tmp2);

        int k=1;
         float totalScore=ts.students.get(0).getTotalScore();
         float creditSum=ts.students.get(0).getCreditSum();
        ArrayList<Student> res=new ArrayList<>();
        Student tmp;
        //ts.students.add(new Student("2012201300001"),)
        for(int i=1;i<ts.students.size();i++){
            if(!ts.students.get(i).getStudentNumber().equals(ts.students.get(i-1).getStudentNumber())) {  // totalScore
                tmp = new Student(ts.students.get(i - 1).getStudentNumber(), ts.students.get(i - 1).getStudentName(), totalScore / creditSum, totalScore, creditSum);
                res.add(tmp);
                k=1;
                totalScore=ts.students.get(i).getTotalScore();
                creditSum=ts.students.get(i).getCreditSum();
            }
            else {
                k++;
                totalScore+=ts.students.get(i).getTotalScore();
                creditSum+=ts.students.get(i).getCreditSum();
            }
        }


        Collections.sort(res,comparator2);
        for(Student s:res){
            System.out.print(s.getStudentNumber());
            System.out.print(s.getStudentAveragedScore());
            System.out.println();
        }

        try {
            outputXls(res);
            //JOptionPane.showConfirmDialog(null, "成绩已经保存到outpuutScore.xls，请到程序根目录查看", "进度", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e2) {

            //JOptionPane.showMessageDialog(null, "发生异常", "错误",JOptionPane.ERROR_MESSAGE);
            e2.printStackTrace();
        }

    }
    public static  void outputXls(ArrayList<Student> data) throws FileNotFoundException, IOException, RowsExceededException, WriteException {

        WritableWorkbook workbook = Workbook.createWorkbook(new FileOutputStream("./result.xls"));
        WritableSheet sheet = workbook.createSheet("SHEET", 0);
//				给表格添加表头
        Label label1 = new Label(0, 0, "学号");
        sheet.addCell(label1);

        Label label2 = new Label(1, 0, "姓名");
        sheet.addCell(label2);

        Label label3 = new Label(2, 0, "均分");
        sheet.addCell(label3);

        Label label4 = new Label(3, 0, "排名");
        sheet.addCell(label4);

//			把arraylist存入表格
        for (int i = 0; i < data.size(); i++)
        {

            Student a = data.get(i);
            String  studentNumber= a.getStudentNumber();
            Label label = new Label(0, i+1, studentNumber);
            sheet.addCell(label);
            String  studentName=a.getStudentName();
            Label labe2 = new Label(1, i+1, studentName);
            sheet.addCell(labe2);
            String  studentScore=String.valueOf(a.getStudentAveragedScore());
            Label labe3 = new Label(2, i+1, studentScore);
            sheet.addCell(labe3);
            String scoreRanking=Integer.toString(i+1);
            Label labe4 = new Label(3, i+1, scoreRanking);
            sheet.addCell(labe4);

        }

        workbook.write();
        workbook.close();
    }

    private void readYearScore(String url) {



            File file = new File(url);
            Student stu;
            try {

                workBook = Workbook.getWorkbook(file);

                sheet = workBook.getSheet(0);
               // String fgh;
                //float sf=extractCredit(fgh);

                //PartyStaff ps;
                float totalScore=0.0f;
                float creditSum=0.0f;
                //float credit=0.0f;
                String[] subjectNames=new String[sheet.getColumns()];
                String[] subjectTypes=new String[sheet.getColumns()];
                float[] credits=new float[sheet.getColumns()];

                String[] temp;
                for(int j=2;j<sheet.getColumns()&&(!sheet.getCell(j,0).getContents().equals(" "));j++){

                    temp=sheet.getCell(j,0).getContents().split("\\s+");
                    subjectNames[j]=(temp[0]);
                    subjectTypes[j]=(temp[1]);
                    credits[j]=Float.parseFloat(temp[2]);
                }

                float score=0;
                for (int i = 1; i < sheet.getRows(); i++) {
                    if(sheet.getCell(0,i).getContents().contains("2013")) {
                        for (int j = 2; j < sheet.getColumns()&&(!sheet.getCell(j,0).getContents().equals(" ")); j++) {

                            //潜在问题，规则制定
                            if(sheet.getCell(j,i).getContents().equals("")||sheet.getCell(j,i).getContents().equals(" ")||subjectTypes[j].contains("选修")||subjectNames[j].contains("初级")||subjectNames[j].contains("体育")||subjectNames[j].contains("高级")||subjectNames[j].contains("军事") || subjectNames[j].contains("思想") || subjectNames[j].contains("马克思") ||subjectNames[j].contains("军事") || subjectNames[j].contains("现代史") || subjectNames[j].contains("毛泽东")||subjectNames[j].contains("形势"))
                                continue;
                            score=Float.parseFloat(sheet.getCell(j, i).getContents());
                            creditSum+=credits[j];
                            if(sheet.getCell(0,i).getContents().equals("2013301200125"))
                                totalScore += (score+2.8)* credits[j];
                            else
                                totalScore +=score * credits[j];
                        }

                        stu = new Student(sheet.getCell(0,i).getContents(),sheet.getCell(1,i).getContents(),0,totalScore,creditSum);
                        students.add(stu);
                        creditSum=0;
                        totalScore=0;
                    }
                }



            } catch (BiffException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


    }



}
