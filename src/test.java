import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class test {

    Cell[] titleCell;
    Cell[][] allCell;
    jxl.Workbook workBook;
    Sheet sheet;

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String url = "F:\\data.xls";
        test ae = new test();
        ae.readExcel(url);
       // ae.splitExcel(500, "F:\\spiltData");

    }

    /*
     * 读取原excel文件，并将相关的数据存储到数组中
     */
    public void readExcel(String source) {

        File file = new File(source);
        ArrayList<Staff> res=new ArrayList<>();
        Comparator<Staff> comparator=new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
               // return (int)(s2.getStudentAveragedScore()*1000000-s1.getStudentAveragedScore()*1000000);
                return Integer.parseInt(s1.getPersonnelNumber())-Integer.parseInt(s2.getPersonnelNumber());
            }
        };
        try {

            workBook = Workbook.getWorkbook(file);
            for(int j=0;j<9;j++) {
                sheet = workBook.getSheet(j);


                for (int i = 1; i < sheet.getRows(); i++) {
                    Staff temp = new Staff(sheet.getCell(2, i).getContents(), sheet.getCell(3, i).getContents(), sheet.getCell(4, i).getContents(), sheet.getCell(24, i).getContents());
                    res.add(temp);
                }
            }

            Collections.sort(res,comparator);
            try {
                outputXls(res);
                //JOptionPane.showConfirmDialog(null, "成绩已经保存到outpuutScore.xls，请到程序根目录查看", "进度", JOptionPane.PLAIN_MESSAGE);
            } catch (Exception e2) {

                //JOptionPane.showMessageDialog(null, "发生异常", "错误",JOptionPane.ERROR_MESSAGE);
                e2.printStackTrace();
            }



        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static  void outputXls(ArrayList<Staff> data) throws FileNotFoundException, IOException, RowsExceededException, WriteException {

        WritableWorkbook workbook = Workbook.createWorkbook(new FileOutputStream("./Result.xls"));
        WritableSheet sheet = workbook.createSheet("SHEET", 0);
//				给表格添加表头
        Label label1 = new Label(0, 0, "月份");
        sheet.addCell(label1);

        Label label2 = new Label(1, 0, "人事编号");
        sheet.addCell(label2);

        Label label3 = new Label(2, 0, "姓名");
        sheet.addCell(label3);

        Label label4 = new Label(3, 0, "税费");
        sheet.addCell(label4);

//			把arraylist存入表格
        for (int i = 0; i < data.size(); i++)
        {

            Staff a = data.get(i);
            String  monthNumber= a.getMonthNumebr();
            Label label = new Label(0, i+1, monthNumber);
            sheet.addCell(label);
            String  personnelNumber=a.getPersonnelNumber();
            Label labe2 = new Label(1, i+1, personnelNumber);
            sheet.addCell(labe2);
            String  name=a.getName();
            Label labe3 = new Label(2, i+1, name);
            sheet.addCell(labe3);
            String texes=a.getTaxes();
            Label labe4 = new Label(3, i+1, texes);
            sheet.addCell(labe4);

        }

        workbook.write();
        workbook.close();
    }

    /*
     *@param number代表需要分隔的行数
     *@param destination代表分隔文件后存储的路径
     */
    public void splitExcel(int number, String destination) {



        int index = (int) Math.ceil(sheet.getRows() / number);//计算需要分隔多少个文件
        File[] files = new File[index + 1];
        //初始化文件数组
        for (int i = 0; i <= index; i++) {
            files[i] = new File(destination + i + ".xls");

        }
        int n = number;
        int y = 1;//用于记录行的位置
        for (int i = 0; i <= index; i++) {

            try {
                jxl.write.WritableWorkbook ww = Workbook
                        .createWorkbook(files[i]);
                WritableSheet ws = ww.createSheet("sheet1", 0);
                for (int t = 0; t < sheet.getColumns(); t++) {
                    ws.addCell(new Label(t, 0, allCell[t][0].getContents()));
                }

                out: for (int m = 1; y < sheet.getRows(); y++, m++) {

                    for (int x = 0; x < sheet.getColumns(); x++) {

                        if (y >number) {
                            number += n;
                            break out;
                        }

                        ws.addCell(new Label(x, m, allCell[x][y].getContents()));

                    }

                }
                ww.write();
                ww.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (RowsExceededException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (WriteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
