/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;


import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
//import org.jfree.data.time.SimpleTimePeriod;
import taskmanager.model.MainModel;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.lang.Character;

import taskmanager.view.CustomRenderer;

/**
 *
 * @author Jonathan
 */
public class GanttChartView extends JPanel
{
    
    JFreeChart chart;
    MainModel mm;
    String rowData[][];
    Date start, deadline, pDate, leftDate = new Date(), rightDate = new Date(),//start, end, progress date, left and right dates will store the left and right most dates of the list of tasks
            today = new Date();
    int xMargin = 156, yMargin = 76, xDateOffset = 863, yDateOffset = 567;      //# of pixels between origin of chart table and the chart itself
    float dayDensity;
    
    public GanttChartView(MainModel mm)
    {
        this.mm = mm;
        this.setLayout(new GridLayout(1, 1));
        
        mm.getTaskData();
        
        loadList(mm.getTaskData());
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (chart != null)
        {
            Rectangle r = new Rectangle(this.getLocation().x, this.getLocation().y-25,
                    this.getWidth(), this.getHeight());
            
            r.setSize(this.getWidth(), this.getHeight());
            
            chart.draw((Graphics2D)g, new Rectangle(r));
            markTodaysDate(g);
        }
    }
    
    public void loadList(List<taskmanager.model.Task> taskData)
    {
        this.removeAll();
        
        //initialize the data array for the table
        rowData = new String[taskData.size()][];
        
        for (int i = 0; i < rowData.length; i++)
        {
            rowData[i] = new String[4];
            
            rowData[i][0] = taskData.get(i).getTitle();
            rowData[i][1] = taskData.get(i).getStartDate();
            rowData[i][2] = taskData.get(i).getDeadline();
            rowData[i][3] = taskData.get(i).getCompletion();
        }
        
        TaskSeries plannedSchedule = new TaskSeries("Planned Implementation");  //the start date and deadlines
        TaskSeries progress = new TaskSeries("Progress");                       //precentage of the task completed
        TaskSeries completed = new TaskSeries("Completed");                     //which tasks are complete
        TaskSeries notCompleted = new TaskSeries("Behind Schedule");
        
        for(int i=0; i<rowData.length; i++) {
            //addToChart = true;
            if (rowData[i][1].compareTo("-") == 0 || rowData[i][2].compareTo("-") == 0) {
                start = new Date();
                deadline = new Date();
            } else {
                start = calculateDate(rowData[i][1]);
                deadline = calculateDate(rowData[i][2]);
            }
            if(rowData[i][3].compareTo("100") == 0) {
                completed.add(new Task(rowData[i][0], start, deadline));
                plannedSchedule.add(new Task(rowData[i][0], start, start));
                progress.add(new Task(rowData[i][0], start, start));
                notCompleted.add(new Task(rowData[i][0], start, start));
            } else {
                plannedSchedule.add(new Task(rowData[i][0], start, deadline));
                pDate = calculateProgress(start,deadline, rowData[i][3]);
                progress.add(new Task(rowData[i][0], start, pDate));
                completed.add(new Task(rowData[i][0], start, start));
                int x = today.compareTo(pDate);
                if(today.compareTo(pDate) > 0) {
                    notCompleted.add(new Task(rowData[i][0], pDate, today));
                } else {
                    notCompleted.add(new Task(rowData[i][0], start, start));
                }
            }
            
            if(leftDate.compareTo(start) > 0)                                   //find the earliest date
                leftDate = start;
            
            if(rightDate.compareTo(deadline) < 0)                               //find the latest date
                rightDate = deadline;
        }
        
        final TaskSeriesCollection collection = new TaskSeriesCollection();
 
        /**
        * Adding the series to the collection
        * Holds actual Dates.
        */
        collection.add(plannedSchedule);
        collection.add(progress);
        collection.add(completed);
        collection.add(notCompleted);
        
        chart = ChartFactory.createGanttChart(
            "Gantt Chart of Tasks", // chart title
            "Task", // domain axis label
            "Date", // range axis label
            collection, // data
            true, // include legend
            true, // tooltips
            false // urls
            );
        
        final CategoryPlot plot = chart.getCategoryPlot();/*
        CategoryItemRenderer renderer = new CustomRenderer();
        renderer.setSeriesPaint(0,Color.green);
        renderer.setSeriesPaint(1,Color.magenta);
        renderer.setSeriesItemLabelsVisible(1, false, true);*/
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        //renderer.setMaximumBarWidth(.9);
        renderer.setItemMargin(-2.1125);                                         //change item margins (causes them to overlap each other)
    }
    
    public Date calculateDate(String xmlDate) {                                 //make a Date() object from (String) xmlDate
        Date date;
        
        String sDay = xmlDate.substring(4,6),
                sMonth = xmlDate.substring(0,3),
                sYear = xmlDate.substring(10);
        int month = 0, day = 0, year;
        char d2;
        
        if (sMonth.compareToIgnoreCase("Jan") == 0){
            month = 0;
        } else if(sMonth.compareToIgnoreCase("Feb") == 0) {
            month = 1;
        } else if(sMonth.compareToIgnoreCase("Mar") == 0) {
            month = 2;
        } else if(sMonth.compareToIgnoreCase("Apr") == 0) {
            month = 3;
        } else if(sMonth.compareToIgnoreCase("May") == 0) {
            month = 4;
        } else if(sMonth.compareToIgnoreCase("Jun") == 0) {
            month = 5;
        } else if(sMonth.compareToIgnoreCase("Jul") == 0) {
            month = 6;
        } else if(sMonth.compareToIgnoreCase("Aug") == 0) {
            month = 7;
        } else if(sMonth.compareToIgnoreCase("Sep") == 0) {
            month = 8;
        } else if(sMonth.compareToIgnoreCase("Oct") == 0) {
            month = 9;
        } else if(sMonth.compareToIgnoreCase("Nov") == 0) {
            month = 10;
        } else if(sMonth.compareToIgnoreCase("Dec") == 0) {
            month = 11;
        }
        
        d2 = sDay.charAt(1);
        if(d2 == ',') {
            sDay = sDay.substring(0,1);
            sYear = xmlDate.substring(9);
        }
        
        day = Integer.parseInt(sDay);
        
        year = Integer.parseInt(sYear)+100;                         //+100 is to offset the year starting at 1900
        date = new Date(year, month, day);
        
        return date;
    }
    
    public Date calculateProgress(Date begin, Date end, String sPercentage)     //calculate a date based on the progress field in the tasks view
    {
        Date date;
        Float percentage = new Float(sPercentage);                              //cast (string) percentage to float
        percentage /= 100;
        int day, month, year,
                iDay, iMonth, iYear;                                            //index of day, month and year value in Calendar c.toString()
        String sDay, sMonth, sYear;
        Calendar c = Calendar.getInstance();
        long timeLength = getTimeDiff(begin, end),                              //timeLength is in days
                accomplished;
        char d2;
        
        accomplished = Math.round(timeLength*percentage);
        c.setTime(begin);
        c.add(Calendar.DATE, (int) accomplished);                               // number of days to add
        
        iDay = c.toString().indexOf("DAY_OF_MONTH=")+13;                        //find the first occurence of DAY_OF_MONTH and then add 13 to the index to move index past DAY_OF_MONTH=
        sDay = c.toString().substring(iDay,iDay+2);
        d2 = sDay.charAt(1);
        if(d2 == ',') {
            sDay = sDay.substring(0,1);
        }
        day = Integer.parseInt(sDay);
        
        iMonth = c.toString().indexOf(",MONTH=")+7;
        sMonth = c.toString().substring(iMonth,iMonth+2);
        
        if(sMonth.charAt(1) == ',') {
            sMonth = sMonth.substring(0,1);
        }
        month = Integer.parseInt(sMonth);
        
        iYear = c.toString().indexOf(",YEAR=20")+8;
        sYear = c.toString().substring(iYear,iYear+2);
        year = Integer.parseInt(sYear)+100;
        
        date = new Date(year, month, day);
        return date;
    }
    
    public void markTodaysDate(Graphics g) {
        daysPerPixel(leftDate,rightDate);
        
        long startTillToday = getTimeDiff(leftDate, today),
                startTillEnd = getTimeDiff(leftDate, rightDate);
        
        startTillToday += 39*dayDensity;
        startTillEnd += (39*dayDensity)*2;
        
        float percentage = (float) startTillToday/startTillEnd;
        
        int pxDateOffset = (int) (percentage* (float) xDateOffset);
        
        /*g.drawRect(xMargin+pxDateOffset-39,yMargin,                                 //draw rectangle as a vertical bar showing today's date
                2,yDateOffset);
        g.setColor(Color.BLACK);                                                //set color fill to black
        g.fillRect(xMargin+pxDateOffset-39,yMargin,                                 //draw rectangle as a vertical bar showing today's date
                2,yDateOffset);*/
    }
    
    public long getTimeDiff(Date begin, Date end) {
        long difference = (end.getTime() - begin.getTime())/(1000*60*60*24);    //milliseconds*seconds*minutes*hours
        return difference;
    }
    
    public void daysPerPixel(Date begin, Date end) {
        long timeLength = getTimeDiff(begin, end),                              //calculate the number of days between the first task and the deadline of the last task
                dayDensity = timeLength/759;                                    //#days per pixel
    }
}

