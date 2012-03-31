/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package taskmanager.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.*;


import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;
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
    
    public GanttChartView(MainModel mm)
    {
        this.mm = mm;
        
        this.setLayout(new GridLayout(1, 1));
        TaskSeries seriesOne = new TaskSeries("Planned Implementation");
        TaskSeries listtwo = new TaskSeries("color test");
        
        loadList(mm.getTaskData());
        
        for(int i=0; i<rowData.length; i++) {
            Date sDate = calculateDate(rowData[i][1]);              //start date
            Date eDate = calculateDate(rowData[i][2]);              //end date
            seriesOne.add(new Task(rowData[i][0], sDate, eDate));
        }
        
        final TaskSeriesCollection collection = new TaskSeriesCollection();
 
        /**
        * Adding the series to the collection
        * Holds actual Dates.
        */
        collection.add(seriesOne);
        
        chart = ChartFactory.createGanttChart(
            "Gantt Chart of Tasks", // chart title
            "Task", // domain axis label
            "Date", // range axis label
            collection, // data
            true, // include legend
            true, // tooltips
            false // urls
            );
        
        /*final CategoryPlot plot = chart.getCategoryPlot();
        CategoryItemRenderer renderer = new CustomRenderer();
        plot.setRenderer(renderer);*/
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (chart != null)
        {
            Rectangle r = new Rectangle(this.getLocation().x, this.getLocation().y,
                    this.getWidth(), this.getHeight());
            
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
            rowData[i] = new String[3];
            
            rowData[i][0] = taskData.get(i).getTitle();
            rowData[i][1] = taskData.get(i).getStartDate();
            rowData[i][2] = taskData.get(i).getDeadline();
        }
    }
    
    public Date calculateDate(String xmlDate) {
        Date date;
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
}
