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
    Date sDate, eDate, aDate;                                            //start, end date, aDate is a date used with sDate to show the completion of the task (sDate <= aDate <= eDate
    private boolean addToChart;
    
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
            Rectangle r = new Rectangle(this.getLocation().x, this.getLocation().y,
                    this.getWidth(), this.getHeight());
            
            r.setSize(this.getWidth(), this.getHeight()-25);
            
            chart.draw((Graphics2D)g, new Rectangle(r));
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
        
        TaskSeries PlannedSchedule = new TaskSeries("Planned Implementation");
        TaskSeries Completion = new TaskSeries("Completion");
        
        for(int i=0; i<rowData.length; i++) {
            addToChart = true;
            sDate = calculateDate(rowData[i][1]);              //start date
            eDate = calculateDate(rowData[i][2]);              //end date
            if(addToChart == true) {
                PlannedSchedule.add(new Task(rowData[i][0], sDate, eDate));
                aDate = calculateCompleteness(sDate,eDate, rowData[i][3]);
                Completion.add(new Task(rowData[i][0], sDate, aDate));
            }
        }
        
        final TaskSeriesCollection collection = new TaskSeriesCollection();
 
        /**
        * Adding the series to the collection
        * Holds actual Dates.
        */
        collection.add(PlannedSchedule);
        collection.add(Completion);
        
        chart = ChartFactory.createGanttChart(
            "Gantt Chart of Tasks", // chart title
            "Task", // domain axis label
            "Date", // range axis label
            collection, // data
            true, // include legend
            true, // tooltips
            false // urls
            );
        
        final CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0,Color.green);
        renderer.setSeriesPaint(1,Color.magenta);
    }
    
    public Date calculateDate(String xmlDate) {
        Date date;
        if(xmlDate.compareTo("-") == 0) {               //if date is not set, try set current date
            addToChart = false;
            date = new Date();
            return date;
        }
        
        String sDay = xmlDate.substring(4,6),
                sMonth = xmlDate.substring(0,3),
                sYear = xmlDate.substring(10);
        int month = 0, day = 0, year;
        char d2;
        
        if (sMonth.compareTo("Jan") == 0){
            month = 0;
        } else if(sMonth.compareTo("Feb") == 0) {
            month = 1;
        } else if(sMonth.compareTo("Mar") == 0) {
            month = 2;
        } else if(sMonth.compareTo("Apr") == 0) {
            month = 3;
        } else if(sMonth.compareTo("May") == 0) {
            month = 4;
        } else if(sMonth.compareTo("Jun") == 0) {
            month = 5;
        } else if(sMonth.compareTo("Jul") == 0) {
            month = 6;
        } else if(sMonth.compareTo("Aug") == 0) {
            month = 7;
        } else if(sMonth.compareTo("Sep") == 0) {
            month = 8;
        } else if(sMonth.compareTo("Oct") == 0) {
            month = 9;
        } else if(sMonth.compareTo("Nov") == 0) {
            month = 10;
        } else if(sMonth.compareTo("Dec") == 0) {
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
    
    public Date calculateCompleteness(Date begin, Date end, String sPercentage)
    {
        Date date;
        Float percentage = new Float(sPercentage);
        percentage /= 100;
        int day, month, year,
                iDay, iMonth, iYear;                                            //index of day, month and year value in Calendar c.toString()
        String sDay, sMonth, sYear;
        Calendar c = Calendar.getInstance();
        long timeLength = (end.getTime() - begin.getTime())/(1000*60*60*24),    //milliseconds*seconds*minutes*hours
                accomplished;
        char d2;
        
        accomplished = Math.round(timeLength*percentage);
        c.setTime(begin);
        c.add(Calendar.DATE, (int) accomplished);  // number of days to add
        
        iDay = c.toString().indexOf("DAY_OF_MONTH=")+13;                        //find the first occurence of DAY_OF_MONTH and then add 13 to the index to move index past DAY_OF_MONTH
        sDay = c.toString().substring(iDay,iDay+2);
        d2 = sDay.charAt(1);
        if(d2 == ',') {
            sDay = sDay.substring(0,1);
        }
        day = Integer.parseInt(sDay);
        
        iMonth = c.toString().indexOf(",MONTH=")+7;
        sMonth = c.toString().substring(iMonth,iMonth+1);
        month = Integer.parseInt(sMonth);
        
        iYear = c.toString().indexOf(",YEAR=20")+8;
        sYear = c.toString().substring(iYear,iYear+2);
        year = Integer.parseInt(sYear)+100;
        
        date = new Date(year, month, day);
        return date;
    }
}

