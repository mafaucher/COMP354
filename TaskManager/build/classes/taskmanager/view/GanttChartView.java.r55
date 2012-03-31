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


import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

/**
 *
 * @author Jonathan
 */
public class GanttChartView extends JPanel
{
    
    JFreeChart chart;
    
    public GanttChartView()
    {
        this.setLayout(new GridLayout(1, 1));
        TaskSeries seriesOne = new TaskSeries("Planned Implementation");
 
        //25 august 1986
        Date dat = new Date(2000, 1, 1);

        
         //12 september 1986
        Date dat2 = new Date(2001, 8, 1);
        
        
        seriesOne.add(new Task("asd", dat, dat2));
        
        
        final TaskSeriesCollection collection = new TaskSeriesCollection();
 
        /**
        * Adding the series to the collection
        * Holds actual Dates.
        */
        collection.add(seriesOne);
        
        chart = ChartFactory.createGanttChart(
            "hahaha title", // chart title
            "Task", // domain axis label
            "Date", // range axis label
            collection, // data
            true, // include legend
            true, // tooltips
            false // urls
            );
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
}
